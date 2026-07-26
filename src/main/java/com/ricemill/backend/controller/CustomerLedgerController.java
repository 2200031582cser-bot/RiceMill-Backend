package com.ricemill.backend.controller;

import com.ricemill.backend.entity.CustomerLedger;
import com.ricemill.backend.repository.CustomerLedgerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ricemill.backend.dto.PaymentRequest;
import com.ricemill.backend.entity.LedgerTransaction;
import com.ricemill.backend.repository.LedgerTransactionRepository;

import java.util.List;

@RestController

@RequestMapping("/ledger")
public class CustomerLedgerController {

    @Autowired
    private CustomerLedgerRepository repository;

    @Autowired
    private LedgerTransactionRepository
            transactionRepository;

    @PostMapping
    public CustomerLedger saveLedger(
            @RequestBody CustomerLedger ledger
    ) {
        return repository.save(ledger);
    }

    @GetMapping
    public List<CustomerLedger> getAll() {
        return repository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<CustomerLedger> getByUserId(
            @PathVariable Long userId
    ) {
        return repository.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteLedger(
            @PathVariable Long id
    ) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public CustomerLedger updateLedger(
            @PathVariable Long id,
            @RequestBody CustomerLedger updatedLedger
    ) {

        CustomerLedger ledger =
                repository.findById(id)
                        .orElseThrow();

        ledger.setAmountReceived(
                updatedLedger.getAmountReceived()
        );

        ledger.setDueAmount(
                updatedLedger.getDueAmount()
        );

        ledger.setStatus(
                updatedLedger.getStatus()
        );

        return repository.save(ledger);
    }

    @PutMapping("/receive-payment/{id}")
public CustomerLedger receivePayment(
        @PathVariable Long id,
        @RequestBody PaymentRequest request
) {

    CustomerLedger ledger =
            repository.findById(id)
                    .orElseThrow();

    Double amount = request.getPaymentAmount();

    if (amount == null || amount <= 0) {
        throw new IllegalArgumentException(
                "Payment amount must be greater than zero"
        );
    }

    Double currentDue =
            ledger.getDueAmount() == null
                    ? 0.0
                    : ledger.getDueAmount();

    if (amount > currentDue) {
        throw new IllegalArgumentException(
                "Payment cannot exceed outstanding amount of ₹"
                        + currentDue
        );
    }

    Double currentReceived =
            ledger.getAmountReceived() == null
                    ? 0.0
                    : ledger.getAmountReceived();

    Double newReceived =
            currentReceived + amount;

    Double newDue =
            currentDue - amount;

    ledger.setAmountReceived(newReceived);
    ledger.setDueAmount(newDue);

    if (newDue <= 0) {
        ledger.setDueAmount(0.0);
        ledger.setStatus("CLEARED");
    } else {
        ledger.setStatus("PENDING");
    }

    CustomerLedger saved =
            repository.save(ledger);

    LedgerTransaction txn =
            new LedgerTransaction();

    txn.setUserId(ledger.getUserId());
    txn.setLedgerId(ledger.getId());

    txn.setTransactionDate(
            java.time.LocalDate.now().toString()
    );

    txn.setParticulars("Payment Received");
    txn.setDebitAmount(0.0);
    txn.setCreditAmount(amount);
    txn.setBalanceAmount(saved.getDueAmount());

    transactionRepository.save(txn);

    return saved;
}
}
