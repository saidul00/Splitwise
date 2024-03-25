package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.InvalidExpenseIdException;
import com.saidul.Splitwise.Exception.InvalidGroupIdException;
import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;

    @Override
    public Group getGroupById(int groupId) {
        Group savedGroup = groupRepository.findGroupById(groupId);
        if(savedGroup == null){
            throw new InvalidGroupIdException("Given group ID does not exist");
        }
        return savedGroup;
    }

    @Override
    public Group createGroup(String groupName, Integer createdBy, List<Integer> members) {
        User admin = userService.getUserById(createdBy);
        Group group = new Group();
        group.setName(groupName);
        group.setAdmin(admin);
        List<User>groupMember = new ArrayList<>();
        addMember(admin,group);
        for(int memberId : members){
            addMember(userService.getUserById(memberId), group);
        }
        group.setMembers(groupMember);
        return groupRepository.save(group);
    }

    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        return null;
    }
    private void addMember(User user, Group group){
        if(user.getGroups()!=null){
            user.getGroups().add(group);
        }
        if(group.getMembers() != null){
            group.getMembers().add(user);
        }
    }
}
