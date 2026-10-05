// Keep arrears business logic inside the billing service package.
package com.property.billing.service;

// Import rent due records used to calculate arrears.
import com.property.billing.entity.RentDue;
// Import the repository used to read rent due records.
import com.property.billing.repository.RentDueRepository;
// Import the response used for overdue rent records.
import com.property.billing.response.ArrearsResponse;
// Import the response used for financial totals.
import com.property.billing.response.FinancialSummaryResponse;
// Import the Spring service annotation.
import org.springframework.stereotype.Service;

// Import BigDecimal for money totals.
import java.math.BigDecimal;
// Import LocalDate for today's date and due date checks.
import java.time.LocalDate;
// Import ChronoUnit to calculate late days.
import java.time.temporal.ChronoUnit;
// Import ArrayList for building response lists.
import java.util.ArrayList;
// Import List for returning many arrears records.
import java.util.List;

// Mark this class as a Spring service.
@Service
// Handle arrears-related business logic.
public class ArrearsService {
    // Store the repository used for rent due records.
    private final RentDueRepository rentDueRepository;

    // Receive the rent due repository through the constructor.
    public ArrearsService(RentDueRepository rentDueRepository) {
        // Keep the repository for arrears calculations.
        this.rentDueRepository = rentDueRepository;
    }

    // Build a list of overdue unpaid rent records.
    public List<ArrearsResponse> getArrears() {
        // Create a list to collect arrears responses.
        List<ArrearsResponse> responses = new ArrayList<>();
        // Use today's date to find overdue rent.
        LocalDate today = LocalDate.now();

        // Load rent records with due dates before today.
        for (RentDue due : rentDueRepository.findByDueDateBeforeOrderByDueDateAsc(today)) {
            // Only include rent due records that are not fully paid.
            if (!due.isPaid()) {
                // Create a response for one overdue rent record.
                ArrearsResponse response = new ArrearsResponse();
                // Copy the rent due ID into the response.
                response.setRentDueId(due.getId());
                // Copy the lease ID into the response.
                response.setLeaseId(due.getLeaseId());
                // Copy the original rent due date.
                response.setDueDate(due.getDueDate());
                // Calculate how many days the rent is late.
                response.setDaysLate(ChronoUnit.DAYS.between(due.getDueDate(), today));
                // Copy the unpaid amount into the response.
                response.setOutstandingAmount(due.getOutstandingAmount());
                // Add this overdue record to the result list.
                responses.add(response);
            }
        }
        // Return all overdue unpaid rent records.
        return responses;
    }

    public FinancialSummaryResponse getSummary() {
        BigDecimal totalDue = BigDecimal.ZERO;
        BigDecimal totalPaid = BigDecimal.ZERO;
        BigDecimal totalOutstanding = BigDecimal.ZERO;
        int overdue = 0;
        LocalDate today = LocalDate.now();

        for (RentDue due : rentDueRepository.findAll()) {
            totalDue = totalDue.add(due.getAmount());
            totalPaid = totalPaid.add(due.getPaidAmount());
            totalOutstanding = totalOutstanding.add(due.getOutstandingAmount());
            if (due.getDueDate().isBefore(today) && !due.isPaid()) {
                overdue++;
            }
        }

        FinancialSummaryResponse response = new FinancialSummaryResponse();
        response.setTotalRentDue(totalDue);
        response.setTotalPaid(totalPaid);
        response.setTotalOutstanding(totalOutstanding);
        response.setOverdueItems(overdue);
        return response;
    }
}
