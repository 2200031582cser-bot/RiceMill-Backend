package com.ricemill.backend.controller;

import com.ricemill.backend.entity.EmployeeSalary;
import com.ricemill.backend.repository.EmployeeSalaryRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController

@RequestMapping("/salary")

public class EmployeeSalaryController {

    @Autowired

    private EmployeeSalaryRepository repository;

    // SAVE

    @PostMapping

    public EmployeeSalary saveSalary(

            @RequestBody EmployeeSalary salary

    ) {

        return repository.save(salary);
    }

    // GET ALL

    @GetMapping

    public List<EmployeeSalary> getAllSalary() {

        return repository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<EmployeeSalary> getSalaryByUserId(
            @PathVariable Long userId
    ) {

        return repository.findByUserId(userId);

    }

    @PutMapping("/pay/{id}")
    public EmployeeSalary paySalary(
            @PathVariable Long id,
            @RequestBody EmployeeSalary request
    ) {

        EmployeeSalary salary =
                repository.findById(id)
                        .orElseThrow();

        double existingPaid =

                salary.getAmountPaid() == null
                        ? 0
                        : salary.getAmountPaid();

        double newPayment =

                request.getAmountPaid() == null
                        ? 0
                        : request.getAmountPaid();

        double paid =

                existingPaid + newPayment;

        salary.setAmountPaid(
                paid
        );

        double finalSalary =

                salary.getFinalSalary() == null
                        ? 0
                        : salary.getFinalSalary();

        salary.setBalanceAmount(
                finalSalary - paid
        );

        return repository.save(
                salary
        );
    }

    // DELETE

    @DeleteMapping("/{id}")

    public void deleteSalary(

            @PathVariable Long id

    ) {

        repository.deleteById(id);
    }
}
