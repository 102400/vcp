package service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PictureDAO;
import dao.SettingDAO;
import entity.Picture;
import entity.Setting;
import entity.User;
import service.PictureService;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	private PictureDAO pictureDao;
	
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

}
