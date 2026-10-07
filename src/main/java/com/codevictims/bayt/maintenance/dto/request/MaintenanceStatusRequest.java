package com.codevictims.bayt.maintenance.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record MaintenanceStatusRequest(
        @NotBlank @Size(max = 255) String status,
        @Positive Long assignedTo,
        @Size(max = 2000) String note)
        implements RequestDto {}
