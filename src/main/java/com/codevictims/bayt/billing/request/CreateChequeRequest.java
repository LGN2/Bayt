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

    // Return the tenant ID from the request.
    public Long getTenantId() { return tenantId; }
    // Save the tenant ID received from the client.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the submitted cheque number.
    public String getChequeNumber() { return chequeNumber; }
    // Save the cheque number from the request.
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }
    // Return the bank name from the request.
    public String getBankName() { return bankName; }
    // Save the bank name received from the client.
    public void setBankName(String bankName) { this.bankName = bankName; }
    // Return the cheque date from the request.
    public LocalDate getChequeDate() { return chequeDate; }
    // Save the date written on the cheque.
    public void setChequeDate(LocalDate chequeDate) { this.chequeDate = chequeDate; }
    // Return the requested cheque amount.
    public BigDecimal getAmount() { return amount; }
    // Save the cheque amount from the request.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
