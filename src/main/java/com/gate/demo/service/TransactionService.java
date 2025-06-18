package com.gate.demo.service;

import com.gate.demo.dto.*;
import com.gate.demo.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto request);

    Page<Transaction> getTransactionHistory(UUID account_id, Pageable pageable);

}

