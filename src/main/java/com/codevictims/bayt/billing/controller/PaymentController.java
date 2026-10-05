// Keep payment API endpoints inside the billing controller package.
package com.codevictims.bayt.billing.controller;

// Import the request body used to create a payment.
import com.codevictims.bayt.billing.request.CreatePaymentRequest;
// Import allocation responses returned for a payment.
import com.codevictims.bayt.billing.response.PaymentAllocationResponse;
// Import payment responses returned to API clients.
import com.codevictims.bayt.billing.response.PaymentResponse;
// Import the service used to read payment allocations.
import com.codevictims.bayt.billing.service.PaymentAllocationService;
// Import the service that handles payment work.
import com.codevictims.bayt.billing.service.PaymentService;
// Import validation support for request bodies.
import jakarta.validation.Valid;
// Import Spring MVC annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Import List for endpoints that return many records.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for payment endpoints.
@RequestMapping("/api/billing/payments")
// Expose payment actions through HTTP endpoints.
public class PaymentController {
    // Store the service used for payment operations.
    private final PaymentService paymentService;
    // Store the service used for payment allocation lookups.
    private final PaymentAllocationService allocationService;

    // Receive controller dependencies through the constructor.
    public PaymentController(PaymentService paymentService,
                             PaymentAllocationService allocationService) {
        // Keep the payment service for endpoint methods.
        this.paymentService = paymentService;
        // Keep the allocation service for allocation endpoint methods.
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
