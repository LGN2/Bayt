package com.codevictims.bayt.billing.mapper;

import com.codevictims.bayt.billing.dto.response.ChequeResponse;
import com.codevictims.bayt.billing.entity.Cheque;

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
