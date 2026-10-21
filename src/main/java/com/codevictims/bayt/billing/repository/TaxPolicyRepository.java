package com.codevictims.bayt.billing.repository;

import com.codevictims.bayt.billing.entity.TaxPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TaxPolicyRepository extends JpaRepository<TaxPolicy, Long> {

    @Query("select p from TaxPolicy p where p.buildingId in :ids order by p.effectiveFrom")
    List<TaxPolicy> findInBuildingsByEffectiveDate(
            @Param("ids") Collection<Long> ids
    );
}