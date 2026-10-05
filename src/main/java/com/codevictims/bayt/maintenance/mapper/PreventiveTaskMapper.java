package com.codevictims.bayt.maintenance.mapper;

import com.codevictims.bayt.maintenance.dto.response.PreventiveTaskResponse;
import com.codevictims.bayt.maintenance.entity.PreventiveTask;

public final class PreventiveTaskMapper {
    private PreventiveTaskMapper() {}

    public static PreventiveTaskResponse toResponse(PreventiveTask entity) {
        if (entity == null) return null;
        return new PreventiveTaskResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.seasonalPriority,
                entity.buildingId,
                entity.unitId,
                entity.title,
                entity.category,
                entity.intervalDays,
                entity.nextDue,
                entity.lastCompleted,
                entity.enabled);
    }
}
