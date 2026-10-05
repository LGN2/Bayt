package com.codevictims.bayt.tenancy.request;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MunicipalityRequest(

        @Size(max = 40)
        String municipalityStatus,

        String municipalityAuthority,

        String municipalityReference,

        BigDecimal municipalityFee
) {
}