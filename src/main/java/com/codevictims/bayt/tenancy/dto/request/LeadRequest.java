package com.codevictims.bayt.tenancy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

public record LeadRequest(

        @NotNull
        Long buildingId,

        @NotNull
        Long unitId,

        @NotBlank
        @Size(max = 255)
        String name,

        @NotBlank
        @Size(max = 80)
        String phone,

        @NotBlank
        @Size(max = 40)
        String status,

        Instant viewingAt,

        LocalDate followUpDate,

        @NotBlank
        String notes
) {
}