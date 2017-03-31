package dao;

import entity.User;

public interface UserDAO {
	
	public int save(User user);
	boolean updateFollowing(User user);
	boolean updateFollowers(User user);
	
	public User findUserByUserId(User user);
	public User findUserByUsername(User user);
	public User findUserByEmail(User user);
	
}
