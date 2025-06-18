package com.gate.demo.service;

import com.gate.demo.dto.AccountRequestDto;
import com.gate.demo.dto.AccountResponseDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponseDto createAccount(AccountRequestDto request);

    List<AccountResponseDto> getAccountsByUserId(UUID user_id);


}

