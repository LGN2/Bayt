package com.codevictims.billing.dto.response;

import java.math.BigDecimal;
import java.time.*;

// Return due information to the API client.
public record DueResponse(
    Long id,
    long version,
    Instant createdAt,
    Long leaseId,
    LocalDate dueDate,
    BigDecimal rentAmount,
    BigDecimal taxAmount,
    BigDecimal amount,
    boolean cancelled,
    LocalDate cancelledOn) {}
