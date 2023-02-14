package com.jeanfrancoisron.budget.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanfrancoisron.budget.dto.ExpenseDto;
import com.jeanfrancoisron.budget.model.Expense;
import com.jeanfrancoisron.budget.repository.ExpenseRepository;
import com.jeanfrancoisron.budget.service.ExpenseService;

@Service
public class ExpenseImplementation implements ExpenseService {
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseImplementation(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = mapToEntity(expenseDto);
        return expenseRepository.save(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses(){
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().map(expense -> mapToDto(expense)).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto findExpenseById(long id) {
        Expense expense = expenseRepository.findById(id).get();
        return mapToDto(expense);
    }

    @Override
    public void updateExpense(ExpenseDto expenseDto) {
        Expense expense = mapToEntity(expenseDto);
        expenseRepository.save(expense);
    }

    @Override
    public void delete(long id) {
        Expense expense = expenseRepository.findById(id).get();
        expenseRepository.delete(expense);
    }

    private ExpenseDto mapToDto(Expense expense) {
        ExpenseDto expenseDto = ExpenseDto.builder()
                .id(expense.getId())
                .expenseName(expense.getExpenseName())
                .amount(expense.getAmount())
                .transactionDate(expense.getTransactionDate())
                .build();
        return expenseDto;
    }
    private Expense mapToEntity(ExpenseDto expenseDto) {
        Expense expense = Expense.builder()
                .id(expenseDto.getId())
                .expenseName(expenseDto.getExpenseName())
                .amount(expenseDto.getAmount())
                .transactionDate(expenseDto.getTransactionDate())
                .build();
        return expense;
    }
}
