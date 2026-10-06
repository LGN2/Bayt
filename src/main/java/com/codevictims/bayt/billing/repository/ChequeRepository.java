// Keep cheque database access classes inside the billing repository package.
package com.codevictims.bayt.billing.repository;

// Import the cheque entity stored by this repository.
import com.codevictims.bayt.billing.entity.Cheque;
// Import Spring Data JPA repository support.
import org.springframework.data.jpa.repository.JpaRepository;
// Import List for returning many cheque records.
import java.util.List;

// Provide database operations for Cheque records with Long IDs.
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    // Find cheques for one tenant, newest cheque date first.
    List<Cheque> findByTenantIdOrderByChequeDateDesc(Long tenantId);
}
