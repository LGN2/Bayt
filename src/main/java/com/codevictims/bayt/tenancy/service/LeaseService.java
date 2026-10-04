package com.codevictims.bayt.tenancy.service;

import com.codevictims.bayt.account.entity.UserAccount;
import com.codevictims.bayt.billing.entity.Due;
import com.codevictims.bayt.billing.entity.TaxPolicy;
import com.codevictims.bayt.billing.repository.AllocationRepository;
import com.codevictims.bayt.billing.repository.DueRepository;
import com.codevictims.bayt.common.audit.service.AuditService;
import com.codevictims.bayt.common.dto.Input;
import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.common.repository.PersistenceSupport;
import com.codevictims.bayt.property.entity.Building;
import com.codevictims.bayt.property.entity.Unit;
import com.codevictims.bayt.property.repository.UnitRepository;
import com.codevictims.bayt.property.service.PropertyService;
import com.codevictims.bayt.security.authorization.AccessService;
import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.entity.Tenant;
import com.codevictims.bayt.tenancy.repository.LeaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(
        isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED
)
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final DueRepository dueRepository;
    private final AllocationRepository allocationRepository;
    private final PersistenceSupport db;
    private final AccessService access;
    private final AuditService audit;
    private final UnitRepository units;
    private final Clock clock;
    private final PropertyService properties;

    public LeaseService(
            PersistenceSupport db,
            AccessService access,
            AuditService audit,
            UnitRepository units,
            Clock clock,
            PropertyService properties,
            LeaseRepository leaseRepository,
            DueRepository dueRepository,
            AllocationRepository allocationRepository) {

        this.leaseRepository = leaseRepository;
        this.dueRepository = dueRepository;
        this.allocationRepository = allocationRepository;
        this.db = db;
        this.access = access;
        this.audit = audit;
        this.units = units;
        this.clock = clock;
        this.properties = properties;
    }

    public List<Lease> leases(Long building) {

        access.role("OWNER", "MANAGER", "TENANT");

        var ids = properties.scope(building);

        if (ids.isEmpty()) {
            return List.of();
        }

        /*
         * Use the repository method currently available
         * in LeaseRepository.
         */
        return leaseRepository.findByBuildingIdInOrderByIdDesc(ids);
    }

    public Lease lease(Input in, Long previousId) {

        Long unitId = in.id("unitId");

        Unit before = db.get(Unit.class, unitId);

        access.manage(before.getBuildingId());

        Unit unit = units.lockById(unitId)
                .orElseThrow(ApiException::missing);

        if (!unit.getAvailability().equals("AVAILABLE")) {
            throw ApiException.conflict("UNIT_UNAVAILABLE");
        }

        Tenant tenant = db.get(
                Tenant.class,
                in.id("tenantId")
        );

        if (!tenant.getBuildingId().equals(unit.getBuildingId())) {
            throw ApiException.invalid("INVALID_TENANT");
        }

        LocalDate start = in.date("startDate");

        int months = in.integer(
                "months",
                1,
                60,
                12
        );

        LocalDate end = start
                .plusMonths(months)
                .minusDays(1);

        /*
         * Use the repository method that actually exists.
         */
        var existing =
                leaseRepository
                        .findByUnitIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                                unit.getId(),
                                end,
                                start
                        );

        for (var existingLease : existing) {

            LocalDate actualEnd =
                    existingLease.getTerminatedOn() == null
                            ? existingLease.getEndDate()
                            : existingLease.getTerminatedOn();

            if (!actualEnd.isBefore(start)) {
                throw ApiException.conflict("LEASE_OVERLAP");
            }
        }

        TaxPolicy policy = db.get(
                TaxPolicy.class,
                in.id("taxPolicyId")
        );

        if (!policy.getBuildingId().equals(unit.getBuildingId())
                || start.isBefore(policy.getEffectiveFrom())
                || (
                policy.getEffectiveTo() != null
                        && end.isAfter(policy.getEffectiveTo())
        )) {

            throw ApiException.invalid("INVALID_TAX_POLICY");
        }

        Building building = db.get(
                Building.class,
                unit.getBuildingId()
        );

        boolean registered = db
                .get(UserAccount.class, building.getOwnerId())
                .isTaxRegistered();

        if (policy.getTreatment().equals("STANDARD")
                && !registered) {

            throw ApiException.invalid("INVALID_TAX_POLICY");
        }

        Lease lease = new Lease();

        lease.setBuildingId(unit.getBuildingId());
        lease.setUnitId(unit.getId());
        lease.setTenantId(tenant.getId());

        lease.setStartDate(start);
        lease.setEndDate(end);

        lease.setRent(
                in.money("rent", true)
        );

        lease.setDeposit(
                in.money("deposit", false)
        );

        lease.setStatus("ACTIVE");

        lease.setTaxTreatment(
                policy.getTreatment()
        );

        lease.setTaxRate(
                policy.getRate()
        );

        lease.setSupplyClassification(
                policy.getSupplyClassification()
        );

        lease.setOwnerTaxRegistered(
                registered
        );

        lease.setMunicipalityStatus(
                "UNREGISTERED"
        );

        if (previousId != null) {

            Lease previous = access.lease(
                    previousId,
                    true
            );

            LocalDate previousEnd =
                    previous.getTerminatedOn() == null
                            ? previous.getEndDate()
                            : previous.getTerminatedOn();

            if (!previous.getUnitId().equals(unit.getId())
                    || !previous.getTenantId().equals(tenant.getId())
                    || !start.isAfter(previousEnd)) {

                throw ApiException.invalid(
                        "INVALID_RENEWAL"
                );
            }

            lease.setPreviousLeaseId(
                    previous.getId()
            );
        }

        db.save(lease);

        for (int i = 0; i < months; i++) {

            Due due = new Due();

            due.setLeaseId(
                    lease.getId()
            );

            due.setDueDate(
                    start.plusMonths(i)
            );

            due.setRentAmount(
                    lease.getRent()
            );

            due.setTaxAmount(
                    lease.getRent()
                            .multiply(lease.getTaxRate())
                            .setScale(
                                    3,
                                    RoundingMode.HALF_UP
                            )
            );

            due.setAmount(
                    due.getRentAmount()
                            .add(due.getTaxAmount())
            );

            db.save(due);
        }

        audit.add(
                lease.getBuildingId(),
                "LEASE",
                lease.getId(),
                previousId == null
                        ? "CREATED"
                        : "RENEWED",
                "Fixed monthly rent; full monthly periods"
        );

        return lease;
    }

    public Lease municipality(
            Long id,
            Input in
    ) {

        Lease lease = access.lease(
                id,
                true
        );

        lease.setMunicipalityStatus(
                in.choice(
                        "municipalityStatus",
                        "UNREGISTERED",
                        "SUBMITTED",
                        "REGISTERED"
                )
        );

        lease.setMunicipalityAuthority(
                in.optional("municipalityAuthority")
        );

        lease.setMunicipalityReference(
                in.optional("municipalityReference")
        );

        lease.setMunicipalityFee(
                in.money(
                        "municipalityFee",
                        false
                )
        );

        if (lease.getMunicipalityStatus().equals("REGISTERED")
                && (
                lease.getMunicipalityReference().isBlank()
                        || lease.getMunicipalityAuthority().isBlank()
        )) {

            throw ApiException.invalid(
                    "INVALID_INPUT"
            );
        }

        audit.add(
                lease.getBuildingId(),
                "LEASE",
                id,
                "MUNICIPALITY_UPDATED",
                lease.getMunicipalityStatus()
        );

        return lease;
    }

    public Lease terminate(
            Long id,
            Input in
    ) {

        Lease initial = access.lease(
                id,
                true
        );

        db.lock(
                Unit.class,
                initial.getUnitId()
        );

        Lease lease = db.lock(
                Lease.class,
                id
        );

        LocalDate when = in.date(
                "terminatedOn"
        );

        if (!lease.getStatus().equals("ACTIVE")
                || when.isBefore(lease.getStartDate())
                || when.isAfter(lease.getEndDate())
                || when.isAfter(LocalDate.now(clock))) {

            throw ApiException.invalid(
                    "INVALID_DATE"
            );
        }

        var future = dueRepository.findFutureDues(
                id,
                when
        );

        for (var due : future) {

            var paid =
                    allocationRepository.findUnreversedForDue(
                            due.getId()
                    );

            if (!paid.isEmpty()) {

                throw ApiException.conflict(
                        "REVERSE_FUTURE_PAYMENTS_FIRST"
                );
            }

            due.setCancelled(true);
            due.setCancelledOn(when);
        }

        lease.setStatus("TERMINATED");

        lease.setTerminatedOn(
                when
        );

        Unit unit = db.get(
                Unit.class,
                lease.getUnitId()
        );

        unit.setVacancySince(
                when.plusDays(1)
        );

        audit.add(
                lease.getBuildingId(),
                "LEASE",
                id,
                "TERMINATED",
                in.text("reason", 255)
        );

        return lease;
    }
}