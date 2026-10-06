package com.codevictims.bayt.account.dto.response;

import java.time.*;

public record BuildingAccessResponse(
        Long id, long version, Instant createdAt, Long buildingId, Long userId, boolean canWrite) {}