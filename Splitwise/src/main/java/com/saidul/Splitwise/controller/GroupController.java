package com.saidul.Splitwise.controller;
import com.saidul.Splitwise.Exception.InvalidGroupRequestData;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.mapper.EntityDTOMapper;
import com.saidul.Splitwise.service.GroupService;
import com.saidul.Splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @PostMapping("/creategroup")
    public ResponseEntity createdGroup(@RequestBody CreateGroupRequestDTO groupRequestDTO){
        validateGroupRequestDTO(groupRequestDTO);
        User user = userService.getUserById(groupRequestDTO.getCreatedByUID());
        List<User> userList = new ArrayList<>();
        for (Integer member : groupRequestDTO.getMemberIds()){
            userList.add(userService.getUserById(member));
        }
        Group group = groupService.createGroup(groupRequestDTO.getGroupName(), user, userList);
        return ResponseEntity.ok(
                EntityDTOMapper.toGroupDTO(group)
        );
    }
    private void validateGroupRequestDTO(CreateGroupRequestDTO groupRequestDTO){
        if(groupRequestDTO.getGroupName().isEmpty() || groupRequestDTO.getCreatedByUID()==null || groupRequestDTO.getMemberIds().isEmpty()){
            throw new InvalidGroupRequestData("Provided data is insufficient to create group");
        }
    }
}
