// Keep rent due entities inside the billing entity package.
package com.codevictims.bayt.billing.entity;

// Import JPA annotations used to map this class to the database.
import jakarta.persistence.*;
// Import BigDecimal for rent money values.
import java.math.BigDecimal;
// Import LocalDate for the rent due date.
import java.time.LocalDate;

// Mark this class as a database entity.
@Entity
// Store rent due rows in the rent_dues table.
@Table(name = "rent_dues")
// Represent one rent due record.
public class RentDue {
    // Mark this field as the primary key.
    @Id
    // Generate the rent due ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the rent due ID.
    private Long id;

    // Require a lease ID in the database.
    @Column(nullable = false)
    // Store the lease connected to this rent due record.
    private Long leaseId;

    // Require the due date in the database.
    @Column(nullable = false)
    // Save the date when the rent becomes due.
    private LocalDate dueDate;

    // Store the rent amount with two decimal places.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount that should be paid.
    private BigDecimal amount;

    // Store the paid amount with two decimal places.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount already paid for this rent due record.
    private BigDecimal paidAmount = BigDecimal.ZERO;

    // Create an empty rent due object for JPA.
    public RentDue() {}

    // Return the rent due ID.
    public Long getId() { return id; }
    // Set the rent due ID.
    public void setId(Long id) { this.id = id; }
    // Return the lease ID for this rent due record.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the date when rent is due.
    public LocalDate getDueDate() { return dueDate; }
    // Set the due date.
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    // Return the full rent amount.
    public BigDecimal getAmount() { return amount; }
    // Set the rent amount.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the amount already paid.
    public BigDecimal getPaidAmount() { return paidAmount; }
    // Set the amount already paid.
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }

    // Calculate the rent amount still unpaid.
    public BigDecimal getOutstandingAmount() {
        // Subtract paid rent from the full rent amount.
        return amount.subtract(paidAmount);
    }

    // Check whether the rent has been fully paid.
    public boolean isPaid() {
        // Compare the paid amount with the full rent amount.
        return paidAmount.compareTo(amount) >= 0;
    }
}
