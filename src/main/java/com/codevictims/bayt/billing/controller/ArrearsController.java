// Place arrears REST endpoints in the billing controller package.
package com.property.billing.controller;

// Return overdue rent records from controller methods.
import com.property.billing.response.ArrearsResponse;
// Return financial summary totals from controller methods.
import com.property.billing.response.FinancialSummaryResponse;
// Use the service that calculates arrears data.
import com.property.billing.service.ArrearsService;
// Use Spring web annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Return lists of arrears responses.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for arrears API requests.
@RequestMapping("/api/billing/arrears")
// Handle HTTP requests for arrears information.
public class ArrearsController {
    // Store the service used by this controller.
    private final ArrearsService arrearsService;

    // Receive the arrears service through constructor injection.
    public ArrearsController(ArrearsService arrearsService) {
        // Keep the service for endpoint methods.
        this.arrearsService = arrearsService;
    }

    // Handle GET requests for all arrears records.
    @GetMapping
    // Return every overdue rent record.
    public List<ArrearsResponse> getArrears() {
        // Ask the service to calculate arrears.
        return arrearsService.getArrears();
    }

    // Handle GET requests for the financial summary.
    @GetMapping("/summary")
    // Return overall billing summary totals.
    public FinancialSummaryResponse getSummary() {
        // Ask the service to build the summary.
        return arrearsService.getSummary();
    }
}
