package com.gate.demo.service.impl;

import com.gate.demo.dto.AccountRequestDto;
import com.gate.demo.dto.AccountResponseDto;
import com.gate.demo.model.Account;
import com.gate.demo.model.User;
import com.gate.demo.repository.AccountRepository;
import com.gate.demo.repository.UserRepository;
import com.gate.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponseDto createAccount(AccountRequestDto request) {

        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setBalance(request.getInitial_balance());
        account.setCreated_at(LocalDateTime.now());

        Account saved = accountRepository.save(account);

        AccountResponseDto response = new AccountResponseDto();
        response.setId(saved.getId());
        response.setUser_id(saved.getUser().getId());
        response.setBalance(saved.getBalance());
        response.setCreated_at(saved.getCreated_at());

        return response;
    }

    public List<AccountResponseDto> getAccountsByUserId(UUID user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + user_id + " not found"));

        List<Account> accounts = accountRepository.findByUserId(user.getId());

        return accounts.stream().map(account -> {
            AccountResponseDto dto = new AccountResponseDto();
            dto.setId(account.getId());
            dto.setUser_id(account.getUser().getId());
            dto.setBalance(account.getBalance());
            dto.setCreated_at(account.getCreated_at());
            return dto;
        }).collect(Collectors.toList());
    }

}

