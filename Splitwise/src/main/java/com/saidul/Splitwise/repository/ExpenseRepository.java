package com.saidul.Splitwise.repository;

import com.saidul.Splitwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    Expense findExpenseById(int id);
}
