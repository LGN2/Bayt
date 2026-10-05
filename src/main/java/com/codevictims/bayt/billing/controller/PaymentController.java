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

    // Handle requests that create a new payment.
    @PostMapping
    // Receive a validated payment request from the HTTP body.
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest request) {
        // Ask the service to create the payment.
        return paymentService.create(request);
    }

    // Handle requests that list every payment.
    @GetMapping
    // Return all payment responses.
    public List<PaymentResponse> getAll() {
        // Ask the service for every payment.
        return paymentService.getAll();
    }

    // Handle requests for one payment by ID.
    @GetMapping("/{id}")
    // Read the payment ID from the URL path.
    public PaymentResponse getById(@PathVariable Long id) {
        // Ask the service to find the payment.
        return paymentService.getById(id);
    }

    // Handle requests for payments that belong to one tenant.
    @GetMapping("/tenant/{tenantId}")
    // Read the tenant ID from the URL path.
    public List<PaymentResponse> getByTenant(@PathVariable Long tenantId) {
        // Ask the service for this tenant's payments.
        return paymentService.getByTenant(tenantId);
    }

    @GetMapping("/{id}/allocations")
    public List<PaymentAllocationResponse> getAllocations(@PathVariable Long id) {
        return allocationService.getByPayment(id);
    }
}
