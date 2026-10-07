package com.codevictims.bayt.common.mapper;

import com.codevictims.bayt.common.dto.response.DocumentResponse;
import com.codevictims.bayt.common.entity.Document;

public final class DocumentMapper {
    private DocumentMapper() {}

    public static DocumentResponse toResponse(Document entity) {
        if (entity == null) return null;
        return new DocumentResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.buildingId,
                entity.unitId,
                entity.tenantId,
                entity.maintenanceId,
                entity.readingId,
                entity.kind,
                entity.filename,
                entity.contentType,
                entity.sizeBytes,
                entity.scanStatus,
                entity.expiryDate);
    }
}
