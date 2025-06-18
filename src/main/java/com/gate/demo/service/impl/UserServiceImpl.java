package com.gate.demo.service.impl;

import com.gate.demo.dto.UserRequestDto;
import com.gate.demo.dto.UserResponseDto;
import com.gate.demo.model.User;
import com.gate.demo.repository.UserRepository;
import com.gate.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setCreated_at(saved.getCreatedAt());

        return response;
    }

    public List<UserResponseDto> getAllUser() {
        List<UserResponseDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserResponseDto response = new UserResponseDto();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setCreated_at(user.getCreatedAt());
            users.add(response);
        });
        return users;
    }

    public UserResponseDto getUser(UUID id) {
        User usr = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponseDto response = new UserResponseDto();
        response.setId(usr.getId());
        response.setName(usr.getName());
        response.setEmail(usr.getEmail());
        response.setCreated_at(usr.getCreatedAt());

        return response;
    }

    public UserResponseDto updateUser(UUID id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreated_at(user.getCreatedAt());

        return response;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}

