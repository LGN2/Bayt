// Keep financial summary responses inside the billing response package.
package com.codevictims.bayt.billing.response;

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

    // Return the total rent due amount.
    public BigDecimal getTotalRentDue() { return totalRentDue; }
    // Set the total rent due amount.
    public void setTotalRentDue(BigDecimal totalRentDue) { this.totalRentDue = totalRentDue; }
    // Return the total paid amount.
    public BigDecimal getTotalPaid() { return totalPaid; }
    // Set the total paid amount.
    public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid = totalPaid; }
    // Return the total outstanding amount.
    public BigDecimal getTotalOutstanding() { return totalOutstanding; }
    // Set the total outstanding amount.
    public void setTotalOutstanding(BigDecimal totalOutstanding) { this.totalOutstanding = totalOutstanding; }
    // Return the number of overdue items.
    public int getOverdueItems() { return overdueItems; }
    // Set the number of overdue rent records.
    public void setOverdueItems(int overdueItems) { this.overdueItems = overdueItems; }
}
