package com.ricemill.backend.service;

import com.ricemill.backend.entity.WeighBridgePayment;
import com.ricemill.backend.repository.WeighBridgePaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeighBridgePaymentService {

    private final WeighBridgePaymentRepository repository;

    public WeighBridgePaymentService(WeighBridgePaymentRepository repository) {
        this.repository = repository;
    }

    public WeighBridgePayment save(WeighBridgePayment payment) {
        return repository.save(payment);
    }

    public List<WeighBridgePayment>

    getCustomerPayments(

            String customerName,

            Long userId

    ){

        return repository.findByCustomerNameAndUserId(

                customerName,

                userId

        );

    }

    public List<WeighBridgePayment> getAllByUser(Long userId){
        return repository.findByUserId(userId);
    }

}