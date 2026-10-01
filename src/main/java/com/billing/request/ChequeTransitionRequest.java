// Place cheque status transition requests in the billing request package.
package com.property.billing.request;

// Use the requested cheque status value.
import com.property.billing.type.ChequeStatus;
// Require the new status in the request.
import jakarta.validation.constraints.NotNull;

// Carry the data needed to change a cheque status.
public class ChequeTransitionRequest {
    // Require the status that the cheque should move to.
    @NotNull
    // Store the requested new cheque status.
    private ChequeStatus newStatus;
    // Store an optional note about the status change.
    private String note;

    // Return the requested new status.
    public ChequeStatus getNewStatus() { return newStatus; }
    // Set the requested new status.
    public void setNewStatus(ChequeStatus newStatus) { this.newStatus = newStatus; }
    // Return the transition note.
    public String getNote() { return note; }
    // Set the transition note.
    public void setNote(String note) { this.note = note; }
}
