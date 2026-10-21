package com.codevictims.bayt.billing.repository;

import com.codevictims.bayt.billing.entity.DepositEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositEntryRepository extends JpaRepository<DepositEntry, Long> {
  @org.springframework.data.jpa.repository.Query(
      "select d from DepositEntry d where d.leaseId=:id order by d.id")
  java.util.List<DepositEntry> findByLeaseInLedgerOrder(
      @org.springframework.data.repository.query.Param("id") Long id);
}
