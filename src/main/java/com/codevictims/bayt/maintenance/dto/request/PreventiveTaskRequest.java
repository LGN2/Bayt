package com.codevictims.bayt.maintenance.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record PreventiveTaskRequest(
        @NotNull @Positive Long buildingId,
        @Positive Long unitId,
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 255) String category,
        Integer intervalDays,
        @NotNull
        @org.springframework.format.annotation.DateTimeFormat(
                iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
        LocalDate nextDue,
        Boolean enabled)
        implements RequestDto {}
