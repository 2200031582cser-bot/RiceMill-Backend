package com.ricemill.backend.repository;

import com.ricemill.backend.entity.GovernmentLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GovernmentLedgerRepository
        extends JpaRepository<GovernmentLedger, Long> {

    List<GovernmentLedger> findByUserId(Long userId);

    GovernmentLedger findByUserIdAndCropSeasonAndCropYear(
            Long userId,
            String cropSeason,
            String cropYear
    );
}