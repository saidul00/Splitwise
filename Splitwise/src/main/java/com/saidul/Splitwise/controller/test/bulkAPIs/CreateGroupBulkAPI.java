package com.saidul.Splitwise.controller.test.bulkAPIs;

import com.saidul.Splitwise.controller.GroupController;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CreateGroupBulkAPI {
    @Autowired
    private GroupController groupController;
    @PostMapping("/creategroup/batch")
    public ResponseEntity createGroupInBulk(@RequestBody List<CreateGroupRequestDTO> groupRequestDTOList){
        for (CreateGroupRequestDTO groupRequestDTO : groupRequestDTOList){
            groupController.createdGroup(groupRequestDTO);
        }
        return ResponseEntity.ok().build();
    }
}
