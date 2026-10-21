package com.codevictims.bayt.billing.mapper;

import com.codevictims.bayt.billing.dto.response.FollowUpResponse;
import com.codevictims.bayt.billing.entity.FollowUp;

public final class FollowUpMapper {
  private FollowUpMapper() {}

  public static FollowUpResponse toResponse(FollowUp entity) {
    if (entity == null) return null;
    return new FollowUpResponse(
        entity.id, entity.version, entity.createdAt, entity.leaseId, entity.note, entity.nextDate);
  }
}
