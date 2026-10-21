package com.codevictims.bayt.billing.controller;

import com.codevictims.bayt.billing.dto.request.TaxPolicyRequest;
import com.codevictims.bayt.billing.dto.response.TaxPolicyResponse;
import com.codevictims.bayt.billing.mapper.TaxPolicyMapper;
import com.codevictims.bayt.billing.service.TaxPolicyService;
import com.codevictims.bayt.common.mapper.RequestMapper;
import com.codevictims.bayt.security.service.Access;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaxPolicyController {
  private final TaxPolicyService s;
  private final Access access;

  public TaxPolicyController(TaxPolicyService s, Access access) {
    this.s = s;
    this.access = access;
  }

  @GetMapping("/tax-policies")
  public List<TaxPolicyResponse> taxes(@RequestParam(required = false) Long buildingId) {
    return s.taxes(buildingId).stream().map(TaxPolicyMapper::toResponse).toList();
  }

  @PostMapping("/tax-policies")
  public TaxPolicyResponse tax(@jakarta.validation.Valid @RequestBody TaxPolicyRequest b) {
    return TaxPolicyMapper.toResponse(s.tax(RequestMapper.toInput(b)));
  }
}
