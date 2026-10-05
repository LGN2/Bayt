package com.codevictims.bayt.maintenance.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record ApproveSummaryRequest(
        @NotBlank @Size(max = 255) String summary, @NotBlank @Size(max = 255) String category)
        implements RequestDto {}
