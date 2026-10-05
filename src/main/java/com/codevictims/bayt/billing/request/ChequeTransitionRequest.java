package com.codevictims.bayt.billing.request;

import com.codevictims.bayt.billing.type.ChequeStatus;
import jakarta.validation.constraints.NotNull;

public class ChequeTransitionRequest {
    @NotNull
    private ChequeStatus newStatus;
    private String note;

    public ChequeStatus getNewStatus() { return newStatus; }
    public void setNewStatus(ChequeStatus newStatus) { this.newStatus = newStatus; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
