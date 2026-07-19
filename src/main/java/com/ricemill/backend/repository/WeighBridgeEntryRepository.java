package com.ricemill.backend.repository;

import com.ricemill.backend.entity.WeighBridgeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeighBridgeEntryRepository
        extends JpaRepository<WeighBridgeEntry, Long> {

    List<WeighBridgeEntry> findByUserId(Long userId);
}