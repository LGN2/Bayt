package com.codevictims.bayt.tenancy.repository;

import com.codevictims.bayt.tenancy.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("select t from Tenant t where t.accountId = :u and t.buildingId = :b")
    List<Tenant> findByAccountAndBuilding(
            @Param("u") Long u,
            @Param("b") Long b);

    @Query("""
            select distinct t
            from Tenant t, Lease l
            where l.tenantId = t.id
              and l.unitId = :unit
              and t.accountId = :user
              and l.startDate <= :today
              and l.endDate >= :today
              and (l.terminatedOn is null or l.terminatedOn >= :today)
            """)
    List<Tenant> findCurrentTenantForUnit(
            @Param("unit") Long unit,
            @Param("user") Long user,
            @Param("today") LocalDate today);

    @Query("select distinct t.buildingId from Tenant t where t.accountId = :id")
    List<Long> findBuildingIdsForAccount(
            @Param("id") Long id);

    @Query("""
            select t
            from Tenant t
            where t.accountId = :id
              and t.buildingId in :ids
            order by t.id
            """)
    List<Tenant> findForAccountInBuildings(
            @Param("id") Long id,
            @Param("ids") Collection<Long> ids);

    @Query("""
            select t
            from Tenant t
            where t.buildingId in :ids
            order by t.id
            """)
    List<Tenant> findInBuildings(
            @Param("ids") Collection<Long> ids);
}