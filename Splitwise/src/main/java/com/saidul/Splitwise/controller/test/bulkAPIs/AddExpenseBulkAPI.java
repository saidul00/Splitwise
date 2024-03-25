package com.saidul.Splitwise.controller.test.bulkAPIs;

import com.saidul.Splitwise.controller.ExpenseController;
import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddExpenseBulkAPI {
    @Autowired
    private ExpenseController expenseController;
    @PostMapping("/addexpense/batch")
    public ResponseEntity createExpenseInBulk(@RequestBody List<AddExpenseRequestDTO> expenses){
        for(AddExpenseRequestDTO expense : expenses){
            expenseController.addExpense(expense);
        }
        return ResponseEntity.ok().build();
    }
}
