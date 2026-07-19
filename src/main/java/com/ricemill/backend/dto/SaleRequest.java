package com.ricemill.backend.dto;

import com.ricemill.backend.entity.SaleItem;
import com.ricemill.backend.entity.Sales;

import java.util.List;

public class SaleRequest {

    private Sales sale;

    private List<SaleItem> items;

    public SaleRequest() {
    }

    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }
}