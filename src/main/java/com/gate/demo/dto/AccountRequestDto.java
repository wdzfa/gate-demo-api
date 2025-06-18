package com.gate.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountRequestDto {

    private UUID user_id;
    private BigDecimal initial_balance;

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getInitial_balance() {
        return initial_balance;
    }

    public void setInitial_balance(BigDecimal initial_balance) {
        this.initial_balance = initial_balance;
    }
}
