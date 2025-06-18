package com.gate.demo.controller;

import com.gate.demo.dto.UserRequestDto;
import com.gate.demo.dto.UserResponseDto;
import com.gate.demo.model.User;
import com.gate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class GateController {

    @Autowired
    UserService userService;

    @PostMapping(value = "create-user")
    public UserResponseDto createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping("/get-all")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping(value = "get-user")
    public UserResponseDto getUser(@RequestParam UUID id) {
        return userService.getUser(id);
    }

    @PutMapping("/update")
    public UserResponseDto updateUser(@RequestParam UUID id, @RequestBody UserRequestDto user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
    }
}
