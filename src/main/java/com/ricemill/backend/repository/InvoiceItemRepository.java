package com.ricemill.backend.repository;

import com.ricemill.backend.entity.Invoice;
import com.ricemill.backend.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceItemRepository
        extends JpaRepository<InvoiceItem, Long> {

    List<InvoiceItem> findByInvoice(Invoice invoice);

}