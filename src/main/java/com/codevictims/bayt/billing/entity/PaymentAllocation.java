// Place the allocation entity in the billing entity package.
package com.property.billing.entity;

// Use JPA annotations to map allocation records.
import jakarta.persistence.*;
// Store allocation amounts with decimal precision.
import java.math.BigDecimal;

// Mark this class as a database entity.
@Entity
// Connect this entity to the payment_allocations table.
@Table(name = "payment_allocations")
// Represent one amount assigned from a payment to a rent due.
public class PaymentAllocation {
    // Mark this field as the primary key.
    @Id
    // Generate the allocation ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the allocation ID.
    private Long id;

    // Link each allocation to one payment.
    @ManyToOne(optional = false)
    // Store the payment foreign key in payment_id.
    @JoinColumn(name = "payment_id")
    // Keep the payment connected to this allocation.
    private Payment payment;

    // Link each allocation to one rent due record.
    @ManyToOne(optional = false)
    // Store the rent due foreign key in rent_due_id.
    @JoinColumn(name = "rent_due_id")
    // Keep the rent due record connected to this allocation.
    private RentDue rentDue;

    // Store the allocated amount with fixed precision and scale.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount assigned to the rent due.
    private BigDecimal allocatedAmount;

    // Provide the empty constructor required by JPA.
    public PaymentAllocation() {}

    // Return the allocation ID.
    public Long getId() { return id; }
    // Set the allocation ID.
    public void setId(Long id) { this.id = id; }
    // Return the linked payment.
    public Payment getPayment() { return payment; }
    // Set the payment linked to this allocation.
    public void setPayment(Payment payment) { this.payment = payment; }
    // Return the linked rent due record.
    public RentDue getRentDue() { return rentDue; }
    // Set the rent due record linked to this allocation.
    public void setRentDue(RentDue rentDue) { this.rentDue = rentDue; }
    // Return the amount allocated to the rent due.
    public BigDecimal getAllocatedAmount() { return allocatedAmount; }
    // Set the amount allocated to the rent due.
    public void setAllocatedAmount(BigDecimal allocatedAmount) { this.allocatedAmount = allocatedAmount; }
}
