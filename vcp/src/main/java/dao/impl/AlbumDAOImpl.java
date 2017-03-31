package dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.AlbumDAO;
import entity.Album;

@Repository
public class AlbumDAOImpl implements AlbumDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public int save(Album album) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(album);
	}

}