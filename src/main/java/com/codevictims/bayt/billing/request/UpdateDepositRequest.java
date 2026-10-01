// Place deposit update request objects in the billing request package.
package com.property.billing.request;

// Allow only zero or positive money values.
import jakarta.validation.constraints.PositiveOrZero;
// Store update amounts with decimal precision.
import java.math.BigDecimal;

// Carry deposit update values from the client.
public class UpdateDepositRequest {
    // Validate that the deduction is not negative.
    @PositiveOrZero
    // Store the new deduction amount.
    private BigDecimal deductionAmount;
    // Validate that the refund is not negative.
    @PositiveOrZero
    // Store the new refunded amount.
    private BigDecimal refundedAmount;
    // Store an optional note for the update.
    private String note;

    // Return the deduction amount from the request.
    public BigDecimal getDeductionAmount() { return deductionAmount; }
    // Set the deduction amount on the request.
    public void setDeductionAmount(BigDecimal deductionAmount) { this.deductionAmount = deductionAmount; }
    // Return the refunded amount from the request.
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    // Set the refunded amount on the request.
    public void setRefundedAmount(BigDecimal refundedAmount) { this.refundedAmount = refundedAmount; }
    // Return the update note.
    public String getNote() { return note; }
    // Set the update note.
    public void setNote(String note) { this.note = note; }
}
