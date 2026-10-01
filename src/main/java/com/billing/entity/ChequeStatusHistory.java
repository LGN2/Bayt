package com.property.billing.entity;

import com.property.billing.type.ChequeStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cheque_status_history")
public class ChequeStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cheque_id")
    private Cheque cheque;

    @Enumerated(EnumType.STRING)
    private ChequeStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChequeStatus newStatus;

    @Column(nullable = false)
    private LocalDateTime changedAt;

    private String note;

    public ChequeStatusHistory() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cheque getCheque() { return cheque; }
    public void setCheque(Cheque cheque) { this.cheque = cheque; }
    public ChequeStatus getOldStatus() { return oldStatus; }
    public void setOldStatus(ChequeStatus oldStatus) { this.oldStatus = oldStatus; }
    public ChequeStatus getNewStatus() { return newStatus; }
    public void setNewStatus(ChequeStatus newStatus) { this.newStatus = newStatus; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
