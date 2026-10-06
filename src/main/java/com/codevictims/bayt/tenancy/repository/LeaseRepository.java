package com.codevictims.bayt.tenancy.repository;

import com.codevictims.bayt.tenancy.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> findByBuildingIdInOrderByIdDesc(
            Collection<Long> buildingIds
    );

    List<Lease> findByUnitIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long unitId,
            LocalDate endDate,
            LocalDate startDate
    );
}