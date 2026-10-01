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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getChequeId() { return chequeId; }
    public void setChequeId(Long chequeId) { this.chequeId = chequeId; }
    public ChequeStatus getOldStatus() { return oldStatus; }
    public void setOldStatus(ChequeStatus oldStatus) { this.oldStatus = oldStatus; }
    public ChequeStatus getNewStatus() { return newStatus; }
    public void setNewStatus(ChequeStatus newStatus) { this.newStatus = newStatus; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
