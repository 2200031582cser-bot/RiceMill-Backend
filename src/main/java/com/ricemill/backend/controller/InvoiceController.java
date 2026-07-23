package com.ricemill.backend.controller;

import com.ricemill.backend.dto.InvoiceRequest;
import com.ricemill.backend.dto.InvoiceResponse;
import com.ricemill.backend.entity.Invoice;
import com.ricemill.backend.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")

public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /*
     * Create Invoice
     */

    @PostMapping
    public ResponseEntity<?> createInvoice(

            @RequestBody InvoiceRequest request

    ) {

        try {

            Invoice invoice =
                    invoiceService.saveInvoice(

                            request.getInvoice(),

                            request.getItems()

                    );

            return ResponseEntity.ok(invoice);

        }

        catch (Exception ex) {

            ex.printStackTrace();

            return ResponseEntity.badRequest()

                    .body(ex.getMessage());

        }

    }

    /*
     * Get All User Invoices
     */

    @GetMapping("/user/{userId}")
    public List<Invoice> getInvoices(

            @PathVariable Long userId

    ) {

        return invoiceService.getInvoices(userId);

    }

    /*
     * Get Single Invoice
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoice(
            @PathVariable Long id
    ) {

        InvoiceResponse response =
                invoiceService.getInvoiceDetails(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    /*
     * Delete Invoice
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(

            @PathVariable Long id

    ) {

        invoiceService.deleteInvoice(id);

        return ResponseEntity.ok("Invoice Deleted");

    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<?> getInvoiceBySale(@PathVariable Long saleId) {

        Invoice invoice = invoiceService.getInvoiceBySale(saleId);

        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/generate/{saleId}")
    public ResponseEntity<?> generateInvoiceFromSale(
            @PathVariable Long saleId
    ) {

        try {

            Invoice invoice =
                    invoiceService.generateInvoiceFromSale(saleId);

            return ResponseEntity.ok(invoice);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

}
