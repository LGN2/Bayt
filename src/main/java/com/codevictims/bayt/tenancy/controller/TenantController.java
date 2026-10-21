package com.codevictims.bayt.tenancy.controller;

import com.codevictims.bayt.common.dto.PageSlice;
import com.codevictims.bayt.common.mapper.RequestMapper;
import com.codevictims.bayt.security.service.Access;
import com.codevictims.bayt.tenancy.dto.request.TenantRequest;
import com.codevictims.bayt.tenancy.dto.response.TenantResponse;
import com.codevictims.bayt.tenancy.mapper.TenantMapper;
import com.codevictims.bayt.tenancy.service.TenantService;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TenantController {
  private final TenantService s;
  private final Access access;

  public TenantController(TenantService s, Access access) {
    this.s = s;
    this.access = access;
  }

  @GetMapping("/tenants")
  public PageSlice<TenantResponse> tenants(
      @RequestParam(required = false) Long buildingId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "") String q) {
    return PageSlice.of(
            s.tenants(buildingId), page, size, q, t -> t.name + " " + t.phone + " " + t.kind)
        .map(TenantMapper::toResponse);
  }

  @PostMapping("/tenants")
  public TenantResponse tenant(@jakarta.validation.Valid @RequestBody TenantRequest b) {
    return TenantMapper.toResponse(s.tenant(RequestMapper.toInput(b), null));
  }

  @PutMapping("/tenants/{id}")
  public TenantResponse tenant(
      @PathVariable Long id, @jakarta.validation.Valid @RequestBody TenantRequest b) {
    return TenantMapper.toResponse(s.tenant(RequestMapper.toInput(b), id));
  }
}
