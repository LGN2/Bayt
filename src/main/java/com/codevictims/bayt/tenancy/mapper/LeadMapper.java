package com.codevictims.bayt.tenancy.mapper;

import com.codevictims.bayt.tenancy.dto.response.LeadResponse;
import com.codevictims.bayt.tenancy.entity.Lead;

public final class LeadMapper {
  private LeadMapper() {}

  public static LeadResponse toResponse(Lead entity) {
    if (entity == null) return null;
    return new LeadResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.buildingId,
        entity.unitId,
        entity.name,
        entity.phone,
        entity.status,
        entity.viewingAt,
        entity.followUpDate,
        entity.notes);
  }
}
