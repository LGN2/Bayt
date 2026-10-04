package com.codevictims.bayt.common.dto.response;

import java.time.*;

public record AuditEventResponse(
        Long id,
        long version,
        Instant createdAt,
        Long buildingId,
        String resourceType,
        Long resourceId,
        Long actorId,
        String action,
        String note) {}
