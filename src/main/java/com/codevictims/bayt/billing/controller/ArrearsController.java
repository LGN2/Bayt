package com.property.billing.controller;

import com.property.billing.response.ArrearsResponse;
import com.property.billing.response.FinancialSummaryResponse;
import com.property.billing.service.ArrearsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing/arrears")
public class ArrearsController {
    private final ArrearsService arrearsService;

    public ArrearsController(ArrearsService arrearsService) {
        this.arrearsService = arrearsService;
    }

    @GetMapping
    public List<ArrearsResponse> getArrears() {
        return arrearsService.getArrears();
    }

    @GetMapping("/summary")
    public FinancialSummaryResponse getSummary() {
        return arrearsService.getSummary();
    }
}
