package com.ricemill.backend.repository;

import com.ricemill.backend.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SalesRepository
        extends JpaRepository<Sales, Long> {

    List<Sales> findByUserId(Long userId);
    Optional<Sales> findById(Long id);
}