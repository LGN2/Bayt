package com.codevictims.billing.mapper;

import com.codevictims.billing.dto.response.DueResponse;
import com.codevictims.billing.entity.Due;

// Convert due entities into API responses.
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
