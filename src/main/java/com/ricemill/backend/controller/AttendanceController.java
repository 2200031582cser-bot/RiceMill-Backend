package com.ricemill.backend.controller;

import com.ricemill.backend.entity.Attendance;

import com.ricemill.backend.repository.AttendanceRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.ricemill.backend.dto.AttendanceSummaryDTO;

import java.util.List;

@RestController



@RequestMapping("/attendance")

public class AttendanceController {

    @Autowired

    private AttendanceRepository attendanceRepository;

    @PostMapping

    public Attendance addAttendance(
            @RequestBody Attendance attendance
    ) {

        return attendanceRepository.save(attendance);

    }

    @PostMapping("/bulk")
    public List<Attendance> addBulkAttendance(
            @RequestBody List<Attendance> attendanceList
    ) {

        return attendanceRepository.saveAll(attendanceList);

    }
    @GetMapping

    public List<Attendance> getAttendance() {

        return attendanceRepository.findAll();

    }

    @GetMapping("/user/{userId}")
    public List<Attendance> getAttendanceByUserId(
            @PathVariable Long userId
    ) {

        return attendanceRepository.findByUserId(userId);

    }

    @GetMapping("/summary/{employee}/{month}")

    public AttendanceSummaryDTO getSummary(

            @PathVariable String employee,

            @PathVariable String month

    ) {

        List<Attendance> list =

                attendanceRepository
                        .findByEmployeeNameAndAttendanceDateStartingWith(

                                employee,

                                month
                        );

        int fullDays = 0;

        int halfDays = 0;

        int absentDays = 0;

        for (Attendance attendance : list) {

            if (

                    attendance.getStatus()
                            .equals("FULL_DAY")

            ) {

                fullDays++;

            }

            else if (

                    attendance.getStatus()
                            .equals("FIRST_HALF")

                            ||

                            attendance.getStatus()
                                    .equals("SECOND_HALF")

            ) {

                halfDays++;

            }

            else {

                absentDays++;
            }
        }

        return new AttendanceSummaryDTO(

                fullDays,

                halfDays,

                absentDays
        );
    }
}
