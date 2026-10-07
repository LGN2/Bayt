package com.codevictims.bayt.tenancy.dto.request;

import com.codevictims.bayt.common.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTenantRequest(

        @NotNull
        Long buildingId,

        Long accountId,

        @NotBlank
        @Size(max = 255)
        String name,

        @NotBlank
        String kind,

        String email,

        @NotBlank
        @Size(max = 80)
        String phone,

        String emergencyContact

) implements RequestDto {
}