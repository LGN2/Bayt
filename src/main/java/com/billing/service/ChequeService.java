package com.property.billing.service;

import com.property.billing.entity.Cheque;
import com.property.billing.entity.ChequeStatusHistory;
import com.property.billing.repository.ChequeRepository;
import com.property.billing.repository.ChequeStatusHistoryRepository;
import com.property.billing.request.ChequeTransitionRequest;
import com.property.billing.request.CreateChequeRequest;
import com.property.billing.response.ChequeResponse;
import com.property.billing.response.ChequeStatusHistoryResponse;
import com.property.billing.type.ChequeStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChequeService {
    private final ChequeRepository chequeRepository;
    private final ChequeStatusHistoryRepository historyRepository;

    public ChequeService(ChequeRepository chequeRepository,
                         ChequeStatusHistoryRepository historyRepository) {
        this.chequeRepository = chequeRepository;
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
