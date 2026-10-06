package com.codevictims.bayt.property.mapper;

import com.codevictims.bayt.property.dto.response.UnitResponse;
import com.codevictims.bayt.property.entity.Unit;

public final class UnitMapper {
    private UnitMapper() {}

    public static UnitResponse toResponse(Unit entity) {
        if (entity == null) return null;
        return new UnitResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.buildingId,
                entity.code,
                entity.floorName,
                entity.size,
                entity.kind,
                entity.availability,
                entity.marketRent,
                entity.vacancySince,
                entity.listing);
    }
}
