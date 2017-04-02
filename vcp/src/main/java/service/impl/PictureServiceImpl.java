package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PictureDAO;
import dao.ProtectedPictureDAO;
import dao.SettingDAO;
import entity.Follower;
import entity.Picture;
import entity.ProtectedPicture;
import entity.Setting;
import entity.User;
import service.PictureService;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	private PictureDAO pictureDao;
	
	@Autowired
	private ProtectedPictureDAO protectedPictureDAO;
	
	@Autowired
	private SettingDAO settingDao;

	@Override
	public boolean addPicture(Picture picture) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(picture.getUserId());
		Setting setting = settingDao.findSettingByUserId(user);
		if(setting==null) return false;
		picture.setAlbumId(setting.getDefaultAlbum());
		
		pictureDao.save(picture);
		
		return true;
	}

	@Override
	public List<Picture> searchAllByFirstAndMax(int first, int max) {
		// TODO Auto-generated method stub
		return pictureDao.searchAllByFirstAndMax(first, max);
	}

	@Override
	public Picture findPictureByUuid(Picture picture) {
		// TODO Auto-generated method stub
		return pictureDao.findPictureByUuid(picture);
	}

	@Override
	public boolean updateAuthorize(Picture picture, ProtectedPicture protectedPicture) {
		// TODO Auto-generated method stub
		int userId = picture.getUserId();
		int authorize = picture.getAuthorize();
		picture = pictureDao.findPictureByPictureId(picture);
		if(picture==null || picture.getUserId()!=userId) {
			return false;
		}
		picture.setAuthorize(authorize);
		if(authorize==3) {  //protected
			//检查protectedPicture是否存在，存在则修改，不存在则增加
			ProtectedPicture pp = new ProtectedPicture();
			pp = protectedPictureDAO.findProtectedPictureByPictureId(protectedPicture);
			if(pp==null) {
				//不存在，增加
				protectedPictureDAO.save(protectedPicture);
			}
			else {
				//存在,，修改
				pp.setCategoryId(protectedPicture.getCategoryId());
				pp.setPictureId(protectedPicture.getPictureId());
				protectedPictureDAO.updateProtectedPicture(pp);
			}
		}
		
		return pictureDao.updatePicture(picture);
	}

	//peopleId==followerId，说明当前的浏览用户为people，则所有图片都可以看到
	//未登录的用户，只显示public
	//先检查是否在followerList里，不是则此用户未关注，显示的还都是public图片
	//在list里，则先得到follower所在的category(List)，如果所在category是people的默认category，显示的还都是public图片
	//不在默认的category则遍历检查picture的authorize，public则显示，如果为protected，查看此图片的所在category
	//如果图片对应的category在follower所在的category(List)里，则显示
	//目前follower只对应一个category，但有可能之后增加功能会对应多个，所以要用list
	@Override
	public List<Picture> searchPeopleByFirstAndMax(Follower follower, int first, int max) {
		// TODO Auto-generated method stub
		List<Picture> pictureList = new ArrayList<>();
		int peopleId = follower.getUserId();
		int followerId = follower.getFollowerId();
		if(peopleId==followerId) {  //当前的浏览用户为people，则所有图片都可以看到
			pictureList = pictureDao.searchPeopleAllByFirstAndMax(follower, first, max);
		}
		else if(followerId<=0) {  //未登录用户只显示public图片
			pictureList = pictureDao.searchPeoplePublicByFirstAndMax(follower, first, max);
		}
		else {
			
			
			
		}
		
		return pictureList;
	}

}
