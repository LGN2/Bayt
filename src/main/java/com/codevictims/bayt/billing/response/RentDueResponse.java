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

    // Return the rent due ID.
    public Long getId() { return id; }
    // Set the rent due ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the lease ID for this response.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID in the response.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the rent due date.
    public LocalDate getDueDate() { return dueDate; }
    // Set the due date returned to the client.
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    // Return the full rent amount.
    public BigDecimal getAmount() { return amount; }
    // Set the rent amount in the response.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the amount already paid.
    public BigDecimal getPaidAmount() { return paidAmount; }
    // Set the paid amount in the response.
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    // Return the amount still outstanding.
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    // Set the outstanding balance in the response.
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
    // Return the rent payment status text.
    public String getStatus() { return status; }
    // Set the status text in the response.
    public void setStatus(String status) { this.status = status; }
}
