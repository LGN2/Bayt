package com.property.billing.response;

import com.property.billing.type.ChequeStatus;
import java.time.LocalDateTime;

public class ChequeStatusHistoryResponse {
    private Long id;
    private Long chequeId;
    private ChequeStatus oldStatus;
    private ChequeStatus newStatus;
    private LocalDateTime changedAt;
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
