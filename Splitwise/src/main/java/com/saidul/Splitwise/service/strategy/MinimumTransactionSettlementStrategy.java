package com.saidul.Splitwise.service.strategy;

import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.entity.UserExpense;
import com.saidul.Splitwise.entity.constant.UserExpenseType;

import java.util.HashMap;
import java.util.List;

public class MinimumTransactionSettlementStrategy implements SettleUpStrategy{
    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        return null;
    }
    public HashMap<User, Double> getOutstandingBalances(List<Expense> expenses){
        HashMap<User, Double> outstandingBalanceMap = new HashMap<>();
        for(Expense expense : expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User participant = userExpense.getUser();
                double amount = userExpense.getAmount();
                if(outstandingBalanceMap.containsKey(participant)){
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        outstandingBalanceMap.put(participant, outstandingBalanceMap.get(participant) + amount);
                    }else{
                        outstandingBalanceMap.put(participant, outstandingBalanceMap.get(participant) - amount);
                    }
                }else {
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        outstandingBalanceMap.put(participant,0 + amount);
                    }else{
                        outstandingBalanceMap.put(participant,0 - amount);
                    }
                }
            }
        }
        return outstandingBalanceMap;
    }
}
