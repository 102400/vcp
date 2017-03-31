package dao;

import java.util.List;

import entity.Picture;

public interface PictureDAO {

	public int save(Picture picture);
	public List<Picture> searchAllByFirstAndMax(int first, int max);
	public Picture findPictureByUuid(Picture picture);
	
}