package dao;

import java.util.List;

import entity.Follower;
import entity.Picture;

public interface PictureDAO {

	public int save(Picture picture);
	public List<Picture> searchAllByFirstAndMax(int first, int max);
	public List<Picture> searchPeopleAllByFirstAndMax(Follower follower, int first, int max);
	public List<Picture> searchPeoplePublicByFirstAndMax(Follower follower, int first, int max);
	public List<Picture> searchPeoplePublicAndProtectedByFirstAndMax(Follower follower, int first, int max);
	public Picture findPictureByUuid(Picture picture);
	public Picture findPictureByPictureId(Picture picture);
	public boolean updatePicture(Picture picture);
	
}