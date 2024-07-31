package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository("transactionDao")
public class TransactionDaoImpl implements TransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Transaction getTransaction(Integer transactionId) {
        return (Transaction) sessionFactory.getCurrentSession().get(Transaction.class, transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) sessionFactory.getCurrentSession().createCriteria(Transaction.class).list();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().saveOrUpdate(transaction);
    }

    @Override
    public void deleteTransaction(Integer transactionId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Transaction WHERE id = "+transactionId).executeUpdate();
    }

    @Override
    public List<Transaction> findPaginatedTransactions(int page, int pageSize) {
        int offset = page * pageSize;
        Session session = sessionFactory.getCurrentSession();
        Query<Transaction> query = session.createQuery("from Transaction ", Transaction.class);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}