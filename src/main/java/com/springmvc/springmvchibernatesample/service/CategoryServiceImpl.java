package com.springmvc.springmvchibernatesample.service;

import com.springmvc.springmvchibernatesample.dao.CategoryDAO;
import com.springmvc.springmvchibernatesample.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Qualifier("categoryDao")
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Category getCategory(Integer categoryId) {
        return categoryDAO.getCategory(categoryId);
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }
    @Override
    @Transactional
    public void deleteCategory(Integer categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }
    public List<Category> findPaginatedCategories(int page, int pageSize){
        int offset = page * pageSize;
        return categoryDAO.findPaginatedCategories(offset,pageSize);
    }
}