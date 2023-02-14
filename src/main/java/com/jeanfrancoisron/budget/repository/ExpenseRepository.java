package com.jeanfrancoisron.budget.repository;

import com.jeanfrancoisron.budget.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByExpenseName(String expenseName);
}
