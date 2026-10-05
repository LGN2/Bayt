package com.codevictims.bayt.tenancy.service;

import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.repository.LeaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeaseService {

    private final LeaseRepository leaseRepository;

    public LeaseService(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    public List<Lease> getAll() {
        return leaseRepository.findAll();
    }

    public Lease getById(Long id) {
        return leaseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lease not found with id: " + id
                        )
                );
    }

    public Lease create(Lease lease) {
        return leaseRepository.save(lease);
    }

    public Lease update(Long id, Lease updatedLease) {

        Lease existing = getById(id);

        existing.setBuildingId(updatedLease.getBuildingId());
        existing.setUnitId(updatedLease.getUnitId());
        existing.setTenantId(updatedLease.getTenantId());
        existing.setStartDate(updatedLease.getStartDate());
        existing.setEndDate(updatedLease.getEndDate());
        existing.setRent(updatedLease.getRent());
        existing.setDeposit(updatedLease.getDeposit());
        existing.setStatus(updatedLease.getStatus());
        existing.setTaxTreatment(updatedLease.getTaxTreatment());
        existing.setTaxRate(updatedLease.getTaxRate());
        existing.setSupplyClassification(
                updatedLease.getSupplyClassification()
        );
        existing.setOwnerTaxRegistered(
                updatedLease.isOwnerTaxRegistered()
        );
        existing.setMunicipalityStatus(
                updatedLease.getMunicipalityStatus()
        );
        existing.setMunicipalityAuthority(
                updatedLease.getMunicipalityAuthority()
        );
        existing.setMunicipalityReference(
                updatedLease.getMunicipalityReference()
        );
        existing.setMunicipalityFee(
                updatedLease.getMunicipalityFee()
        );
        existing.setPreviousLeaseId(
                updatedLease.getPreviousLeaseId()
        );
        existing.setTerminatedOn(
                updatedLease.getTerminatedOn()
        );

        return leaseRepository.save(existing);
    }

    public void delete(Long id) {
        Lease lease = getById(id);
        leaseRepository.delete(lease);
    }
}