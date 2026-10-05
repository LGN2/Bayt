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

    public ChequeResponse create(CreateChequeRequest request) {
        Cheque cheque = new Cheque();
        cheque.setTenantId(request.getTenantId());
        cheque.setChequeNumber(request.getChequeNumber());
        cheque.setBankName(request.getBankName());
        cheque.setChequeDate(request.getChequeDate());
        cheque.setAmount(request.getAmount());
        cheque.setStatus(ChequeStatus.RECEIVED);
        cheque = chequeRepository.save(cheque);

        saveHistory(cheque, null, ChequeStatus.RECEIVED, "Cheque received");
        return toResponse(cheque);
    }

    public ChequeResponse changeStatus(Long id, ChequeTransitionRequest request) {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cheque not found"));

        ChequeStatus oldStatus = cheque.getStatus();
        cheque.setStatus(request.getNewStatus());
        cheque = chequeRepository.save(cheque);
        saveHistory(cheque, oldStatus, request.getNewStatus(), request.getNote());
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
