package com.codevictims.bayt.property.entity;

import com.codevictims.bayt.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "safety_record")
public class SafetyRecord extends BaseEntity {
    @Column(nullable = false)
    public Long buildingId;

    @Column(nullable = false, length = 40)
    public String kind = "";

    @Column(nullable = false)
    public String reference = "";

    @Column(nullable = false)
    public LocalDate expiryDate;

    @Column(nullable = false)
    public String notes = "";
}
