package com.codevictims.bayt.tenancy.controller;

import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.mapper.LeaseMapper;
import com.codevictims.bayt.tenancy.dto.request.LeaseRequest;
import com.codevictims.bayt.tenancy.dto.response.LeaseResponse;
import com.codevictims.bayt.tenancy.service.LeaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenancy/leases")
public class LeaseController {

    private final LeaseService service;

    public LeaseController(LeaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<LeaseResponse> list() {
        return service.getAll()
                .stream()
                .map(LeaseMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public LeaseResponse get(@PathVariable Long id) {
        return LeaseMapper.toResponse(
                service.getById(id)
        );
    }

    @PostMapping
    public LeaseResponse create(
            @Valid @RequestBody LeaseRequest request) {

        Lease lease = new Lease();

        lease.setBuildingId(request.buildingId());
        lease.setUnitId(request.unitId());
        lease.setTenantId(request.tenantId());

        lease.setStartDate(request.startDate());
        lease.setEndDate(
                request.startDate()
                        .plusMonths(request.months())
                        .minusDays(1)
        );

        lease.setRent(request.rent());
        lease.setDeposit(
                request.deposit() != null
                        ? request.deposit()
                        : java.math.BigDecimal.ZERO
        );

        lease.setStatus("ACTIVE");

        lease.setTaxTreatment(request.taxTreatment());
        lease.setTaxRate(request.taxRate());
        lease.setSupplyClassification(
                request.supplyClassification()
        );

        lease.setOwnerTaxRegistered(
                Boolean.TRUE.equals(request.ownerTaxRegistered())
        );

        lease.setMunicipalityStatus(
                request.municipalityStatus() != null
                        ? request.municipalityStatus()
                        : "UNREGISTERED"
        );

        lease.setMunicipalityAuthority(
                request.municipalityAuthority() != null
                        ? request.municipalityAuthority()
                        : ""
        );

        lease.setMunicipalityReference(
                request.municipalityReference() != null
                        ? request.municipalityReference()
                        : ""
        );

        lease.setMunicipalityFee(
                request.municipalityFee() != null
                        ? request.municipalityFee()
                        : java.math.BigDecimal.ZERO
        );

        lease.setPreviousLeaseId(
                request.previousLeaseId()
        );

        return LeaseMapper.toResponse(
                service.create(lease)
        );
    }

    @PutMapping("/{id}")
    public LeaseResponse update(
            @PathVariable Long id,
            @Valid @RequestBody LeaseRequest request) {

        Lease lease = new Lease();

        lease.setBuildingId(request.buildingId());
        lease.setUnitId(request.unitId());
        lease.setTenantId(request.tenantId());

        lease.setStartDate(request.startDate());
        lease.setEndDate(
                request.startDate()
                        .plusMonths(request.months())
                        .minusDays(1)
        );

        lease.setRent(request.rent());
        lease.setDeposit(
                request.deposit() != null
                        ? request.deposit()
                        : java.math.BigDecimal.ZERO
        );

        lease.setStatus("ACTIVE");

        lease.setTaxTreatment(request.taxTreatment());
        lease.setTaxRate(request.taxRate());
        lease.setSupplyClassification(
                request.supplyClassification()
        );

        lease.setOwnerTaxRegistered(
                Boolean.TRUE.equals(request.ownerTaxRegistered())
        );

        lease.setMunicipalityStatus(
                request.municipalityStatus() != null
                        ? request.municipalityStatus()
                        : "UNREGISTERED"
        );

        lease.setMunicipalityAuthority(
                request.municipalityAuthority() != null
                        ? request.municipalityAuthority()
                        : ""
        );

        lease.setMunicipalityReference(
                request.municipalityReference() != null
                        ? request.municipalityReference()
                        : ""
        );

        lease.setMunicipalityFee(
                request.municipalityFee() != null
                        ? request.municipalityFee()
                        : java.math.BigDecimal.ZERO
        );

        lease.setPreviousLeaseId(
                request.previousLeaseId()
        );

        return LeaseMapper.toResponse(
                service.update(id, lease)
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}