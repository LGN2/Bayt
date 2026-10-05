// Keep payment entities inside the billing entity package.
package com.codevictims.bayt.billing.entity;

// Import the enum that stores how the payment was made.
import com.codevictims.bayt.billing.type.PaymentMethod;
// Import the enum that stores the payment state.
import com.codevictims.bayt.billing.type.PaymentStatus;
// Import JPA annotations used to map this class to the database.
import jakarta.persistence.*;
// Import BigDecimal for money values.
import java.math.BigDecimal;
// Import LocalDateTime for the payment date and time.
import java.time.LocalDateTime;

// Mark this class as a database entity.
@Entity
// Store payment rows in the payments table.
@Table(name = "payments")
// Represent one payment record.
public class Payment {
    // Mark this field as the primary key.
    @Id
    // Generate the payment ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the payment ID.
    private Long id;

    // Require a tenant ID in the database.
    @Column(nullable = false)
    // Store the tenant who made the payment.
    private Long tenantId;

    // Require a lease ID in the database.
    @Column(nullable = false)
    // Store the lease connected to this payment.
    private Long leaseId;

    // Store the amount with two decimal places.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount received for this payment.
    private BigDecimal amount;

    // Save the payment method enum as text.
    @Enumerated(EnumType.STRING)
    // Require a payment method in the database.
    @Column(nullable = false)
    // Store the selected way of paying.
    private PaymentMethod method;

    // Save the payment status enum as text.
    @Enumerated(EnumType.STRING)
    // Require a payment status in the database.
    @Column(nullable = false)
    // Track the current payment status.
    private PaymentStatus status;

    // Require the payment date in the database.
    @Column(nullable = false)
    // Keep the date and time when the payment was recorded.
    private LocalDateTime paymentDate;

    // Store an optional external reference for the payment.
    private String referenceNumber;

    // Create an empty payment object for JPA.
    public Payment() {}

    // Return the payment ID.
    public Long getId() { return id; }
    // Set the payment ID.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID for this payment.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the lease ID for this payment.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID.
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
