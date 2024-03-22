package com.saidul.Splitwise.controller;

import com.saidul.Splitwise.dto.UserLoginRequestDto;
import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import com.saidul.Splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserSignUpRequestDTO dto) throws Exception{

        return ResponseEntity.ok(userService.signUp(dto.getName(), dto.getEmail(), dto.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto dto) throws Exception{
        return ResponseEntity.ok(userService.login(dto.getEmail(), dto.getPassword()));
    }
}
