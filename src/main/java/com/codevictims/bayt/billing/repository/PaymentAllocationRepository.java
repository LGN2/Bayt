// Keep allocation database access classes inside the billing repository package.
package com.codevictims.bayt.billing.repository;

// Import the allocation entity stored by this repository.
import com.codevictims.bayt.billing.entity.PaymentAllocation;
// Import Spring Data JPA repository support.
import org.springframework.data.jpa.repository.JpaRepository;
// Import List for returning many allocation records.
import java.util.List;

// Provide database operations for PaymentAllocation records with Long IDs.
public interface PaymentAllocationRepository extends JpaRepository<PaymentAllocation, Long> {
    // Find all allocations connected to one payment ID.
    List<PaymentAllocation> findByPaymentId(Long paymentId);
}
