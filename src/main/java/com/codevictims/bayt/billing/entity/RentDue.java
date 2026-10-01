// Place rent due entities in the billing entity package.
package com.property.billing.entity;

// Use JPA annotations to map this class to the database.
import jakarta.persistence.*;
// Store rent amounts with decimal precision.
import java.math.BigDecimal;
// Store the due date for rent.
import java.time.LocalDate;

// Mark this class as a JPA entity.
@Entity
// Connect this entity to the rent_dues table.
@Table(name = "rent_dues")
// Represent one rent amount that is due.
public class RentDue {
    // Mark this field as the primary key.
    @Id
    // Generate the rent due ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the rent due record ID.
    private Long id;

    // Require a lease ID in the database.
    @Column(nullable = false)
    // Store the lease linked to this rent due.
    private Long leaseId;

    // Require the rent due date in the database.
    @Column(nullable = false)
    // Keep the date when the rent becomes due.
    private LocalDate dueDate;

    // Store the rent amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the full rent amount due.
    private BigDecimal amount;

    // Store the paid amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount already paid.
    private BigDecimal paidAmount = BigDecimal.ZERO;

    // Provide the empty constructor required by JPA.
    public RentDue() {}

    // Return the rent due ID.
    public Long getId() { return id; }
    // Set the rent due ID.
    public void setId(Long id) { this.id = id; }
    // Return the lease ID.
    public Long getLeaseId() { return leaseId; }
    // Set the lease ID.
    public void setLeaseId(Long leaseId) { this.leaseId = leaseId; }
    // Return the rent due date.
    public LocalDate getDueDate() { return dueDate; }
    // Set the rent due date.
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    // Return the full rent amount.
    public BigDecimal getAmount() { return amount; }
    // Set the full rent amount.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the amount already paid.
    public BigDecimal getPaidAmount() { return paidAmount; }
    // Set the amount already paid.
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }

    // Calculate how much rent is still unpaid.
    public BigDecimal getOutstandingAmount() {
        // Subtract the paid amount from the full rent amount.
        return amount.subtract(paidAmount);
    }

    // Check whether this rent due is fully paid.
    public boolean isPaid() {
        // Treat the rent as paid when paid amount reaches the full amount.
        return paidAmount.compareTo(amount) >= 0;
    }
}
