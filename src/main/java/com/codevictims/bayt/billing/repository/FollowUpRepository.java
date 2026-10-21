package com.codevictims.bayt.billing.repository;

import com.codevictims.bayt.billing.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    @Query("select f from FollowUp f where f.leaseId = :id order by f.createdAt desc")
    List<FollowUp> findByLeaseNewestFirst(@Param("id") Long id);
}