// Place allocation database access in the billing repository package.
package com.property.billing.repository;

// Use the allocation entity managed by this repository.
import com.property.billing.entity.PaymentAllocation;
// Use Spring Data JPA repository features.
import org.springframework.data.jpa.repository.JpaRepository;
// Return multiple allocation records.
import java.util.List;

// Provide database access for PaymentAllocation entities with Long IDs.
public interface PaymentAllocationRepository extends JpaRepository<PaymentAllocation, Long> {
    // Find all allocations that belong to one payment.
    List<PaymentAllocation> findByPaymentId(Long paymentId);
}
