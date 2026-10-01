package com.property.billing.repository;

import com.property.billing.entity.DepositRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepositRecordRepository extends JpaRepository<DepositRecord, Long> {
    List<DepositRecord> findByTenantId(Long tenantId);
}
