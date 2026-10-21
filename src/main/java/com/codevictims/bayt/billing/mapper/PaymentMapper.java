package com.codevictims.bayt.billing.mapper;

import com.codevictims.bayt.billing.dto.response.PaymentResponse;
import com.codevictims.bayt.billing.entity.Payment;

public final class PaymentMapper {
  private PaymentMapper() {}

  public static PaymentResponse toResponse(Payment entity) {
    if (entity == null) return null;
    return new PaymentResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.leaseId,
        entity.amount,
        entity.method,
        entity.reference,
        entity.effectiveDate,
        entity.idempotencyKey,
        entity.reversedOn,
        entity.reversalReason);
  }
}
