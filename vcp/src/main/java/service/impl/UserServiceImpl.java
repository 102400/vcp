package service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AlbumDAO;
import dao.FollowerCategoryDAO;
import dao.SettingDAO;
import dao.UserDAO;
import entity.Album;
import entity.FollowerCategory;
import entity.Setting;
import entity.User;
import service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private AlbumDAO albumDao;
	
	@Autowired
	private FollowerCategoryDAO followerCategoryDAO;
	
	@Autowired
	private SettingDAO settingDao;

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		user.setFollowers(0);
		user.setFollowing(0);
		int userId =  userDao.save(user);
		
		Album album = new Album();
		album.setUserId(userId);
		album.setDescription("");
		album.setName("default");
		
		int albumId = albumDao.save(album);
		
		FollowerCategory followerCategory = new FollowerCategory();
		followerCategory.setUserId(userId);
		followerCategory.setName("default");
		followerCategory.setDescription("");
		int followerCategoryId = followerCategoryDAO.save(followerCategory);
		
		Setting setting = new Setting();
		setting.setUserId(userId);
		setting.setDefaultAlbum(albumId);
		setting.setDefaultFollowerCategory(followerCategoryId);
		
		settingDao.save(setting);
		
		return true;
	}
	
	@Override
	public User findUserByUserId(User user) {
		// TODO Auto-generated method stub
		return userDao.findUserByUserId(user);
	}

	@Override
	public User findUserByUsernameOrEmail(User user) {
		// TODO Auto-generated method stub
		User u = null;
		if(user.getEmail()!=null && user.getEmail()!="") {
			u = userDao.findUserByEmail(user);
		}
		else if(user.getUsername()!=null && user.getUsername()!="") {
			u = userDao.findUserByUsername(user);
		}
		return u;
	}

}
