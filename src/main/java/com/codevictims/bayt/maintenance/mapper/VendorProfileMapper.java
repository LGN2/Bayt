package com.codevictims.bayt.maintenance.mapper;

import com.codevictims.bayt.maintenance.dto.response.VendorProfileResponse;
import com.codevictims.bayt.maintenance.entity.VendorProfile;

public final class VendorProfileMapper {
    private VendorProfileMapper() {}

    public static VendorProfileResponse toResponse(VendorProfile entity) {
        if (entity == null) return null;
        return new VendorProfileResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.buildingId,
                entity.userId,
                entity.name,
                entity.categories,
                entity.hourlyRate);
    }
}
