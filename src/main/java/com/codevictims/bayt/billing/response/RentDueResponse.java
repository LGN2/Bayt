// Place rent due response objects in the billing response package.
package com.property.billing.response;

// Return rent amounts with decimal precision.
import java.math.BigDecimal;
// Return the rent due date.
import java.time.LocalDate;

// Send rent due details back to the API client.
public class RentDueResponse {
    // Store the rent due ID returned to the client.
    private Long id;
    // Store the lease ID returned to the client.
    private Long leaseId;
    // Store the due date returned to the client.
    private LocalDate dueDate;
    // Store the full rent amount returned to the client.
    private BigDecimal amount;
    // Store the paid amount returned to the client.
    private BigDecimal paidAmount;
    // Store the outstanding amount returned to the client.
    private BigDecimal outstandingAmount;
    // Store the payment status returned to the client.
    private String status;

    // Return the rent due ID.
    public Long getId() { return id; }
    // Set the rent due ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the lease ID.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID in the response.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the due date.
    public LocalDate getDueDate() { return dueDate; }
    // Set the due date in the response.
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    // Return the full rent amount.
    public BigDecimal getAmount() { return amount; }
    // Set the full rent amount in the response.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the amount already paid.
    public BigDecimal getPaidAmount() { return paidAmount; }
    // Set the paid amount in the response.
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    // Return the outstanding amount.
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    // Set the outstanding amount in the response.
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
    // Return the rent payment status.
    public String getStatus() { return status; }
    // Set the rent payment status in the response.
    public void setStatus(String status) { this.status = status; }
}
