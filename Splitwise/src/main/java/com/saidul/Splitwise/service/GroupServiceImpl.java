package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.InvalidExpenseIdException;
import com.saidul.Splitwise.Exception.InvalidGroupIdException;
import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.repository.GroupRepository;
import org.hibernate.type.descriptor.java.CurrencyJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
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
        groupMember.add(admin);
        if(admin.getGroups() == null){
            admin.setGroups(new ArrayList<>());
        }
        admin.getGroups().add(group);
        for(int memberId : members){
            User savedUser = userService.getUserById(memberId);
            groupMember.add(savedUser);
            if(savedUser.getGroups() == null){
                savedUser.setGroups(new ArrayList<>());
            }
            savedUser.getGroups().add(group);
        }
        group.setMembers(groupMember);
        return groupRepository.save(group);
    }

    @Override
    public Group addExpenseToGroup(Expense expense, int groupId) {
        Group savedGroup = groupRepository.findGroupById(groupId);
        if(savedGroup.getExpenses() == null){
            savedGroup.setExpenses(new ArrayList<>());
        }
        savedGroup.getExpenses().add(expense);
        if(expense.getCurrency().getCurrencyCode().equals("INR")){
            savedGroup.setTotalAmountSpent(savedGroup.getTotalAmountSpent() + expense.getAmount());
        }else{
            savedGroup.setTotalAmountSpent(savedGroup.getTotalAmountSpent() + CurrencyConverter.convertToINR(expense
                    .getCurrency(), expense.getAmount()));
        }
        return groupRepository.save(savedGroup);
    }

    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        return null;
    }
}
