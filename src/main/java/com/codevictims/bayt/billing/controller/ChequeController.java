// Keep cheque API endpoints inside the billing controller package.
package com.codevictims.bayt.billing.controller;

// Import the request used to change cheque status.
import com.codevictims.bayt.billing.request.ChequeTransitionRequest;
// Import the request used to create a cheque.
import com.codevictims.bayt.billing.request.CreateChequeRequest;
// Import cheque responses returned to API clients.
import com.codevictims.bayt.billing.response.ChequeResponse;
// Import history responses returned for a cheque.
import com.codevictims.bayt.billing.response.ChequeStatusHistoryResponse;
// Import the service that handles cheque work.
import com.codevictims.bayt.billing.service.ChequeService;
// Import validation support for request bodies.
import jakarta.validation.Valid;
// Import Spring MVC annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Import List for endpoints that return many records.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for cheque endpoints.
@RequestMapping("/api/billing/cheques")
// Expose cheque actions through HTTP endpoints.
public class ChequeController {
    // Store the service used for cheque operations.
    private final ChequeService chequeService;

    // Receive the cheque service through the constructor.
    public ChequeController(ChequeService chequeService) {
        // Keep the cheque service for endpoint methods.
        this.chequeService = chequeService;
    }

    // Handle requests that create a new cheque.
    @PostMapping
    // Receive a validated cheque request from the HTTP body.
    public ChequeResponse create(@Valid @RequestBody CreateChequeRequest request) {
        // Ask the service to create the cheque.
        return chequeService.create(request);
    }

    // Handle requests that update a cheque status.
    @PutMapping("/{id}/status")
    // Read the cheque ID from the URL and the new status from the body.
    public ChequeResponse changeStatus(@PathVariable Long id,
                                       @Valid @RequestBody ChequeTransitionRequest request) {
        // Ask the service to change the cheque status.
        return chequeService.changeStatus(id, request);
    }

    // Handle requests that list all cheques.
    @GetMapping
    // Return all cheque responses.
    public List<ChequeResponse> getAll() {
        // Ask the service for every cheque.
        return chequeService.getAll();
    }

    // Handle requests for status history of one cheque.
    @GetMapping("/{id}/history")
    // Read the cheque ID from the URL path.
    public List<ChequeStatusHistoryResponse> getHistory(@PathVariable Long id) {
        // Ask the service for the cheque status history.
        return chequeService.getHistory(id);
    }
}
