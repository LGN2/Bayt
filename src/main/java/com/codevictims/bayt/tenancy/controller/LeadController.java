package com.codevictims.bayt.tenancy.controller;

import com.codevictims.bayt.tenancy.entity.Lead;
import com.codevictims.bayt.tenancy.mapper.LeadMapper;
import com.codevictims.bayt.tenancy.request.LeadRequest;
import com.codevictims.bayt.tenancy.response.LeadResponse;
import com.codevictims.bayt.tenancy.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenancy/leads")
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @GetMapping
    public List<LeadResponse> list() {

        return service.getAll()
                .stream()
                .map(LeadMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public LeadResponse get(@PathVariable Long id) {

        return LeadMapper.toResponse(
                service.getById(id)
        );
    }

    @PostMapping
    public LeadResponse create(
            @Valid @RequestBody LeadRequest request) {

        Lead lead = new Lead();

        lead.setBuildingId(request.buildingId());
        lead.setUnitId(request.unitId());
        lead.setName(request.name());
        lead.setPhone(request.phone());
        lead.setStatus(request.status());
        lead.setViewingAt(request.viewingAt());
        lead.setFollowUpDate(request.followUpDate());
        lead.setNotes(request.notes());

        return LeadMapper.toResponse(
                service.create(lead)
        );
    }

    @PutMapping("/{id}")
    public LeadResponse update(
            @PathVariable Long id,
            @Valid @RequestBody LeadRequest request) {

        Lead lead = new Lead();

        lead.setBuildingId(request.buildingId());
        lead.setUnitId(request.unitId());
        lead.setName(request.name());
        lead.setPhone(request.phone());
        lead.setStatus(request.status());
        lead.setViewingAt(request.viewingAt());
        lead.setFollowUpDate(request.followUpDate());
        lead.setNotes(request.notes());

        return LeadMapper.toResponse(
                service.update(id, lead)
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}