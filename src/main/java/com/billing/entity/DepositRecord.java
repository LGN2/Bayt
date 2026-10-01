package com.property.billing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deposit_records")
public class DepositRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tenantId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal heldAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal deductionAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal refundedAmount = BigDecimal.ZERO;

    private LocalDate refundDate;

    private String note;

    public DepositRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public BigDecimal getHeldAmount() { return heldAmount; }
    public void setHeldAmount(BigDecimal heldAmount) { this.heldAmount = heldAmount; }
    public BigDecimal getDeductionAmount() { return deductionAmount; }
    public void setDeductionAmount(BigDecimal deductionAmount) { this.deductionAmount = deductionAmount; }
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    public void setRefundedAmount(BigDecimal refundedAmount) { this.refundedAmount = refundedAmount; }
    public LocalDate getRefundDate() { return refundDate; }
    public void setRefundDate(LocalDate refundDate) { this.refundDate = refundDate; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public BigDecimal getRemainingAmount() {
        return heldAmount.subtract(deductionAmount).subtract(refundedAmount);
    }
}
