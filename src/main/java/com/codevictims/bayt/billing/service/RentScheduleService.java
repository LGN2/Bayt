// Keep rent schedule business logic inside the billing service package.
package com.property.billing.service;

// Import the rent due entity saved in the database.
import com.property.billing.entity.RentDue;
// Import the repository used for rent due records.
import com.property.billing.repository.RentDueRepository;
// Import the response object returned to clients.
import com.property.billing.response.RentDueResponse;
// Import the Spring service annotation.
import org.springframework.stereotype.Service;

// Import BigDecimal for rent amount values.
import java.math.BigDecimal;
// Import LocalDate for rent due dates.
import java.time.LocalDate;
// Import ArrayList for building response lists.
import java.util.ArrayList;
// Import List for returning many rent due responses.
import java.util.List;

// Mark this class as a Spring service.
@Service
// Handle rent due scheduling operations.
public class RentScheduleService {
    // Store the repository used for rent due records.
    private final RentDueRepository rentDueRepository;

    // Receive the rent due repository through the constructor.
    public RentScheduleService(RentDueRepository rentDueRepository) {
        // Keep the repository for later database work.
        this.rentDueRepository = rentDueRepository;
    }

    // Create one rent due record.
    public RentDueResponse createRentDue(Long leaseId, LocalDate dueDate, BigDecimal amount) {
        // Check that the rent amount is present and positive.
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            // Stop when the amount is missing or not greater than zero.
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Create a new rent due entity.
        RentDue rentDue = new RentDue();
        // Link the rent due record to the lease.
        rentDue.setLeaseId(leaseId);
        // Store the date when rent becomes due.
        rentDue.setDueDate(dueDate);
        // Store the full rent amount.
        rentDue.setAmount(amount);
        // Start the paid amount at zero.
        rentDue.setPaidAmount(BigDecimal.ZERO);
        // Save the rent due record and convert it to a response.
        return toResponse(rentDueRepository.save(rentDue));
    }

    // Return every rent due record as response objects.
    public List<RentDueResponse> getAll() {
        // Create a list to collect rent due responses.
        List<RentDueResponse> responses = new ArrayList<>();
        // Read every rent due record from the database.
        for (RentDue due : rentDueRepository.findAll()) {
            // Convert each rent due record and add it to the list.
            responses.add(toResponse(due));
        }
        // Return all converted rent due responses.
        return responses;
    }

    // Return rent due records for one lease.
    public List<RentDueResponse> getByLease(Long leaseId) {
        // Create a list for this lease's rent due responses.
        List<RentDueResponse> responses = new ArrayList<>();
        // Find rent due records for the lease in due date order.
        for (RentDue due : rentDueRepository.findByLeaseIdOrderByDueDateAsc(leaseId)) {
            // Convert each lease rent due record into a response.
            responses.add(toResponse(due));
        }
        // Return the lease rent due responses.
        return responses;
    }

    // Convert a rent due entity into a response object.
    public RentDueResponse toResponse(RentDue due) {
        // Create the response object.
        RentDueResponse response = new RentDueResponse();
        // Copy the rent due ID into the response.
        response.setId(due.getId());
        // Copy the lease ID into the response.
        response.setLeaseId(due.getLeaseId());
        // Copy the due date into the response.
        response.setDueDate(due.getDueDate());
        // Copy the full rent amount into the response.
        response.setAmount(due.getAmount());
        // Copy the paid amount into the response.
        response.setPaidAmount(due.getPaidAmount());
        // Copy the remaining balance into the response.
        response.setOutstandingAmount(due.getOutstandingAmount());
        // Set status text based on whether the rent is fully paid.
        response.setStatus(due.isPaid() ? "PAID" : "UNPAID");
        // Return the completed rent due response.
        return response;
    }
}
