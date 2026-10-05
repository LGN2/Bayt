package com.codevictims.bayt.billing.repository;

import com.codevictims.bayt.billing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTenantIdOrderByPaymentDateDesc(Long tenantId);
}
