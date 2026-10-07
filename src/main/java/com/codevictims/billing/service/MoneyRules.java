package com.codevictims.billing.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

// Split payments across outstanding dues.
/** Pure allocation rules, independent of HTTP and persistence. Caller orders dues oldest first. */
public final class MoneyRules {
  private MoneyRules() {}

  // Represent the remaining amount of a due.
  public record Balance(long dueId, BigDecimal remaining) {}

  // Represent one allocated part of a payment.
  public record Part(long dueId, BigDecimal amount) {}

  // Allocate the requested payment amount across dues.
  public static List<Part> allocate(BigDecimal requested, List<Balance> dues) {
    BigDecimal amount = requested.setScale(3, RoundingMode.UNNECESSARY);
    // Validate the requested amount before allocation.
    if (amount.signum() <= 0) throw new IllegalArgumentException("INVALID_AMOUNT");
    // Calculate the total remaining balance.
    BigDecimal total =
        dues.stream().map(Balance::remaining).reduce(BigDecimal.ZERO, BigDecimal::add);
    if (amount.compareTo(total) > 0) throw new IllegalArgumentException("OVERPAYMENT");
    List<Part> parts = new ArrayList<>();
    // Allocate money to each due until the payment is fully used.
    for (Balance due : dues) {
      BigDecimal allocated = amount.min(due.remaining());
      if (allocated.signum() > 0) parts.add(new Part(due.dueId(), allocated));
      amount = amount.subtract(allocated);
      if (amount.signum() == 0) break;
    }
    return List.copyOf(parts);
  }

  // Check whether a payment is settled on the selected date.
  public static boolean settledOn(
      java.time.LocalDate paid, java.time.LocalDate reversed, java.time.LocalDate asOf) {
    return !paid.isAfter(asOf) && (reversed == null || reversed.isAfter(asOf));
  }
}
