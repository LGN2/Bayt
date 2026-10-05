package com.codevictims.bayt.maintenance.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record MaintenanceRequest(
        @NotNull @Positive Long unitId,
        @Positive Long tenantId,
        @NotBlank @Size(max = 2000) String description,
        @NotBlank @Size(max = 255) String category,
        Boolean urgent)
        implements RequestDto {}
