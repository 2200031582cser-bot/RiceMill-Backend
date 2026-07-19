package com.ricemill.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_salary")

public class EmployeeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long userId;

    private String employeeName;

    private String monthName;

    private Double totalSalary;

    private Double dailyWage;

    private Double fullDays;

    private Double halfDays;

    private Double monthlyFixed;

    private Double finalSalary;

    private Double amountPaid;

    private Double balanceAmount;

    public EmployeeSalary() {
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(
            String employeeName
    ) {
        this.employeeName = employeeName;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(
            String monthName
    ) {
        this.monthName = monthName;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(
            Double totalSalary
    ) {
        this.totalSalary = totalSalary;
    }

    public Double getDailyWage() {
        return dailyWage;
    }

    public void setDailyWage(
            Double dailyWage
    ) {
        this.dailyWage = dailyWage;
    }

    public Double getFullDays() {
        return fullDays;
    }

    public void setFullDays(
            Double fullDays
    ) {
        this.fullDays = fullDays;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(
            Double amountPaid
    ) {
        this.amountPaid = amountPaid;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(
            Double balanceAmount
    ) {
        this.balanceAmount = balanceAmount;
    }

    public Double getHalfDays() {
        return halfDays;
    }

    public void setHalfDays(
            Double halfDays
    ) {
        this.halfDays = halfDays;
    }

    public Double getMonthlyFixed() {
        return monthlyFixed;
    }

    public void setMonthlyFixed(
            Double monthlyFixed
    ) {
        this.monthlyFixed = monthlyFixed;
    }

    public Double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(
            Double finalSalary
    ) {
        this.finalSalary = finalSalary;
    }
}