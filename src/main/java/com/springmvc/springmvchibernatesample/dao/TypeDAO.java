package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.Type;

import java.util.List;

public interface TypeDAO {
    public Type getType(Integer typeId);

    public List<Type> getAllTypes();

    public void addType(Type type);

    public void deleteType(Integer typeId);
    public List<Type> findPaginatedTypes(int page, int pageSize);

}
