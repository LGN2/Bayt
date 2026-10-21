package com.codevictims.bayt.tenancy.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.*;

/** Editable input only. Ownership and ledger state are assigned by the service. */
public record MunicipalityRequest(
    @NotBlank @Size(max = 255) String municipalityStatus,
    @Size(max = 255) String municipalityAuthority,
    @Size(max = 255) String municipalityReference,
    @NotNull BigDecimal municipalityFee)
    implements RequestDto {}
