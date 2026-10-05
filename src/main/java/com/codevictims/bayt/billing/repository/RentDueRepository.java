// Keep rent due database access classes inside the billing repository package.
package com.property.billing.repository;

// Import the rent due entity stored by this repository.
import com.property.billing.entity.RentDue;
// Import Spring Data JPA repository support.
import org.springframework.data.jpa.repository.JpaRepository;
// Import LocalDate for due date searches.
import java.time.LocalDate;
// Import List for returning many rent due records.
import java.util.List;

// Provide database operations for RentDue records with Long IDs.
public interface RentDueRepository extends JpaRepository<RentDue, Long> {
    // Find rent dues for one lease, ordered by due date.
    List<RentDue> findByLeaseIdOrderByDueDateAsc(Long leaseId);
    // Find rent dues before a date, ordered by due date.
    List<RentDue> findByDueDateBeforeOrderByDueDateAsc(LocalDate date);
}
