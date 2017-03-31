package dao;

import entity.Follower;

public interface FollowerDAO {
	
	public int save(Follower follower);
	public boolean deleteFollower(Follower follower);
	public boolean isFollowerInTable(Follower follower);

}
