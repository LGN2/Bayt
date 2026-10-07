package com.codevictims.billing.mapper;

import com.codevictims.billing.dto.response.DepositEntryResponse;
import com.codevictims.billing.entity.DepositEntry;

// Convert deposit entities into API responses.
public final class DepositEntryMapper {
  private DepositEntryMapper() {}

  public static DepositEntryResponse toResponse(DepositEntry entity) {
    if (entity == null) return null;
    return new DepositEntryResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.leaseId,
        entity.kind,
        entity.amount,
        entity.effectiveDate,
        entity.reason,
        entity.idempotencyKey,
        entity.reversesId);
  }
}
