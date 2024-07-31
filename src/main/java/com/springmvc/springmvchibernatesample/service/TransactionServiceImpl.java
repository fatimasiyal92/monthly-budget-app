package com.springmvc.springmvchibernatesample.service;

import com.springmvc.springmvchibernatesample.dao.TransactionDAO;
import com.springmvc.springmvchibernatesample.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("transactionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    @Qualifier("transactionDao")
    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public Transaction getTransaction(Integer transactionId) {
        return transactionDAO.getTransaction(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(Integer transactionId) {
        transactionDAO.deleteTransaction(transactionId);
    }

    @Override
    public List<Transaction> findPaginatedTransactions(int page, int pageSize){
        int offset = page * pageSize;
        return transactionDAO.findPaginatedTransactions(offset,pageSize);
    }
}