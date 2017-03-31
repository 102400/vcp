package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entity.Follower;
import entity.User;
import service.FollowerService;
import service.PictureService;
import service.UserService;

@Controller
@RequestMapping("/people")
public class PeopleController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(path = "/{user_id}", method = RequestMethod.GET)
	public String get(Model model,HttpServletRequest request, @PathVariable int user_id) {
		User people = new User();
		people.setUserId(user_id);
		people = userService.findUserByUserId(people);
		if(people==null) {
			return "redirect:/";
		}
		model.addAttribute("people", people);
		
		if((boolean) request.getAttribute("isLogin")) {
			Follower follower = new Follower();
			follower.setUserId(user_id);
			follower.setFollowerId((int) request.getAttribute("userId"));
			if(followerService.isFollowerInTable(follower)) {
				model.addAttribute("isFollow", true);
			}
			else {
				model.addAttribute("isFollow", false);
			}
		}
		
		return "people";
	}

}