package com.ricemill.backend.controller;

import com.ricemill.backend.entity.Expense;
import com.ricemill.backend.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/user/{userId}")
    public List<Expense> getByUserId(
            @PathVariable Long userId
    ) {

        return expenseRepository.findByUserId(userId);
    }

    @PostMapping
    public Expense saveExpense(
            @RequestBody Expense expense
    ) {

        return expenseRepository.save(expense);
    }

    @GetMapping
    public List<Expense> getExpenses() {

        return expenseRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(
            @PathVariable Long id
    ) {

        expenseRepository.deleteById(id);

        return "Expense Deleted Successfully";
    }
}
