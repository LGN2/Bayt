package com.codevictims.bayt.tenancy.controller;

import com.codevictims.bayt.common.dto.PageSlice;
import com.codevictims.bayt.common.mapper.RequestMapper;
import com.codevictims.bayt.security.authorization.AccessService;
import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.mapper.LeaseMapper;
import com.codevictims.bayt.tenancy.request.LeaseRequest;
import com.codevictims.bayt.tenancy.request.MunicipalityRequest;
import com.codevictims.bayt.tenancy.request.TerminateLeaseRequest;
import com.codevictims.bayt.tenancy.response.LeaseResponse;
import com.codevictims.bayt.tenancy.service.LeaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeaseController {

    private final LeaseService service;
    private final AccessService access;

    public LeaseController(
            LeaseService service,
            AccessService access
    ) {
        this.service = service;
        this.access = access;
    }

    @GetMapping("/leases")
    public PageSlice<LeaseResponse> leases(
            @RequestParam(required = false) Long buildingId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String q
    ) {

        return PageSlice.of(
                service.leases(buildingId),
                page,
                size,
                q,
                lease ->
                        lease.getId()
                                + " "
                                + lease.getUnitId()
                                + " "
                                + lease.getTenantId()
                                + " "
                                + lease.getStatus()
                                + " "
                                + lease.getMunicipalityStatus()
        ).map(LeaseMapper::toResponse);
    }

    @GetMapping("/leases/{id}")
    public LeaseResponse lease(
            @PathVariable Long id
    ) {
        return LeaseMapper.toResponse(
                access.lease(id, false)
        );
    }

    @PostMapping("/leases")
    public LeaseResponse create(
            @Valid @RequestBody LeaseRequest request
    ) {

        return LeaseMapper.toResponse(
                service.lease(
                        RequestMapper.toInput(request),
                        null
                )
        );
    }

    @PostMapping("/leases/{id}/renew")
    public LeaseResponse renew(
            @PathVariable Long id,
            @Valid @RequestBody LeaseRequest request
    ) {

        return LeaseMapper.toResponse(
                service.lease(
                        RequestMapper.toInput(request),
                        id
                )
        );
    }

    @PostMapping("/leases/{id}/terminate")
    public LeaseResponse terminate(
            @PathVariable Long id,
            @Valid @RequestBody TerminateLeaseRequest request
    ) {

        return LeaseMapper.toResponse(
                service.terminate(
                        id,
                        RequestMapper.toInput(request)
                )
        );
    }

    @PutMapping("/leases/{id}/municipality")
    public LeaseResponse municipality(
            @PathVariable Long id,
            @Valid @RequestBody MunicipalityRequest request
    ) {

        return LeaseMapper.toResponse(
                service.municipality(
                        id,
                        RequestMapper.toInput(request)
                )
        );
    }
}