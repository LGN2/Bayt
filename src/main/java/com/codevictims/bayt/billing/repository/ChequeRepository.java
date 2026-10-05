package com.codevictims.bayt.billing.repository;

import com.codevictims.bayt.billing.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    List<Cheque> findByTenantIdOrderByChequeDateDesc(Long tenantId);
}
