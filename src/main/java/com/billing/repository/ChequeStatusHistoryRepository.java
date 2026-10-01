package com.property.billing.repository;

import com.property.billing.entity.ChequeStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChequeStatusHistoryRepository extends JpaRepository<ChequeStatusHistory, Long> {
    List<ChequeStatusHistory> findByChequeIdOrderByChangedAtDesc(Long chequeId);
}
