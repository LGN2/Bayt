package com.codevictims.bayt.tenancy.mapper;

import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.response.LeaseResponse;

public final class LeaseMapper {

    private LeaseMapper() {
    }

    public static LeaseResponse toResponse(Lease entity) {

        if (entity == null) {
            return null;
        }

        return new LeaseResponse(
                entity.getId(),
                entity.getBuildingId(),
                entity.getUnitId(),
                entity.getTenantId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getRent(),
                entity.getDeposit(),
                entity.getStatus(),
                entity.getTaxTreatment(),
                entity.getTaxRate(),
                entity.getSupplyClassification(),
                entity.isOwnerTaxRegistered(),
                entity.getMunicipalityStatus(),
                entity.getMunicipalityAuthority(),
                entity.getMunicipalityReference(),
                entity.getMunicipalityFee(),
                entity.getPreviousLeaseId(),
                entity.getTerminatedOn()
        );
    }
}