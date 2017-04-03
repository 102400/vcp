package dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.PictureDAO;
import entity.Follower;
import entity.Picture;

@Repository
public class PictureDAOImpl implements PictureDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(Picture picture) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(picture);
	}

	@Override
	public List<Picture> searchAllByFirstAndMax(int first, int max) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT p FROM Picture p WHERE p.authorize=2 ORDER BY p DESC";  //只有照片为public时
		TypedQuery<Picture> typedQuery=session.createQuery(query, Picture.class);
		typedQuery.setFirstResult(first);
		typedQuery.setMaxResults(max);
		
		return typedQuery.getResultList();
	}

	@Override
	public Picture findPictureByUuid(Picture picture) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Picture> criteria = builder.createQuery(Picture.class);
		Root<Picture> contactRoot = criteria.from(Picture.class);
		criteria.select(contactRoot);
		criteria.where(builder.equal(contactRoot.get("uuid"), picture.getUuid()));
		List<Picture> list = session.createQuery(criteria).getResultList();
		
		
		return list.size()==0 ? null : list.get(0);
	}

	@Override
	public Picture findPictureByPictureId(Picture picture) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().find(Picture.class, picture.getPictureId());
	}

	@Override
	public boolean updatePicture(Picture picture) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(picture);
		return true;
	}

	@Override
	public List<Picture> searchPeopleAllByFirstAndMax(Follower follower, int first, int max) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT p FROM Picture p WHERE p.userId=" + follower.getUserId() + " ORDER BY p DESC";
		TypedQuery<Picture> typedQuery=session.createQuery(query, Picture.class);
		typedQuery.setFirstResult(first);
		typedQuery.setMaxResults(max);
		
		return typedQuery.getResultList();
	}

	@Override
	public List<Picture> searchPeoplePublicByFirstAndMax(Follower follower, int first, int max) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT p FROM Picture p WHERE p.userId=" + follower.getUserId() 
			+ " AND p.authorize=2 "
			+ " ORDER BY p DESC";
		TypedQuery<Picture> typedQuery=session.createQuery(query, Picture.class);
		typedQuery.setFirstResult(first);
		typedQuery.setMaxResults(max);
		
		return typedQuery.getResultList();
	}

	@Override
	public List<Picture> searchPeoplePublicAndProtectedByFirstAndMax(Follower follower, int first, int max) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// http://yukinami.github.io/2016/05/21/Hibernate%E4%BD%BF%E7%94%A8outer%20join%20fetch%E9%9B%86%E5%90%88%E6%97%B6%E8%BF%94%E5%9B%9E%E9%87%8D%E5%A4%8D%E7%9A%84%E7%BB%93%E6%9E%9C/
		// 结果重复 加DISTINCT
		String query = "SELECT DISTINCT p FROM Picture p, ProtectedPicture pp"
			+ " WHERE p.userId=" + follower.getUserId() 
			+ " AND ( p.authorize=2 OR ( p.authorize=3 AND pp.pictureId=p.pictureId AND pp.categoryId=" + follower.getCategoryId() + " ))"
			+ " ORDER BY p DESC";
		
		org.hibernate.query.Query q = session.createQuery("SELECT p FROM Picture p, ProtectedPicture pp"
			+ " WHERE p.userId=" + follower.getUserId() 
			+ " AND ( p.authorize=2 OR ( p.authorize=3 AND pp.pictureId=p.pictureId AND pp.categoryId=" + follower.getCategoryId() + " ))"
			+ " ORDER BY p DESC", Picture.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		TypedQuery<Picture> typedQuery=session.createQuery(query, Picture.class);
//		TypedQuery<Picture> typedQuery=q;  //影响分页
		typedQuery.setFirstResult(first);
		typedQuery.setMaxResults(max);
		
		return typedQuery.getResultList();
	}

}