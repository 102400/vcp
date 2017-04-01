package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entity.FollowerCategory;
import service.FollowerCategoryService;

@Controller
@RequestMapping("/manage/follower/category")
public class ManageFollowerCategoryController {
	
	@Autowired
	private FollowerCategoryService followerCategoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, HttpServletRequest request) {
		
		FollowerCategory followerCategory = new FollowerCategory();
		followerCategory.setUserId((int) request.getAttribute("userId"));
		
		List<FollowerCategory> followerCategoryList = 
				followerCategoryService.findFollowerCategoryListByUserId(followerCategory);
		
		model.addAttribute("followerCategoryList", followerCategoryList);
		
		return "manage_follower_category";
	}

}