// Place cheque status values in the billing type package.
package com.property.billing.type;

// Define the available statuses for a cheque.
public enum ChequeStatus {
    // The cheque has been received by the system.
    RECEIVED,
    // The cheque has been sent to the bank for deposit.
    DEPOSITED,
    // The cheque has cleared successfully.
    CLEARED,
    // The cheque was rejected or returned by the bank.
    BOUNCED,
    // The cheque has been cancelled.
    CANCELLED
}
