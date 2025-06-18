package com.gate.demo.service;

import com.gate.demo.dto.UserRequestDto;
import com.gate.demo.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserRequestDto request);

    UserResponseDto getUser(UUID id);

    List<UserResponseDto> getAllUser();

    ResponseEntity<String> updateUser(UserRequestDto requestDto);

    ResponseEntity<String> deleteUser(UUID id);

}

