// Place deposit response objects in the billing response package.
package com.property.billing.response;

// Return deposit amounts with decimal precision.
import java.math.BigDecimal;
// Return refund dates to the client.
import java.time.LocalDate;

// Send deposit details back to the API client.
public class DepositResponse {
    // Store the deposit ID returned to the client.
    private Long id;
    // Store the tenant ID returned to the client.
    private Long tenantId;
    // Store the held amount returned to the client.
    private BigDecimal heldAmount;
    // Store the deducted amount returned to the client.
    private BigDecimal deductionAmount;
    // Store the refunded amount returned to the client.
    private BigDecimal refundedAmount;
    // Store the remaining amount returned to the client.
    private BigDecimal remainingAmount;
    // Store the refund date returned to the client.
    private LocalDate refundDate;
    // Store the note returned to the client.
    private String note;

    // Return the deposit ID.
    public Long getId() {
        return id; }
    // Set the deposit ID in the response.
    public void setId(Long id) {
        this.id = id; }
    // Return the tenant ID.
    public Long getTenantId() {
        return tenantId; }
    // Set the tenant ID in the response.
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId; }
    // Return the held amount.
    public BigDecimal getHeldAmount() {
        return heldAmount; }
    // Set the held amount in the response.
    public void setHeldAmount(BigDecimal heldAmount) {
        this.heldAmount = heldAmount; }
    // Return the deducted amount.
    public BigDecimal getDeductionAmount() {
        return deductionAmount; }
    // Set the deducted amount in the response.
    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount; }
    // Return the refunded amount.
    public BigDecimal getRefundedAmount() {
        return refundedAmount; }
    // Set the refunded amount in the response.
    public void setRefundedAmount(BigDecimal refundedAmount) {
        this.refundedAmount = refundedAmount; }
    // Return the remaining amount.
    public BigDecimal getRemainingAmount() {
        return remainingAmount; }
    // Set the remaining amount in the response.
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount; }
    // Return the refund date.
    public LocalDate getRefundDate() {
        return refundDate; }
    // Set the refund date in the response.
    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate; }
    // Return the deposit note.
    public String getNote() {
        return note; }
    // Set the deposit note in the response.
    public void setNote(String note) {
        this.note = note; }
}
