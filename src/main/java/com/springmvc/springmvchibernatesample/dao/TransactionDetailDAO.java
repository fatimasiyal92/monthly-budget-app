package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailDAO {
    public TransactionDetail getTransactionDetail(Integer transactionDetailId);

    public List<TransactionDetail> getAllTransactionDetails();

    public void addTransactionDetail(TransactionDetail transactionDetail);

    public void deleteTransactionDetail(Integer transactionDetailId);

    public List<TransactionDetail> findPaginatedTransactionDetails(int page, int pageSize);
}