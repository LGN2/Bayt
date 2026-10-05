package com.codevictims.bayt.common.repository;

import com.codevictims.bayt.common.entity.BaseEntity;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryCatalog {
    private final Map<Class<?>, JpaRepository<?, Long>> repositories = new HashMap<>();

    public RepositoryCatalog(
            com.codevictims.bayt.billing.repository.ChequeRepository cheque,
            com.codevictims.bayt.billing.repository.PaymentRepository payment,
            com.codevictims.bayt.billing.repository.DueRepository due,
            com.codevictims.bayt.billing.repository.DepositEntryRepository depositEntry,
            com.codevictims.bayt.billing.repository.TaxPolicyRepository taxPolicy,
            com.codevictims.bayt.billing.repository.ExpenseRepository expense,
            com.codevictims.bayt.billing.repository.AllocationRepository allocation,
            com.codevictims.bayt.billing.repository.FollowUpRepository followUp,
            com.codevictims.bayt.common.repository.AuditEventRepository auditEvent,
            com.codevictims.bayt.common.repository.DocumentRepository document,
            com.codevictims.bayt.account.repository.UserRepository userAccount,
            com.codevictims.bayt.account.repository.BuildingAccessRepository buildingAccess,
            com.codevictims.bayt.maintenance.repository.PreventiveTaskRepository
                    preventiveTask,
            com.codevictims.bayt.maintenance.repository.VendorProfileRepository
                    vendorProfile,
            com.codevictims.bayt.maintenance.repository.MaintenanceRepository maintenance,
            com.codevictims.bayt.property.repository.MeterReadingRepository meterReading,
            com.codevictims.bayt.property.repository.NoticeRepository notice,
            com.codevictims.bayt.property.repository.ParkingRepository parking,
            com.codevictims.bayt.property.repository.UnitRepository unit,
            com.codevictims.bayt.property.repository.BuildingRepository building,
            com.codevictims.bayt.property.repository.MeterRepository meter,
            com.codevictims.bayt.property.repository.GuardCheckInRepository guardCheckIn,
            com.codevictims.bayt.property.repository.SafetyRecordRepository safetyRecord,
            com.codevictims.bayt.property.repository.VisitRepository visit,
            com.codevictims.bayt.tenancy.repository.LeadRepository lead,
            com.codevictims.bayt.tenancy.repository.TenantRepository tenant,
            com.codevictims.bayt.tenancy.repository.LeaseRepository lease) {
        repositories.put(com.codevictims.bayt.billing.entity.Cheque.class, cheque);
        repositories.put(com.codevictims.bayt.billing.entity.Payment.class, payment);
        repositories.put(com.codevictims.bayt.billing.entity.Due.class, due);
        repositories.put(
                com.codevictims.bayt.billing.entity.DepositEntry.class, depositEntry);
        repositories.put(com.codevictims.bayt.billing.entity.TaxPolicy.class, taxPolicy);
        repositories.put(com.codevictims.bayt.billing.entity.Expense.class, expense);
        repositories.put(
                com.codevictims.bayt.billing.entity.Allocation.class, allocation);
        repositories.put(com.codevictims.bayt.billing.entity.FollowUp.class, followUp);
        repositories.put(com.codevictims.bayt.common.entity.AuditEvent.class, auditEvent);
        repositories.put(com.codevictims.bayt.common.entity.Document.class, document);
        repositories.put(
                com.codevictims.bayt.account.entity.UserAccount.class, userAccount);
        repositories.put(
                com.codevictims.bayt.account.entity.BuildingAccess.class, buildingAccess);
        repositories.put(
                com.codevictims.bayt.maintenance.entity.PreventiveTask.class, preventiveTask);
        repositories.put(
                com.codevictims.bayt.maintenance.entity.VendorProfile.class, vendorProfile);
        repositories.put(
                com.codevictims.bayt.maintenance.entity.Maintenance.class, maintenance);
        repositories.put(
                com.codevictims.bayt.property.entity.MeterReading.class, meterReading);
        repositories.put(com.codevictims.bayt.property.entity.Notice.class, notice);
        repositories.put(com.codevictims.bayt.property.entity.Parking.class, parking);
        repositories.put(com.codevictims.bayt.property.entity.Unit.class, unit);
        repositories.put(com.codevictims.bayt.property.entity.Building.class, building);
        repositories.put(com.codevictims.bayt.property.entity.Meter.class, meter);
        repositories.put(
                com.codevictims.bayt.property.entity.GuardCheckIn.class, guardCheckIn);
        repositories.put(
                com.codevictims.bayt.property.entity.SafetyRecord.class, safetyRecord);
        repositories.put(com.codevictims.bayt.property.entity.Visit.class, visit);
        repositories.put(com.codevictims.bayt.tenancy.entity.Lead.class, lead);
        repositories.put(com.codevictims.bayt.tenancy.entity.Tenant.class, tenant);
        repositories.put(com.codevictims.bayt.tenancy.entity.Lease.class, lease);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> JpaRepository<T, Long> repository(Class<T> type) {
        JpaRepository<?, Long> repository = repositories.get(type);
        if (repository == null)
            throw new IllegalArgumentException("No repository for " + type.getName());
        return (JpaRepository<T, Long>) repository;
    }
}
