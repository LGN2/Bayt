// Place rent due REST endpoints in the billing controller package.
package com.property.billing.controller;

// Return rent due details from controller methods.
import com.property.billing.response.RentDueResponse;
// Use the service that manages rent schedules.
import com.property.billing.service.RentScheduleService;
// Use Spring web annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Read rent amounts from request parameters.
import java.math.BigDecimal;
// Read due dates from request parameters.
import java.time.LocalDate;
// Return lists of rent due responses.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for rent due API requests.
@RequestMapping("/api/billing/rent-dues")
// Handle HTTP requests for rent due records.
public class RentDueController {
    // Store the service used by this controller.
    private final RentScheduleService rentScheduleService;

    // Receive the rent schedule service through constructor injection.
    public RentDueController(RentScheduleService rentScheduleService) {
        // Keep the service for endpoint methods.
        this.rentScheduleService = rentScheduleService;
    }

    // Handle POST requests that create a rent due record.
    @PostMapping
    // Create rent due data from request parameters.
    public RentDueResponse create(@RequestParam Long leaseId,
                                  // Read the due date from the request parameter.
                                  @RequestParam LocalDate dueDate,
                                  // Read the rent amount from the request parameter.
                                  @RequestParam BigDecimal amount) {
        // Ask the service to create the rent due record.
        return rentScheduleService.createRentDue(leaseId, dueDate, amount);
    }

    // Handle GET requests for all rent due records.
    @GetMapping
    // Return every rent due response.
    public List<RentDueResponse> getAll() {
        // Ask the service for all rent due records.
        return rentScheduleService.getAll();
    }

    // Handle GET requests for rent dues linked to one lease.
    @GetMapping("/lease/{leaseId}")
    // Return rent due records for the lease path ID.
    public List<RentDueResponse> getByLease(@PathVariable Long leaseId) {
        // Ask the service for rent dues owned by this lease.
        return rentScheduleService.getByLease(leaseId);
    }
}
