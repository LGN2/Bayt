// Keep cheque creation requests inside the billing request package.
package com.codevictims.bayt.billing.request;

// Import validation to require text values.
import jakarta.validation.constraints.NotBlank;
// Import validation to require a value.
import jakarta.validation.constraints.NotNull;
// Import validation to require a positive amount.
import jakarta.validation.constraints.Positive;
// Import BigDecimal for cheque amount values.
import java.math.BigDecimal;
// Import LocalDate for the cheque date.
import java.time.LocalDate;

// Carry the data needed to create a cheque.
public class CreateChequeRequest {
    // Require the tenant ID in the request.
    @NotNull
    // Store the tenant connected to the cheque.
    private Long tenantId;
    // Require a cheque number in the request.
    @NotBlank
    // Store the cheque number from the client.
    private String chequeNumber;
    // Require the bank name in the request.
    @NotBlank
    // Store the bank that issued the cheque.
    private String bankName;
    // Require the cheque date in the request.
    @NotNull
    // Store the date written on the cheque.
    private LocalDate chequeDate;
    // Require the cheque amount in the request.
    @NotNull
    // Make sure the cheque amount is greater than zero.
    @Positive
    // Store the amount written on the cheque.
    private BigDecimal amount;

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public LocalDate getChequeDate() { return chequeDate; }
    public void setChequeDate(LocalDate chequeDate) { this.chequeDate = chequeDate; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
