// Place cheque database access in the billing repository package.
package com.property.billing.repository;

// Use the Cheque entity managed by this repository.
import com.property.billing.entity.Cheque;
// Use Spring Data JPA repository features.
import org.springframework.data.jpa.repository.JpaRepository;
// Return multiple cheque records.
import java.util.List;

// Provide database access for Cheque entities with Long IDs.
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    // Find cheques for one tenant, newest cheque date first.
    List<Cheque> findByTenantIdOrderByChequeDateDesc(Long tenantId);
}
