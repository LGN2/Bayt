// Place payment request objects in the billing request package.
package main.java.com.codevictims.bayt.billing.request;

// Use the selected method for the new payment.
import main.java.com.codevictims.bayt.billing.type.PaymentMethod;
// Require fields to be present in the request.
import jakarta.validation.constraints.NotNull;
// Require the amount to be greater than zero.
import jakarta.validation.constraints.Positive;
// Store the requested payment amount.
import java.math.BigDecimal;

// Carry the data needed to create a payment.
public class CreatePaymentRequest {
    // Require the tenant ID.
    @NotNull
    // Store the tenant making the payment.
    private Long tenantId;
    // Require the lease ID.
    @NotNull
    // Store the lease connected to the payment.
    private Long leaseId;
    // Require the payment amount.
    @NotNull
    // Make sure the amount is positive.
    @Positive
    // Store the amount sent by the client.
    private BigDecimal amount;
    // Require the payment method.
    @NotNull
    // Store how the payment was made.
    private PaymentMethod method;
    // Store an optional reference number from the client.
    private String referenceNumber;

    // Return the tenant ID from the request.
    public Long getTenantId() {
        // Give the service the tenant ID value.
        return tenantId; }
    // Set the tenant ID on the request.
    public void setTenantId(Long tenantId) {
        // Save the tenant ID received from the client.
        this.tenantId = tenantId; }
    // Return the lease ID from the request.
    public Long getLeaseId() {
        return leaseId; }
    // Set the lease ID on the request.
    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId; }
    // Return the requested payment amount.
    public BigDecimal getAmount() {
        // Give the service the requested amount.
        return amount; }
    // Set the requested payment amount.
    public void setAmount(BigDecimal amount) {
        // Save the amount received from the client.
        this.amount = amount; }
    // Return the requested payment method.
    public PaymentMethod getMethod() {
        // Give the service the selected method.
        return method; }
    // Set the requested payment method.
    public void setMethod(PaymentMethod method) {
        // Save the method received from the client.
        this.method = method; }
    // Return the optional reference number.
    public String getReferenceNumber() {
        return referenceNumber; }
    // Set the optional reference number.
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber; }
}
