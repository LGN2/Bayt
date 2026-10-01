// Place payment method values in the billing type package.
package main.java.com.billing.type;

// Define the supported ways a payment can be made.
public enum PaymentMethod {
    // The tenant paid using cash.
    CASH,
    // The tenant paid using a card.
    CARD,
    // The tenant paid through a bank transfer.
    BANK_TRANSFER,
    // The tenant paid using a cheque.
    CHEQUE
}
