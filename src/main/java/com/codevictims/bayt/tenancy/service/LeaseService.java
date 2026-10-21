package com.codevictims.bayt.tenancy.service;

import com.codevictims.bayt.account.entity.UserAccount;
import com.codevictims.bayt.billing.entity.Due;
import com.codevictims.bayt.billing.entity.TaxPolicy;
import com.codevictims.bayt.billing.repository.AllocationRepository;
import com.codevictims.bayt.billing.repository.DueRepository;
import com.codevictims.bayt.common.dto.Input;
import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.common.repository.PersistenceSupport;
import com.codevictims.bayt.common.service.AuditService;
import com.codevictims.bayt.property.entity.Building;
import com.codevictims.bayt.property.entity.Unit;
import com.codevictims.bayt.property.repository.UnitRepository;
import com.codevictims.bayt.property.service.PropertyService;
import com.codevictims.bayt.security.service.Access;
import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.entity.Tenant;
import com.codevictims.bayt.tenancy.repository.LeaseRepository;
import com.codevictims.bayt.tenancy.repository.TenantRepository;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED)
public class LeaseService {
  private final TenantRepository tenantRepository;
  private final LeaseRepository leaseRepository;
  private final DueRepository dueRepository;
  private final AllocationRepository allocationRepository;
  private final PersistenceSupport db;
  private final Access access;
  private final AuditService audit;
  private final UnitRepository units;
  private final Clock clock;
  private final PropertyService properties;

  public LeaseService(
      PersistenceSupport db,
      Access access,
      AuditService audit,
      UnitRepository units,
      Clock clock,
      PropertyService properties,
      TenantRepository tenantRepository,
      LeaseRepository leaseRepository,
      DueRepository dueRepository,
      AllocationRepository allocationRepository) {
    this.tenantRepository = tenantRepository;
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
    if (ids.isEmpty()) return List.of();
    if (access.user().role.equals("TENANT"))
      return leaseRepository.findForTenantAccountInBuildings(access.user().id, ids);
    return leaseRepository.findInBuildings(ids);
  }

  public Lease lease(Input in, Long previousId) {
    Long unitId = in.id("unitId");
    Unit before = db.get(Unit.class, unitId);
    access.manage(before.buildingId);
    Unit u = units.lockById(unitId).orElseThrow(ApiException::missing);
    if (!u.availability.equals("AVAILABLE")) throw ApiException.conflict("UNIT_UNAVAILABLE");
    Tenant t = db.get(Tenant.class, in.id("tenantId"));
    if (!t.buildingId.equals(u.buildingId)) throw ApiException.invalid("INVALID_TENANT");
    LocalDate start = in.date("startDate");
    int months = in.integer("months", 1, 60, 12);
    LocalDate end = start.plusMonths(months).minusDays(1);
    var existing = leaseRepository.findOverlappingTerms(u.id, start, end);
    for (var l : existing) {
      LocalDate actualEnd = l.terminatedOn == null ? l.endDate : l.terminatedOn;
      if (!actualEnd.isBefore(start)) throw ApiException.conflict("LEASE_OVERLAP");
    }
    TaxPolicy policy = db.get(TaxPolicy.class, in.id("taxPolicyId"));
    if (!policy.buildingId.equals(u.buildingId)
        || start.isBefore(policy.effectiveFrom)
        || (policy.effectiveTo != null && end.isAfter(policy.effectiveTo)))
      throw ApiException.invalid("INVALID_TAX_POLICY");
    Building b = db.get(Building.class, u.buildingId);
    boolean registered = db.get(UserAccount.class, b.ownerId).taxRegistered;
    if (policy.treatment.equals("STANDARD") && !registered)
      throw ApiException.invalid("INVALID_TAX_POLICY");
    Lease l = new Lease();
    l.buildingId = u.buildingId;
    l.unitId = u.id;
    l.tenantId = t.id;
    l.startDate = start;
    l.endDate = end;
    l.rent = in.money("rent", true);
    l.deposit = in.money("deposit", false);
    l.status = "ACTIVE";
    l.taxTreatment = policy.treatment;
    l.taxRate = policy.rate;
    l.supplyClassification = policy.supplyClassification;
    l.ownerTaxRegistered = registered;
    l.municipalityStatus = "UNREGISTERED";
    if (previousId != null) {
      var previous = access.lease(previousId, true);
      if (!previous.unitId.equals(u.id)
          || !previous.tenantId.equals(t.id)
          || !start.isAfter(
              previous.terminatedOn == null ? previous.endDate : previous.terminatedOn))
        throw ApiException.invalid("INVALID_RENEWAL");
      l.previousLeaseId = previous.id;
    }
    db.save(l);
    for (int i = 0; i < months; i++) {
      Due d = new Due();
      d.leaseId = l.id;
      d.dueDate = start.plusMonths(i);
      d.rentAmount = l.rent;
      d.taxAmount = l.rent.multiply(l.taxRate).setScale(3, RoundingMode.HALF_UP);
      d.amount = d.rentAmount.add(d.taxAmount);
      db.save(d);
    }
    audit.add(
        l.buildingId,
        "LEASE",
        l.id,
        previousId == null ? "CREATED" : "RENEWED",
        "Fixed monthly rent; full monthly periods");
    return l;
  }

