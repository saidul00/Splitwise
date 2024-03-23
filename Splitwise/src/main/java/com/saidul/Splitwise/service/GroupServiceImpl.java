package com.saidul.Splitwise.service;

import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group createGroup(String groupName, User createdBy, List<User> members) {
        Group group = new Group(groupName, createdBy, members);
        for (User user : members){
            user.getGroups().add(group);
        }
        return groupRepository.save(group);
    }

    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        return null;
    }
}
