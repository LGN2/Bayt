package com.codevictims.bayt.billing.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DueBalanceResponse(
    Long id,
    Long leaseId,
    LocalDate dueDate,
    BigDecimal rentAmount,
    BigDecimal taxAmount,
    BigDecimal amount,
    BigDecimal paid,
    BigDecimal outstanding,
    boolean cancelled,
    long daysLate) {}
