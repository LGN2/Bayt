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

    public DepositResponse update(Long id, UpdateDepositRequest request) {
        DepositRecord deposit = depositRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deposit not found"));

        if (request.getDeductionAmount() != null) {
            deposit.setDeductionAmount(request.getDeductionAmount());
        }
        if (request.getRefundedAmount() != null) {
            deposit.setRefundedAmount(request.getRefundedAmount());
            if (request.getRefundedAmount().compareTo(BigDecimal.ZERO) > 0) {
                deposit.setRefundDate(LocalDate.now());
            }
        }
        if (request.getNote() != null) {
            deposit.setNote(request.getNote());
        }

        if (deposit.getRemainingAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deduction and refund cannot exceed held amount");
        }

        return toResponse(depositRepository.save(deposit));
    }

    public List<DepositResponse> getAll() {
        List<DepositResponse> responses = new ArrayList<>();
        for (DepositRecord deposit : depositRepository.findAll()) {
            responses.add(toResponse(deposit));
        }
        return responses;
    }

    private DepositResponse toResponse(DepositRecord deposit) {
        DepositResponse response = new DepositResponse();
        response.setId(deposit.getId());
        response.setTenantId(deposit.getTenantId());
        response.setHeldAmount(deposit.getHeldAmount());
        response.setDeductionAmount(deposit.getDeductionAmount());
        response.setRefundedAmount(deposit.getRefundedAmount());
        response.setRemainingAmount(deposit.getRemainingAmount());
        response.setRefundDate(deposit.getRefundDate());
        response.setNote(deposit.getNote());
        return response;
    }
}
