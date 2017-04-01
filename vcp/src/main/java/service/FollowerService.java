package service;

import java.util.List;

import entity.Follower;
import entity.User;

public interface FollowerService {
	
	public int addFollower(Follower follower);
	public boolean deleteFollower(Follower follower);
	public boolean isFollowerInTable(Follower follower);
	public List<User> findFollowerByUserId(Follower follower);
	public boolean updateFollowerCategory(Follower follower);

}
