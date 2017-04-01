package dao;

import java.util.List;

import entity.FollowerCategory;

public interface FollowerCategoryDAO {
	
	public int save(FollowerCategory followerCategory);
	public List<FollowerCategory> findFollowerCategoryListByUserId(FollowerCategory followerCategory);

}
