package com.ricemill.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "government_ledger")
public class GovernmentLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String cropSeason;
    private String cropYear;

    private Double paddyReceived;

    private Double riceDelivered;

    public GovernmentLedger() {
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

    public String getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(String cropSeason) {
        this.cropSeason = cropSeason;
    }

    public String getCropYear() {
        return cropYear;
    }

    public void setCropYear(String cropYear) {
        this.cropYear = cropYear;
    }

    public Double getPaddyReceived() {
        return paddyReceived;
    }

    public void setPaddyReceived(Double paddyReceived) {
        this.paddyReceived = paddyReceived;
    }

    public Double getRiceDelivered() {
        return riceDelivered;
    }

    public void setRiceDelivered(Double riceDelivered) {
        this.riceDelivered = riceDelivered;
    }

}