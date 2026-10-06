package com.codevictims.bayt.account.mapper;

import com.codevictims.bayt.account.dto.response.BuildingAccessResponse;
import com.codevictims.bayt.account.entity.BuildingAccess;

public final class BuildingAccessMapper {
    private BuildingAccessMapper() {}

    public static BuildingAccessResponse toResponse(BuildingAccess entity) {
        if (entity == null) return null;
        return new BuildingAccessResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.buildingId,
                entity.userId,
                entity.canWrite);
    }
}