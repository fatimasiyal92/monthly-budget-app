package com.springmvc.springmvchibernatesample.service;

import com.springmvc.springmvchibernatesample.dao.TransactionDetailDAO;
import com.springmvc.springmvchibernatesample.entity.TransactionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("transactionDetailService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransactionDetailServiceImpl implements TransactionDetailService {

    @Qualifier("transactionDetailDao")
    @Autowired
    private TransactionDetailDAO transactionDetailDAO;

    @Override
    public TransactionDetail getTransactionDetail(Integer transactionDetailId) {
        return transactionDetailDAO.getTransactionDetail(transactionDetailId);
    }

    @Override
    public List<TransactionDetail> getAllTransactionDetails() {
        return transactionDetailDAO.getAllTransactionDetails();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addTransactionDetail(TransactionDetail transactionDetail) {
        transactionDetailDAO.addTransactionDetail(transactionDetail);
    }

    @Override
    @Transactional
    public void deleteTransactionDetail(Integer transactionDetailId) {
        transactionDetailDAO.deleteTransactionDetail(transactionDetailId);
    }

    @Override
    public List<TransactionDetail> findPaginatedTransactionDetails(int page, int pageSize){
        int offset = page * pageSize;
        return transactionDetailDAO.findPaginatedTransactionDetails(offset,pageSize);
    }
}