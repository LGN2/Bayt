// Place payment database access code in the billing repository package.
package main.java.com.billing.repository;

// Use the Payment entity managed by this repository.
import main.java.com.billing.entity.Payment;
// Use Spring Data JPA repository features.
import org.springframework.data.jpa.repository.JpaRepository;
// Return multiple payment records.
import java.util.List;

// Provide database access for Payment entities with Long IDs.
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Use the tenant ID parameter to select matching payments.
    // Find payments for one tenant, newest payment date first.
    List<Payment> findByTenantIdOrderByPaymentDateDesc(Long tenantId);
}
