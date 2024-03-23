package com.saidul.Splitwise.mapper;

import com.saidul.Splitwise.dto.GroupResponseDTO;
import com.saidul.Splitwise.dto.UserFriendResponseDTO;
import com.saidul.Splitwise.dto.UserLoginResponseDTO;
import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOMapper {
    public static UserLoginResponseDTO toDTO(User user){
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        List<UserFriendResponseDTO> friendList = new ArrayList<>();
        if(user.getFriends() != null){
            for(User friend : user.getFriends()){
                friendList.add(toFriendDTO(friend));
            }
            responseDTO.setFriendList(friendList);
        }

        List<GroupResponseDTO> groups = new ArrayList<>();
        if(user.getGroups() != null){
            for(Group group : user.getGroups()){
                groups.add(toGroupDTO(group));
            }
            responseDTO.setGroups(groups);
        }
        return responseDTO;
    }

    public static UserFriendResponseDTO toFriendDTO(User user){
        UserFriendResponseDTO responseDTO = new UserFriendResponseDTO();
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }
    public static GroupResponseDTO toGroupDTO(Group group){
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        groupResponseDTO.setGroupName(group.getName());
        groupResponseDTO.setAmount(group.getTotalAmountSpent());
        return groupResponseDTO;
    }
}
