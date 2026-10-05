// Keep rent due entities inside the billing entity package.
package com.property.billing.entity;

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

    public RentDue() {}

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

    public BigDecimal getOutstandingAmount() {
        return amount.subtract(paidAmount);
    }

    public boolean isPaid() {
        return paidAmount.compareTo(amount) >= 0;
    }
}
