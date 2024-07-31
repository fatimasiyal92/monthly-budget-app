package com.springmvc.springmvchibernatesample.service;

import com.springmvc.springmvchibernatesample.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {

    public TransactionDetail getTransactionDetail(Integer transactionDetailId);

    public List<TransactionDetail> getAllTransactionDetails();

    public void addTransactionDetail(TransactionDetail transactionDetail);

    public void deleteTransactionDetail(Integer transactionDetailId);

    public List<TransactionDetail> findPaginatedTransactionDetails(int page, int pageSize);
}