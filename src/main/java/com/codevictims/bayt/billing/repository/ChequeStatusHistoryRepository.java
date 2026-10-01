// Place cheque status history database access in the billing repository package.
package com.property.billing.repository;

// Use the ChequeStatusHistory entity managed by this repository.
import com.property.billing.entity.ChequeStatusHistory;
// Use Spring Data JPA repository features.
import org.springframework.data.jpa.repository.JpaRepository;
// Return multiple history records.
import java.util.List;

// Provide database access for ChequeStatusHistory entities with Long IDs.
public interface ChequeStatusHistoryRepository extends JpaRepository<ChequeStatusHistory, Long> {
    // Use the cheque ID parameter to select matching history rows.
    // Find history records for one cheque, newest change first.
    List<ChequeStatusHistory> findByChequeIdOrderByChangedAtDesc(Long chequeId);
}
