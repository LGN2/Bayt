package com.codevictims.bayt.tenancy.response;

import java.time.Instant;
import java.time.LocalDate;

public record LeadResponse(
        Long id,
        Long buildingId,
        Long unitId,
        String name,
        String phone,
        String status,
        Instant viewingAt,
        LocalDate followUpDate,
        String notes
) {
}