package com.saidul.Splitwise.controller;

import com.saidul.Splitwise.Exception.InavlidExpenseDataException;
import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.mapper.EntityDTOMapper;
import com.saidul.Splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/addexpense")
    public ResponseEntity addExpense(@RequestBody AddExpenseRequestDTO expenseRequestDTO){
        validateExpenseRequestDTO(expenseRequestDTO);
        Expense savedExpense = expenseService.addExpense(expenseRequestDTO.getDescription(), expenseRequestDTO.getAmount(),
                expenseRequestDTO.getCurrency(), expenseRequestDTO.getAddedBy());
        //TODO : expense
        return ResponseEntity.ok(
                EntityDTOMapper.toExpenseDTO(savedExpense)
        );
    }
    private void validateExpenseRequestDTO(AddExpenseRequestDTO expenseRequestDTO){
        if(expenseRequestDTO.getDescription().isEmpty() || expenseRequestDTO.getAddedBy()==null ||
        expenseRequestDTO.getAmount() == null){
            throw new InavlidExpenseDataException("Invalid data to create expense");
        }
    }
}
