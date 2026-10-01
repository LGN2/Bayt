// Place cheque response objects in the billing response package.
package com.property.billing.response;

// Include the cheque status in the API response.
import com.property.billing.type.ChequeStatus;
// Return cheque amounts with decimal precision.
import java.math.BigDecimal;
// Return the cheque date.
import java.time.LocalDate;

// Send cheque details back to the API client.
public class ChequeResponse {
    // Store the cheque ID returned to the client.
    private Long id;
    // Store the tenant ID returned to the client.
    private Long tenantId;
    // Store the cheque number returned to the client.
    private String chequeNumber;
    // Store the bank name returned to the client.
    private String bankName;
    // Store the cheque date returned to the client.
    private LocalDate chequeDate;
    // Store the cheque amount returned to the client.
    private BigDecimal amount;
    // Store the cheque status returned to the client.
    private ChequeStatus status;

    // Return the cheque ID.
    public Long getId() { return id; }
    // Set the cheque ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID.
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
    // Return the cheque status.
    public ChequeStatus getStatus() { return status; }
    // Set the cheque status in the response.
    public void setStatus(ChequeStatus status) { this.status = status; }
}
