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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public Long getLeaseId() { return leaseId; }
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
}
