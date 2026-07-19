package com.ricemill.backend.dto;

import com.ricemill.backend.entity.Invoice;
import com.ricemill.backend.entity.InvoiceItem;

import java.util.List;

public class InvoiceResponse {

    private Invoice invoice;

    private List<InvoiceItem> items;

    public InvoiceResponse() {
    }

    public InvoiceResponse(Invoice invoice, List<InvoiceItem> items) {
        this.invoice = invoice;
        this.items = items;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }
}