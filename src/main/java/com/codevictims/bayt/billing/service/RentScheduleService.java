// Place rent schedule business logic in the billing service package.
package com.property.billing.service;

// Use the RentDue entity for database records.
import com.property.billing.entity.RentDue;
// Use the repository that saves and reads rent due records.
import com.property.billing.repository.RentDueRepository;
// Use the response object returned by service methods.
import com.property.billing.response.RentDueResponse;
// Mark this class as a Spring service.
import org.springframework.stereotype.Service;

// Handle rent money values.
import java.math.BigDecimal;
// Store rent due dates.
import java.time.LocalDate;
// Build response lists manually.
import java.util.ArrayList;
// Return lists of rent due responses.
import java.util.List;

// Register this class as a Spring service bean.
@Service
// Handle rent due creation and lookup.
public class RentScheduleService {
    // Store the repository used for rent due data access.
    private final RentDueRepository rentDueRepository;

    // Receive the rent due repository through constructor injection.
    public RentScheduleService(RentDueRepository rentDueRepository) {
        // Keep the repository for later service operations.
        this.rentDueRepository = rentDueRepository;
    }

    // Create a new rent due record.
    public RentDueResponse createRentDue(Long leaseId, LocalDate dueDate, BigDecimal amount) {
        // Check that the rent amount is present and positive.
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            // Stop when the rent amount is invalid.
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Build a new empty rent due entity.
        RentDue rentDue = new RentDue();
        // Store the lease linked to this rent due.
        rentDue.setLeaseId(leaseId);
        // Store the date when rent becomes due.
        rentDue.setDueDate(dueDate);
        // Store the full rent amount.
        rentDue.setAmount(amount);
        // Start with no paid amount.
        rentDue.setPaidAmount(BigDecimal.ZERO);
        // Save the rent due and convert it to a response.
        return toResponse(rentDueRepository.save(rentDue));
    }

    // Get all rent due records as response objects.
    public List<RentDueResponse> getAll() {
        // Prepare a list for rent due responses.
        List<RentDueResponse> responses = new ArrayList<>();
        // Read every rent due record from the repository.
        for (RentDue due : rentDueRepository.findAll()) {
            // Convert each rent due and add it to the list.
            responses.add(toResponse(due));
        }
        // Return all converted rent due records.
        return responses;
    }

    // Get rent due records for one lease.
    public List<RentDueResponse> getByLease(Long leaseId) {
        // Prepare a list for this lease's rent due responses.
        List<RentDueResponse> responses = new ArrayList<>();
        // Read rent dues for the lease ordered by due date.
        for (RentDue due : rentDueRepository.findByLeaseIdOrderByDueDateAsc(leaseId)) {
            // Convert each lease rent due and add it to the list.
            responses.add(toResponse(due));
        }
        // Return the rent dues for this lease.
        return responses;
    }

    // Convert a RentDue entity into a RentDueResponse.
    public RentDueResponse toResponse(RentDue due) {
        // Create an empty rent due response.
        RentDueResponse response = new RentDueResponse();
        // Copy the rent due ID into the response.
        response.setId(due.getId());
        // Copy the lease ID into the response.
        response.setLeaseId(due.getLeaseId());
        // Copy the due date into the response.
        response.setDueDate(due.getDueDate());
        // Copy the rent amount into the response.
        response.setAmount(due.getAmount());
        // Copy the paid amount into the response.
        response.setPaidAmount(due.getPaidAmount());
        // Copy the outstanding amount into the response.
        response.setOutstandingAmount(due.getOutstandingAmount());
        // Set the status based on whether the rent is fully paid.
        response.setStatus(due.isPaid() ? "PAID" : "UNPAID");
        // Return the completed rent due response.
        return response;
    }
}
