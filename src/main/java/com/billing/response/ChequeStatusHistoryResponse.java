// Place cheque status history responses in the billing response package.
package com.property.billing.response;

// Include cheque status values in the response.
import com.property.billing.type.ChequeStatus;
// Return the date and time of the status change.
import java.time.LocalDateTime;

// Send cheque status history details back to the API client.
public class ChequeStatusHistoryResponse {
    // Store the history ID returned to the client.
    private Long id;
    // Store the cheque ID returned to the client.
    private Long chequeId;
    // Store the previous status returned to the client.
    private ChequeStatus oldStatus;
    // Store the new status returned to the client.
    private ChequeStatus newStatus;
    // Store the status change time returned to the client.
    private LocalDateTime changedAt;
    // Store the status change note returned to the client.
    private String note;

    // Return the history ID.
    public Long getId() { return id; }
    // Set the history ID in the response.
    public void setId(Long id) { this.id = id; }
    // Return the cheque ID.
    public Long getChequeId() { return chequeId; }
    // Set the cheque ID in the response.
    public void setChequeId(Long chequeId) { this.chequeId = chequeId; }
    // Return the previous cheque status.
    public ChequeStatus getOldStatus() { return oldStatus; }
    // Set the previous cheque status in the response.
    public void setOldStatus(ChequeStatus oldStatus) { this.oldStatus = oldStatus; }
    // Return the new cheque status.
    public ChequeStatus getNewStatus() { return newStatus; }
    // Set the new cheque status in the response.
    public void setNewStatus(ChequeStatus newStatus) { this.newStatus = newStatus; }
    // Return when the status changed.
    public LocalDateTime getChangedAt() { return changedAt; }
    // Set when the status changed in the response.
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
    // Return the status change note.
    public String getNote() { return note; }
    // Set the status change note in the response.
    public void setNote(String note) { this.note = note; }
}
