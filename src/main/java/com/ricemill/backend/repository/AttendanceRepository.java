package com.ricemill.backend.repository;

import com.ricemill.backend.entity.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUserId(Long userId);

    List<Attendance> findByEmployeeNameAndAttendanceDateStartingWith(

            String employeeName,

            String attendanceDate

    );
}