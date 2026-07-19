package com.ricemill.backend.repository;

import com.ricemill.backend.entity.LedgerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerTransactionRepository
        extends JpaRepository<LedgerTransaction, Long> {

    List<LedgerTransaction> findByLedgerId(Long ledgerId);

}