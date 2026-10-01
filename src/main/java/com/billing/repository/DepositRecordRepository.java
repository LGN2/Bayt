// Place deposit database access in the billing repository package.
package com.property.billing.repository;

// Use the DepositRecord entity managed by this repository.
import com.property.billing.entity.DepositRecord;
// Use Spring Data JPA repository features.
import org.springframework.data.jpa.repository.JpaRepository;
// Return multiple deposit records.
import java.util.List;

// Provide database access for DepositRecord entities with Long IDs.
public interface DepositRecordRepository extends JpaRepository<DepositRecord, Long> {
    // Find deposit records that belong to one tenant.
    List<DepositRecord> findByTenantId(Long tenantId);
}
