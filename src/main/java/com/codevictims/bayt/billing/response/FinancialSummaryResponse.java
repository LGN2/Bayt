// Keep financial summary responses inside the billing response package.
package com.property.billing.response;

// Import BigDecimal for financial totals.
import java.math.BigDecimal;

// Send overall billing totals back to the client.
public class FinancialSummaryResponse {
    // Store the total rent amount due.
    private BigDecimal totalRentDue;
    // Store the total amount already paid.
    private BigDecimal totalPaid;
    // Store the total amount still outstanding.
    private BigDecimal totalOutstanding;
    // Store how many rent due records are overdue.
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
