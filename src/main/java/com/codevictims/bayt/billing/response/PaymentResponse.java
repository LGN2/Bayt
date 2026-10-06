// Keep payment response objects inside the billing response package.
package com.codevictims.bayt.billing.response;

// Import the payment method returned to the client.
import com.codevictims.bayt.billing.type.PaymentMethod;
// Import the payment status returned to the client.
import com.codevictims.bayt.billing.type.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
// Import BigDecimal for returned money values.
import java.math.BigDecimal;
// Import LocalDateTime for the payment date value.
import java.time.LocalDateTime;

// Send payment details back to the client.
@Setter
@Getter
public class PaymentResponse {
    // Set the payment ID in the response.
    // Return the payment ID.
    // Store the payment ID returned in the response.
    private Long id;
    // Set the tenant ID in the response.
    // Return the tenant ID for this payment.
    // Store the tenant ID connected to the payment.
    private Long tenantId;
    // Set the lease ID in the response.
    // Return the lease ID for this payment.
    // Store the lease ID connected to the payment.
    private Long leaseId;
    // Set the amount shown to the client.
    // Return the amount paid.
    // Store the amount paid.
    private BigDecimal amount;
    // Set the payment method in the response.
    // Return the payment method.
    // Store the payment method shown in the response.
    private PaymentMethod method;
    // Set the status shown to the client.
    // Return the payment status.
    // Store the current payment status.
    private PaymentStatus status;
    // Set the payment date in the response.
    // Return the recorded payment date.
    // Store the date and time when the payment was recorded.
    private LocalDateTime paymentDate;
    // Set the reference number in the response.
    // Return the payment reference number.
    // Store the optional reference number returned to the client.
    private String referenceNumber;

}
