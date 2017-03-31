package dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.FollowerCategoryDAO;
import entity.FollowerCategory;

@Repository
public class FollowerCategoryDAOImpl implements FollowerCategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(FollowerCategory followerCategory) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(followerCategory);
	}

}
