package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entity.Picture;
import entity.ProtectedPicture;
import service.PictureService;
import service.ProtectedPictureService;

@Controller
@RequestMapping("/picture")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private ProtectedPictureService protectedPictureService;
	
	//权限控制, 此页仅进行了简单权限控制, 
	//逻辑上 如果用户知道了uuid则访问/pic/{uuid}就可以访问图片
	//不是public的图片, 不能让无关用户知道uuid
	@RequestMapping(path="/{uuid}", method = RequestMethod.GET)
	public String get(Model model, HttpServletRequest request, @PathVariable String uuid) {
		
		if(uuid.length()!=32) {
			return "redirect:/";
		}
		Picture picture = new Picture();
		picture.setUuid(uuid);
		try {
			picture = pictureService.findPictureByUuid(picture);
			if(picture==null) {
				return "redirect:/";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		//没有登录并且图片不是public的则重定向
		if(!(boolean) request.getAttribute("isLogin")&&picture.getAuthorize()!=2) {
			return "redirect:/";
		}
		
		//用户登录了, 并且用户拥有这张图片则可以进行管理
		int userId = (int) request.getAttribute("userId");
		if((boolean) request.getAttribute("isLogin") && userId==picture.getUserId()) {
			model.addAttribute("canManage", true);
			ProtectedPicture protectedPicture = new ProtectedPicture();
			protectedPicture.setPictureId(picture.getPictureId());
			protectedPicture = protectedPictureService.findProtectedPictureByPictureId(protectedPicture);
			if(protectedPicture!=null) {
				model.addAttribute("categoryId", protectedPicture.getCategoryId());
			}
		}
		
		model.addAttribute("picture", picture);
		
		return "picture";
	}

}
