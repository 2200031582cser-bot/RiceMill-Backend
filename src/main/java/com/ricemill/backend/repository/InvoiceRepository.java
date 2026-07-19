package com.ricemill.backend.repository;

import com.ricemill.backend.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUserIdAndSaleId(Long userId, Long saleId);
    List<Invoice> findByUserIdOrderByIdDesc(Long userId);
    Optional<Invoice> findTopByUserIdOrderByIdDesc(Long userId);
    Optional<Invoice> findTopByUserIdOrderByInvoiceNumberDesc(Long userId);

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    List<Invoice> findBySaleId(Long saleId);

    Optional<Invoice> findTopByOrderByIdDesc();

}