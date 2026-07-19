package com.ricemill.backend.repository;

import com.ricemill.backend.entity.BrokenRice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BrokenRiceRepository
        extends JpaRepository<BrokenRice, Long> {
    List<BrokenRice> findByUserId(Long userId);
}