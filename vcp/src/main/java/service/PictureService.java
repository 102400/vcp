package service;

import java.util.List;

import entity.Follower;
import entity.Picture;
import entity.ProtectedPicture;

public interface PictureService {
	
	public boolean addPicture(Picture picture);
	public List<Picture> searchAllByFirstAndMax(int first, int max);
	public List<Picture> searchPeopleByFirstAndMax(Follower follower, int first, int max);
	public Picture findPictureByUuid(Picture picture);
	public boolean updateAuthorize(Picture picture, ProtectedPicture protectedPicture);

}