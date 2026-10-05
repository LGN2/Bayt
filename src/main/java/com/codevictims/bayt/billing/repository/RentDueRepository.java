package com.property.billing.repository;

import com.property.billing.entity.RentDue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface RentDueRepository extends JpaRepository<RentDue, Long> {
    List<RentDue> findByLeaseIdOrderByDueDateAsc(Long leaseId);
    List<RentDue> findByDueDateBeforeOrderByDueDateAsc(LocalDate date);
}
