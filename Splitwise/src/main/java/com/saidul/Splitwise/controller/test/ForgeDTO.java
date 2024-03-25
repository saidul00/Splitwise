package com.saidul.Splitwise.controller.test;

import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForgeDTO {
    //user
    private List<UserSignUpRequestDTO> signUpRequestDTOS;
    //group
    private List<CreateGroupRequestDTO> groupRequestDTOS;
    //expense
    private List<AddExpenseRequestDTO> expenseRequestDTOS;
}
