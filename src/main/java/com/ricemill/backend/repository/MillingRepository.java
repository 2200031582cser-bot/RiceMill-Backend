package com.ricemill.backend.repository;

import com.ricemill.backend.entity.MillingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MillingRepository extends JpaRepository<MillingSession, Long> {

    List<MillingSession> findByUserId(Long userId);

    List<MillingSession> findByUserIdAndDate(Long userId, String date);

}