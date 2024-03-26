package com.saidul.Splitwise.controller;

import com.saidul.Splitwise.Exception.InavlidExpenseDataException;
import com.saidul.Splitwise.Exception.InvalidExpenseIdException;
import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import com.saidul.Splitwise.dto.ExpenseToGroupMappingDTO;
import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.mapper.EntityDTOMapper;
import com.saidul.Splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/addexpense")
    public ResponseEntity addExpense(@RequestBody AddExpenseRequestDTO expenseRequestDTO){
        validateExpenseRequestDTO(expenseRequestDTO);
        Expense savedExpense = expenseService.addExpense(expenseRequestDTO.getDescription(), expenseRequestDTO.getAmount(),
                expenseRequestDTO.getCurrency(), expenseRequestDTO.getAddedBy());
        return ResponseEntity.ok(
                EntityDTOMapper.toExpenseDTO(savedExpense)
        );
    }
    @GetMapping("/expense/{id}")
    public ResponseEntity getExpense(@PathVariable ("id") int expenseId){
        validateExpenseId(expenseId);
        try {
            Expense savedExpense = expenseService.getExpenseById(expenseId);
            return ResponseEntity.ok(
                    EntityDTOMapper.toExpenseDTO(savedExpense)
            );
        }catch (InvalidExpenseIdException expenseIdException){
            return ResponseEntity.badRequest().body("Expense not found");
        }
    }
    @PostMapping("/addexpensetogroup")
    public ResponseEntity addExpenseToGroup(@RequestBody ExpenseToGroupMappingDTO expenseToGroupMappingDTO){
        expenseService.addExpenseToGroup(expenseToGroupMappingDTO.getExpenseId(), expenseToGroupMappingDTO.getGroupId());
        return ResponseEntity.ok("Added successfully");
    }
    private void validateExpenseRequestDTO(AddExpenseRequestDTO expenseRequestDTO){
        if(expenseRequestDTO.getDescription().isEmpty() || expenseRequestDTO.getAddedBy()==null ||
        expenseRequestDTO.getAmount() == null){
            throw new InavlidExpenseDataException("Invalid data to create expense");
        }
    }
    private void validateExpenseId(int expenseId){
        if(expenseId < 1){
            throw new InvalidExpenseIdException("Invalid expense Id");
        }
    }
}
