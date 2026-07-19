package com.ricemill.backend.repository;

import com.ricemill.backend.entity.WeighBridgePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeighBridgePaymentRepository
        extends JpaRepository<WeighBridgePayment, Long> {

    List<WeighBridgePayment>
    findByCustomerNameAndUserId(

            String customerName,

            Long userId

    );

    List<WeighBridgePayment> findByUserId(Long userId);
}