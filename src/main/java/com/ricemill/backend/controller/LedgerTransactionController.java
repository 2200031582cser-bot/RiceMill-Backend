package com.ricemill.backend.controller;

import com.ricemill.backend.entity.LedgerTransaction;
import com.ricemill.backend.repository.LedgerTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/ledger-transaction")
public class LedgerTransactionController {

    @Autowired
    private LedgerTransactionRepository repository;

    @PostMapping
    public LedgerTransaction save(
            @RequestBody LedgerTransaction transaction
    ) {
        return repository.save(transaction);
    }

    @GetMapping("/{ledgerId}")
    public List<LedgerTransaction> getByLedgerId(
            @PathVariable Long ledgerId
    ) {
        return repository.findByLedgerId(ledgerId);
    }
}
