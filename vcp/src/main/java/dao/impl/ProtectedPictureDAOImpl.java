package dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.ProtectedPictureDAO;
import entity.ProtectedPicture;

@Repository
public class ProtectedPictureDAOImpl implements ProtectedPictureDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(ProtectedPicture protectedPicture) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(protectedPicture);
	}

	@Override
	public ProtectedPicture findProtectedPictureByPictureId(ProtectedPicture protectedPicture) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProtectedPicture> criteria = builder.createQuery(ProtectedPicture.class);
		Root<ProtectedPicture> contactRoot = criteria.from(ProtectedPicture.class);
		criteria.select(contactRoot);
		criteria.where(builder.equal(contactRoot.get("pictureId"), protectedPicture.getPictureId()));
		List<ProtectedPicture> list = session.createQuery(criteria).getResultList();
		
		return list.size()==0 ? null : list.get(0);
	}

	@Override
	public boolean updateProtectedPicture(ProtectedPicture protectedPicture) {
		// TODO Auto-generated method stub
		 sessionFactory.getCurrentSession().update(protectedPicture);
		return true;
	}

}
