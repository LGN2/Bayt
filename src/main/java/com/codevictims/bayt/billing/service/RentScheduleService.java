package com.property.billing.service;

import com.property.billing.entity.RentDue;
import com.property.billing.repository.RentDueRepository;
import com.property.billing.response.RentDueResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentScheduleService {
    private final RentDueRepository rentDueRepository;

    public RentScheduleService(RentDueRepository rentDueRepository) {
        this.rentDueRepository = rentDueRepository;
    }

    public RentDueResponse createRentDue(Long leaseId, LocalDate dueDate, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        RentDue rentDue = new RentDue();
        rentDue.setLeaseId(leaseId);
        rentDue.setDueDate(dueDate);
        rentDue.setAmount(amount);
        rentDue.setPaidAmount(BigDecimal.ZERO);
        return toResponse(rentDueRepository.save(rentDue));
    }

    public List<RentDueResponse> getAll() {
        List<RentDueResponse> responses = new ArrayList<>();
        for (RentDue due : rentDueRepository.findAll()) {
            responses.add(toResponse(due));
        }
        return responses;
    }

    public List<RentDueResponse> getByLease(Long leaseId) {
        List<RentDueResponse> responses = new ArrayList<>();
        for (RentDue due : rentDueRepository.findByLeaseIdOrderByDueDateAsc(leaseId)) {
            responses.add(toResponse(due));
        }
        return responses;
    }

    public RentDueResponse toResponse(RentDue due) {
        RentDueResponse response = new RentDueResponse();
        response.setId(due.getId());
        response.setLeaseId(due.getLeaseId());
        response.setDueDate(due.getDueDate());
        response.setAmount(due.getAmount());
        response.setPaidAmount(due.getPaidAmount());
        response.setOutstandingAmount(due.getOutstandingAmount());
        response.setStatus(due.isPaid() ? "PAID" : "UNPAID");
        return response;
    }
}
