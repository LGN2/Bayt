package com.codevictims.bayt.tenancy.mapper;

import com.codevictims.bayt.tenancy.entity.Lead;
import com.codevictims.bayt.tenancy.response.LeadResponse;

public final class LeadMapper {

    private LeadMapper() {
    }

    public static LeadResponse toResponse(Lead entity) {

        if (entity == null) {
            return null;
        }

        return new LeadResponse(
                entity.getId(),
                entity.getBuildingId(),
                entity.getUnitId(),
                entity.getName(),
                entity.getPhone(),
                entity.getStatus(),
                entity.getViewingAt(),
                entity.getFollowUpDate(),
                entity.getNotes()
        );
    }
}