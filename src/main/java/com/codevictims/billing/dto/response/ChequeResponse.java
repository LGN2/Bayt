package com.codevictims.billing.dto.response;

import java.math.BigDecimal;
import java.time.*;

// Return cheque information to the API client.
public record ChequeResponse(
    Long id,
    long version,
    Instant createdAt,
    Long leaseId,
    String chequeNumber,
    String bank,
    LocalDate chequeDate,
    BigDecimal amount,
    String status,
    Long paymentId) {}
