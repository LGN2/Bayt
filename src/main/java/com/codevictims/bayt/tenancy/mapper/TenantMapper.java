package com.codevictims.bayt.tenancy.mapper;

import com.codevictims.bayt.tenancy.dto.response.TenantResponse;
import com.codevictims.bayt.tenancy.entity.Tenant;

public final class TenantMapper {
  private TenantMapper() {}

  public static TenantResponse toResponse(Tenant entity) {
    if (entity == null) return null;
    return new TenantResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.buildingId,
        entity.accountId,
        entity.name,
        entity.kind,
        entity.email,
        entity.phone,
        entity.emergencyContact);
  }
}
