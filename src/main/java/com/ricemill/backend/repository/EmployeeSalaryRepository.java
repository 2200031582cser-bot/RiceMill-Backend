package com.ricemill.backend.repository;

import com.ricemill.backend.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeSalaryRepository
        extends JpaRepository<EmployeeSalary, Long> {

    List<EmployeeSalary> findByUserId(Long userId);

}