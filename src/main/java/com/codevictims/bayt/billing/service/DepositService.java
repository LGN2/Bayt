// Place deposit business logic in the billing service package.
package com.property.billing.service;

// Use the DepositRecord entity for database records.
import com.property.billing.entity.DepositRecord;
// Use the repository that saves and reads deposits.
import com.property.billing.repository.DepositRecordRepository;
// Use the request data for deposit updates.
import com.property.billing.request.UpdateDepositRequest;
// Use the response object returned by service methods.
import com.property.billing.response.DepositResponse;
// Mark this class as a Spring service.
import org.springframework.stereotype.Service;

// Handle deposit money values.
import java.math.BigDecimal;
// Store the current date for refunds.
import java.time.LocalDate;
// Build response lists manually.
import java.util.ArrayList;
// Return lists of deposit responses.
import java.util.List;

// Register this class as a Spring service bean.
@Service
// Handle business logic for deposit records.
public class DepositService {
    // Store the repository used for deposit data access.
    private final DepositRecordRepository depositRepository;

    // Receive the deposit repository through constructor injection.
    public DepositService(DepositRecordRepository depositRepository) {
        // Keep the repository for later service operations.
        this.depositRepository = depositRepository;
    }

    // Create a new deposit record for a tenant.
    public DepositResponse create(Long tenantId, BigDecimal amount) {
        // Check that the deposit amount is present and positive.
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            // Stop when the deposit amount is invalid.
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        // Build a new empty deposit record.
        DepositRecord deposit = new DepositRecord();
        // Store the tenant linked to this deposit.
        deposit.setTenantId(tenantId);
        // Store the amount being held as deposit.
        deposit.setHeldAmount(amount);
        // Start with no deduction.
        deposit.setDeductionAmount(BigDecimal.ZERO);
        // Start with no refunded amount.
        deposit.setRefundedAmount(BigDecimal.ZERO);
        // Save the deposit and return it as a response.
        return toResponse(depositRepository.save(deposit));
    }

    // Update an existing deposit record.
    public DepositResponse update(Long id, UpdateDepositRequest request) {
        // Search for the deposit record by ID.
        DepositRecord deposit = depositRepository.findById(id)
                // Stop if the deposit record does not exist.
                .orElseThrow(() -> new IllegalArgumentException("Deposit not found"));

        // Check whether the request includes a deduction amount.
        if (request.getDeductionAmount() != null) {
            // Update the deduction amount from the request.
            deposit.setDeductionAmount(request.getDeductionAmount());
        }
        // Check whether the request includes a refunded amount.
        if (request.getRefundedAmount() != null) {
            // Update the refunded amount from the request.
            deposit.setRefundedAmount(request.getRefundedAmount());
            // Check whether a positive refund was recorded.
            if (request.getRefundedAmount().compareTo(BigDecimal.ZERO) > 0) {
                // Store today's date as the refund date.
                deposit.setRefundDate(LocalDate.now());
            }
        }
        // Check whether the request includes a note.
        if (request.getNote() != null) {
            // Update the deposit note from the request.
            deposit.setNote(request.getNote());
        }

        // Check that deductions and refunds do not exceed the held amount.
        if (deposit.getRemainingAmount().compareTo(BigDecimal.ZERO) < 0) {
            // Stop when the deposit would become negative.
            throw new IllegalArgumentException("Deduction and refund cannot exceed held amount");
        }

        // Save the updated deposit and convert it to a response.
        return toResponse(depositRepository.save(deposit));
    }

    // Get all deposit records as response objects.
    public List<DepositResponse> getAll() {
        // Prepare a list for deposit responses.
        List<DepositResponse> responses = new ArrayList<>();
        // Read every deposit record from the repository.
        for (DepositRecord deposit : depositRepository.findAll()) {
            // Convert each deposit and add it to the list.
            responses.add(toResponse(deposit));
        }
        // Return all converted deposit records.
        return responses;
    }

    // Convert a DepositRecord entity into a DepositResponse.
    private DepositResponse toResponse(DepositRecord deposit) {
        // Create an empty deposit response.
        DepositResponse response = new DepositResponse();
        // Copy the deposit ID into the response.
        response.setId(deposit.getId());
        // Copy the tenant ID into the response.
        response.setTenantId(deposit.getTenantId());
        // Copy the held amount into the response.
        response.setHeldAmount(deposit.getHeldAmount());
        // Copy the deduction amount into the response.
        response.setDeductionAmount(deposit.getDeductionAmount());
        // Copy the refunded amount into the response.
        response.setRefundedAmount(deposit.getRefundedAmount());
        // Copy the calculated remaining amount into the response.
        response.setRemainingAmount(deposit.getRemainingAmount());
        // Copy the refund date into the response.
        response.setRefundDate(deposit.getRefundDate());
        // Copy the note into the response.
        response.setNote(deposit.getNote());
        // Return the completed deposit response.
        return response;
    }
}
