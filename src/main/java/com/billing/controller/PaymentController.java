// Place payment REST endpoints in the billing controller package.
package main.java.com.billing.controller;

// Use the request object that carries new payment details.
import main.java.com.billing.request.CreatePaymentRequest;
// Return allocation details when a payment's allocations are requested.
import com.property.billing.response.PaymentAllocationResponse;
// Return payment details from the payment endpoints.
import main.java.com.billing.response.PaymentResponse;
// Use the allocation service to read allocation records for a payment.
import com.property.billing.service.PaymentAllocationService;
// Use the payment service for payment actions.
import main.java.com.billing.service.PaymentService;
// Validate the request body before creating a payment.
import jakarta.validation.Valid;
// Use Spring web annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Return lists of payment or allocation responses.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for payment API requests.
@RequestMapping("/api/billing/payments")
// Handle HTTP requests for payments.
public class PaymentController {
    // Store the service that manages payment operations.
    private final PaymentService paymentService;
    // Store the service that reads payment allocation data.
    private final PaymentAllocationService allocationService;

    // Receive the required services through constructor injection.
    public PaymentController(PaymentService paymentService,
                             // Receive the allocation service for allocation lookups.
                             PaymentAllocationService allocationService) {
        // Keep the payment service for controller methods.
        this.paymentService = paymentService;
        // Keep the allocation service for allocation endpoints.
        this.allocationService = allocationService;
    }

    // Handle POST requests that create a new payment.
    @PostMapping
    // Validate and read the JSON body as a payment request.
    // Create a payment from the validated request body.
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest request) {
        // Send the create request to the payment service.
        return paymentService.create(request);
    }

    // Handle GET requests for all payments.
    @GetMapping
    // Return every payment response.
    public List<PaymentResponse> getAll() {
        // Ask the service for all payments.
        return paymentService.getAll();
    }

    // Handle GET requests for one payment ID.
    @GetMapping("/{id}")
    // Read the payment ID from the URL path.
    // Return one payment by its path ID.
    public PaymentResponse getById(@PathVariable Long id) {
        // Ask the service to find the payment.
        return paymentService.getById(id);
    }

    // Handle GET requests for payments that belong to one tenant.
    @GetMapping("/tenant/{tenantId}")
    // Read the tenant ID from the URL path.
    // Return payments for the tenant path ID.
    public List<PaymentResponse> getByTenant(@PathVariable Long tenantId) {
        // Ask the service for payments owned by this tenant.
        return paymentService.getByTenant(tenantId);
    }

    // Handle GET requests for allocations linked to one payment.
    @GetMapping("/{id}/allocations")
    // Use the payment ID from the path to load allocations.
    // Return allocation responses for the payment path ID.
    public List<PaymentAllocationResponse> getAllocations(@PathVariable Long id) {
        // Ask the allocation service for allocations of this payment.
        return allocationService.getByPayment(id);
    }
}
