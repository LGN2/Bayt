package com.codevictims.bayt.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="user_account")
public class UserAccount extends BaseEntity{

    @Column(nullable = false)
    public String username = "";
}
