package com.codevictims.bayt.tenancy.entity;

import com.codevictims.bayt.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lease")
@Getter
@Setter
@NoArgsConstructor
public class Lease extends BaseEntity {

    @Column(nullable = false)
    private Long buildingId;

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long tenantId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal rent = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal deposit = BigDecimal.ZERO;

    @Column(nullable = false, length = 40)
    private String status = "";

    @Column(nullable = false, length = 40)
    private String taxTreatment = "";

    @Column(nullable = false, precision = 7, scale = 4)
    private BigDecimal taxRate = BigDecimal.ZERO;

    @Column(nullable = false)
    private String supplyClassification = "";

    @Column(nullable = false)
    private boolean ownerTaxRegistered;

    @Column(nullable = false, length = 40)
    private String municipalityStatus = "";

    @Column(nullable = false)
    private String municipalityAuthority = "";

    @Column(nullable = false)
    private String municipalityReference = "";

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal municipalityFee = BigDecimal.ZERO;

    private Long previousLeaseId;

    private LocalDate terminatedOn;
}