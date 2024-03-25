package com.saidul.Splitwise.controller.test.bulkAPIs;

import com.saidul.Splitwise.controller.UserController;
import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignUpBulkAPI {
    @Autowired
    private UserController userController;
    @PostMapping("/signup/batch")
    public ResponseEntity batchSignUp(@RequestBody List<UserSignUpRequestDTO> dtoList){
        for(UserSignUpRequestDTO user : dtoList){
            userController.signUp(user);
        }
        return ResponseEntity.ok().build();
    }
}
