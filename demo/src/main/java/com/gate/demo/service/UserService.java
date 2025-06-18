package com.gate.demo.service;


import com.gate.demo.dto.UserRequestDto;
import com.gate.demo.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserRequestDto request);

    UserResponseDto getUser(UUID id);

    List<UserResponseDto> getAllUser();

    UserResponseDto updateUser(UUID id, UserRequestDto requestDto);

    void deleteUser(UUID id);

}

