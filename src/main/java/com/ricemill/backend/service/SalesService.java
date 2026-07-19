package com.ricemill.backend.service;

import com.ricemill.backend.dto.SaleRequest;
import com.ricemill.backend.entity.CustomerLedger;
import com.ricemill.backend.entity.LedgerTransaction;
import com.ricemill.backend.entity.SaleItem;
import com.ricemill.backend.entity.Sales;
import com.ricemill.backend.repository.CustomerLedgerRepository;
import com.ricemill.backend.repository.LedgerTransactionRepository;
import com.ricemill.backend.repository.SaleItemRepository;
import com.ricemill.backend.repository.SalesRepository;
import com.ricemill.backend.dto.SalesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private CustomerLedgerRepository ledgerRepository;

    @Autowired
    private LedgerTransactionRepository ledgerTransactionRepository;

    public Sales saveSale(SaleRequest request) {

        Sales sale = request.getSale();

        Sales savedSale = salesRepository.save(sale);

        if(request.getItems()!=null){

            for(SaleItem item : request.getItems()){

                item.setSaleId(savedSale.getId());

                saleItemRepository.save(item);

            }

        }

        double dueAmount =
                sale.getTotalAmount()
                        -
                        sale.getAmountReceived();

        if(

                dueAmount>0

                        &&

                        "DUE".equals(
                                sale.getPaymentStatus()
                        )

        ){

            CustomerLedger existingLedger =
                    ledgerRepository
                            .findByUserIdAndCustomerName(

                                    sale.getUserId(),

                                    sale.getCustomerName()

                            );

            CustomerLedger savedLedger;

            if(existingLedger!=null){

                existingLedger.setBillAmount(

                        existingLedger.getBillAmount()

                                +

                                sale.getTotalAmount()

                );

                existingLedger.setAmountReceived(

                        existingLedger.getAmountReceived()

                                +

                                sale.getAmountReceived()

                );

                existingLedger.setDueAmount(

                        existingLedger.getDueAmount()

                                +

                                dueAmount

                );

                savedLedger =
                        ledgerRepository.save(
                                existingLedger
                        );

            }

            else{

                CustomerLedger ledger =
                        new CustomerLedger();

                ledger.setUserId(
                        sale.getUserId()
                );

                ledger.setCustomerName(
                        sale.getCustomerName()
                );

                ledger.setPhoneNumber(
                        sale.getCustomerPhone()
                );

                ledger.setBillAmount(
                        sale.getTotalAmount()
                );

                ledger.setAmountReceived(
                        sale.getAmountReceived()
                );

                ledger.setDueAmount(
                        dueAmount
                );

                ledger.setLedgerDate(
                        sale.getSaleDate()
                );

                ledger.setStatus(
                        "PENDING"
                );

                savedLedger =
                        ledgerRepository.save(
                                ledger
                        );

            }

            LedgerTransaction transaction =
                    new LedgerTransaction();

            transaction.setUserId(
                    sale.getUserId()
            );

            transaction.setLedgerId(
                    savedLedger.getId()
            );

            transaction.setTransactionDate(
                    sale.getSaleDate()
            );

            transaction.setParticulars(
                    "Sale Invoice"
            );

            transaction.setDebitAmount(
                    sale.getTotalAmount()
            );

            transaction.setCreditAmount(
                    sale.getAmountReceived()
            );

            transaction.setBalanceAmount(
                    dueAmount
            );

            ledgerTransactionRepository.save(
                    transaction
            );

        }

        return savedSale;

    }

    public List<SalesResponse> getSalesByUser(Long userId) {

        List<Sales> sales = salesRepository.findByUserId(userId);

        List<SalesResponse> response = new ArrayList<>();

        for (Sales sale : sales) {

            SalesResponse dto = new SalesResponse();

            dto.setSale(sale);

            dto.setItems(

                    saleItemRepository.findBySaleId(

                            sale.getId()

                    )

            );

            response.add(dto);

        }

        return response;

    }

}