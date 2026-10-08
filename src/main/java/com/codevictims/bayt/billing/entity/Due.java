package com.codevictims.bayt.billing.entity;

import com.codevictims.bayt.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "due")
public class Due extends BaseEntity {
  // Store rent due information for a lease.
  @Column(nullable = false)
  public Long leaseId;

  // Store the due date and rent amounts.
  @Column(nullable = false)
  public LocalDate dueDate;

  @Column(nullable = false, precision = 15, scale = 3)
  public BigDecimal rentAmount = BigDecimal.ZERO;

  @Column(nullable = false, precision = 15, scale = 3)
  public BigDecimal taxAmount = BigDecimal.ZERO;

  // Store the total amount including tax.
  @Column(nullable = false, precision = 15, scale = 3)
  public BigDecimal amount = BigDecimal.ZERO;

  // Track whether the due has been cancelled.
  @Column(nullable = false)
  public boolean cancelled;

  public LocalDate cancelledOn;
}
