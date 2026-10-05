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

    // Build a financial summary from all rent due records.
    public FinancialSummaryResponse getSummary() {
        // Start the total rent due amount at zero.
        BigDecimal totalDue = BigDecimal.ZERO;
        // Start the total paid amount at zero.
        BigDecimal totalPaid = BigDecimal.ZERO;
        // Start the total outstanding amount at zero.
        BigDecimal totalOutstanding = BigDecimal.ZERO;
        // Start the overdue record count at zero.
        int overdue = 0;
        // Use today's date for overdue checks.
        LocalDate today = LocalDate.now();

        // Read every rent due record from the database.
        for (RentDue due : rentDueRepository.findAll()) {
            // Add this rent amount to the total due amount.
            totalDue = totalDue.add(due.getAmount());
            // Add this paid amount to the total paid amount.
            totalPaid = totalPaid.add(due.getPaidAmount());
            // Add this outstanding amount to the total unpaid amount.
            totalOutstanding = totalOutstanding.add(due.getOutstandingAmount());
            // Count unpaid rent records with due dates before today.
            if (due.getDueDate().isBefore(today) && !due.isPaid()) {
                // Increase the overdue count.
                overdue++;
            }
        }

        // Create the financial summary response.
        FinancialSummaryResponse response = new FinancialSummaryResponse();
        // Store the total rent due amount.
        response.setTotalRentDue(totalDue);
        // Store the total paid amount.
        response.setTotalPaid(totalPaid);
        // Store the total outstanding amount.
        response.setTotalOutstanding(totalOutstanding);
        // Store the number of overdue rent records.
        response.setOverdueItems(overdue);
        // Return the completed financial summary.
        return response;
    }
}
