package com.codevictims.bayt.billing.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record ReversalRequest(@NotBlank @Size(max = 255) String reason) implements RequestDto {}
