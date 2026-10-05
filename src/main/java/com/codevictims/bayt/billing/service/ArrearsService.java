package com.property.billing.service;

import com.property.billing.entity.RentDue;
import com.property.billing.repository.RentDueRepository;
import com.property.billing.response.ArrearsResponse;
import com.property.billing.response.FinancialSummaryResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArrearsService {
    private final RentDueRepository rentDueRepository;

    public ArrearsService(RentDueRepository rentDueRepository) {
        this.rentDueRepository = rentDueRepository;
    }

    public List<ArrearsResponse> getArrears() {
        List<ArrearsResponse> responses = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (RentDue due : rentDueRepository.findByDueDateBeforeOrderByDueDateAsc(today)) {
            if (!due.isPaid()) {
                ArrearsResponse response = new ArrearsResponse();
                response.setRentDueId(due.getId());
                response.setLeaseId(due.getLeaseId());
                response.setDueDate(due.getDueDate());
                response.setDaysLate(ChronoUnit.DAYS.between(due.getDueDate(), today));
                response.setOutstandingAmount(due.getOutstandingAmount());
                responses.add(response);
            }
        }
        return responses;
    }

    public FinancialSummaryResponse getSummary() {
        BigDecimal totalDue = BigDecimal.ZERO;
        BigDecimal totalPaid = BigDecimal.ZERO;
        BigDecimal totalOutstanding = BigDecimal.ZERO;
        int overdue = 0;
        LocalDate today = LocalDate.now();

        for (RentDue due : rentDueRepository.findAll()) {
            totalDue = totalDue.add(due.getAmount());
            totalPaid = totalPaid.add(due.getPaidAmount());
            totalOutstanding = totalOutstanding.add(due.getOutstandingAmount());
            if (due.getDueDate().isBefore(today) && !due.isPaid()) {
                overdue++;
            }
        }

        FinancialSummaryResponse response = new FinancialSummaryResponse();
        response.setTotalRentDue(totalDue);
        response.setTotalPaid(totalPaid);
        response.setTotalOutstanding(totalOutstanding);
        response.setOverdueItems(overdue);
        return response;
    }
}
