package com.property.billing.type;

// Define the possible states of a payment.
public enum PaymentStatus {
    // The payment exists but is not completed yet.
    PENDING,
    // The payment has been completed.
    COMPLETED,
    // The payment has been cancelled.
    CANCELLED
}
