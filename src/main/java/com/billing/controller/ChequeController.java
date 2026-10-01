package com.property.billing.controller;

import com.property.billing.request.ChequeTransitionRequest;
import com.property.billing.request.CreateChequeRequest;
import com.property.billing.response.ChequeResponse;
import com.property.billing.response.ChequeStatusHistoryResponse;
import com.property.billing.service.ChequeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing/cheques")
public class ChequeController {
    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @PostMapping
    public ChequeResponse create(@Valid @RequestBody CreateChequeRequest request) {
        return chequeService.create(request);
    }

    @PutMapping("/{id}/status")
    public ChequeResponse changeStatus(@PathVariable Long id,
                                       @Valid @RequestBody ChequeTransitionRequest request) {
        return chequeService.changeStatus(id, request);
    }

    @GetMapping
    public List<ChequeResponse> getAll() {
        return chequeService.getAll();
    }

    @GetMapping("/{id}/history")
    public List<ChequeStatusHistoryResponse> getHistory(@PathVariable Long id) {
        return chequeService.getHistory(id);
    }
}
