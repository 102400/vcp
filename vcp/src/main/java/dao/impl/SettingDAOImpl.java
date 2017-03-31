package dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.SettingDAO;
import entity.Setting;
import entity.User;

@Repository
public class SettingDAOImpl implements SettingDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public int save(Setting setting) {
		// TODO Auto-generated method stub
		return (int) sessionFactory.getCurrentSession().save(setting);
	}

	@Override
	public Setting findSettingByUserId(User user) {
		// TODO Auto-generated method stub
		return (Setting) sessionFactory.getCurrentSession().find(Setting.class, user.getUserId());
	}

}
