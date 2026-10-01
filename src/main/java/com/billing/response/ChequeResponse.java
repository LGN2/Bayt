package com.property.billing.response;

import com.property.billing.type.ChequeStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ChequeResponse {
    private Long id;
    private Long tenantId;
    private String chequeNumber;
    private String bankName;
    private LocalDate chequeDate;
    private BigDecimal amount;
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
