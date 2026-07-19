package com.ricemill.backend.dto;

public class PaymentRequest {

    private Double paymentAmount;

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}