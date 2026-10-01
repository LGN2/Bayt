// Place cheque REST endpoints in the billing controller package.
package com.property.billing.controller;

// Use the request object for cheque status changes.
import com.property.billing.request.ChequeTransitionRequest;
// Use the request object for creating cheques.
import com.property.billing.request.CreateChequeRequest;
// Return cheque details from controller methods.
import com.property.billing.response.ChequeResponse;
// Return cheque status history details when requested.
import com.property.billing.response.ChequeStatusHistoryResponse;
// Use the service that handles cheque logic.
import com.property.billing.service.ChequeService;
// Validate request bodies before service calls.
import jakarta.validation.Valid;
// Use Spring web annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Return lists of cheques or history entries.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for cheque API requests.
@RequestMapping("/api/billing/cheques")
// Handle HTTP requests for cheque operations.
public class ChequeController {
    // Store the service used by this controller.
    private final ChequeService chequeService;

    // Receive the cheque service through constructor injection.
    public ChequeController(ChequeService chequeService) {
        // Keep the cheque service for endpoint methods.
        this.chequeService = chequeService;
    }

    // Handle POST requests that create a cheque.
    @PostMapping
    // Create a cheque from the validated request body.
    public ChequeResponse create(@Valid @RequestBody CreateChequeRequest request) {
        // Send the create request to the service.
        return chequeService.create(request);
    }

    // Handle PUT requests that change a cheque status.
    @PutMapping("/{id}/status")
    // Change the status for the cheque ID from the path.
    public ChequeResponse changeStatus(@PathVariable Long id,
                                       // Read the new status from the request body.
                                       @Valid @RequestBody ChequeTransitionRequest request) {
        // Ask the service to update the cheque status.
        return chequeService.changeStatus(id, request);
    }

    // Handle GET requests for all cheques.
    @GetMapping
    // Return every cheque response.
    public List<ChequeResponse> getAll() {
        // Ask the service for all cheques.
        return chequeService.getAll();
    }

    // Handle GET requests for one cheque history list.
    @GetMapping("/{id}/history")
    // Return history entries for the cheque path ID.
    public List<ChequeStatusHistoryResponse> getHistory(@PathVariable Long id) {
        // Ask the service for the cheque status history.
        return chequeService.getHistory(id);
    }
}
