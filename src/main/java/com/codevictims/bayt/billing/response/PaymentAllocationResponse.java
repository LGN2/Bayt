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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public Long getRentDueId() { return rentDueId; }
    public void setRentDueId(Long rentDueId) { this.rentDueId = rentDueId; }
    public BigDecimal getAllocatedAmount() { return allocatedAmount; }
    public void setAllocatedAmount(BigDecimal allocatedAmount) { this.allocatedAmount = allocatedAmount; }
}
