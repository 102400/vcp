package dao;

import entity.ProtectedPicture;

public interface ProtectedPictureDAO {
	
	public int save(ProtectedPicture protectedPicture);
	public ProtectedPicture findProtectedPictureByPictureId(ProtectedPicture protectedPicture);
	public boolean updateProtectedPicture(ProtectedPicture protectedPicture);

}