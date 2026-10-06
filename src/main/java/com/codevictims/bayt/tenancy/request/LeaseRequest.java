package com.codevictims.bayt.tenancy.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LeaseRequest(

        @NotNull
        Long buildingId,

        @NotNull
        Long unitId,

        @NotNull
        Long tenantId,

        @NotNull
        LocalDate startDate,

        @NotNull
        @Positive
        Integer months,

        @NotNull
        @Positive
        BigDecimal rent,

        @Positive
        BigDecimal deposit,

        @NotNull
        @Size(max = 40)
        String taxTreatment,

        @NotNull
        BigDecimal taxRate,

        @NotNull
        String supplyClassification,

        @NotNull
        Boolean ownerTaxRegistered,

        @Size(max = 40)
        String municipalityStatus,

        String municipalityAuthority,

        String municipalityReference,

        @Positive
        BigDecimal municipalityFee,

        Long previousLeaseId
) {
}