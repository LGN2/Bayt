package com.property.billing.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DepositResponse {
    private Long id;
    private Long tenantId;
    private BigDecimal heldAmount;
    private BigDecimal deductionAmount;
    private BigDecimal refundedAmount;
    private BigDecimal remainingAmount;
    private LocalDate refundDate;
    private String note;

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
    public BigDecimal getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(BigDecimal remainingAmount) { this.remainingAmount = remainingAmount; }
    public LocalDate getRefundDate() { return refundDate; }
    public void setRefundDate(LocalDate refundDate) { this.refundDate = refundDate; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
