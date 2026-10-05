// Keep payment response objects inside the billing response package.
package com.codevictims.bayt.billing.response;

// Import the payment method returned to the client.
import com.codevictims.bayt.billing.type.PaymentMethod;
// Import the payment status returned to the client.
import com.codevictims.bayt.billing.type.PaymentStatus;
// Import BigDecimal for returned money values.
import java.math.BigDecimal;
// Import LocalDateTime for the payment date value.
import java.time.LocalDateTime;

// Send payment details back to the client.
public class PaymentResponse {
    // Store the payment ID returned in the response.
    private Long id;
    // Store the tenant ID connected to the payment.
    private Long tenantId;
    // Store the lease ID connected to the payment.
    private Long leaseId;
    // Store the amount paid.
    private BigDecimal amount;
    // Store the payment method shown in the response.
    private PaymentMethod method;
    // Store the current payment status.
    private PaymentStatus status;
    // Store the date and time when the payment was recorded.
    private LocalDateTime paymentDate;
    // Store the optional reference number returned to the client.
    private String referenceNumber;

    // Return the payment ID.
    public Long getId() { return id; }
    // Set the payment ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID for this payment.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID in the response.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the lease ID for this payment.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID in the response.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the amount paid.
    public BigDecimal getAmount() { return amount; }
    // Set the amount shown to the client.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the payment method.
    public PaymentMethod getMethod() { return method; }
    // Set the payment method in the response.
    public void setMethod(PaymentMethod method) { this.method = method; }
    // Return the payment status.
    public PaymentStatus getStatus() { return status; }
    // Set the status shown to the client.
    public void setStatus(PaymentStatus status) { this.status = status; }
    // Return the recorded payment date.
    public LocalDateTime getPaymentDate() { return paymentDate; }
    // Set the payment date in the response.
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    // Return the payment reference number.
    public String getReferenceNumber() { return referenceNumber; }
    // Set the reference number in the response.
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
}
