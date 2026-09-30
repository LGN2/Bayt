package com.codevictims.bayt.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "building_access")
public class BuildingAccess extends BaseEntity {

    @Column(nullable = false)
    public Long userId;

    @Column(nullable = false)
    public boolean canWrite;



}