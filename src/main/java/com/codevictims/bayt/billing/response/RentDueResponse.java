// Keep rent due response objects inside the billing response package.
package com.property.billing.response;

// Import BigDecimal for returned money values.
import java.math.BigDecimal;
// Import LocalDate for the returned due date.
import java.time.LocalDate;

// Send rent due details back to the client.
public class RentDueResponse {
    // Store the rent due ID returned in the response.
    private Long id;
    // Store the lease ID connected to the rent due.
    private Long leaseId;
    // Store the date when the rent is due.
    private LocalDate dueDate;
    // Store the full rent amount.
    private BigDecimal amount;
    // Store the amount already paid.
    private BigDecimal paidAmount;
    // Store the amount still unpaid.
    private BigDecimal outstandingAmount;
    // Store the paid or unpaid status text.
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLeaseId() { return leaseId; }
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
