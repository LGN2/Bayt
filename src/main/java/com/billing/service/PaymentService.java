package main.java.com.billing.service;

// Use the Payment entity for database records.
import main.java.com.billing.entity.Payment;
// Use the repository that saves and reads payments.
import main.java.com.billing.repository.PaymentRepository;
// Use the request data needed to create a payment.
import main.java.com.billing.request.CreatePaymentRequest;
// Use the response object returned by service methods.
import main.java.com.billing.response.PaymentResponse;
// Use the payment status enum when creating payments.
import main.java.com.billing.type.PaymentStatus;
// Mark this class as a Spring service.
import org.springframework.stereotype.Service;

// Set the current date and time for new payments.
import java.time.LocalDateTime;
// Build response lists manually.
import java.util.ArrayList;
// Return lists of payment responses.
import java.util.List;

// Register this class as a Spring service bean.
@Service
// Handle business logic for payments.
public class PaymentService {
    // Store the repository used for payment data access.
    private final PaymentRepository paymentRepository;
    // Store the service that allocates saved payments.
    private final PaymentAllocationService paymentAllocationService;

    // Receive required services through constructor injection.
    public PaymentService(PaymentRepository paymentRepository,
                          PaymentAllocationService paymentAllocationService) {
        // Keep the payment repository for later use.
        this.paymentRepository = paymentRepository;
        // Keep the allocation service for later use.
        this.paymentAllocationService = paymentAllocationService;
    }

    // Create a new payment from the request data.
    public PaymentResponse create(CreatePaymentRequest request) {
        // Create a new empty payment entity.
        Payment payment = new Payment();
        // Copy the tenant ID from the request.
        payment.setTenantId(request.getTenantId());
        // Copy the lease ID from the request.
        payment.setLeaseId(request.getLeaseId());
        // Copy the amount from the request.
        payment.setAmount(request.getAmount());
        // Copy the payment method from the request.
        payment.setMethod(request.getMethod());
        // Mark the new payment as completed.
        payment.setStatus(PaymentStatus.COMPLETED);
        // Store the current date and time as the payment date.
        payment.setPaymentDate(LocalDateTime.now());
        // Copy the optional reference number from the request.
        payment.setReferenceNumber(request.getReferenceNumber());

        // Save the payment in the database.
        payment = paymentRepository.save(payment);
        // Allocate the saved payment to related billing records.
        paymentAllocationService.allocatePayment(payment);
        // Convert the saved payment into an API response.
        return toResponse(payment);
    }

    // Get all payments as response objects.
    public List<PaymentResponse> getAll() {
        // Prepare a list for converted payment responses.
        List<PaymentResponse> responses = new ArrayList<>();
        // Read every payment from the repository.
        for (Payment payment : paymentRepository.findAll()) {
            // Convert each payment and add it to the response list.
            responses.add(toResponse(payment));
        }
        // Return all converted payments.
        return responses;
    }

    // Get payments for one tenant.
    public List<PaymentResponse> getByTenant(Long tenantId) {
        // Prepare a list for the tenant's payment responses.
        List<PaymentResponse> responses = new ArrayList<>();
        // Read this tenant's payments ordered by newest payment date.
        for (Payment payment : paymentRepository.findByTenantIdOrderByPaymentDateDesc(tenantId)) {
            // Convert each tenant payment and add it to the response list.
            responses.add(toResponse(payment));
        }
        // Return the tenant payment responses.
        return responses;
    }

    // Get one payment by its ID.
    public PaymentResponse getById(Long id) {
        // Search for the payment in the repository.
        Payment payment = paymentRepository.findById(id)
                // Stop if no payment exists for the ID.
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        // Convert the found payment into a response.
        return toResponse(payment);
    }

    // Convert a Payment entity into a PaymentResponse.
    private PaymentResponse toResponse(Payment payment) {
        // Create an empty response object.
        PaymentResponse response = new PaymentResponse();
        // Copy the payment ID into the response.
        response.setId(payment.getId());
        // Copy the tenant ID into the response.
        response.setTenantId(payment.getTenantId());
        // Copy the lease ID into the response.
        response.setLeaseId(payment.getLeaseId());
        // Copy the amount into the response.
        response.setAmount(payment.getAmount());
        // Copy the method into the response.
        response.setMethod(payment.getMethod());
        // Copy the status into the response.
        response.setStatus(payment.getStatus());
        // Copy the payment date into the response.
        response.setPaymentDate(payment.getPaymentDate());
        // Copy the reference number into the response.
        response.setReferenceNumber(payment.getReferenceNumber());
        // Return the completed response object.
        return response;
    }
}
