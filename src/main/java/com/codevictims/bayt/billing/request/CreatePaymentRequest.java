// Keep payment request objects inside the billing request package.
package com.codevictims.bayt.billing.request;

// Import the enum used for the selected payment method.
import com.codevictims.bayt.billing.type.PaymentMethod;
// Import validation to require a value.
import jakarta.validation.constraints.NotNull;
// Import validation to require a positive number.
import jakarta.validation.constraints.Positive;
// Import BigDecimal for money amounts.
import java.math.BigDecimal;

// Carry the data needed to create a payment.
public class CreatePaymentRequest {
    // Require the tenant ID in the request.
    @NotNull
    // Store the tenant who made the payment.
    private Long tenantId;
    // Require the lease ID in the request.
    @NotNull
    // Store the lease connected to the payment.
    private Long leaseId;
    // Require the amount in the request.
    @NotNull
    // Make sure the payment amount is greater than zero.
    @Positive
    // Store the amount received for the payment.
    private BigDecimal amount;
    // Require the payment method in the request.
    @NotNull
    // Store how the tenant paid.
    private PaymentMethod method;
    // Store an optional reference number for the payment.
    private String referenceNumber;

    // Return the tenant ID from the request.
    public Long getTenantId() { return tenantId; }
    // Save the tenant ID received from the client.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the lease ID from the request.
    public Long getLeaseId() { return leaseId; }
    // Save the lease ID received from the client.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the requested payment amount.
    public BigDecimal getAmount() { return amount; }
    // Save the payment amount from the request.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the selected payment method.
    public PaymentMethod getMethod() { return method; }
    // Save the payment method from the request.
    public void setMethod(PaymentMethod method) { this.method = method; }
    // Return the optional payment reference number.
    public String getReferenceNumber() { return referenceNumber; }
    // Save the reference number sent by the client.
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
}
