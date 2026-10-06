// Keep payment request objects inside the billing request package.
package com.codevictims.bayt.billing.request;

// Import the enum used for the selected payment method.
import com.codevictims.bayt.billing.type.PaymentMethod;
// Import validation to require a value.
import jakarta.validation.constraints.NotNull;
// Import validation to require a positive number.
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
// Import BigDecimal for money amounts.
import java.math.BigDecimal;

// Carry the data needed to create a payment.
@Setter
@Getter
public class CreatePaymentRequest {
    // Save the tenant ID received from the client.
    // Return the tenant ID from the request.
    // Require the tenant ID in the request.
    @NotNull
    // Store the tenant who made the payment.
    private Long tenantId;
    // Save the lease ID received from the client.
    // Return the lease ID from the request.
    // Require the lease ID in the request.
    @NotNull
    // Store the lease connected to the payment.
    private Long leaseId;
    // Save the payment amount from the request.
    // Return the requested payment amount.
    // Require the amount in the request.
    @NotNull
    // Make sure the payment amount is greater than zero.
    @Positive
    // Store the amount received for the payment.
    private BigDecimal amount;
    // Save the payment method from the request.
    // Return the selected payment method.
    // Require the payment method in the request.
    @NotNull
    // Store how the tenant paid.
    private PaymentMethod method;
    // Save the reference number sent by the client.
    // Return the optional payment reference number.
    // Store an optional reference number for the payment.
    private String referenceNumber;

}
