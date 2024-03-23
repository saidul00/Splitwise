package com.saidul.Splitwise.service;

import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;

import java.util.List;

public interface GroupService {
    Group createGroup(String groupName, User createdBy, List<User> members);
    List<SettlementTransaction> settleUp(int groupId);
}
