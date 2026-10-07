package com.codevictims.billing.mapper;

import com.codevictims.billing.dto.response.AllocationResponse;
import com.codevictims.billing.entity.Allocation;

public final class AllocationMapper {
  private AllocationMapper() {}

  // Convert the allocation entity to a response.
  public static AllocationResponse toResponse(Allocation entity) {
    if (entity == null) return null;
    return new AllocationResponse(
        entity.id, entity.version, entity.createdAt, entity.paymentId, entity.dueId, entity.amount);
  }
}
