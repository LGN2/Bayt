// Keep allocation response objects inside the billing response package.
package com.codevictims.bayt.billing.response;

// Import BigDecimal for returned money values.
import java.math.BigDecimal;

// Send payment allocation details back to the client.
public class PaymentAllocationResponse {
    // Store the allocation ID returned in the response.
    private Long id;
    // Store the payment ID linked to this allocation.
    private Long paymentId;
    // Store the rent due ID linked to this allocation.
    private Long rentDueId;
    // Store the amount assigned to the rent due record.
    private BigDecimal allocatedAmount;

    // Return the allocation ID.
    public Long getId() { return id; }
    // Set the allocation ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the payment ID for this allocation.
    public Long getPaymentId() { return paymentId; }
    // Set the payment ID in the response.
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    // Return the rent due ID for this allocation.
    public Long getRentDueId() { return rentDueId; }
    // Set the rent due ID in the response.
    public void setRentDueId(Long rentDueId) { this.rentDueId = rentDueId; }
    // Return the allocated amount.
    public BigDecimal getAllocatedAmount() { return allocatedAmount; }
    // Set the allocated amount returned to the client.
    public void setAllocatedAmount(BigDecimal allocatedAmount) { this.allocatedAmount = allocatedAmount; }
}
