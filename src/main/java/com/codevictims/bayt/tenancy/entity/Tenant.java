package com.codevictims.bayt.tenancy.entity;

import com.codevictims.bayt.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tenant")
@lombok.Getter
@lombok.Setter
public class Tenant extends BaseEntity {
  @Column(nullable = false)
  public Long buildingId;

  @Column(nullable = true)
  public Long accountId;

  @Column(nullable = false)
  public String name = "";

  @Column(nullable = false, length = 40)
  public String kind = "";

  @Column(nullable = false)
  public String email = "";

  @Column(nullable = false)
  public String phone = "";

  @Column(nullable = false)
  public String emergencyContact = "";
}
