// Place rent due database access in the billing repository package.
package com.property.billing.repository;

// Use the RentDue entity managed by this repository.
import com.property.billing.entity.RentDue;
// Use Spring Data JPA repository features.
import org.springframework.data.jpa.repository.JpaRepository;
// Use dates when searching for overdue rent.
import java.time.LocalDate;
// Return multiple rent due records.
import java.util.List;

// Provide database access for RentDue entities with Long IDs.
public interface RentDueRepository extends JpaRepository<RentDue, Long> {
    // Find rent dues for one lease, oldest due date first.
    List<RentDue> findByLeaseIdOrderByDueDateAsc(Long leaseId);
    // Find rent dues before a date, oldest due date first.
    List<RentDue> findByDueDateBeforeOrderByDueDateAsc(LocalDate date);
}
