package dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
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

	@Override
	public List<FollowerCategory> findFollowerCategoryListByUserId(FollowerCategory followerCategory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FollowerCategory> criteria = builder.createQuery(FollowerCategory.class);
		Root<FollowerCategory> contactRoot = criteria.from(FollowerCategory.class);
		criteria.select(contactRoot);
		criteria.where(builder.equal(contactRoot.get("userId"), followerCategory.getUserId()));
		List<FollowerCategory> list = session.createQuery(criteria).getResultList();
		
		return list.size()==0 ? null : list;
	}

}
