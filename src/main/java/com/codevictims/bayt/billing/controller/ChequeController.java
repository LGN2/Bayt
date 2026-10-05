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
