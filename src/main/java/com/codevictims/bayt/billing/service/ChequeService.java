// Keep cheque business logic inside the billing service package.
package com.codevictims.bayt.billing.service;

// Import the cheque entity saved in the database.
import com.codevictims.bayt.billing.entity.Cheque;
// Import the history entity used to record status changes.
import com.codevictims.bayt.billing.entity.ChequeStatusHistory;
// Import the repository used for cheque records.
import com.codevictims.bayt.billing.repository.ChequeRepository;
// Import the repository used for cheque status history records.
import com.codevictims.bayt.billing.repository.ChequeStatusHistoryRepository;
// Import the request used to change cheque status.
import com.codevictims.bayt.billing.request.ChequeTransitionRequest;
// Import the request used to create cheques.
import com.codevictims.bayt.billing.request.CreateChequeRequest;
// Import the response returned for cheque data.
import com.codevictims.bayt.billing.response.ChequeResponse;
// Import the response returned for cheque status history.
import com.codevictims.bayt.billing.response.ChequeStatusHistoryResponse;
// Import the cheque status enum.
import com.codevictims.bayt.billing.type.ChequeStatus;
// Import the Spring service annotation.
import org.springframework.stereotype.Service;

// Import LocalDateTime for status history timestamps.
import java.time.LocalDateTime;
// Import ArrayList for building response lists.
import java.util.ArrayList;
// Import List for returning many responses.
import java.util.List;

// Mark this class as a Spring service.
@Service
// Handle cheque business operations.
public class ChequeService {
    // Store the repository used for cheque records.
    private final ChequeRepository chequeRepository;
    // Store the repository used for cheque status history records.
    private final ChequeStatusHistoryRepository historyRepository;

    // Receive service dependencies through the constructor.
    public ChequeService(ChequeRepository chequeRepository,
                         ChequeStatusHistoryRepository historyRepository) {
        // Keep the cheque repository for later database work.
        this.chequeRepository = chequeRepository;
        // Keep the history repository for status history saves and lookups.
        this.historyRepository = historyRepository;
    }

    // Create and store a new cheque from request data.
    public ChequeResponse create(CreateChequeRequest request) {
        // Create a new cheque entity.
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
        // Start new cheques with the received status.
        cheque.setStatus(ChequeStatus.RECEIVED);
        // Save the new cheque in the database.
        cheque = chequeRepository.save(cheque);

        // Record the first status history item for this cheque.
        saveHistory(cheque, null, ChequeStatus.RECEIVED, "Cheque received");
        // Convert the saved cheque into a response.
        return toResponse(cheque);
    }

    // Change the status of an existing cheque.
    public ChequeResponse changeStatus(Long id, ChequeTransitionRequest request) {
        // Find the cheque by its ID.
        Cheque cheque = chequeRepository.findById(id)
                // Stop when the requested cheque does not exist.
                .orElseThrow(() -> new IllegalArgumentException("Cheque not found"));

        // Keep the current status before changing it.
        ChequeStatus oldStatus = cheque.getStatus();
        // Apply the new status from the request.
        cheque.setStatus(request.getNewStatus());
        // Save the updated cheque in the database.
        cheque = chequeRepository.save(cheque);
        // Store a history record for this status change.
        saveHistory(cheque, oldStatus, request.getNewStatus(), request.getNote());
        // Convert the updated cheque into a response.
        return toResponse(cheque);
    }

    // Return every cheque as response objects.
    public List<ChequeResponse> getAll() {
        // Create a list to collect cheque responses.
        List<ChequeResponse> responses = new ArrayList<>();
        // Read every cheque from the database.
        for (Cheque cheque : chequeRepository.findAll()) {
            // Convert each cheque and add it to the result list.
            responses.add(toResponse(cheque));
        }
        // Return all converted cheque responses.
        return responses;
    }

    // Return status history records for one cheque.
    public List<ChequeStatusHistoryResponse> getHistory(Long chequeId) {
        // Create a list to collect history responses.
        List<ChequeStatusHistoryResponse> responses = new ArrayList<>();
        // Find history records for this cheque, newest first.
        for (ChequeStatusHistory item : historyRepository.findByChequeIdOrderByChangedAtDesc(chequeId)) {
            // Create a response for one history record.
            ChequeStatusHistoryResponse response = new ChequeStatusHistoryResponse();
            // Copy the history record ID.
            response.setId(item.getId());
            // Copy the linked cheque ID.
            response.setChequeId(item.getCheque().getId());
            // Copy the previous cheque status.
            response.setOldStatus(item.getOldStatus());
            // Copy the new cheque status.
            response.setNewStatus(item.getNewStatus());
            // Copy when the status changed.
            response.setChangedAt(item.getChangedAt());
            // Copy the note saved with the change.
            response.setNote(item.getNote());
            // Add this history response to the result list.
            responses.add(response);
        }
        // Return the cheque status history responses.
        return responses;
    }

    // Save one cheque status history record.
    private void saveHistory(Cheque cheque, ChequeStatus oldStatus,
                             ChequeStatus newStatus, String note) {
        // Create a new history entity.
        ChequeStatusHistory history = new ChequeStatusHistory();
        // Link the history record to the cheque.
        history.setCheque(cheque);
        // Store the status before the change.
        history.setOldStatus(oldStatus);
        // Store the status after the change.
        history.setNewStatus(newStatus);
        // Store the current time as the change time.
        history.setChangedAt(LocalDateTime.now());
        // Store the note for this status change.
        history.setNote(note);
        // Save the history record in the database.
        historyRepository.save(history);
    }

    // Convert a cheque entity into a response object.
    private ChequeResponse toResponse(Cheque cheque) {
        // Create the response object.
        ChequeResponse response = new ChequeResponse();
        // Copy the cheque ID into the response.
        response.setId(cheque.getId());
        // Copy the tenant ID into the response.
        response.setTenantId(cheque.getTenantId());
        // Copy the cheque number into the response.
        response.setChequeNumber(cheque.getChequeNumber());
        // Copy the bank name into the response.
        response.setBankName(cheque.getBankName());
        // Copy the cheque date into the response.
        response.setChequeDate(cheque.getChequeDate());
        // Copy the cheque amount into the response.
        response.setAmount(cheque.getAmount());
        // Copy the cheque status into the response.
        response.setStatus(cheque.getStatus());
        // Return the completed cheque response.
        return response;
    }
}
