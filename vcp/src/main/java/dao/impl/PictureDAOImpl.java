package dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.PictureDAO;
import entity.Picture;
import entity.User;

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

}
