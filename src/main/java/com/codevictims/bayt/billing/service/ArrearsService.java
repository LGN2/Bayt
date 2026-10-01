// Place arrears business logic in the billing service package.
package com.property.billing.service;

// Use rent due records for arrears calculations.
import com.property.billing.entity.RentDue;
// Use the repository that reads rent due records.
import com.property.billing.repository.RentDueRepository;
// Use the response object for overdue rent records.
import com.property.billing.response.ArrearsResponse;
// Use the response object for financial totals.
import com.property.billing.response.FinancialSummaryResponse;
// Mark this class as a Spring service.
import org.springframework.stereotype.Service;

// Handle total money values.
import java.math.BigDecimal;
// Compare rent due dates with today's date.
import java.time.LocalDate;
// Calculate how many days a rent record is late.
import java.time.temporal.ChronoUnit;
// Build response lists manually.
import java.util.ArrayList;
// Return lists of arrears responses.
import java.util.List;

// Register this class as a Spring service bean.
@Service
// Handle arrears calculations.
public class ArrearsService {
    // Store the repository used for rent due records.
    private final RentDueRepository rentDueRepository;

    // Receive the rent due repository through constructor injection.
    public ArrearsService(RentDueRepository rentDueRepository) {
        // Keep the repository for later service operations.
        this.rentDueRepository = rentDueRepository;
    }

    // Get all overdue rent records that are not fully paid.
    public List<ArrearsResponse> getArrears() {
        // Prepare a list for arrears responses.
        List<ArrearsResponse> responses = new ArrayList<>();
        // Use today's date to find overdue rent.
        LocalDate today = LocalDate.now();

        // Read rent due records with due dates before today.
        for (RentDue due : rentDueRepository.findByDueDateBeforeOrderByDueDateAsc(today)) {
            // Only include rent records that are still unpaid.
            if (!due.isPaid()) {
                // Create a response for one overdue rent record.
                ArrearsResponse response = new ArrearsResponse();
                // Copy the rent due ID into the response.
                response.setRentDueId(due.getId());
                // Copy the lease ID into the response.
                response.setLeaseId(due.getLeaseId());
                // Copy the original due date into the response.
                response.setDueDate(due.getDueDate());
                // Calculate how many days the rent is late.
                response.setDaysLate(ChronoUnit.DAYS.between(due.getDueDate(), today));
                // Copy the unpaid amount into the response.
                response.setOutstandingAmount(due.getOutstandingAmount());
                // Add the arrears response to the result list.
                responses.add(response);
            }
        }
        // Return all overdue unpaid rent records.
        return responses;
    }

    // Build the financial summary for rent records.
    public FinancialSummaryResponse getSummary() {
        // Start the total rent due at zero.
        BigDecimal totalDue = BigDecimal.ZERO;
        // Start the total paid amount at zero.
        BigDecimal totalPaid = BigDecimal.ZERO;
        // Start the total outstanding amount at zero.
        BigDecimal totalOutstanding = BigDecimal.ZERO;
        // Start the overdue item count at zero.
        int overdue = 0;
        // Use today's date to check overdue records.
        LocalDate today = LocalDate.now();

        // Read every rent due record for summary totals.
        for (RentDue due : rentDueRepository.findAll()) {
            // Add this rent amount to the total due amount.
            totalDue = totalDue.add(due.getAmount());
            // Add this paid amount to the total paid amount.
            totalPaid = totalPaid.add(due.getPaidAmount());
            // Add this outstanding amount to the total outstanding amount.
            totalOutstanding = totalOutstanding.add(due.getOutstandingAmount());
            // Count this record if it is overdue and unpaid.
            if (due.getDueDate().isBefore(today) && !due.isPaid()) {
                // Increase the overdue count.
                overdue++;
            }
        }

        // Create the summary response object.
        FinancialSummaryResponse response = new FinancialSummaryResponse();
        // Store the total rent due in the response.
        response.setTotalRentDue(totalDue);
        // Store the total paid amount in the response.
        response.setTotalPaid(totalPaid);
        // Store the total outstanding amount in the response.
        response.setTotalOutstanding(totalOutstanding);
        // Store the overdue item count in the response.
        response.setOverdueItems(overdue);
        // Return the completed financial summary.
        return response;
    }
}
