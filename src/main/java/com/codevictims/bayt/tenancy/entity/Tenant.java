package com.codevictims.bayt.tenancy.entity;
import com.codevictims.bayt.entity.BaseEntity;
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

    @Column(nullable = false)
    private Long buildingId;

    private Long accountId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 40)
    private String kind;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String emergencyContact;
}