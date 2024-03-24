package com.saidul.Splitwise.controller;

import com.saidul.Splitwise.Exception.*;
import com.saidul.Splitwise.dto.UserAddFriendRequestDTO;
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
    public ResponseEntity signUp(@RequestBody UserSignUpRequestDTO dto){
        try {
            validateUserSignUpRequestDTO(dto);
        }catch (UserRegistrationInvalidDataException e){
            return ResponseEntity.badRequest().body("Invalid sign up data");
        }
        User savedUser = userService.signUp(dto.getName(),dto.getEmail(),dto.getPassword());
        return ResponseEntity.ok(
                EntityDTOMapper.toDTO(savedUser)
        );
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO dto) throws Exception{
        try {
            validateUserLoginRequestDTO(dto);
        }catch (UserLoginInvalidDataException e){
            ResponseEntity.badRequest().body("Invalid login credential");
        }
        try {
            User savedUser = userService.login(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(
                    EntityDTOMapper.toDTO(savedUser)
            );
        }catch (InvalidEmailException e){
            return ResponseEntity.badRequest().body("Email not found.");
        }catch (InvalidPasswordException e){
            return ResponseEntity.badRequest().body("Incorrect password");
        }
    }

    @PostMapping("/addfriend")
    public ResponseEntity addFriend(@RequestBody UserAddFriendRequestDTO friendRequestDTO){
        try {
            validateFriendRequestDTO(friendRequestDTO);
        }catch (InvalidFriendRequestData exception){
            return ResponseEntity.badRequest().body("Invalid data for friend request");
        }


        validateFriendRequestDTO(friendRequestDTO);
        try {
            User savedUser = userService.addFriend(friendRequestDTO.getUserId(), friendRequestDTO.getFriendEmail());
            return ResponseEntity.ok(
                    EntityDTOMapper.toDTO(savedUser)
            );
        }catch (InvalidEmailException invalidEmailException){
            return ResponseEntity.badRequest().body("User with email not found");
        }catch (InvalidFriendRequestException friendRequestException){
            return ResponseEntity.badRequest().body("Adding the same friend multiple times is not allowed.");
        }
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
    private void validateFriendRequestDTO(UserAddFriendRequestDTO friendRequestDTO){
        if(friendRequestDTO.getUserId()==null || friendRequestDTO.getFriendEmail().isEmpty()){
            throw new InvalidFriendRequestData("Invalid data for friend request");
        }
    }

}
