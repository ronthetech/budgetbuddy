package com.jeanfrancoisron.budget.controller;

import com.jeanfrancoisron.budget.dto.ExpenseDto;
import com.jeanfrancoisron.budget.model.Expense;
import com.jeanfrancoisron.budget.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public String listExpenses(Model model){
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses",expenses);
        return "expense-list";
    }

    @GetMapping("/expenses/new")
    public String createExpenseForm(Model model){
        Expense expense = new Expense();
        model.addAttribute("expense",expense);
        return "expense-form";
    }

    @PostMapping("/expenses/new")
    public String saveNewExpense(@Valid @ModelAttribute("expense") ExpenseDto expenseDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("expense",expenseDto);
            return "expense-form";
        }
        expenseService.createExpense(expenseDto);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}")
    public String getExpenseDetails(@PathVariable long id, Model model){
        ExpenseDto expenseDto = expenseService.findExpenseById(id);
        model.addAttribute("expense", expenseDto);
        return "expense-details";
    }

    @GetMapping("/expenses/{id}/delete")
    public String deleteExpense(@PathVariable("id") long id) {
        expenseService.delete(id);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}/edit")
    public String editExpenseForm(@PathVariable("id") long id, Model model) {
        ExpenseDto expenseDto = expenseService.findExpenseById(id);
        model.addAttribute("expense",expenseDto);
        return "expense-edit";
    }

    @PostMapping("/expenses/{id}/edit")
    public String updateExpense(@PathVariable("id") long id,
                                @Valid @ModelAttribute("expense") ExpenseDto expenseDto,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "expense-edit";
        }
        expenseDto.setId(id);
        expenseService.updateExpense(expenseDto);
        return "redirect:/expenses";
    }
}
