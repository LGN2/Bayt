// Place cheque creation request objects in the billing request package.
package com.property.billing.request;

// Require text fields to contain a value.
import jakarta.validation.constraints.NotBlank;
// Require fields to be present in the request.
import jakarta.validation.constraints.NotNull;
// Require the amount to be greater than zero.
import jakarta.validation.constraints.Positive;
// Store cheque amounts with decimal precision.
import java.math.BigDecimal;
// Store the cheque date from the request.
import java.time.LocalDate;

// Carry the data needed to create a cheque.
public class CreateChequeRequest {
    // Require the tenant ID.
    @NotNull
    // Store the tenant connected to the cheque.
    private Long tenantId;
    // Require the cheque number.
    @NotBlank
    // Store the cheque number entered by the user.
    private String chequeNumber;
    // Require the bank name.
    @NotBlank
    // Store the bank name entered by the user.
    private String bankName;
    // Require the cheque date.
    @NotNull
    // Store the date written on the cheque.
    private LocalDate chequeDate;
    // Require the cheque amount.
    @NotNull
    // Make sure the cheque amount is positive.
    @Positive
    // Store the amount written on the cheque.
    private BigDecimal amount;

    // Return the tenant ID from the request.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID on the request.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the cheque number from the request.
    public String getChequeNumber() { return chequeNumber; }
    // Set the cheque number on the request.
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }
    // Return the bank name from the request.
    public String getBankName() { return bankName; }
    // Set the bank name on the request.
    public void setBankName(String bankName) { this.bankName = bankName; }
    // Return the cheque date from the request.
    public LocalDate getChequeDate() { return chequeDate; }
    // Set the cheque date on the request.
    public void setChequeDate(LocalDate chequeDate) { this.chequeDate = chequeDate; }
    // Return the requested cheque amount.
    public BigDecimal getAmount() { return amount; }
    // Set the requested cheque amount.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
