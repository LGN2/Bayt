package com.codevictims.bayt.tenancy.repository;

import com.codevictims.bayt.tenancy.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}