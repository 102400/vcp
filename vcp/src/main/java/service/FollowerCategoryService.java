package service;

import java.util.List;

import entity.FollowerCategory;

public interface FollowerCategoryService {
	
	public List<FollowerCategory> findFollowerCategoryListByUserId(FollowerCategory followerCategory);
	public int addFollowerCategory(FollowerCategory followerCategory);
	
}
