package com.codevictims.bayt.property.mapper;

import com.codevictims.bayt.property.dto.response.ParkingResponse;
import com.codevictims.bayt.property.entity.Parking;

public final class ParkingMapper {
    private ParkingMapper() {}

    public static ParkingResponse toResponse(Parking entity) {
        if (entity == null) return null;
        return new ParkingResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.buildingId,
                entity.unitId,
                entity.space,
                entity.vehicle);
    }
}
