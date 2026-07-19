package com.ricemill.backend.repository;

import com.ricemill.backend.entity.PaddyProcurement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaddyProcurementRepository
        extends JpaRepository<PaddyProcurement, Long> {
    List<PaddyProcurement> findByUserId(Long userId);
}