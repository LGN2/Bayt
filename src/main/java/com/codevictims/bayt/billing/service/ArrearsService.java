package com.codevictims.bayt.billing.service;
import com.codevictims.bayt.billing.response.*;
import com.codevictims.bayt.security.service.Access;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.springframework.stereotype.Service;
@Service
public class ArrearsService {
 private final RentScheduleService schedules; private final Clock clock; private final Access access;
 public ArrearsService(RentScheduleService schedules,Clock clock,Access access){this.schedules=schedules;this.clock=clock;this.access=access;}
 public List<ArrearsResponse> getArrears(){access.role("OWNER","MANAGER");return schedules.getAll().stream().filter(d->d.getDueDate().isBefore(LocalDate.now(clock))&&d.getOutstandingAmount().signum()>0).map(d->{var r=new ArrearsResponse();r.setRentDueId(d.getId());r.setLeaseId(d.getLeaseId());r.setDueDate(d.getDueDate());r.setOutstandingAmount(d.getOutstandingAmount());r.setDaysLate(ChronoUnit.DAYS.between(d.getDueDate(),LocalDate.now(clock)));return r;}).toList();}
 public FinancialSummaryResponse getSummary(){access.role("OWNER","MANAGER");var rows=schedules.getAll();var r=new FinancialSummaryResponse();r.setTotalRentDue(rows.stream().map(RentDueResponse::getAmount).reduce(BigDecimal.ZERO.setScale(3),BigDecimal::add));r.setTotalPaid(rows.stream().map(RentDueResponse::getPaidAmount).reduce(BigDecimal.ZERO.setScale(3),BigDecimal::add));r.setTotalOutstanding(rows.stream().map(RentDueResponse::getOutstandingAmount).reduce(BigDecimal.ZERO.setScale(3),BigDecimal::add));r.setOverdueItems(getArrears().size());return r;}
}
