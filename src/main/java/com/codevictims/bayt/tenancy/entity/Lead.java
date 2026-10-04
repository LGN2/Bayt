package com.codevictims.bayt.tenancy.entity;
import com.codevictims.bayt.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "leasing_lead")
public class Lead extends BaseEntity {

    @Column(nullable = false)
    public Long buildingId;

    @Column(nullable = false)
    public Long unitId;

    @Column(nullable = false)
    public String name = "";

    @Column(nullable = false)
    public String phone = "";

    @Column(nullable = false, length = 40)
    public String status = "";

    @Column(nullable = true)
    public Instant viewingAt;

    @Column(nullable = true)
    public LocalDate followUpDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    public String notes = "";
}