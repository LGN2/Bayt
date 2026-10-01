// Place allocation business logic in the billing service package.
package com.property.billing.service;

// Use the payment that provides money for allocations.
import com.property.billing.entity.Payment;
// Use the allocation entity saved by this service.
import com.property.billing.entity.PaymentAllocation;
// Use rent due records that receive allocated payments.
import com.property.billing.entity.RentDue;
// Use the repository that stores allocation records.
import com.property.billing.repository.PaymentAllocationRepository;
// Use the repository that reads and updates rent due records.
import com.property.billing.repository.RentDueRepository;
// Use the response object returned by allocation methods.
import com.property.billing.response.PaymentAllocationResponse;
// Mark this class as a Spring service.
import org.springframework.stereotype.Service;

// Handle money values during allocation.
import java.math.BigDecimal;
// Build response lists manually.
import java.util.ArrayList;
// Return lists of allocation responses.
import java.util.List;

// Register this class as a Spring service bean.
@Service
// Handle payment allocation logic.
public class PaymentAllocationService {
    // Store the repository used for allocation records.
    private final PaymentAllocationRepository allocationRepository;
    // Store the repository used for rent due records.
    private final RentDueRepository rentDueRepository;

    // Receive required repositories through constructor injection.
    public PaymentAllocationService(PaymentAllocationRepository allocationRepository,
                                    // Receive the repository that manages rent dues.
                                    RentDueRepository rentDueRepository) {
        // Keep the allocation repository for later operations.
        this.allocationRepository = allocationRepository;
        // Keep the rent due repository for later operations.
        this.rentDueRepository = rentDueRepository;
    }

    // Allocate one payment across unpaid rent due records.
    public List<PaymentAllocationResponse> allocatePayment(Payment payment) {
        // Start with the full payment amount available to allocate.
        BigDecimal remainingPayment = payment.getAmount();
        // Prepare the responses for created allocations.
        List<PaymentAllocationResponse> responses = new ArrayList<>();
        // Load rent dues for the payment lease, oldest due date first.
        List<RentDue> dues = rentDueRepository.findByLeaseIdOrderByDueDateAsc(payment.getLeaseId());

        // Process each rent due record in due date order.
        for (RentDue due : dues) {
            // Check whether there is still money left to allocate.
            if (remainingPayment.compareTo(BigDecimal.ZERO) <= 0) {
                // Stop once the full payment amount has been used.
                break;
            }
            // Check whether this rent due is already fully paid.
            if (due.isPaid()) {
                // Skip rent dues that do not need more payment.
                continue;
            }

            // Read how much is still unpaid for this rent due.
            BigDecimal outstanding = due.getOutstandingAmount();
            // Allocate only what the payment can cover or what the rent due needs.
            BigDecimal amountToAllocate = remainingPayment.min(outstanding);

            // Add the allocated amount to the rent due paid amount.
            due.setPaidAmount(due.getPaidAmount().add(amountToAllocate));
            // Save the updated rent due record.
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
