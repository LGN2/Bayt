package com.codevictims.bayt.tenancy.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TerminateLeaseRequest(

        @NotNull
        LocalDate terminatedOn,

        String reason
) {
}