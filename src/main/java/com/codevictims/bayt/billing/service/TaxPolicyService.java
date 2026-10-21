package com.codevictims.bayt.billing.service;

import com.codevictims.bayt.billing.entity.TaxPolicy;
import com.codevictims.bayt.billing.repository.TaxPolicyRepository;
import com.codevictims.bayt.common.dto.Input;
import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.common.repository.PersistenceSupport;
import com.codevictims.bayt.common.service.AuditService;
import com.codevictims.bayt.property.entity.Building;
import com.codevictims.bayt.property.repository.UnitRepository;
import com.codevictims.bayt.property.service.PropertyService;
import com.codevictims.bayt.security.service.Access;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED)
public class TaxPolicyService {
  private final TaxPolicyRepository taxPolicyRepository;
  private final PersistenceSupport db;
  private final Access access;
  private final AuditService audit;
  private final UnitRepository units;
  private final Clock clock;
  private final PropertyService properties;

  public TaxPolicyService(
      PersistenceSupport db,
      Access access,
      AuditService audit,
      UnitRepository units,
      Clock clock,
      PropertyService properties,
      TaxPolicyRepository taxPolicyRepository) {
    this.taxPolicyRepository = taxPolicyRepository;
    this.db = db;
    this.access = access;
    this.audit = audit;
    this.units = units;
    this.clock = clock;
    this.properties = properties;
  }

  public List<TaxPolicy> taxes(Long building) {
    access.role("OWNER", "MANAGER");
    var ids = properties.scope(building);
    return ids.isEmpty() ? List.of() : taxPolicyRepository.findInBuildingsByEffectiveDate(ids);
  }

  public TaxPolicy tax(Input in) {
    Long bid = in.id("buildingId");
    access.owner(bid);
    db.lock(Building.class, bid);
    TaxPolicy p = new TaxPolicy();
    p.buildingId = bid;
    p.treatment = in.choice("treatment", "STANDARD", "ZERO_RATED", "EXEMPT", "OUT_OF_SCOPE");
    p.supplyClassification = in.text("supplyClassification", 255);
    p.rate = in.rate("rate");
    p.effectiveFrom = in.date("effectiveFrom");
    p.effectiveTo = in.optionalDate("effectiveTo");
    if (p.effectiveTo != null && p.effectiveTo.isBefore(p.effectiveFrom))
      throw ApiException.invalid("INVALID_DATE");
    if (!p.treatment.equals("STANDARD") && p.rate.signum() != 0)
      throw ApiException.invalid("INVALID_TAX_POLICY");
    if (p.treatment.equals("STANDARD") && (!access.user().taxRegistered || p.rate.signum() == 0))
      throw ApiException.invalid("INVALID_TAX_POLICY");
    for (var old : taxes(bid))
      if (old.supplyClassification.equals(p.supplyClassification)
          && !p.effectiveFrom.isAfter(old.effectiveTo == null ? LocalDate.MAX : old.effectiveTo)
          && !(p.effectiveTo == null ? LocalDate.MAX : p.effectiveTo).isBefore(old.effectiveFrom))
        throw ApiException.conflict("TAX_POLICY_OVERLAP");
    db.save(p);
    audit.add(bid, "TAX_POLICY", p.id, "CREATED", p.treatment);
    return p;
  }
}
