package com.ricemill.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weigh_bridge_payment")
public class WeighBridgePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String customerName;

    private String paymentDate;

    private Double amountPaid;

    private String paymentMode;

    private String remarks;

    public WeighBridgePayment() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}