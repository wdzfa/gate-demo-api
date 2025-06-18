package com.gate.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private UUID accountId;

    @Column(name = "TYPE", nullable = false)
    private String type; // e.g., "DEBIT" or "CREDIT"

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "TIMESTAMP", nullable = false, updatable = false)
    private Timestamp timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

