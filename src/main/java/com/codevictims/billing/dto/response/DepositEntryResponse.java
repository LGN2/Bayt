package com.codevictims.billing.dto.response;

import java.math.BigDecimal;
import java.time.*;

// Return a single deposit entry to the API client.
public record DepositEntryResponse(
    Long id,
    long version,
    Instant createdAt,
    Long leaseId,
    String kind,
    BigDecimal amount,
    LocalDate effectiveDate,
    String reason,
    String idempotencyKey,
    Long reversesId) {}
