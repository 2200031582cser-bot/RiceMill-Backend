package com.ricemill.backend.controller;

import com.ricemill.backend.entity.CustomerLedger;
import com.ricemill.backend.entity.LedgerTransaction;
import com.ricemill.backend.entity.Sales;

import com.ricemill.backend.repository.CustomerLedgerRepository;
import com.ricemill.backend.repository.LedgerTransactionRepository;
import com.ricemill.backend.repository.SalesRepository;
import com.ricemill.backend.repository.SaleItemRepository;
import com.ricemill.backend.service.SalesService;
import com.ricemill.backend.dto.SaleRequest;
import com.ricemill.backend.dto.SalesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesService salesService;


    @GetMapping("/user/{userId}")
    public List<SalesResponse> getSalesByUser(

            @PathVariable Long userId

    ) {

        return salesService.getSalesByUser(userId);

    }

    @PostMapping
    public Sales saveSale(
            @RequestBody SaleRequest request
    ) {

        return salesService.saveSale(request);

    }

    @GetMapping
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteSale(
            @PathVariable Long id
    ) {
        salesRepository.deleteById(id);
    }
}