  /** Issued financial terms are immutable; use renewal for new terms. */
  public Lease update(Long id, Input in) {
    Lease original=access.lease(id,true);
    db.lock(Unit.class,original.unitId);
    Lease l=db.lock(Lease.class,id);
    if (!l.unitId.equals(in.id("unitId")) || !l.tenantId.equals(in.id("tenantId"))
        || !l.startDate.equals(in.date("startDate"))
        || !l.endDate.equals(in.date("startDate").plusMonths(in.integer("months",1,60,12)).minusDays(1))
        || l.rent.compareTo(in.money("rent",true))!=0 || l.deposit.compareTo(in.money("deposit",false))!=0)
      throw ApiException.conflict("ISSUED_TERMS_IMMUTABLE_USE_RENEWAL");
    TaxPolicy policy=db.get(TaxPolicy.class,in.id("taxPolicyId"));
    if (!policy.buildingId.equals(l.buildingId) || !policy.treatment.equals(l.taxTreatment)
        || policy.rate.compareTo(l.taxRate)!=0) throw ApiException.conflict("ISSUED_TAX_IMMUTABLE");
    audit.add(l.buildingId,"LEASE",l.id,"REVIEWED","");
    return l;
  }

  public void delete(Long id) {
    access.lease(id,true);
    throw ApiException.conflict("LEASE_HAS_FINANCIAL_HISTORY_USE_TERMINATION");
  }

  public Lease municipality(Long id, Input in) {
    Lease l = access.lease(id, true);
    l.municipalityStatus =
        in.choice("municipalityStatus", "UNREGISTERED", "SUBMITTED", "REGISTERED");
    l.municipalityAuthority = in.optional("municipalityAuthority");
    l.municipalityReference = in.optional("municipalityReference");
    l.municipalityFee = in.money("municipalityFee", false);
    if (l.municipalityStatus.equals("REGISTERED")
        && (l.municipalityReference.isBlank() || l.municipalityAuthority.isBlank()))
      throw ApiException.invalid("INVALID_INPUT");
    audit.add(l.buildingId, "LEASE", id, "MUNICIPALITY_UPDATED", l.municipalityStatus);
    return l;
  }

  public Lease terminate(Long id, Input in) {
    Lease initial = access.lease(id, true);
    db.lock(Unit.class, initial.unitId);
    Lease l = db.lock(Lease.class, id);
    LocalDate when = in.date("terminatedOn");
    if (!l.status.equals("ACTIVE")
        || when.isBefore(l.startDate)
        || when.isAfter(l.endDate)
        || when.isAfter(LocalDate.now(clock))) throw ApiException.invalid("INVALID_DATE");
    var future = dueRepository.findFutureDues(id, when);
    for (var due : future) {
      var paid = allocationRepository.findUnreversedForDue(due.id);
      if (!paid.isEmpty()) throw ApiException.conflict("REVERSE_FUTURE_PAYMENTS_FIRST");
      due.cancelled = true;
      due.cancelledOn = when;
    }
    l.status = "TERMINATED";
    l.terminatedOn = when;
    db.get(Unit.class, l.unitId).vacancySince = when.plusDays(1);
    audit.add(l.buildingId, "LEASE", id, "TERMINATED", in.text("reason", 255));
    return l;
  }
}
