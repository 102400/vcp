package service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProtectedPictureDAO;
import entity.ProtectedPicture;
import service.ProtectedPictureService;

@Service("protectedPictureService")
@Transactional
public class ProtectedPictureServiceImpl implements ProtectedPictureService {
	
	@Autowired
	private ProtectedPictureDAO protectedPictureDao;

	@Override
	public ProtectedPicture findProtectedPictureByPictureId(ProtectedPicture protectedPicture) {
		// TODO Auto-generated method stub
		return protectedPictureDao.findProtectedPictureByPictureId(protectedPicture);
	}

}
