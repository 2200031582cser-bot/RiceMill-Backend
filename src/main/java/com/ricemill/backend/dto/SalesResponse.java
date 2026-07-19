package com.ricemill.backend.dto;

import java.util.List;

import com.ricemill.backend.entity.Sales;
import com.ricemill.backend.entity.SaleItem;

public class SalesResponse {

    private Sales sale;

    private List<SaleItem> items;

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