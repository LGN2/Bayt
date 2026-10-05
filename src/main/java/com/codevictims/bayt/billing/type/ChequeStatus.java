// Keep cheque status types inside the billing package.
package com.codevictims.bayt.billing.type;

// Define the possible processing states for a cheque.
public enum ChequeStatus {
    // Mark a cheque that has been received.
    RECEIVED,
    // Mark a cheque that has been deposited.
    DEPOSITED,
    // Mark a cheque that cleared successfully.
    CLEARED,
    // Mark a cheque that was rejected by the bank.
    BOUNCED,
    // Mark a cheque that was cancelled.
    CANCELLED
}
