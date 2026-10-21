package com.codevictims.bayt.tenancy.dto.response;

import java.time.*;

public record TenantResponse(
    Long id,
    long version,
    Instant createdAt,
    Long buildingId,
    Long accountId,
    String name,
    String kind,
    String email,
    String phone,
    String emergencyContact) {}
