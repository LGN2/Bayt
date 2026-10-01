// Place allocation response objects in the billing response package.
package com.property.billing.response;

// Return allocation amounts with decimal precision.
import java.math.BigDecimal;

// Send payment allocation details back to the API client.
public class PaymentAllocationResponse {
    // Store the allocation ID returned to the client.
    private Long id;
    // Store the payment ID returned to the client.
    private Long paymentId;
    // Store the rent due ID returned to the client.
    private Long rentDueId;
    // Store the amount allocated to the rent due.
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
