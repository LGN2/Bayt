// Keep rent due API endpoints inside the billing controller package.
package com.property.billing.controller;

// Import rent due responses returned to API clients.
import com.property.billing.response.RentDueResponse;
// Import the service that handles rent due work.
import com.property.billing.service.RentScheduleService;
// Import Spring MVC annotations for REST endpoints.
import org.springframework.web.bind.annotation.*;

// Import BigDecimal for rent amount request values.
import java.math.BigDecimal;
// Import LocalDate for rent due date request values.
import java.time.LocalDate;
// Import List for endpoints that return many records.
import java.util.List;

// Mark this class as a REST controller.
@RestController
// Set the base URL for rent due endpoints.
@RequestMapping("/api/billing/rent-dues")
// Expose rent due actions through HTTP endpoints.
public class RentDueController {
    // Store the service used for rent schedule operations.
    private final RentScheduleService rentScheduleService;

    // Receive the rent schedule service through the constructor.
    public RentDueController(RentScheduleService rentScheduleService) {
        // Keep the service for endpoint methods.
        this.rentScheduleService = rentScheduleService;
    }

    // Handle requests that create a rent due record.
    @PostMapping
    // Read the lease, date, and amount from request parameters.
    public RentDueResponse create(@RequestParam Long leaseId,
                                  @RequestParam LocalDate dueDate,
                                  @RequestParam BigDecimal amount) {
        // Ask the service to create the rent due record.
        return rentScheduleService.createRentDue(leaseId, dueDate, amount);
    }

    // Handle requests that list every rent due record.
    @GetMapping
    // Return all rent due responses.
    public List<RentDueResponse> getAll() {
        // Ask the service for every rent due record.
        return rentScheduleService.getAll();
    }

    // Handle requests for rent dues under one lease.
    @GetMapping("/lease/{leaseId}")
    // Read the lease ID from the URL path.
    public List<RentDueResponse> getByLease(@PathVariable Long leaseId) {
        // Ask the service for rent due records for this lease.
        return rentScheduleService.getByLease(leaseId);
    }
}
