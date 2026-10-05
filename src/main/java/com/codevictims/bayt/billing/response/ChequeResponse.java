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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public ChequeStatus getStatus() { return status; }
    public void setStatus(ChequeStatus status) { this.status = status; }
}
