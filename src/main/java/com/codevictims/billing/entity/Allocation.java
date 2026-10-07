package com.codevictims.billing.entity;

import com.codevictims.propertymanagement.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "allocation")
public class Allocation extends BaseEntity {
  // Store the allocation between a payment and a due.
  @Column(nullable = false)
  public Long paymentId;

  // Link the allocated payment amount to a due.
  @Column(nullable = false)
  public Long dueId;

  // Store the amount allocated to this due.
  @Column(nullable = false, precision = 15, scale = 3)
  public BigDecimal amount = BigDecimal.ZERO;
}
