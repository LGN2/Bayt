// Keep arrears response objects inside the billing response package.
package com.property.billing.response;

// Import BigDecimal for outstanding money values.
import java.math.BigDecimal;
// Import LocalDate for the rent due date.
import java.time.LocalDate;

// Send one overdue rent record back to the client.
public class ArrearsResponse {
    // Store the rent due record ID.
    private Long rentDueId;
    // Store the lease linked to the overdue rent.
    private Long leaseId;
    // Store the date when the rent was due.
    private LocalDate dueDate;
    // Store how many days the rent is late.
    private long daysLate;
    // Store the unpaid amount for this rent due record.
    private BigDecimal outstandingAmount;

    public Long getRentDueId() { return rentDueId; }
    public void setRentDueId(Long rentDueId) { this.rentDueId = rentDueId; }
    public Long getLeaseId() { return leaseId; }
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public long getDaysLate() { return daysLate; }
    public void setDaysLate(long daysLate) { this.daysLate = daysLate; }
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
}
