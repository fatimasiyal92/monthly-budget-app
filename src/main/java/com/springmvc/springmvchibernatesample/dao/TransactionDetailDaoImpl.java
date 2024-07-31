package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.TransactionDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository("transactionDetailDao")
public class TransactionDetailDaoImpl implements TransactionDetailDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTransactionDetail(TransactionDetail transactionDetail) {
        sessionFactory.getCurrentSession().saveOrUpdate(transactionDetail);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionDetail> getAllTransactionDetails() {
        return sessionFactory.getCurrentSession().createQuery("from TransactionDetail")
                .list();
    }

    @Override
    public void deleteTransactionDetail(Integer transactionDetailId) {
        TransactionDetail transactionDetail = (TransactionDetail) sessionFactory.getCurrentSession().load(
                TransactionDetail.class, transactionDetailId);
        if (null != transactionDetail) {
            this.sessionFactory.getCurrentSession().delete(transactionDetail);
        }
    }

    @Override
    public TransactionDetail getTransactionDetail(Integer transactionDetailId) {
        return (TransactionDetail) sessionFactory.getCurrentSession().get(
                TransactionDetail.class, transactionDetailId);
    }

    @Override
    public List<TransactionDetail> findPaginatedTransactionDetails(int offset, int pageSize){
        return sessionFactory.getCurrentSession().createQuery("from TransactionDetail")
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .list();
    }
}