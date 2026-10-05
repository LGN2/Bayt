// Keep payment allocation entities inside the billing entity package.
package com.codevictims.bayt.billing.entity;

// Import JPA annotations used to map this entity.
import jakarta.persistence.*;
// Import BigDecimal for money values.
import java.math.BigDecimal;

// Mark this class as a database entity.
@Entity
// Store allocation rows in the payment_allocations table.
@Table(name = "payment_allocations")
// Represent one payment allocation record.
public class PaymentAllocation {
    // Mark this field as the primary key.
    @Id
    // Generate the allocation ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the allocation ID.
    private Long id;

    // Link many allocations to one payment.
    @ManyToOne(optional = false)
    // Store the payment foreign key column.
    @JoinColumn(name = "payment_id")
    // Keep the payment connected to this allocation.
    private Payment payment;

    // Link many allocations to one rent due record.
    @ManyToOne(optional = false)
    // Store the rent due foreign key column.
    @JoinColumn(name = "rent_due_id")
    // Keep the rent due record that receives this amount.
    private RentDue rentDue;

    // Store the allocated amount with two decimal places.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount assigned to the rent due record.
    private BigDecimal allocatedAmount;

    // Create an empty allocation object for JPA.
    public PaymentAllocation() {}

    // Return the allocation ID.
    public Long getId() { return id; }
    // Set the allocation ID.
    public void setId(Long id) { this.id = id; }
    // Return the payment linked to this allocation.
    public Payment getPayment() { return payment; }
    // Set the payment for this allocation.
    public void setPayment(Payment payment) { this.payment = payment; }
    // Return the rent due record for this allocation.
    public RentDue getRentDue() { return rentDue; }
    // Set the rent due record that receives the amount.
    public void setRentDue(RentDue rentDue) { this.rentDue = rentDue; }
    // Return the amount assigned to the rent due record.
    public BigDecimal getAllocatedAmount() { return allocatedAmount; }
    // Set the amount assigned by this allocation.
    public void setAllocatedAmount(BigDecimal allocatedAmount) { this.allocatedAmount = allocatedAmount; }
}
