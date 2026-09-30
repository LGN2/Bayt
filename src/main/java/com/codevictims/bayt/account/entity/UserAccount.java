package com.codevictims.bayt.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="user_account")
public class UserAccount extends BaseEntity{

    @Column(nullable = false)
    public String username = "";

    @Column(nullable = false)
    @JsonIgnore
    public String passwordHash = "";

    @Column(nullable = false)
    public String displayName = "";

    @Column(nullable = false, length = 40)
    public String role = "";

    @Column(nullable = true)
    public Long ownerId;

    @Column(nullable = false)
    public boolean active;

    @Column(nullable = false)
    public boolean taxRegistered;




}
