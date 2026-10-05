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

    public List<RentDueResponse> getAll() {
        List<RentDueResponse> responses = new ArrayList<>();
        for (RentDue due : rentDueRepository.findAll()) {
            responses.add(toResponse(due));
        }
        return responses;
    }

    public List<RentDueResponse> getByLease(Long leaseId) {
        List<RentDueResponse> responses = new ArrayList<>();
        for (RentDue due : rentDueRepository.findByLeaseIdOrderByDueDateAsc(leaseId)) {
            responses.add(toResponse(due));
        }
        return responses;
    }

    public RentDueResponse toResponse(RentDue due) {
        RentDueResponse response = new RentDueResponse();
        response.setId(due.getId());
        response.setLeaseId(due.getLeaseId());
        response.setDueDate(due.getDueDate());
        response.setAmount(due.getAmount());
        response.setPaidAmount(due.getPaidAmount());
        response.setOutstandingAmount(due.getOutstandingAmount());
        response.setStatus(due.isPaid() ? "PAID" : "UNPAID");
        return response;
    }
}
