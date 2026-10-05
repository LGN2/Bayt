// Keep allocation business logic inside the billing service package.
package com.codevictims.bayt.billing.service;

// Import the payment entity that provides the amount to allocate.
import com.codevictims.bayt.billing.entity.Payment;
// Import the allocation entity saved by this service.
import com.codevictims.bayt.billing.entity.PaymentAllocation;
// Import rent due records that receive payment amounts.
import com.codevictims.bayt.billing.entity.RentDue;
// Import the repository used for allocation records.
import com.codevictims.bayt.billing.repository.PaymentAllocationRepository;
// Import the repository used for rent due records.
import com.codevictims.bayt.billing.repository.RentDueRepository;
// Import the response object returned by this service.
import com.codevictims.bayt.billing.response.PaymentAllocationResponse;
// Import the Spring service annotation.
import org.springframework.stereotype.Service;

// Import BigDecimal for money calculations.
import java.math.BigDecimal;
// Import ArrayList for building response lists.
import java.util.ArrayList;
// Import List for returning many allocation responses.
import java.util.List;

// Mark this class as a Spring service.
@Service
// Handle payment allocation business operations.
public class PaymentAllocationService {
    // Store the repository used for payment allocation records.
    private final PaymentAllocationRepository allocationRepository;
    // Store the repository used for rent due records.
    private final RentDueRepository rentDueRepository;

    // Receive service dependencies through the constructor.
    public PaymentAllocationService(PaymentAllocationRepository allocationRepository,
                                    RentDueRepository rentDueRepository) {
        // Keep the allocation repository for later database work.
        this.allocationRepository = allocationRepository;
        // Keep the rent due repository for lookup and save operations.
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
