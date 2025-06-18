package com.gate.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountResponseDto {

    private UUID id;
    private UUID user_id;
    private BigDecimal balance;
    private LocalDateTime created_at;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public AccountResponseDto() {}
}
