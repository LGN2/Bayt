// Place payment response objects in the billing response package.
package main.java.com.billing.response;

// Include the payment method in the API response.
import main.java.com.billing.type.PaymentMethod;
// Include the payment status in the API response.
import main.java.com.billing.type.PaymentStatus;
// Return the payment amount with decimal precision.
import java.math.BigDecimal;
// Return the payment date and time.
import java.time.LocalDateTime;

// Send payment details back to the API client.
public class PaymentResponse {
    // Store the payment ID returned to the client.
    private Long id;
    // Store the tenant ID returned to the client.
    private Long tenantId;
    // Store the lease ID returned to the client.
    private Long leaseId;
    // Store the payment amount returned to the client.
    private BigDecimal amount;
    // Store the payment method returned to the client.
    private PaymentMethod method;
    // Store the payment status returned to the client.
    private PaymentStatus status;
    // Store the payment date returned to the client.
    private LocalDateTime paymentDate;
    // Store the reference number returned to the client.
    private String referenceNumber;

    // Return the payment ID.
    public Long getId() {
        return id; }
    // Set the payment ID in the response.
    public void setId(Long id) {
        this.id = id; }
    // Return the tenant ID.
    public Long getTenantId() {
        return tenantId; }
    // Set the tenant ID in the response.
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId; }
    // Return the lease ID.
    public Long getLeaseId() {
        return leaseId; }
    // Set the lease ID in the response.
    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId; }
    // Return the payment amount.
    public BigDecimal getAmount() {
        return amount; }
    // Set the payment amount in the response.
    public void setAmount(BigDecimal amount) {
        this.amount = amount; }
    // Return the payment method.
    public PaymentMethod getMethod() {
        return method; }
    // Set the payment method in the response.
    public void setMethod(PaymentMethod method) {
        this.method = method; }
    // Return the payment status.
    public PaymentStatus getStatus() {
        return status; }
    // Set the payment status in the response.
    public void setStatus(PaymentStatus status) {
        this.status = status; }
    // Return the payment date.
    public LocalDateTime getPaymentDate() {
        return paymentDate; }
    // Set the payment date in the response.
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate; }
    // Return the reference number.
    public String getReferenceNumber() {
        return referenceNumber; }
    // Set the reference number in the response.
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber; }
}
