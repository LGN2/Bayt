package com.codevictims.billing.repository;

import com.codevictims.billing.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

// Access stored cheque records.

public interface ChequeRepository extends JpaRepository<Cheque, Long> {

  @org.springframework.data.jpa.repository.Query(
      "select c from Cheque c where c.leaseId=:id order by c.chequeDate")
  java.util.List<Cheque> findByLeaseOrderByChequeDate(
      @org.springframework.data.repository.query.Param("id") Long id);
}
