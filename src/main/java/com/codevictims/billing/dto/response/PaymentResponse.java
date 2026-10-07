package com.codevictims.billing.dto.response;

import java.math.BigDecimal;
import java.time.*;

// Return payment information to the client.
public record PaymentResponse(
    Long id,
    long version,
    Instant createdAt,
    Long leaseId,
    BigDecimal amount,
    String method,
    String reference,
    LocalDate effectiveDate,
    String idempotencyKey,
    LocalDate reversedOn,
    String reversalReason) {}
