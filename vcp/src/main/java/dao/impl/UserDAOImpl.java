package dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.UserDAO;
import entity.Follower;
import entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public boolean updateFollowing(User user) {
		User u = findUserByUserId(user);
		int x = u.getFollowing() + user.getFollowing();
		u.setFollowing(x);
		sessionFactory.getCurrentSession().update(u);
		
		return true;
	}
	
	@Override
	public boolean updateFollowers(User user) {
		User u = findUserByUserId(user);
		int x = u.getFollowers() + user.getFollowers();
		u.setFollowers(x);
		sessionFactory.getCurrentSession().update(u);
		
		return true;
	}
	
	@Override
	public User findUserByUserId(User user) {
		return sessionFactory.getCurrentSession().find(User.class, user.getUserId());
	}
	
	@Override
	public User findUserByUsername(User user) {
		Session session = sessionFactory.getCurrentSession();
		
//		Criteria c = session.createCriteria(User.class);
//		c.add(Restrictions.eq("username",user.getUsername()));
//		List<User> list = c.list();
//		
//		return list.size()==0 ? null : list.get(0);
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> contactRoot = criteria.from(User.class);
		criteria.select(contactRoot);
		criteria.where(builder.equal(contactRoot.get("username"), user.getUsername()));
		List<User> list = session.createQuery(criteria).getResultList();
		// https://teamtreehouse.com/community/the-sessions-method-createcriteria-is-deprecated-how-should-i-proceed-for-establishing-class-criteria
		// https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
		
		return list.size()==0 ? null : list.get(0);
	}

	@Override
	public User findUserByEmail(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		Criteria c = session.createCriteria(User.class);  // JPA Criteria example --> User findUserByUsername(User user)
		c.add(Restrictions.eq("email",user.getEmail()));
		List<User> list = c.list();
		
		return list.size()==0 ? null : list.get(0);
	}

}
