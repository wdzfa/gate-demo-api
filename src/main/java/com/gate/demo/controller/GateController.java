package com.gate.demo.controller;

import com.gate.demo.dto.*;
import com.gate.demo.model.Transaction;
import com.gate.demo.model.TransactionType;
import com.gate.demo.service.AccountService;
import com.gate.demo.service.ReportService;
import com.gate.demo.service.TransactionService;
import com.gate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class GateController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ReportService reportService;

    @PostMapping(value = "create-user")
    public UserResponseDto createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping("get-all-user")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping(value = "get-user")
    public UserResponseDto getUser(@RequestParam UUID id) {
        return userService.getUser(id);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDto user) {
        return userService.updateUser(user);
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteUser(@RequestParam UUID id) {
        return userService.deleteUser(id);
    }

    @PostMapping("create-account")
    public AccountResponseDto createAccount(@RequestBody AccountRequestDto request) {
        return accountService.createAccount(request);
    }

    @GetMapping("all-account")
    public List<AccountResponseDto> getAccountsByUserId(@RequestParam UUID user_id) {
        return accountService.getAccountsByUserId(user_id);
    }

    @PostMapping("transaction")
    public TransactionResponseDto createTransaction(@RequestBody TransactionRequestDto request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("transaction-history")
    public Page<Transaction> getTransactionHistory(
            @RequestParam UUID account_id,
            Pageable pageable
    ) {
        return transactionService.getTransactionHistory(account_id, pageable);
    }

    @GetMapping("/report")
    public ResponseEntity<TransactionReportResponse> getTransactionReport(
            @RequestParam(required = false) TransactionType type,
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);

        TransactionReportResponse report = reportService.getTransactionReport(type, start, end);
        return ResponseEntity.ok(report);
    }
}
