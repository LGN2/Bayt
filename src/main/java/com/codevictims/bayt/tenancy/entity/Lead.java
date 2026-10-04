package com.codevictims.bayt.tenancy.entity;

import com.codevictims.bayt.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leasing_lead")
@Getter
@Setter
@NoArgsConstructor
public class Lead extends BaseEntity {

    @Column(nullable = false)
    private Long buildingId;

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private String name = "";

    @Column(nullable = false)
    private String phone = "";

    @Column(nullable = false, length = 40)
    private String status = "";

    @Column(nullable = true)
    private Instant viewingAt;

    @Column(nullable = true)
    private LocalDate followUpDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes = "";
}