package com.codevictims.bayt.property.mapper;

import com.codevictims.bayt.property.dto.response.NoticeResponse;
import com.codevictims.bayt.property.entity.Notice;

public final class NoticeMapper {
    private NoticeMapper() {}

    public static NoticeResponse toResponse(Notice entity) {
        if (entity == null) return null;
        return new NoticeResponse(
                entity.id,
                entity.version,
                entity.createdAt,
                entity.buildingId,
                entity.titleAr,
                entity.titleEn,
                entity.bodyAr,
                entity.bodyEn);
    }
}
