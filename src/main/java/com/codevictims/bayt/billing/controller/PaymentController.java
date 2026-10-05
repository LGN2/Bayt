package com.codevictims.bayt.billing.controller;

import com.codevictims.bayt.billing.request.CreatePaymentRequest;
import com.codevictims.bayt.billing.response.PaymentAllocationResponse;
import com.codevictims.bayt.billing.response.PaymentResponse;
import com.codevictims.bayt.billing.service.PaymentAllocationService;
import com.codevictims.bayt.billing.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentAllocationService allocationService;

    public PaymentController(PaymentService paymentService,
                             PaymentAllocationService allocationService) {
        this.paymentService = paymentService;
        this.allocationService = allocationService;
    }

    @PostMapping
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.create(request);
    }

    @GetMapping
    public List<PaymentResponse> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @GetMapping("/tenant/{tenantId}")
    public List<PaymentResponse> getByTenant(@PathVariable Long tenantId) {
        return paymentService.getByTenant(tenantId);
    }

    @GetMapping("/{id}/allocations")
    public List<PaymentAllocationResponse> getAllocations(@PathVariable Long id) {
        return allocationService.getByPayment(id);
    }
}
