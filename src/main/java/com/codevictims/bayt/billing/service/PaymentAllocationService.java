package com.codevictims.bayt.billing.service;

import com.codevictims.bayt.billing.entity.Payment;
import com.codevictims.bayt.billing.entity.PaymentAllocation;
import com.codevictims.bayt.billing.entity.RentDue;
import com.codevictims.bayt.billing.repository.PaymentAllocationRepository;
import com.codevictims.bayt.billing.repository.RentDueRepository;
import com.codevictims.bayt.billing.response.PaymentAllocationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentAllocationService {
    private final PaymentAllocationRepository allocationRepository;
    private final RentDueRepository rentDueRepository;

    public PaymentAllocationService(PaymentAllocationRepository allocationRepository,
                                    RentDueRepository rentDueRepository) {
        this.allocationRepository = allocationRepository;
        this.rentDueRepository = rentDueRepository;
    }

    public List<PaymentAllocationResponse> allocatePayment(Payment payment) {
        BigDecimal remainingPayment = payment.getAmount();
        List<PaymentAllocationResponse> responses = new ArrayList<>();
        List<RentDue> dues = rentDueRepository.findByLeaseIdOrderByDueDateAsc(payment.getLeaseId());

        for (RentDue due : dues) {
            if (remainingPayment.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
            if (due.isPaid()) {
                continue;
            }

            BigDecimal outstanding = due.getOutstandingAmount();
            BigDecimal amountToAllocate = remainingPayment.min(outstanding);

            due.setPaidAmount(due.getPaidAmount().add(amountToAllocate));
            rentDueRepository.save(due);

            PaymentAllocation allocation = new PaymentAllocation();
            allocation.setPayment(payment);
            allocation.setRentDue(due);
            allocation.setAllocatedAmount(amountToAllocate);
            allocation = allocationRepository.save(allocation);

            responses.add(toResponse(allocation));
            remainingPayment = remainingPayment.subtract(amountToAllocate);
        }
        return responses;
    }

    public List<PaymentAllocationResponse> getByPayment(Long paymentId) {
        List<PaymentAllocationResponse> responses = new ArrayList<>();
        for (PaymentAllocation allocation : allocationRepository.findByPaymentId(paymentId)) {
            responses.add(toResponse(allocation));
        }
        return responses;
    }

    private PaymentAllocationResponse toResponse(PaymentAllocation allocation) {
        PaymentAllocationResponse response = new PaymentAllocationResponse();
        response.setId(allocation.getId());
        response.setPaymentId(allocation.getPayment().getId());
        response.setRentDueId(allocation.getRentDue().getId());
        response.setAllocatedAmount(allocation.getAllocatedAmount());
        return response;
    }
}
