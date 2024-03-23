package com.saidul.Splitwise.service;

import com.saidul.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface GroupService {
    List<SettlementTransaction> settleUp(int groupId);
}
