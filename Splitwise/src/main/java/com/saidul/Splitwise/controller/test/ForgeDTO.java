package com.saidul.Splitwise.controller.test;

import com.saidul.Splitwise.controller.test.bulkDTOs.AddExpenseBatchDTO;
import com.saidul.Splitwise.controller.test.bulkDTOs.CreateGroupBulkDTO;
import com.saidul.Splitwise.controller.test.bulkDTOs.SignUpBatchReqDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgeDTO {

    private SignUpBatchReqDTO signupDTOs;
    private CreateGroupBulkDTO groupDTOs;
    private AddExpenseBatchDTO expenseDTOs;
}
