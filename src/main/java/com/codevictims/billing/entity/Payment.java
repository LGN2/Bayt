package com.codevictims.billing.entity;

import com.codevictims.propertymanagement.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
  // Store the lease that this payment belongs to.
  @Column(nullable = false)
  public Long leaseId;

  // Store the payment amount and payment method.
  @Column(nullable = false, precision = 15, scale = 3)
  public BigDecimal amount = BigDecimal.ZERO;

  @Column(nullable = false, length = 40)
  public String method = "";

  // Store reference details and the date the payment becomes effective.
  @Column(nullable = false)
  public String reference = "";

  @Column(nullable = false)
  public LocalDate effectiveDate;

  // Store idempotency and reversal information.
  @Column(nullable = false)
  public String idempotencyKey = "";

  @Column(nullable = true)
  public LocalDate reversedOn;

  @Column(nullable = false)
  public String reversalReason = "";
}
