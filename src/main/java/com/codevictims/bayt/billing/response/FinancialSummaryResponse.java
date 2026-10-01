// Place financial summary responses in the billing response package.
package com.property.billing.response;

// Return summary money values with decimal precision.
import java.math.BigDecimal;

// Send overall billing totals back to the API client.
public class FinancialSummaryResponse {
    // Store the total rent amount due.
    private BigDecimal totalRentDue;
    // Store the total amount already paid.
    private BigDecimal totalPaid;
    // Store the total amount still outstanding.
    private BigDecimal totalOutstanding;
    // Store the number of overdue rent records.
    private int overdueItems;

    // Return the total rent due.
    public BigDecimal getTotalRentDue() { return totalRentDue; }
    // Set the total rent due in the response.
    public void setTotalRentDue(BigDecimal totalRentDue) { this.totalRentDue = totalRentDue; }
    // Return the total amount paid.
    public BigDecimal getTotalPaid() { return totalPaid; }
    // Set the total amount paid in the response.
    public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid = totalPaid; }
    // Return the total outstanding amount.
    public BigDecimal getTotalOutstanding() { return totalOutstanding; }
    // Set the total outstanding amount in the response.
    public void setTotalOutstanding(BigDecimal totalOutstanding) { this.totalOutstanding = totalOutstanding; }
    // Return the count of overdue items.
    public int getOverdueItems() { return overdueItems; }
    // Set the count of overdue items.
    public void setOverdueItems(int overdueItems) { this.overdueItems = overdueItems; }
}
