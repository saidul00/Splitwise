package com.saidul.Splitwise.controller;

import com.saidul.Splitwise.Exception.UserLoginInvalidDataException;
import com.saidul.Splitwise.Exception.UserRegistrationInvalidDataException;
import com.saidul.Splitwise.dto.UserLoginRequestDTO;
import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.mapper.EntityDTOMapper;
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
        validateUserSignUpRequestDTO(dto);
        User savedUser = userService.signUp(dto.getName(),dto.getEmail(),dto.getPassword());
        return ResponseEntity.ok(
                EntityDTOMapper.toDTO(savedUser)
        );
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO dto) throws Exception{
        validateUserLoginRequestDTO(dto);
        User savedUser = userService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(
                EntityDTOMapper.toDTO(savedUser)
        );
    }

    private void validateUserSignUpRequestDTO(UserSignUpRequestDTO signUpRequestDTO){
        if(signUpRequestDTO.getEmail().isEmpty() || signUpRequestDTO.getName().isEmpty() || signUpRequestDTO.getPassword().isEmpty()){
            throw new UserRegistrationInvalidDataException("Invalid sign up data");
        }
    }
    private void validateUserLoginRequestDTO(UserLoginRequestDTO userLoginRequestDTO){
        if(userLoginRequestDTO.getEmail().isEmpty() || userLoginRequestDTO.getPassword().isEmpty()){
            throw new UserLoginInvalidDataException("Invalid login credential");
        }
    }

}
