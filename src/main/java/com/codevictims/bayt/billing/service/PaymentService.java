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

    public PaymentResponse create(CreatePaymentRequest request) {
        Payment payment = new Payment();
        payment.setTenantId(request.getTenantId());
        payment.setLeaseId(request.getLeaseId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setReferenceNumber(request.getReferenceNumber());

        payment = paymentRepository.save(payment);
        paymentAllocationService.allocatePayment(payment);
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
