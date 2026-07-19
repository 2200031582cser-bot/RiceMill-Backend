package com.ricemill.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "milling_session")
public class MillingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String date;

    private Integer sessionNo;

    private String riceType;

    private Double inputPaddy;

    private Double outputRice;

    private Double outputBrokenRice;

    private String remarks;

    public MillingSession() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSessionNo() {
        return sessionNo;
    }

    public void setSessionNo(Integer sessionNo) {
        this.sessionNo = sessionNo;
    }

    public String getRiceType() {
        return riceType;
    }

    public void setRiceType(String riceType) {
        this.riceType = riceType;
    }

    public Double getInputPaddy() {
        return inputPaddy;
    }

    public void setInputPaddy(Double inputPaddy) {
        this.inputPaddy = inputPaddy;
    }

    public Double getOutputRice() {
        return outputRice;
    }

    public void setOutputRice(Double outputRice) {
        this.outputRice = outputRice;
    }

    public Double getOutputBrokenRice() {
        return outputBrokenRice;
    }

    public void setOutputBrokenRice(Double outputBrokenRice) {
        this.outputBrokenRice = outputBrokenRice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}