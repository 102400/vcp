package service;

import entity.Follower;

public interface FollowerService {
	
	public int addFollower(Follower follower);
	public boolean deleteFollower(Follower follower);
	boolean isFollowerInTable(Follower follower);

}
