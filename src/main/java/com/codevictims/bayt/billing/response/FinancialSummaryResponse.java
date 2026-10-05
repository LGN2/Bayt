package com.property.billing.response;

import java.math.BigDecimal;

public class FinancialSummaryResponse {
    private BigDecimal totalRentDue;
    private BigDecimal totalPaid;
    private BigDecimal totalOutstanding;
    private int overdueItems;

    public BigDecimal getTotalRentDue() { return totalRentDue; }
    public void setTotalRentDue(BigDecimal totalRentDue) { this.totalRentDue = totalRentDue; }
    public BigDecimal getTotalPaid() { return totalPaid; }
    public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid = totalPaid; }
    public BigDecimal getTotalOutstanding() { return totalOutstanding; }
    public void setTotalOutstanding(BigDecimal totalOutstanding) { this.totalOutstanding = totalOutstanding; }
    public int getOverdueItems() { return overdueItems; }
    public void setOverdueItems(int overdueItems) { this.overdueItems = overdueItems; }
}
