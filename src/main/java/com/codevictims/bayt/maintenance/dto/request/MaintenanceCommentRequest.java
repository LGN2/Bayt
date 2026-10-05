package com.codevictims.bayt.maintenance.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record MaintenanceCommentRequest(@NotBlank @Size(max = 2000) String note)
        implements RequestDto {}
