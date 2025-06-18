package com.gate.demo.service.impl;

import com.gate.demo.dto.TransactionRequestDto;
import com.gate.demo.dto.TransactionResponseDto;
import com.gate.demo.model.Account;
import com.gate.demo.model.Transaction;
import com.gate.demo.model.TransactionType;
import com.gate.demo.repository.AccountRepository;
import com.gate.demo.repository.TransactionRepository;
import com.gate.demo.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto request) {

        Account account = accountRepository.findById(request.getAccount_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (request.getType() == TransactionType.DEBIT) {
            if (account.getBalance().compareTo(request.getAmount()) < 0) {
                throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Insufficient balance");
            }
            account.setBalance(account.getBalance().subtract(request.getAmount()));
        } else if (request.getType() == TransactionType.CREDIT) {
            account.setBalance(account.getBalance().add(request.getAmount()));
        }

        Transaction transaction = new Transaction();
        transaction.setAccountId(request.getAccount_id());
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());

        transaction = transactionRepository.save(transaction);

        accountRepository.save(account);

        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(transaction.getId());
        response.setAccountId(transaction.getAccountId());
        response.setType(transaction.getType());
        response.setAmount(transaction.getAmount());
        response.setTimestamp(transaction.getTimestamp());
        return response;

    }

    public Page<Transaction> getTransactionHistory(UUID accountId, Pageable pageable) {
        return transactionRepository.findByAccountId(accountId, pageable);
    }
}

