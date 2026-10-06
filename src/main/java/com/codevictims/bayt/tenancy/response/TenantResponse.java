package com.codevictims.bayt.tenancy.response;

public record TenantResponse(
        Long id,
        Long buildingId,
        Long accountId,
        String name,
        String kind,
        String email,
        String phone,
        String emergencyContact
) {
}