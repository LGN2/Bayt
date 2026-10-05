package com.codevictims.bayt.tenancy.mapper;

import com.codevictims.bayt.tenancy.entity.Tenant;
import com.codevictims.bayt.tenancy.response.TenantResponse;

public final class TenantMapper {

    private TenantMapper() {
    }

    public static TenantResponse toResponse(Tenant entity) {

        if (entity == null) {
            return null;
        }

        return new TenantResponse(
                entity.getId(),
                entity.getBuildingId(),
                entity.getAccountId(),
                entity.getName(),
                entity.getKind(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getEmergencyContact()
        );
    }
}