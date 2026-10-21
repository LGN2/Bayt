package com.codevictims.bayt.billing.mapper;

import com.codevictims.bayt.billing.dto.response.DueResponse;
import com.codevictims.bayt.billing.entity.Due;

public final class DueMapper {
  private DueMapper() {}

  public static DueResponse toResponse(Due entity) {
    if (entity == null) return null;
    return new DueResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.leaseId,
        entity.dueDate,
        entity.rentAmount,
        entity.taxAmount,
        entity.amount,
        entity.cancelled,
        entity.cancelledOn);
  }
}
