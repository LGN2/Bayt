package com.codevictims.bayt.tenancy.entity;

import com.codevictims.bayt.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tenant")
@Getter
@Setter
@NoArgsConstructor
public class Tenant extends BaseEntity {

    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public Long buildingId;

    public Long accountId;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false, length = 40)
    public String kind;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String phone;

    @Column(nullable = false)
    public String emergencyContact;
}