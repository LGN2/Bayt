package com.codevictims.bayt.property.mapper;

import com.codevictims.bayt.property.dto.response.BuildingResponse;
import com.codevictims.bayt.property.entity.Building;

public final class BuildingMapper {
    private BuildingMapper() {}

    public static BuildingResponse toResponse(Building entity) {
        if (entity == null) return null;
        return new BuildingResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.ownerId,
                entity.name,
                entity.wilayat,
                entity.address,
                entity.investmentValue,
                entity.reminderDays,
                entity.seasonalStart,
                entity.seasonalEnd);
    }
}
