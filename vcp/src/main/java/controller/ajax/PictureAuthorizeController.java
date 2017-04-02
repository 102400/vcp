package controller.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Picture;
import entity.ProtectedPicture;
import json.ChangePictureAuthorizeyJson;
import json.SuccessJson;
import service.PictureService;

@Controller
@RequestMapping("/ajax/pictureAuthorize")
public class PictureAuthorizeController {
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(path="/changeAuthorizey", method=RequestMethod.POST)
	public @ResponseBody SuccessJson 
		changeAuthorize(HttpServletRequest request, @RequestBody ChangePictureAuthorizeyJson changePictureAuthorizey) {
		
		SuccessJson successJson = new SuccessJson();
		successJson.setIsSuccess(false);
		
		int userId = changePictureAuthorizey.getUserId();
		int pictureId = changePictureAuthorizey.getPictureId();
		int categoryId = changePictureAuthorizey.getCategoryId();
		if(!(boolean) request.getAttribute("isLogin") || (int) request.getAttribute("userId") != userId || pictureId<=0 || changePictureAuthorizey.getChoose()==null) {
			return successJson;
		}
		//service层 检查picture是否由userId所属
		Picture picture = new Picture();
		picture.setPictureId(pictureId);
		picture.setUserId(userId);
		
		boolean b = false;
		switch(changePictureAuthorizey.getChoose()) {
			case "public":
				picture.setAuthorize(2);
				b = pictureService.updateAuthorize(picture, null);
				successJson.setIsSuccess(b);
				return successJson;
			case "private":
				picture.setAuthorize(1);
				b = pictureService.updateAuthorize(picture, null);
				successJson.setIsSuccess(b);
				return successJson;
			case "protected":
				if(categoryId<=0) return successJson;
				picture.setAuthorize(3);
				//暂时一个picture只能对应一个categoryId
				//一个picture有多个categoryId会搞得很复杂
				ProtectedPicture protectedPicture = new ProtectedPicture();
				protectedPicture.setPictureId(pictureId);
				protectedPicture.setCategoryId(categoryId);
				
				b = pictureService.updateAuthorize(picture, protectedPicture);
				
				successJson.setIsSuccess(b);
				return successJson;
			default:
				return successJson;
		}
	}

}
