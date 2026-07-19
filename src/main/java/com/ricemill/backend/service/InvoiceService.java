package com.ricemill.backend.service;

import com.ricemill.backend.dto.InvoiceResponse;
import com.ricemill.backend.entity.Invoice;
import com.ricemill.backend.entity.InvoiceItem;
import com.ricemill.backend.entity.InvoiceStatus;
import com.ricemill.backend.entity.Sales;
import com.ricemill.backend.repository.InvoiceItemRepository;
import com.ricemill.backend.repository.InvoiceRepository;
import com.ricemill.backend.repository.SalesRepository;
import com.ricemill.backend.repository.SaleItemRepository;
import com.ricemill.backend.entity.SaleItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    /*
     * =====================================
     * GENERATE INVOICE NUMBER
     * =====================================
     */

    public String generateInvoiceNumber(
            Long userId,
            LocalDate saleDate
    ) {

        int financialYearStart;

        if (saleDate.getMonthValue() >= 4) {

            financialYearStart = saleDate.getYear();

        } else {

            financialYearStart = saleDate.getYear() - 1;

        }

        String financialYear =

                financialYearStart +

                        "-" +

                        String.valueOf(financialYearStart + 1)

                                .substring(2);

        Optional<Invoice> latest =

                invoiceRepository

                        .findTopByUserIdOrderByIdDesc(userId);

        int nextNumber = 1;

        if (latest.isPresent()) {

            String lastInvoice =

                    latest.get()

                            .getInvoiceNumber();

            if (lastInvoice != null &&
                    lastInvoice.contains("-")) {

                String last =

                        lastInvoice.substring(

                                lastInvoice.lastIndexOf("-") + 1

                        );

                nextNumber =

                        Integer.parseInt(last) + 1;

            }

        }

        return String.format(

                "INV-%s-%06d",

                financialYear,

                nextNumber

        );

    }

    /*
     * =====================================
     * SAVE MANUAL INVOICE
     * =====================================
     */

    public Invoice saveInvoice(

            Invoice invoice,

            List<InvoiceItem> items

    ) {

        LocalDate invoiceDate =

                invoice.getInvoiceDate() != null

                        ? invoice.getInvoiceDate()

                        : LocalDate.now();

        if (

                invoice.getInvoiceNumber() == null ||

                        invoice.getInvoiceNumber().isBlank()

        ) {

            invoice.setInvoiceNumber(

                    generateInvoiceNumber(

                            invoice.getUserId(),

                            invoiceDate

                    )

            );

        }

        invoice.setInvoiceDate(invoiceDate);

        invoice.setStatus(

                InvoiceStatus.GENERATED

        );

        Invoice savedInvoice =

                invoiceRepository.save(invoice);

        for (InvoiceItem item : items) {

            item.setInvoice(savedInvoice);

            invoiceItemRepository.save(item);

        }

        return savedInvoice;

    }

    /*
     * =====================================
     * GENERATE FROM SALE
     * =====================================
     */

    public Invoice generateInvoiceFromSale(

            Long saleId

    ) {

        Sales sale =

                salesRepository.findById(saleId)

                        .orElseThrow(

                                () -> new RuntimeException(

                                        "Sale not found"

                                )

                        );

        /*
         * Already generated?
         */

        List<Invoice> existing =

                invoiceRepository.findBySaleId(saleId);

        if (!existing.isEmpty()) {

            return existing.get(0);

        }

        LocalDate saleDate =

                LocalDate.parse(

                        sale.getSaleDate()

                );

        Invoice invoice = new Invoice();

        invoice.setUserId(

                sale.getUserId()

        );

        invoice.setSaleId(

                sale.getId()

        );

        invoice.setInvoiceNumber(

                generateInvoiceNumber(

                        sale.getUserId(),

                        saleDate

                )

        );

        int fyStart =

                saleDate.getMonthValue() >= 4

                        ? saleDate.getYear()

                        : saleDate.getYear() - 1;

        invoice.setFinancialYear(

                fyStart +

                        "-" +

                        String.valueOf(fyStart + 1)

                                .substring(2)

        );

        invoice.setInvoiceDate(

                saleDate

        );

        invoice.setCustomerName(

                sale.getCustomerName()

        );

        invoice.setCustomerPhone(

                sale.getCustomerPhone()

        );

        invoice.setTaxableAmount(

                sale.getTotalAmount()

        );

        invoice.setTotalAmount(

                sale.getTotalAmount()

        );

        invoice.setStatus(

                InvoiceStatus.GENERATED

        );

        Invoice savedInvoice =

                invoiceRepository.save(invoice);

        List<SaleItem> saleItems =

                saleItemRepository.findBySaleId(

                        sale.getId()

                );

        for (SaleItem saleItem : saleItems) {

            InvoiceItem item = new InvoiceItem();

            item.setInvoice(savedInvoice);

            item.setProductName(

                    saleItem.getSkuName()

            );

            item.setQuantity(

                    saleItem.getQuantity()

            );

            item.setRate(

                    saleItem.getRate()

            );

            item.setTaxableValue(

                    saleItem.getAmount()

            );

            item.setTotalAmount(

                    saleItem.getAmount()

            );

            item.setUnit(

                    saleItem.getUnit()

            );

            item.setGstRate(0.0);

            item.setGstAmount(0.0);

            item.setHsnCode("");

            invoiceItemRepository.save(item);

        }

        return savedInvoice;

    }

    /*
     * =====================================
     * GET ALL USER INVOICES
     * =====================================
     */

    public List<Invoice> getInvoices(Long userId) {

        return invoiceRepository
                .findByUserIdOrderByIdDesc(userId);

    }

    /*
     * =====================================
     * GET SINGLE INVOICE
     * =====================================
     */

    public Invoice getInvoice(Long id) {

        Optional<Invoice> invoice =
                invoiceRepository.findById(id);

        return invoice.orElse(null);

    }

    /*
     * =====================================
     * GET INVOICE BY SALE
     * =====================================
     */

    public Invoice getInvoiceBySale(Long saleId) {

        List<Invoice> invoices =
                invoiceRepository.findBySaleId(saleId);

        if (invoices.isEmpty()) {

            return null;

        }

        return invoices.get(0);

    }

    /*
     * =====================================
     * GET INVOICE ITEMS
     * =====================================
     */

    public List<InvoiceItem> getInvoiceItems(
            Invoice invoice
    ) {

        return invoiceItemRepository
                .findByInvoice(invoice);

    }

    /*
     * =====================================
     * DELETE INVOICE
     * =====================================
     */

    public void deleteInvoice(Long id) {

        Invoice invoice = getInvoice(id);

        if (invoice == null) {

            return;

        }

        List<InvoiceItem> items =
                getInvoiceItems(invoice);

        invoiceItemRepository.deleteAll(items);

        invoiceRepository.delete(invoice);

    }

    /*
     * =====================================
     * GET COMPLETE INVOICE DETAILS
     * =====================================
     */

    public InvoiceResponse getInvoiceDetails(Long id) {

        Invoice invoice = getInvoice(id);

        if (invoice == null) {

            return null;

        }

        List<InvoiceItem> items =
                getInvoiceItems(invoice);

        return new InvoiceResponse(
                invoice,
                items
        );

    }

}