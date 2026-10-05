package com.codevictims.bayt.billing.service;

import com.codevictims.bayt.billing.entity.Payment;
import com.codevictims.bayt.billing.repository.PaymentRepository;
import com.codevictims.bayt.billing.request.CreatePaymentRequest;
import com.codevictims.bayt.billing.response.PaymentResponse;
import com.codevictims.bayt.billing.type.PaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentAllocationService paymentAllocationService;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentAllocationService paymentAllocationService) {
        this.paymentRepository = paymentRepository;
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
