package com.property.billing.request;

import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class UpdateDepositRequest {
    @PositiveOrZero
    private BigDecimal deductionAmount;
    @PositiveOrZero
    private BigDecimal refundedAmount;
    private String note;

    public BigDecimal getDeductionAmount() { return deductionAmount; }
    public void setDeductionAmount(BigDecimal deductionAmount) { this.deductionAmount = deductionAmount; }
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    public void setRefundedAmount(BigDecimal refundedAmount) { this.refundedAmount = refundedAmount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
