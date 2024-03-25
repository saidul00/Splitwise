package com.saidul.Splitwise.controller.test.bulkDTOs;

import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddExpenseBatchDTO {
    private List<AddExpenseRequestDTO> addExpenseRequestDTOList;
}
