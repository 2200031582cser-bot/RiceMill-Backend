package com.ricemill.backend.repository;

import com.ricemill.backend.entity.CustomerLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerLedgerRepository
        extends JpaRepository<CustomerLedger, Long> {

    List<CustomerLedger> findByUserId(Long userId);

    CustomerLedger findByUserIdAndCustomerName(
            Long userId,
            String customerName
    );
}