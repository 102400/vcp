package service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.FollowerCategoryDAO;
import entity.FollowerCategory;
import service.FollowerCategoryService;

@Service("followerCategoryService")
@Transactional
public class FollowerCategoryServiceImpl implements FollowerCategoryService {

	@Autowired
	private FollowerCategoryDAO followerCategoryDAO;
	
	@Override
	public List<FollowerCategory> findFollowerCategoryListByUserId(FollowerCategory followerCategory) {
		// TODO Auto-generated method stub
		return followerCategoryDAO.findFollowerCategoryListByUserId(followerCategory);
	}

	@Override
	public int addFollowerCategory(FollowerCategory followerCategory) {
		// TODO Auto-generated method stub
		return followerCategoryDAO.save(followerCategory);
	}

}
