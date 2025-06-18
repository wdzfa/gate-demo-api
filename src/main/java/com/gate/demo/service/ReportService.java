package com.gate.demo.service;

import com.gate.demo.dto.TransactionReportResponse;
import com.gate.demo.model.TransactionType;

import java.time.LocalDateTime;

public interface ReportService {

    TransactionReportResponse getTransactionReport(TransactionType type, LocalDateTime startDate, LocalDateTime endDate);

}

