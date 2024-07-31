package com.springmvc.springmvchibernatesample.service;

import com.springmvc.springmvchibernatesample.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category getCategory(Integer categoryId);

    public List<Category> getAllCategories();

    public void addCategory(Category category);

    public void deleteCategory(Integer categoryId);

    public List<Category> findPaginatedCategories(int page, int pageSize);
}