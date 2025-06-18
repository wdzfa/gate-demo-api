package com.gate.demo.service.impl;

import com.gate.demo.dto.TransactionReportResponse;
import com.gate.demo.model.TransactionType;
import com.gate.demo.repository.AccountRepository;
import com.gate.demo.repository.TransactionRepository;
import com.gate.demo.service.ReportService;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionReportResponse getTransactionReport(TransactionType type, LocalDateTime startDate, LocalDateTime endDate) {

        if (type != null &&
                type != TransactionType.DEBIT &&
                type != TransactionType.CREDIT) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type must be DEBIT or CREDIT or empty");
        }

        Tuple result = transactionRepository.getTransactionReport(
                type,
                startDate,
                endDate
        );

        long totalTransactions = ((Number) result.get("totalTransactions")).longValue();
        BigDecimal totalAmount = (BigDecimal) result.get("totalAmount");

        return new TransactionReportResponse(
                type != null ? type.name() : "ALL",
                totalTransactions,
                totalAmount
        );
    }

}

