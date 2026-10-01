package com.property.billing.service;

import com.property.billing.entity.DepositRecord;
import com.property.billing.repository.DepositRecordRepository;
import com.property.billing.request.UpdateDepositRequest;
import com.property.billing.response.DepositResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepositService {
    private final DepositRecordRepository depositRepository;

    public DepositService(DepositRecordRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public DepositResponse create(Long tenantId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        DepositRecord deposit = new DepositRecord();
        deposit.setTenantId(tenantId);
        deposit.setHeldAmount(amount);
        deposit.setDeductionAmount(BigDecimal.ZERO);
        deposit.setRefundedAmount(BigDecimal.ZERO);
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
