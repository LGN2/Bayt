// Place arrears response objects in the billing response package.
package com.property.billing.response;

// Return unpaid money values with decimal precision.
import java.math.BigDecimal;
// Return the original rent due date.
import java.time.LocalDate;

// Send one overdue rent record back to the API client.
public class ArrearsResponse {
    // Store the rent due ID linked to this arrears record.
    private Long rentDueId;
    // Store the lease ID linked to this arrears record.
    private Long leaseId;
    // Store the date when the rent was due.
    private LocalDate dueDate;
    // Store how many days the rent is late.
    private long daysLate;
    // Store the amount that is still unpaid.
    private BigDecimal outstandingAmount;

    // Return the rent due ID.
    public Long getRentDueId() { return rentDueId; }
    // Set the rent due ID in the response.
    public void setRentDueId(Long rentDueId) { this.rentDueId = rentDueId; }
    // Return the lease ID.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID in the response.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the original due date.
    public LocalDate getDueDate() { return dueDate; }
    // Set the original due date in the response.
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    // Return the number of late days.
    public long getDaysLate() { return daysLate; }
    // Set the number of late days.
    public void setDaysLate(long daysLate) { this.daysLate = daysLate; }
    // Return the unpaid amount.
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    // Set the unpaid amount in the response.
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
}
