// Place deposit REST endpoints in the billing controller package.
package com.property.billing.controller;

// Use the request object for deposit updates.
import com.property.billing.request.UpdateDepositRequest;
// Return deposit details from controller methods.
import com.property.billing.response.DepositResponse;
// Use the service that handles deposit logic.
import com.property.billing.service.DepositService;
// Validate request bodies before service calls.
import jakarta.validation.Valid;
// Use Spring web annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Read deposit amounts from request parameters.
import java.math.BigDecimal;
// Return lists of deposit responses.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for deposit API requests.
@RequestMapping("/api/billing/deposits")
// Handle HTTP requests for deposit records.
public class DepositController {
    // Store the service used by this controller.
    private final DepositService depositService;

    // Receive the deposit service through constructor injection.
    public DepositController(DepositService depositService) {
        // Keep the deposit service for endpoint methods.
        this.depositService = depositService;
    }

    // Handle POST requests that create a deposit record.
    @PostMapping
    // Create a deposit using tenant ID and amount request parameters.
    public DepositResponse create(@RequestParam Long tenantId,
                                  // Read the deposit amount from the request parameter.
                                  @RequestParam BigDecimal amount) {
        // Send the create request to the service.
        return depositService.create(tenantId, amount);
    }

    // Handle PUT requests that update a deposit record.
    @PutMapping("/{id}")
    // Update the deposit identified by the path ID.
    public DepositResponse update(@PathVariable Long id,
                                  // Read and validate the update request body.
                                  @Valid @RequestBody UpdateDepositRequest request) {
        // Send the update request to the service.
        return depositService.update(id, request);
    }

    // Handle GET requests for all deposit records.
    @GetMapping
    // Return every deposit response.
    public List<DepositResponse> getAll() {
        // Ask the service for all deposits.
        return depositService.getAll();
    }
}
