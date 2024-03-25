package com.saidul.Splitwise.controller.test;

import com.saidul.Splitwise.controller.test.bulkAPIs.AddExpenseBulkAPI;
import com.saidul.Splitwise.controller.test.bulkAPIs.CreateGroupBulkAPI;
import com.saidul.Splitwise.controller.test.bulkAPIs.SignUpBulkAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataForge {
    /*This class can create all the data in single click from a json file
    data creation flow is
    1.user DTOs
    2.group DTOs
    3.expense DTOs
     */
    @Autowired
    private SignUpBulkAPI signUpBulkAPI;
    @Autowired
    private CreateGroupBulkAPI createGroupBulkAPI;
    @Autowired
    private AddExpenseBulkAPI addExpenseBulkAPI;

    @PostMapping("/forge")
    public ResponseEntity forgeData(@RequestBody ForgeDTO forgeDTO){
        signUpBulkAPI.batchSignUp(forgeDTO.getSignupDTOs().getSingUpDTOBatch());
        createGroupBulkAPI.createGroupInBulk(forgeDTO.getGroupDTOs().getGroupRequestDTOList());
        addExpenseBulkAPI.createExpenseInBulk(forgeDTO.getExpenseDTOs().getAddExpenseRequestDTOList());
        return ResponseEntity.ok().build();
    }

}
