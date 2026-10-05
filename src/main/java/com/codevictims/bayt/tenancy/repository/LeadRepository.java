package com.codevictims.bayt.tenancy.repository;

import com.codevictims.bayt.tenancy.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByBuildingIdInOrderByIdDesc(Collection<Long> buildingIds);
}