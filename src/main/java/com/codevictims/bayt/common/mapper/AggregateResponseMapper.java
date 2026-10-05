package com.codevictims.bayt.common.mapper;

import com.codevictims.bayt.common.entity.BaseEntity;
import java.util.*;

public final class AggregateResponseMapper {
    private AggregateResponseMapper() {}

    public static Object map(Object value) {
        if (value instanceof com.codevictims.bayt.billing.entity.Cheque entity)
            return com.codevictims.bayt.billing.mapper.ChequeMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.billing.entity.Payment entity)
            return com.codevictims.bayt.billing.mapper.PaymentMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.billing.entity.Due entity)
            return com.codevictims.bayt.billing.mapper.DueMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.billing.entity.DepositEntry entity)
            return com.codevictims.bayt.billing.mapper.DepositEntryMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.billing.entity.TaxPolicy entity)
            return com.codevictims.bayt.billing.mapper.TaxPolicyMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.billing.entity.Expense entity)
            return com.codevictims.bayt.billing.mapper.ExpenseMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.billing.entity.Allocation entity)
            return com.codevictims.bayt.billing.mapper.AllocationMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.billing.entity.FollowUp entity)
            return com.codevictims.bayt.billing.mapper.FollowUpMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.common.entity.AuditEvent entity)
            return com.codevictims.bayt.common.mapper.AuditEventMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.common.entity.Document entity)
            return com.codevictims.bayt.common.mapper.DocumentMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.account.entity.UserAccount entity)
            return com.codevictims.bayt.account.mapper.UserAccountMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.account.entity.BuildingAccess entity)
            return com.codevictims.bayt.account.mapper.BuildingAccessMapper.toResponse(
                    entity);
        if (value
                instanceof com.codevictims.bayt.maintenance.entity.PreventiveTask entity)
            return com.codevictims.bayt.maintenance.mapper.PreventiveTaskMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.maintenance.entity.VendorProfile entity)
            return com.codevictims.bayt.maintenance.mapper.VendorProfileMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.maintenance.entity.Maintenance entity)
            return com.codevictims.bayt.maintenance.mapper.MaintenanceMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.property.entity.MeterReading entity)
            return com.codevictims.bayt.property.mapper.MeterReadingMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.property.entity.Notice entity)
            return com.codevictims.bayt.property.mapper.NoticeMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.property.entity.Parking entity)
            return com.codevictims.bayt.property.mapper.ParkingMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.property.entity.Unit entity)
            return com.codevictims.bayt.property.mapper.UnitMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.property.entity.Building entity)
            return com.codevictims.bayt.property.mapper.BuildingMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.property.entity.Meter entity)
            return com.codevictims.bayt.property.mapper.MeterMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.property.entity.GuardCheckIn entity)
            return com.codevictims.bayt.property.mapper.GuardCheckInMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.property.entity.SafetyRecord entity)
            return com.codevictims.bayt.property.mapper.SafetyRecordMapper.toResponse(
                    entity);
        if (value instanceof com.codevictims.bayt.property.entity.Visit entity)
            return com.codevictims.bayt.property.mapper.VisitMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.tenancy.entity.Lead entity)
            return com.codevictims.bayt.tenancy.mapper.LeadMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.tenancy.entity.Tenant entity)
            return com.codevictims.bayt.tenancy.mapper.TenantMapper.toResponse(entity);
        if (value instanceof com.codevictims.bayt.tenancy.entity.Lease entity)
            return com.codevictims.bayt.tenancy.mapper.LeaseMapper.toResponse(entity);
        if (value instanceof Map<?, ?> values) {
            Map<String, Object> result = new LinkedHashMap<>();
            values.forEach((k, v) -> result.put(k.toString(), map(v)));
            return result;
        }
        if (value instanceof List<?> values)
            return values.stream().map(AggregateResponseMapper::map).toList();
        if (value instanceof BaseEntity) throw new IllegalArgumentException("Missing response mapping");
        return value;
    }
}
