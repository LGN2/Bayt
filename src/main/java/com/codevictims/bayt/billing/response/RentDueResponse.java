package com.property.billing.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentDueResponse {
    private Long id;
    private Long leaseId;
    private LocalDate dueDate;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private BigDecimal outstandingAmount;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLeaseId() { return leaseId; }
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
