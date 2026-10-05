// Keep arrears API endpoints inside the billing controller package.
package com.property.billing.controller;

// Import arrears records returned to API clients.
import com.property.billing.response.ArrearsResponse;
// Import the financial summary returned by the API.
import com.property.billing.response.FinancialSummaryResponse;
// Import the service that handles arrears work.
import com.property.billing.service.ArrearsService;
// Import Spring MVC annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Import List for endpoints that return many arrears records.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for arrears endpoints.
@RequestMapping("/api/billing/arrears")
// Expose arrears information through HTTP endpoints.
public class ArrearsController {
    // Store the service used for arrears operations.
    private final ArrearsService arrearsService;

    // Receive the arrears service through the constructor.
    public ArrearsController(ArrearsService arrearsService) {
        // Keep the service for endpoint methods.
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
