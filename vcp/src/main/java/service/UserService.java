package service;

import entity.User;

public interface UserService {
	
	public boolean addUser(User user);
	public User findUserByUserId(User user);
	public User findUserByUsernameOrEmail(User user);

}