package com.codevictims.bayt.maintenance.controller;

import com.codevictims.bayt.common.dto.PageSlice;
import com.codevictims.bayt.common.mapper.RequestMapper;
import com.codevictims.bayt.maintenance.dto.request.AiSuggestionRequest;
import com.codevictims.bayt.maintenance.dto.request.ApproveSummaryRequest;
import com.codevictims.bayt.maintenance.dto.request.MaintenanceCommentRequest;
import com.codevictims.bayt.maintenance.dto.request.MaintenanceRequest;
import com.codevictims.bayt.maintenance.dto.request.MaintenanceStatusRequest;
import com.codevictims.bayt.maintenance.dto.response.MaintenanceResponse;
import com.codevictims.bayt.maintenance.mapper.MaintenanceMapper;
import com.codevictims.bayt.maintenance.service.AiAssistant;
import com.codevictims.bayt.maintenance.service.MaintenanceService;
import com.codevictims.bayt.security.service.Access;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {
    private final MaintenanceService s;
    private final Access access;
    private final AiAssistant ai;

    public MaintenanceController(MaintenanceService s, Access access, AiAssistant ai) {
        this.s = s;
        this.access = access;
        this.ai = ai;
    }

    @GetMapping
    public PageSlice<MaintenanceResponse> list(
            @RequestParam(required = false) Long buildingId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "") String status) {
        return PageSlice.of(
                        s.list(buildingId).stream()
                                .filter(m -> status.isBlank() || m.status.equals(status))
                                .toList(),
                        page,
                        size,
                        q,
                        m -> m.description + " " + m.category + " " + m.status)
                .map(MaintenanceMapper::toResponse);
    }

    @GetMapping("/{id}")
    public MaintenanceResponse get(@PathVariable Long id) {
        return MaintenanceMapper.toResponse(access.maintenance(id));
    }

    @PostMapping
    public MaintenanceResponse create(@jakarta.validation.Valid @RequestBody MaintenanceRequest b) {
        return MaintenanceMapper.toResponse(s.create(RequestMapper.toInput(b)));
    }

    @PostMapping("/{id}/status")
    public MaintenanceResponse transition(
            @PathVariable Long id, @jakarta.validation.Valid @RequestBody MaintenanceStatusRequest b) {
        return MaintenanceMapper.toResponse(s.transition(id, RequestMapper.toInput(b)));
    }

    @PostMapping("/{id}/comments")
    void comment(
            @PathVariable Long id, @jakarta.validation.Valid @RequestBody MaintenanceCommentRequest b) {
        s.comment(id, RequestMapper.toInput(b));
    }

    @PostMapping("/{id}/approve-summary")
    public MaintenanceResponse approve(
            @PathVariable Long id, @jakarta.validation.Valid @RequestBody ApproveSummaryRequest b) {
        return MaintenanceMapper.toResponse(s.approve(id, RequestMapper.toInput(b)));
    }

    @PostMapping("/{id}/ai-suggestion")
    public MaintenanceResponse suggest(
            @PathVariable Long id, @jakarta.validation.Valid @RequestBody AiSuggestionRequest b) {
        var m = access.maintenance(id);
        access.manage(m.buildingId);
        String language = RequestMapper.toInput(b).choice("language", "ar", "en");
        // The request is already committed; provider I/O never shares its creation transaction.
        return MaintenanceMapper.toResponse(s.aiResult(id, ai.suggest(m.description, language)));
    }
}
