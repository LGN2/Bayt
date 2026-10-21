package com.codevictims.bayt.billing.service;
import com.codevictims.bayt.billing.entity.Due;
import com.codevictims.bayt.billing.response.RentDueResponse;
import com.codevictims.bayt.billing.dto.response.DueBalanceResponse;
import com.codevictims.bayt.billing.repository.DueRepository;
import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.common.repository.PersistenceSupport;
import com.codevictims.bayt.security.service.Access;
import com.codevictims.bayt.tenancy.entity.Lease;
import com.codevictims.bayt.tenancy.service.LeaseService;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/** Compatibility API backed exclusively by the authoritative Due/allocation ledger. */
@Service
@Transactional
public class RentScheduleService {
 private final FinanceService finance; private final LeaseService leases; private final Access access;
 private final PersistenceSupport db; private final DueRepository dues; private final Clock clock;
 public RentScheduleService(FinanceService finance, LeaseService leases, Access access, PersistenceSupport db, DueRepository dues, Clock clock) {
 this.finance=finance; this.leases=leases; this.access=access; this.db=db; this.dues=dues; this.clock=clock;
 }
 public RentDueResponse createRentDue(Long leaseId, LocalDate date, BigDecimal amount) {
 access.lease(leaseId,true); Lease l=db.lock(Lease.class,leaseId);
 if(date==null || amount==null || amount.signum()<=0 || amount.scale()>3) throw ApiException.invalid("INVALID_INPUT");
 // Schedules are created atomically with the lease. A retry may only retrieve an identical entry.
 Due d=dues.findByLeaseInAllocationOrder(leaseId).stream().filter(x->x.dueDate.equals(date)).findFirst().orElseThrow(()->ApiException.conflict("USE_LEASE_SCHEDULE"));
 if(d.amount.compareTo(amount)!=0) throw ApiException.conflict("SCHEDULE_CONFLICT");
 return response(finance.view(d,LocalDate.now(clock)));
 }
 public List<RentDueResponse> getAll() { return leases.leases(null).stream().flatMap(l->getByLease(l.id).stream()).toList(); }
 public List<RentDueResponse> getByLease(Long id) { return finance.dues(id,LocalDate.now(clock)).stream().map(this::response).toList(); }
 private RentDueResponse response(DueBalanceResponse d) { var r=new RentDueResponse();r.setId(d.id());r.setLeaseId(d.leaseId());r.setDueDate(d.dueDate());r.setAmount(d.cancelled()?BigDecimal.ZERO.setScale(3):d.amount());r.setPaidAmount(d.paid());r.setOutstandingAmount(d.outstanding());r.setStatus(d.cancelled()?"CANCELLED":d.outstanding().signum()==0?"PAID":"UNPAID");return r; }
}
