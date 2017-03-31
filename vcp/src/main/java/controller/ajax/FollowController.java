package controller.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Follower;
import json.FollowJson;
import json.SuccessJson;
import service.FollowerService;

@Controller
@RequestMapping("/ajax/follow")
public class FollowController {
	
	@Autowired
	private FollowerService followerService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody SuccessJson post(HttpServletRequest request, @RequestBody FollowJson followJson) {
		SuccessJson successJson = new SuccessJson();
		if(!(boolean) request.getAttribute("isLogin")) {
			successJson.setIsSuccess(false);
			return successJson;
		}
		int userId = (int) request.getAttribute("userId");

		boolean isFollow = followJson.getIsFollow();
		int peopleId = followJson.getPeopleId();
		
		Follower follower = new Follower();
		follower.setUserId(peopleId);
		follower.setFollowerId(userId);
		
		if(!isFollow) {  //增加关注者
			if(followerService.addFollower(follower)>0) {
				successJson.setIsSuccess(true);
				return successJson;
			}
		}
		else {  //删除关注者
			if(followerService.deleteFollower(follower)) {
				successJson.setIsSuccess(true);
				return successJson;
			}
		}
		
		successJson.setIsSuccess(false);
		return successJson;
	}

}