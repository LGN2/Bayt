package com.property.billing.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ArrearsResponse {
    private Long rentDueId;
    private Long leaseId;
    private LocalDate dueDate;
    private long daysLate;
    private BigDecimal outstandingAmount;

    public Long getRentDueId() { return rentDueId; }
    public void setRentDueId(Long rentDueId) { this.rentDueId = rentDueId; }
    public Long getLeaseId() { return leaseId; }
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public long getDaysLate() { return daysLate; }
    public void setDaysLate(long daysLate) { this.daysLate = daysLate; }
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
}
