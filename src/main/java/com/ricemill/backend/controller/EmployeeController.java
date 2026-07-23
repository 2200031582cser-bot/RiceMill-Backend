package com.ricemill.backend.controller;

import com.ricemill.backend.entity.Employee;

import com.ricemill.backend.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/employee")

public class EmployeeController {

    @Autowired

    private EmployeeRepository repository;

    // SAVE
    @GetMapping("/user/{userId}")
    public List<Employee> getEmployeesByUserId(
            @PathVariable Long userId
    ) {

        return repository.findByUserId(userId);

    }

    @PostMapping

    public Employee saveEmployee(

            @RequestBody Employee employee

    ) {

        return repository.save(employee);
    }

    // GET ALL

    @GetMapping

    public List<Employee> getAllEmployees() {

        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updated
    ) {

        Employee employee =
                repository.findById(id)
                        .orElseThrow();

        employee.setEmployeeName(
                updated.getEmployeeName()
        );

        employee.setPhoneNumber(
                updated.getPhoneNumber()
        );

        employee.setJoiningDate(
                updated.getJoiningDate()
        );

        employee.setMonthlySalary(
                updated.getMonthlySalary()
        );

        employee.setDailyWage(
                updated.getDailyWage()
        );

        return repository.save(employee);
    }


}
