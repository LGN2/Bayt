// Keep payment method types inside the billing package.
package com.codevictims.bayt.billing.type;

// Define the supported ways to make a payment.
public enum PaymentMethod {
    // Use cash as the payment method.
    CASH,
    // Use a card as the payment method.
    CARD,
    // Use a bank transfer as the payment method.
    BANK_TRANSFER,
    // Use a cheque as the payment method.
    CHEQUE
}
