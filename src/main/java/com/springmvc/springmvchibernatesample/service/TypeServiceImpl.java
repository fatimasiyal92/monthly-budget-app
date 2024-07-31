package com.springmvc.springmvchibernatesample.service;

import com.springmvc.springmvchibernatesample.dao.TypeDAO;
import com.springmvc.springmvchibernatesample.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("typeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TypeServiceImpl implements TypeService {

	@Qualifier("typeDao")
	@Autowired
	private TypeDAO typeDAO;

	@Override
	public Type getType(Integer typeId) {
		return typeDAO.getType(typeId);
	}
	@Override
	public List<Type> getAllTypes() {
		return typeDAO.getAllTypes();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addType(Type type) {
		typeDAO.addType(type);
	}
	@Override
	@Transactional
	public void deleteType(Integer typeId) {
		typeDAO.deleteType(typeId);
	}
	public List<Type> findPaginatedTypes(int page, int pageSize){
		int offset = page * pageSize;
		return typeDAO.findPaginatedTypes(offset,pageSize);
	}
}
