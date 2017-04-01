package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.FollowerCategoryDAO;
import dao.FollowerDAO;
import dao.SettingDAO;
import dao.UserDAO;
import entity.Follower;
import entity.FollowerCategory;
import entity.Setting;
import entity.User;
import service.FollowerService;

@Service("followerService")
@Transactional
public class FollowerServiceImpl implements FollowerService {
	
	@Autowired
	private FollowerDAO followerDao;
	
	@Autowired
	private FollowerCategoryDAO followerCategoryDao;
	
	@Autowired
	private SettingDAO settingDao;
	
	@Autowired
	private UserDAO userDao;

	@Override
	public int addFollower(Follower follower) {
		// TODO Auto-generated method stub
		//先检查这个关注者是否在关注列表中
		
		if(!followerDao.isFollowerInTable(follower)) {
		
			User user = new User();
			user.setUserId(follower.getUserId());
			Setting setting = settingDao.findSettingByUserId(user);
			follower.setCategoryId(setting.getDefaultFollowerCategory());
			
			int followerId = followerDao.save(follower);
			user.setFollowers(1);
			userDao.updateFollowers(user);
			
			User userF = new User();
			userF.setUserId(follower.getFollowerId());
			userF.setFollowing(1);
			userDao.updateFollowing(userF);
			
			return followerId;
		}
		return 0;
	}

	@Override
	public boolean deleteFollower(Follower follower) {
		// TODO Auto-generated method stub
		
		
		User user = new User();
		user.setUserId(follower.getUserId());
		user.setFollowers(-1);
		userDao.updateFollowers(user);
		
		
		User userF = new User();
		userF.setUserId(follower.getFollowerId());
		userF.setFollowing(-1);
		userDao.updateFollowing(userF);
		
		return followerDao.deleteFollower(follower);
	}

	@Override
	public boolean isFollowerInTable(Follower follower) {
		// TODO Auto-generated method stub
		return followerDao.isFollowerInTable(follower);
	}

	@Override
	public List<User> findFollowerByUserId(Follower follower) {
		// TODO Auto-generated method stub
		List<Follower> followerList = followerDao.findFollowerByUserId(follower);
		if(followerList==null) return null;
		List<User> userList = new ArrayList<>();
		for(Follower f : followerList) {
			User user = new User();
			user.setUserId(f.getFollowerId());
			user = userDao.findUserByUserId(user);
			userList.add(user);
		}
		
		return userList.size()==0 ? null : userList;
	}

	@Override
	public boolean updateFollowerCategory(Follower follower) {
		// TODO Auto-generated method stub
		//检查category是否由这个用户所属
		FollowerCategory followerCategory = new FollowerCategory();
		followerCategory.setUserId(follower.getUserId());
		List<FollowerCategory> followerCategoryList = followerCategoryDao.findFollowerCategoryListByUserId(followerCategory);
		if(followerCategoryList==null) return false;
		for(FollowerCategory fc :followerCategoryList) {
			if(fc.getCategoryId()==follower.getCategoryId()) {
				return followerDao.updateFollowerCategory(follower);
			}
		}
		
		return false;
	}

}
