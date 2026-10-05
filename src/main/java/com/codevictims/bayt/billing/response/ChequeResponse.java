// Keep cheque response objects inside the billing response package.
package com.codevictims.bayt.billing.response;

// Import the cheque status returned to clients.
import com.codevictims.bayt.billing.type.ChequeStatus;
// Import BigDecimal for returned money values.
import java.math.BigDecimal;
// Import LocalDate for the returned cheque date.
import java.time.LocalDate;

// Send cheque details back to the client.
public class ChequeResponse {
    // Store the cheque ID returned in the response.
    private Long id;
    // Store the tenant ID connected to the cheque.
    private Long tenantId;
    // Store the cheque number shown to the client.
    private String chequeNumber;
    // Store the bank name shown in the response.
    private String bankName;
    // Store the date written on the cheque.
    private LocalDate chequeDate;
    // Store the cheque amount returned to the client.
    private BigDecimal amount;
    // Store the current cheque status.
    private ChequeStatus status;

    // Return the cheque ID.
    public Long getId() { return id; }
    // Set the cheque ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID for this cheque.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID in the response.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the cheque number.
    public String getChequeNumber() { return chequeNumber; }
    // Set the cheque number in the response.
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }
    // Return the bank name.
    public String getBankName() { return bankName; }
    // Set the bank name in the response.
    public void setBankName(String bankName) { this.bankName = bankName; }
    // Return the cheque date.
    public LocalDate getChequeDate() { return chequeDate; }
    // Set the cheque date in the response.
    public void setChequeDate(LocalDate chequeDate) { this.chequeDate = chequeDate; }
    // Return the cheque amount.
    public BigDecimal getAmount() { return amount; }
    // Set the cheque amount in the response.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the current cheque status.
    public ChequeStatus getStatus() { return status; }
    // Set the cheque status in the response.
    public void setStatus(ChequeStatus status) { this.status = status; }
}
