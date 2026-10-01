// Place deposit record entities in the billing entity package.
package com.property.billing.entity;

// Use JPA annotations to map this class to the database.
import jakarta.persistence.*;
// Store deposit money values with decimal precision.
import java.math.BigDecimal;
// Store the date when a refund happens.
import java.time.LocalDate;

// Mark this class as a JPA entity.
@Entity
// Connect this entity to the deposit_records table.
@Table(name = "deposit_records")
// Represent one deposit record for a tenant.
public class DepositRecord {
    // Mark this field as the primary key.
    @Id
    // Generate the deposit ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the deposit record ID.
    private Long id;

    // Require a tenant ID in the database.
    @Column(nullable = false)
    // Store the tenant linked to this deposit.
    private Long tenantId;

    // Store the held amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount currently held as deposit.
    private BigDecimal heldAmount;

    // Store the deduction amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount deducted from the deposit.
    private BigDecimal deductionAmount = BigDecimal.ZERO;

    // Store the refunded amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount already refunded.
    private BigDecimal refundedAmount = BigDecimal.ZERO;

    // Store the date when a refund was made.
    private LocalDate refundDate;

    // Store an optional note about the deposit.
    private String note;

    // Provide the empty constructor required by JPA.
    public DepositRecord() {}

    // Return the deposit record ID.
    public Long getId() { return id; }
    // Set the deposit record ID.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the held deposit amount.
    public BigDecimal getHeldAmount() { return heldAmount; }
    // Set the held deposit amount.
    public void setHeldAmount(BigDecimal heldAmount) { this.heldAmount = heldAmount; }
    // Return the deducted amount.
    public BigDecimal getDeductionAmount() { return deductionAmount; }
    // Set the deducted amount.
    public void setDeductionAmount(BigDecimal deductionAmount) { this.deductionAmount = deductionAmount; }
    // Return the refunded amount.
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    // Set the refunded amount.
    public void setRefundedAmount(BigDecimal refundedAmount) { this.refundedAmount = refundedAmount; }
    // Return the refund date.
    public LocalDate getRefundDate() { return refundDate; }
    // Set the refund date.
    public void setRefundDate(LocalDate refundDate) { this.refundDate = refundDate; }
    // Return the deposit note.
    public String getNote() { return note; }
    // Set the deposit note.
    public void setNote(String note) { this.note = note; }

    // Calculate the deposit amount still available.
    public BigDecimal getRemainingAmount() {
        // Subtract deductions and refunds from the held amount.
        return heldAmount.subtract(deductionAmount).subtract(refundedAmount);
    }
}
