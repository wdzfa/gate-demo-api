package com.gate.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "T_ACCOUNT")

public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "USER_ID", nullable = false)
    private UUID userId;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "CREATED_AT", updatable = false)
    private Timestamp createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
