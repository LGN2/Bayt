// Place cheque status history entities in the billing entity package.
package com.property.billing.entity;

// Use the enum that stores cheque status values.
import com.property.billing.type.ChequeStatus;
// Use JPA annotations to map this entity to the database.
import jakarta.persistence.*;
// Store the date and time of the status change.
import java.time.LocalDateTime;

// Mark this class as a JPA entity.
@Entity
// Connect this entity to the cheque_status_history table.
@Table(name = "cheque_status_history")
// Represent one change in a cheque status.
public class ChequeStatusHistory {
    // Mark this field as the primary key.
    @Id
    // Generate the history ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the history record ID.
    private Long id;

    // Link each history record to one cheque.
    @ManyToOne(optional = false)
    // Store the cheque foreign key in cheque_id.
    @JoinColumn(name = "cheque_id")
    // Keep the cheque connected to this history record.
    private Cheque cheque;

    // Store the previous status as readable text.
    @Enumerated(EnumType.STRING)
    // Keep the status before the change.
    private ChequeStatus oldStatus;

    // Store the new status as readable text.
    @Enumerated(EnumType.STRING)
    // Require the new status in the database.
    @Column(nullable = false)
    // Keep the status after the change.
    private ChequeStatus newStatus;

    // Require the change time in the database.
    @Column(nullable = false)
    // Store when the status changed.
    private LocalDateTime changedAt;

    // Store an optional note about the status change.
    private String note;

    // Provide the empty constructor required by JPA.
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
