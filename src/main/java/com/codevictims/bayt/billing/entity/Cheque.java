// Keep cheque entities inside the billing entity package.
package com.codevictims.bayt.billing.entity;

// Import the enum that stores the cheque status.
import com.codevictims.bayt.billing.type.ChequeStatus;
// Import JPA annotations used to map this class to the database.
import jakarta.persistence.*;
// Import BigDecimal for money values.
import java.math.BigDecimal;
// Import LocalDate for the cheque date.
import java.time.LocalDate;

// Mark this class as a database entity.
@Entity
// Store cheque rows in the cheques table.
@Table(name = "cheques")
// Represent one cheque record.
public class Cheque {
    // Mark this field as the primary key.
    @Id
    // Generate the cheque ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Store the cheque ID.
    private Long id;

    // Require a tenant ID in the database.
    @Column(nullable = false)
    // Store the tenant connected to this cheque.
    private Long tenantId;

    // Require a unique cheque number in the database.
    @Column(nullable = false, unique = true)
    // Keep the cheque number.
    private String chequeNumber;

    // Require the bank name in the database.
    @Column(nullable = false)
    // Store the bank that issued the cheque.
    private String bankName;

    // Require the cheque date in the database.
    @Column(nullable = false)
    // Keep the date written on the cheque.
    private LocalDate chequeDate;

    // Store the cheque amount with two decimal places.
    @Column(nullable = false, precision = 10, scale = 2)
    // Keep the amount written on the cheque.
    private BigDecimal amount;

    // Save the cheque status enum as text.
    @Enumerated(EnumType.STRING)
    // Require a cheque status in the database.
    @Column(nullable = false)
    // Track the current cheque status.
    private ChequeStatus status;

    // Create an empty cheque object for JPA.
    public Cheque() {}

    // Return the cheque ID.
    public Long getId() { return id; }
    // Set the cheque ID.
    public void setId(Long id) { this.id = id; }
    // Return the tenant ID for this cheque.
    public Long getTenantId() { return tenantId; }
    // Set the tenant ID.
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    // Return the cheque number.
    public String getChequeNumber() { return chequeNumber; }
    // Set the cheque number.
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }
    // Return the bank name.
    public String getBankName() { return bankName; }
    // Set the bank that issued the cheque.
    public void setBankName(String bankName) { this.bankName = bankName; }
    // Return the cheque date.
    public LocalDate getChequeDate() { return chequeDate; }
    // Set the date written on the cheque.
    public void setChequeDate(LocalDate chequeDate) { this.chequeDate = chequeDate; }
    // Return the cheque amount.
    public BigDecimal getAmount() { return amount; }
    // Set the amount written on the cheque.
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    // Return the current cheque status.
    public ChequeStatus getStatus() { return status; }
    // Set the current cheque status.
    public void setStatus(ChequeStatus status) { this.status = status; }
}
