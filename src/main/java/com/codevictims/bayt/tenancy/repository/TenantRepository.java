package com.codevictims.bayt.tenancy.repository;

import com.codevictims.bayt.tenancy.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("""
            select t from Tenant t
            where t.accountId = :accountId
              and t.buildingId in :ids
            order by t.id
            """)
    List<Tenant> findForAccountInBuildings(
            @Param("accountId") Long accountId,
            @Param("ids") Collection<Long> ids
    );

    @Query("""
            select t from Tenant t
            where t.buildingId in :ids
            order by t.id
            """)
    List<Tenant> findInBuildings(
            @Param("ids") Collection<Long> ids
    );

    @Query("""
            select distinct t.buildingId from Tenant t
            where t.accountId = :accountId
            order by t.buildingId
            """)
    List<Long> findBuildingIdsForAccount(
            @Param("accountId") Long accountId
    );
}