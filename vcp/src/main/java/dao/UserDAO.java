package dao;

import entity.User;

public interface UserDAO {
	
	public int save(User user);
	public boolean updateFollowing(User user);
	public boolean updateFollowers(User user);
	
	public User findUserByUserId(User user);
	public User findUserByUsername(User user);
	public User findUserByEmail(User user);
	
}
