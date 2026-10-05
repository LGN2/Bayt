// Keep payment database access classes inside the billing repository package.
package com.codevictims.bayt.billing.repository;

// Import the payment entity stored by this repository.
import com.codevictims.bayt.billing.entity.Payment;
// Import Spring Data JPA repository support.
import org.springframework.data.jpa.repository.JpaRepository;
// Import List for returning many payment records.
import java.util.List;

// Provide database operations for Payment records with Long IDs.
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Find payments for one tenant, newest payment first.
    List<Payment> findByTenantIdOrderByPaymentDateDesc(Long tenantId);
}
