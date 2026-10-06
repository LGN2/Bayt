package com.codevictims.bayt.account.dto.response;

import java.time.*;

public record UserAccountResponse(
        Long id,
        long version,
        Instant createdAt,
        String username,
        String displayName,
        String role,
        Long ownerId,
        boolean active,
        boolean taxRegistered) {}