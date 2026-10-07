package com.codevictims.billing.repository;

import com.codevictims.billing.entity.Allocation;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
  // Find allocations linked to a due that are effective on the selected day.
  @org.springframework.data.jpa.repository.Query(
      "select a from Allocation a,Payment p where a.paymentId=p.id and a.dueId=:id and"
          + " p.effectiveDate<=:day and (p.reversedOn is null or p.reversedOn>:day)")
  java.util.List<Allocation> findEffectiveForDue(
      @org.springframework.data.repository.query.Param("id") Long id,
      @org.springframework.data.repository.query.Param("day") LocalDate day);

  // Find allocations created from one payment.
  @org.springframework.data.jpa.repository.Query("select a from Allocation a where a.paymentId=:p")
  java.util.List<Allocation> findByPayment(
      @org.springframework.data.repository.query.Param("p") Long p);

  // Find allocations linked to a due where the payment is not reversed.
  @org.springframework.data.jpa.repository.Query(
      "select a from Allocation a, Payment p where a.paymentId=p.id and a.dueId=:d and p.reversedOn"
          + " is null")
  java.util.List<Allocation> findUnreversedForDue(
      @org.springframework.data.repository.query.Param("d") Long d);
}
