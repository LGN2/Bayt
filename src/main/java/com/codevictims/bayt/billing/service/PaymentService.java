// Keep payment business logic inside the billing service package.
package com.codevictims.bayt.billing.service;

// Import the payment entity saved in the database.
import com.codevictims.bayt.billing.entity.Payment;
// Import the repository used for payment records.
import com.codevictims.bayt.billing.repository.PaymentRepository;
// Import the request data used to create payments.
import com.codevictims.bayt.billing.request.CreatePaymentRequest;
// Import the response object returned to clients.
import com.codevictims.bayt.billing.response.PaymentResponse;
// Import the payment status enum.
import com.codevictims.bayt.billing.type.PaymentStatus;
// Import the Spring service annotation.
import org.springframework.stereotype.Service;

// Import LocalDateTime to store the current payment time.
import java.time.LocalDateTime;
// Import ArrayList for building response lists.
import java.util.ArrayList;
// Import List for returning many payment responses.
import java.util.List;

// Mark this class as a Spring service.
@Service
// Handle payment business operations.
public class PaymentService {
    // Store the repository used for payment records.
    private final PaymentRepository paymentRepository;
    // Store the service used to allocate saved payments.
    private final PaymentAllocationService paymentAllocationService;

    // Receive service dependencies through the constructor.
    public PaymentService(PaymentRepository paymentRepository,
                          PaymentAllocationService paymentAllocationService) {
        // Keep the payment repository for later database work.
        this.paymentRepository = paymentRepository;
        // Keep the allocation service for payment allocation.
        this.paymentAllocationService = paymentAllocationService;
    }

    // Create and store a new payment from request data.
    public PaymentResponse create(CreatePaymentRequest request) {
        // Create a new payment entity.
        Payment payment = new Payment();
        // Copy the tenant ID from the request.
        payment.setTenantId(request.getTenantId());
        // Copy the lease ID from the request.
        payment.setLeaseId(request.getLeaseId());
        // Copy the payment amount from the request.
        payment.setAmount(request.getAmount());
        // Copy the selected payment method from the request.
        payment.setMethod(request.getMethod());
        // Mark new payments as completed.
        payment.setStatus(PaymentStatus.COMPLETED);
        // Store the current date and time as the payment date.
        payment.setPaymentDate(LocalDateTime.now());
        // Copy the optional reference number from the request.
        payment.setReferenceNumber(request.getReferenceNumber());

        // Save the new payment in the database.
        payment = paymentRepository.save(payment);
        // Allocate the saved payment to related billing records.
        paymentAllocationService.allocatePayment(payment);
        // Convert the saved payment into a response.
        return toResponse(payment);
    }

    public List<PaymentResponse> getAll() {
        List<PaymentResponse> responses = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            responses.add(toResponse(payment));
        }
        return responses;
    }

    public List<PaymentResponse> getByTenant(Long tenantId) {
        List<PaymentResponse> responses = new ArrayList<>();
        for (Payment payment : paymentRepository.findByTenantIdOrderByPaymentDateDesc(tenantId)) {
            responses.add(toResponse(payment));
        }
        return responses;
    }

    public PaymentResponse getById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return toResponse(payment);
    }

    private PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setTenantId(payment.getTenantId());
        response.setLeaseId(payment.getLeaseId());
        response.setAmount(payment.getAmount());
        response.setMethod(payment.getMethod());
        response.setStatus(payment.getStatus());
        response.setPaymentDate(payment.getPaymentDate());
        response.setReferenceNumber(payment.getReferenceNumber());
        return response;
    }
}
