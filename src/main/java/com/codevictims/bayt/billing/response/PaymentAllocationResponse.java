package com.codevictims.bayt.billing.response;

import java.math.BigDecimal;

public class PaymentAllocationResponse {
    private Long id;
    private Long paymentId;
    private Long rentDueId;
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
