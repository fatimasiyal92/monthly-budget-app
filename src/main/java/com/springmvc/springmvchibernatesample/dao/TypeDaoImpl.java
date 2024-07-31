package com.springmvc.springmvchibernatesample.dao;

import com.springmvc.springmvchibernatesample.entity.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository("typeDao")
public class TypeDaoImpl implements TypeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Type getType(Integer typeId) {
		return (Type) sessionFactory.getCurrentSession().get(Type.class, typeId);
	}

	@Override
	public List<Type> getAllTypes() {
		return (List<Type>) sessionFactory.getCurrentSession().createCriteria(Type.class).list();
	}

	@Override
	public void addType(Type type) {
		sessionFactory.getCurrentSession().saveOrUpdate(type);
	}

	@Override
	public void deleteType(Integer typeId) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Type WHERE id = "+typeId).executeUpdate();
	}
	@Override
	public List<Type> findPaginatedTypes(int page, int pageSize) {
		int offset = page * pageSize;
		Session session = sessionFactory.getCurrentSession();
		Query<Type> query = session.createQuery("from Type ", Type.class);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
}
