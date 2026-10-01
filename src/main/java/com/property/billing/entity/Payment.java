package com.property.billing.entity;

// Use the enum that stores how the payment was made.
import com.property.billing.type.PaymentMethod;
// Use the enum that stores the payment state.
import com.property.billing.type.PaymentStatus;
// Use JPA annotations to map this class to the database.
import jakarta.persistence.*;
// Store money values with decimal precision.
import java.math.BigDecimal;
// Store the payment date and time.
import java.time.LocalDateTime;

// Mark this class as a JPA entity.
@Entity
// Map this entity to the payments table.
@Table(name = "payments")
// Represent one payment record in the system.
public class Payment {
    // Mark this field as the primary key.
    @Id
    // Let the database generate the ID value.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the payment ID.
    private Long id;

    // Require a tenant ID in the database.
    @Column(nullable = false)
    // Store the tenant that made the payment.
    private Long tenantId;

    // Require a lease ID in the database.
    @Column(nullable = false)
    // Store the lease connected to this payment.
    private Long leaseId;

    // Store the amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount paid.
    private BigDecimal amount;

    // Save the enum name as text.
    @Enumerated(EnumType.STRING)
    // Require a payment method in the database.
    @Column(nullable = false)
    // Keep the selected payment method.
    private PaymentMethod method;

    // Save the enum name as text.
    @Enumerated(EnumType.STRING)
    // Require a payment status in the database.
    @Column(nullable = false)
    // Keep the current payment status.
    private PaymentStatus status;

    // Require a payment date in the database.
    @Column(nullable = false)
    // Store when the payment was made.
    private LocalDateTime paymentDate;

    // Store an optional external reference number.
    private String referenceNumber;

    // Provide the empty constructor required by JPA.
    public Payment() {}

    // Return the payment ID.
    public Long getId() { return id; }
    // Set the payment ID.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the lease ID.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the payment amount.
    public BigDecimal getAmount() { return amount; }
    // Set the payment amount.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the payment method.
    public PaymentMethod getMethod() { return method; }
    // Set the payment method.
    public void setMethod(PaymentMethod method) { this.method = method; }
    // Return the payment status.
    public PaymentStatus getStatus() { return status; }
    // Set the payment status.
    public void setStatus(PaymentStatus status) { this.status = status; }
    // Return the payment date.
    public LocalDateTime getPaymentDate() { return paymentDate; }
    // Set the payment date.
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    // Return the reference number.
    public String getReferenceNumber() { return referenceNumber; }
    // Set the reference number.
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
}
