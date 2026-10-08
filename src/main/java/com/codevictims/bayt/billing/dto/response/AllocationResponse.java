package com.codevictims.bayt.billing.dto.response;

import java.math.BigDecimal;
import java.time.*;

// Return allocation information to the client.
public record AllocationResponse(
    Long id, long version, Instant createdAt, Long paymentId, Long dueId, BigDecimal amount) {}
