package service;

import java.util.List;

import entity.Picture;
import entity.User;

public interface PictureService {
	
	public boolean addPicture(Picture picture);
	public List<Picture> searchAllByFirstAndMax(int first, int max);
	//public List<Picture> searchUserByFirstAndMax(User user, int first, int max);
	public Picture findPictureByUuid(Picture picture);

}
