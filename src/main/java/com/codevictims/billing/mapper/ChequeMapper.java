package com.codevictims.billing.mapper;

import com.codevictims.billing.dto.response.ChequeResponse;
import com.codevictims.billing.entity.Cheque;

// Convert cheque entities for API responses.
public final class ChequeMapper {
  private ChequeMapper() {}

  public static ChequeResponse toResponse(Cheque entity) {
    if (entity == null) return null;
    return new ChequeResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.leaseId,
        entity.chequeNumber,
        entity.bank,
        entity.chequeDate,
        entity.amount,
        entity.status,
        entity.paymentId);
  }
}
