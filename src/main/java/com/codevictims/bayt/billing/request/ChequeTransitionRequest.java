// Keep cheque status change requests inside the billing request package.
package com.codevictims.bayt.billing.request;

// Import the status requested by the client.
import com.codevictims.bayt.billing.type.ChequeStatus;
// Import validation to require a new status.
import jakarta.validation.constraints.NotNull;

// Carry the data needed to change a cheque status.
public class ChequeTransitionRequest {
    // Require the new cheque status in the request.
    @NotNull
    // Store the status the cheque should move to.
    private ChequeStatus newStatus;
    // Store an optional note about the status change.
    private String note;

    // Return the requested new cheque status.
    public ChequeStatus getNewStatus() { return newStatus; }
    // Save the new cheque status from the request.
    public void setNewStatus(ChequeStatus newStatus) { this.newStatus = newStatus; }
    // Return the optional transition note.
    public String getNote() { return note; }
    // Save the note sent with the status change.
    public void setNote(String note) { this.note = note; }
}
