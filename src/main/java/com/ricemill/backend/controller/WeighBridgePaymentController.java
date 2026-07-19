package com.ricemill.backend.controller;

import com.ricemill.backend.entity.WeighBridgePayment;
import com.ricemill.backend.service.WeighBridgePaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weighbridge/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class WeighBridgePaymentController {

    private final WeighBridgePaymentService service;

    public WeighBridgePaymentController(
            WeighBridgePaymentService service) {

        this.service = service;

    }

    // Save Payment

    @PostMapping
    public WeighBridgePayment savePayment(

            @RequestBody WeighBridgePayment payment) {

        return service.save(payment);

    }

    // Get Payments of an Entry

    @GetMapping("/customer")

    public List<WeighBridgePayment>

    getCustomerPayments(

            @RequestParam String customerName,

            @RequestParam Long userId

    ){

        return service.getCustomerPayments(

                customerName,

                userId

        );

    }

    @GetMapping("/user/{userId}")
    public List<WeighBridgePayment> getAllPayments(
            @PathVariable Long userId){

        return service.getAllByUser(userId);

    }

}