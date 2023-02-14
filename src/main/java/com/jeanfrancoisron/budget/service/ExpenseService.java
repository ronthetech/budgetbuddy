package com.jeanfrancoisron.budget.service;

import com.jeanfrancoisron.budget.dto.ExpenseDto;
import com.jeanfrancoisron.budget.model.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(ExpenseDto expenseDto);
    List<ExpenseDto> getAllExpenses();

    ExpenseDto findExpenseById(long id);

    void updateExpense(ExpenseDto expenseDto);

    void delete(long id);
}
