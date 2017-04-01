package dao;

import java.util.List;

import entity.Follower;

public interface FollowerDAO {
	
	public int save(Follower follower);
	public boolean deleteFollower(Follower follower);
	public boolean isFollowerInTable(Follower follower);
	public List<Follower> findFollowerByUserId(Follower follower);
	public boolean updateFollowerCategory(Follower follower);

}
