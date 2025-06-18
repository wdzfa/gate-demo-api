package com.gate.demo.service.impl;

import com.gate.demo.dto.UserRequestDto;
import com.gate.demo.dto.UserResponseDto;
import com.gate.demo.model.User;
import com.gate.demo.repository.UserRepository;
import com.gate.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        UserResponseDto response = new UserResponseDto();
        response.setId(usr.getId());
        response.setName(usr.getName());
        response.setEmail(usr.getEmail());
        response.setCreated_at(usr.getCreatedAt());

        return response;
    }

    public ResponseEntity<String> updateUser(UserRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        userRepository.save(user);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}

