package com.gate.demo.dto;

import java.math.BigDecimal;

public class TransactionReportResponse {
    private String type;
    private long totalTransactions;
    private BigDecimal totalAmount;

    public TransactionReportResponse() {}

    public TransactionReportResponse(String type, long totalTransactions, BigDecimal totalAmount) {
        this.type = type;
        this.totalTransactions = totalTransactions;
        this.totalAmount = totalAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
