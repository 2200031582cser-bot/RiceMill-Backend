package com.ricemill.backend.repository;

import com.ricemill.backend.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
    List<Employee> findByUserId(Long userId);
}