package com.saidul.Splitwise.dto;

import com.saidul.Splitwise.entity.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLoginResponseDTO {
    private int id;
    private String name;
    private String email;
    private List<UserFriendResponseDTO> friendList;
    private List<GroupResponseDTO> groups;
}
