package com.ricemill.backend.dto;

public class AttendanceSummaryDTO {

    private int fullDays;

    private int halfDays;

    private int absentDays;

    public AttendanceSummaryDTO(
            int fullDays,
            int halfDays,
            int absentDays
    ) {

        this.fullDays = fullDays;

        this.halfDays = halfDays;

        this.absentDays = absentDays;
    }

    public int getFullDays() {
        return fullDays;
    }

    public void setFullDays(int fullDays) {
        this.fullDays = fullDays;
    }

    public int getHalfDays() {
        return halfDays;
    }

    public void setHalfDays(int halfDays) {
        this.halfDays = halfDays;
    }

    public int getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(int absentDays) {
        this.absentDays = absentDays;
    }
}