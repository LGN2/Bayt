// Keep payment status types inside the billing package.
package com.codevictims.bayt.billing.type;

// Define the possible states for a payment.
public enum PaymentStatus {
    // Mark a payment that is not finished yet.
    PENDING,
    // Mark a payment that has been completed.
    COMPLETED,
    // Mark a payment that was cancelled.
    CANCELLED
}
