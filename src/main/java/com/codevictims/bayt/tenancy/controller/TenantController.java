package com.codevictims.bayt.tenancy.controller;

import com.codevictims.bayt.common.dto.PageSlice;
import com.codevictims.bayt.common.mapper.RequestMapper;
import com.codevictims.bayt.tenancy.entity.Tenant;
import com.codevictims.bayt.tenancy.mapper.TenantMapper;
import com.codevictims.bayt.tenancy.request.CreateTenantRequest;
import com.codevictims.bayt.tenancy.response.TenantResponse;
import com.codevictims.bayt.tenancy.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenancy/tenants")
public class TenantController {

    private final TenantService service;

    public TenantController(TenantService service) {
        this.service = service;
    }

    @GetMapping
    public PageSlice<TenantResponse> list(
            @RequestParam(required = false) Long buildingId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String q) {

        var rows = service.tenants(buildingId);

        return PageSlice.of(
                rows,
                page,
                size,
                q,
                Tenant::toString
        ).map(TenantMapper::toResponse);
    }

    @GetMapping("/{id}")
    public TenantResponse get(@PathVariable Long id) {

        return TenantMapper.toResponse(
                service.get(id)
        );
    }

    @PostMapping
    public TenantResponse create(
            @Valid @RequestBody CreateTenantRequest request) {

        return TenantMapper.toResponse(
                service.tenant(
                        RequestMapper.toInput(request),
                        null
                )
        );
    }

    @PutMapping("/{id}")
    public TenantResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CreateTenantRequest request) {

        return TenantMapper.toResponse(
                service.tenant(
                        RequestMapper.toInput(request),
                        id
                )
        );
    }
}