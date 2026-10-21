package com.codevictims.bayt.billing.mapper;

import com.codevictims.bayt.billing.dto.response.TaxPolicyResponse;
import com.codevictims.bayt.billing.entity.TaxPolicy;

public final class TaxPolicyMapper {
  private TaxPolicyMapper() {}

  public static TaxPolicyResponse toResponse(TaxPolicy entity) {
    if (entity == null) return null;
    return new TaxPolicyResponse(
        entity.id,
        entity.version,
        entity.createdAt,
        entity.buildingId,
        entity.treatment,
        entity.supplyClassification,
        entity.rate,
        entity.effectiveFrom,
        entity.effectiveTo);
  }
}
