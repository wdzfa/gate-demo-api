package com.gate.demo.dto;

import com.gate.demo.model.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionRequestDto {

    private UUID account_id;
    private TransactionType type;
    private BigDecimal amount;

    public UUID getAccount_id() {
        return account_id;
    }

    public void setAccount_id(UUID account_id) {
        this.account_id = account_id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

