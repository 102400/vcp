package controller.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Follower;
import entity.FollowerCategory;
import json.AddFollowerCategoryJson;
import json.AddUserToFollowerCategoryJson;
import json.SuccessJson;
import service.FollowerCategoryService;
import service.FollowerService;

@Controller
@RequestMapping("/ajax/followerCategory")
public class FollowerCategoryController {
	
	@Autowired
	private FollowerCategoryService followerCategoryService;
	
	@Autowired
	private FollowerService followerService;
	
	@RequestMapping(path="/addCategory", method=RequestMethod.POST)
	public @ResponseBody SuccessJson addCategory(HttpServletRequest request, @RequestBody AddFollowerCategoryJson addCategoryJson) {
		SuccessJson successJson = new SuccessJson();
		successJson.setIsSuccess(false);
		int userId = addCategoryJson.getUserId();
		if(!(boolean) request.getAttribute("isLogin") || (int) request.getAttribute("userId") != userId || addCategoryJson.getCategoryName()==null || addCategoryJson.getCategoryName().equals("")) {
			return successJson;
		}
		FollowerCategory followerCategory = new FollowerCategory();
		followerCategory.setUserId(userId);
		followerCategory.setName(addCategoryJson.getCategoryName());
		if(addCategoryJson.getCategoryDescription()==null) {
			followerCategory.setDescription("");
		}
		else {
			followerCategory.setDescription(addCategoryJson.getCategoryDescription());
		}
		int followerCategoryId = followerCategoryService.addFollowerCategory(followerCategory);
		if(followerCategoryId<=0) {
			return successJson;
		}
		
		successJson.setIsSuccess(true);
		successJson.setInfo(followerCategoryId + "");
		
		return successJson;
	}
	
	@RequestMapping(path="/addUserToCategory", method=RequestMethod.POST)
	public @ResponseBody SuccessJson addUserToCategory(HttpServletRequest request, @RequestBody AddUserToFollowerCategoryJson addUserToCategory) {
		SuccessJson successJson = new SuccessJson();
		successJson.setIsSuccess(false);
		int userId = addUserToCategory.getUserId();
		if(!(boolean) request.getAttribute("isLogin") || (int) request.getAttribute("userId") != userId || addUserToCategory.getCategoryId()<=0) {
			return successJson;
		}
		
		Follower follower = new Follower();
		follower.setUserId(userId);
		follower.setFollowerId(addUserToCategory.getPeopleId());
		follower.setCategoryId(addUserToCategory.getCategoryId());
		if(followerService.updateFollowerCategory(follower)) {
			successJson.setIsSuccess(true);
		}
		
		return successJson;
	}

}
