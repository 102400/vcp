package dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.FollowerDAO;
import entity.Follower;
import entity.Picture;

@Repository
public class FollowerDAOImpl implements FollowerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int save(Follower follower) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(follower);
	}
	
	@Override
	public boolean deleteFollower(Follower follower) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Follower> criteria = builder.createCriteriaDelete(Follower.class);
//		CriteriaQuery<Follower> criteria = builder.createQuery(Follower.class);
		Root<Follower> contactRoot = criteria.from(Follower.class);
		criteria.where(builder.equal(contactRoot.get("userId"), follower.getUserId()));
		criteria.where(builder.equal(contactRoot.get("followerId"), follower.getFollowerId()));
//		List<Follower> list = session.createQuery(criteria).getResultList();
		int x = session.createQuery(criteria).executeUpdate();
		
		return x<=0 ? false : true;
	}

	@Override
	public boolean isFollowerInTable(Follower follower) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Follower> criteria = builder.createQuery(Follower.class);
		Root<Follower> contactRoot = criteria.from(Follower.class);
		criteria.select(contactRoot);
		criteria.where(builder.equal(contactRoot.get("userId"), follower.getUserId()));
		criteria.where(builder.equal(contactRoot.get("followerId"), follower.getFollowerId()));
		List<Follower> list = session.createQuery(criteria).getResultList();
		
		return list.size()==0 ? false : true;
	}

	@Override
	public List<Follower> findFollowerByUserId(Follower follower) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Follower> criteria = builder.createQuery(Follower.class);
		Root<Follower> contactRoot = criteria.from(Follower.class);
		criteria.select(contactRoot);
		criteria.where(builder.equal(contactRoot.get("userId"), follower.getUserId()));
		List<Follower> list = session.createQuery(criteria).getResultList();
		
		return list.size()==0 ? null : list;
	}

	@Override
	public boolean updateFollowerCategory(Follower follower) {
		// TODO Auto-generated method stub
		List<Follower> list = findFollowerByUserId(follower);
		if(list==null) return false;
		
		for(Follower f:list) {
			if(f.getFollowerId()==follower.getFollowerId()) {
				//service层检查这个category是否属于这个用户
				f.setCategoryId(follower.getCategoryId());
				sessionFactory.getCurrentSession().update(f);
				return true;
			}
		}
		
		return false;
	}

}
