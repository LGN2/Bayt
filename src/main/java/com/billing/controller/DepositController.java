package com.property.billing.controller;

import com.property.billing.request.UpdateDepositRequest;
import com.property.billing.response.DepositResponse;
import com.property.billing.service.DepositService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/billing/deposits")
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping
    public DepositResponse create(@RequestParam Long tenantId,
                                  @RequestParam BigDecimal amount) {
        return depositService.create(tenantId, amount);
    }

    @PutMapping("/{id}")
    public DepositResponse update(@PathVariable Long id,
                                  @Valid @RequestBody UpdateDepositRequest request) {
        return depositService.update(id, request);
    }

    @GetMapping
    public List<DepositResponse> getAll() {
        return depositService.getAll();
    }
}
