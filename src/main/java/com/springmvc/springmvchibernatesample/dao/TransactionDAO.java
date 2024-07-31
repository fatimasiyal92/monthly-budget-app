package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.Transaction;

import java.util.List;

public interface TransactionDAO {
    public Transaction getTransaction(Integer transactionId);

    public List<Transaction> getAllTransactions();

    public void addTransaction(Transaction transaction);

    public void deleteTransaction(Integer transactionId);

    public List<Transaction> findPaginatedTransactions(int page, int pageSize);
}