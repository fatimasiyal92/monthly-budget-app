package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category getCategory(Integer categoryId) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) sessionFactory.getCurrentSession().createCriteria(Category.class).list();
    }

    @Override
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Category WHERE id = "+categoryId).executeUpdate();
    }
    @Override
    public List<Category> findPaginatedCategories(int page, int pageSize) {
        int offset = page * pageSize;
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("from Category ", Category.class);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}