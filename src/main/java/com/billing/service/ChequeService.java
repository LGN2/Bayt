// Place cheque business logic in the billing service package.
package com.property.billing.service;

// Use the Cheque entity for database records.
import com.property.billing.entity.Cheque;
// Use cheque status history records without copying that entity section.
import com.property.billing.entity.ChequeStatusHistory;
// Use the repository that saves and reads cheques.
import com.property.billing.repository.ChequeRepository;
// Use the repository that saves and reads cheque status history.
import com.property.billing.repository.ChequeStatusHistoryRepository;
// Use the request data for cheque status changes.
import com.property.billing.request.ChequeTransitionRequest;
// Use the request data for creating cheques.
import com.property.billing.request.CreateChequeRequest;
// Use the response object returned for cheque details.
import com.property.billing.response.ChequeResponse;
// Use the response object returned for cheque history details.
import com.property.billing.response.ChequeStatusHistoryResponse;
// Use the cheque status enum.
import com.property.billing.type.ChequeStatus;
// Mark this class as a Spring service.
import org.springframework.stereotype.Service;

// Store the time when a status history entry is created.
import java.time.LocalDateTime;
// Build response lists manually.
import java.util.ArrayList;
// Return lists of cheque responses or history responses.
import java.util.List;

// Register this class as a Spring service bean.
@Service
// Handle business logic for cheques.
public class ChequeService {
    // Store the repository used for cheque records.
    private final ChequeRepository chequeRepository;
    // Store the repository used for cheque status history records.
    private final ChequeStatusHistoryRepository historyRepository;

    // Receive required repositories through constructor injection.
    public ChequeService(ChequeRepository chequeRepository,
                         // Receive the history repository used for audit records.
                         ChequeStatusHistoryRepository historyRepository) {
        // Keep the cheque repository for later operations.
        this.chequeRepository = chequeRepository;
        // Keep the history repository for later operations.
        this.historyRepository = historyRepository;
    }

    // Create a new cheque from request values.
    public ChequeResponse create(CreateChequeRequest request) {
        // Build a new empty cheque entity.
        Cheque cheque = new Cheque();
        // Copy the tenant ID from the request.
        cheque.setTenantId(request.getTenantId());
        // Copy the cheque number from the request.
        cheque.setChequeNumber(request.getChequeNumber());
        // Copy the bank name from the request.
        cheque.setBankName(request.getBankName());
        // Copy the cheque date from the request.
        cheque.setChequeDate(request.getChequeDate());
        // Copy the cheque amount from the request.
        cheque.setAmount(request.getAmount());
        // Start the cheque in the received status.
        cheque.setStatus(ChequeStatus.RECEIVED);
        // Save the new cheque in the database.
        cheque = chequeRepository.save(cheque);

        // Record the first status history entry for this cheque.
        saveHistory(cheque, null, ChequeStatus.RECEIVED, "Cheque received");
        // Convert the saved cheque into the API response.
        return toResponse(cheque);
    }

    // Change the status of an existing cheque.
    public ChequeResponse changeStatus(Long id, ChequeTransitionRequest request) {
        // Search for the cheque by its ID.
        Cheque cheque = chequeRepository.findById(id)
                // Stop if no cheque exists for this ID.
                .orElseThrow(() -> new IllegalArgumentException("Cheque not found"));

        // Remember the current status before changing it.
        ChequeStatus oldStatus = cheque.getStatus();
        // Set the new status from the request.
        cheque.setStatus(request.getNewStatus());
        // Save the cheque with the updated status.
        cheque = chequeRepository.save(cheque);
        // Record the status change in cheque history.
        saveHistory(cheque, oldStatus, request.getNewStatus(), request.getNote());
        // Return the updated cheque details.
        return toResponse(cheque);
    }

    public List<ChequeResponse> getAll() {
        List<ChequeResponse> responses = new ArrayList<>();
        for (Cheque cheque : chequeRepository.findAll()) {
            responses.add(toResponse(cheque));
        }
        return responses;
    }

    public List<ChequeStatusHistoryResponse> getHistory(Long chequeId) {
        List<ChequeStatusHistoryResponse> responses = new ArrayList<>();
        for (ChequeStatusHistory item : historyRepository.findByChequeIdOrderByChangedAtDesc(chequeId)) {
            ChequeStatusHistoryResponse response = new ChequeStatusHistoryResponse();
            response.setId(item.getId());
            response.setChequeId(item.getCheque().getId());
            response.setOldStatus(item.getOldStatus());
            response.setNewStatus(item.getNewStatus());
            response.setChangedAt(item.getChangedAt());
            response.setNote(item.getNote());
            responses.add(response);
        }
        return responses;
    }

    private void saveHistory(Cheque cheque, ChequeStatus oldStatus,
                             ChequeStatus newStatus, String note) {
        ChequeStatusHistory history = new ChequeStatusHistory();
        history.setCheque(cheque);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        history.setNote(note);
        historyRepository.save(history);
    }

    private ChequeResponse toResponse(Cheque cheque) {
        ChequeResponse response = new ChequeResponse();
        response.setId(cheque.getId());
        response.setTenantId(cheque.getTenantId());
        response.setChequeNumber(cheque.getChequeNumber());
        response.setBankName(cheque.getBankName());
        response.setChequeDate(cheque.getChequeDate());
        response.setAmount(cheque.getAmount());
        response.setStatus(cheque.getStatus());
        return response;
    }
}
