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

    // Return the allocation ID.
    public Long getId() { return id; }
    // Set the allocation ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the linked payment ID.
    public Long getPaymentId() { return paymentId; }
    // Set the linked payment ID in the response.
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    // Return the linked rent due ID.
    public Long getRentDueId() { return rentDueId; }
    // Set the linked rent due ID in the response.
    public void setRentDueId(Long rentDueId) { this.rentDueId = rentDueId; }
    // Return the allocated amount.
    public BigDecimal getAllocatedAmount() { return allocatedAmount; }
    // Set the allocated amount in the response.
    public void setAllocatedAmount(BigDecimal allocatedAmount) { this.allocatedAmount = allocatedAmount; }
}
