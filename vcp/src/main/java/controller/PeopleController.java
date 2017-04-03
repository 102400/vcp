package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.Config;
import entity.Follower;
import entity.Picture;
import entity.User;
import service.FollowerService;
import service.PictureService;
import service.UserService;

@Controller
@RequestMapping("/people/{people_id}")
public class PeopleController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String people(Model model,HttpServletRequest request, @PathVariable int people_id, @RequestParam(value="page", required=true, defaultValue="1") int page) {
		
		User people = new User();
		people.setUserId(people_id);
		people = userService.findUserByUserId(people);
		if(people==null) {
			return "redirect:/";
		}
		model.addAttribute("people", people);
		
		Follower follower = new Follower();
		follower.setUserId(people_id);
		if((boolean) request.getAttribute("isLogin")) {
			follower.setFollowerId((int) request.getAttribute("userId"));
			
			if(followerService.isFollowerInTable(follower)) {
				model.addAttribute("isFollow", true);
				System.out.println("aaa");
			}
			else {
				model.addAttribute("isFollow", false);
				System.out.println("bbb");
			}
			System.out.println(follower.getUserId());
			System.out.println(follower.getFollowerId());
		}
		else {
			follower.setFollowerId(0);
		}
		
		int max = Config.DEFAULT_PICTURE_MAX;
		
		List<Picture> pictureList = 
				pictureService.searchPeopleByFirstAndMax(follower, (page-1) * max, max);
		
		model.addAttribute("allPictureList", pictureList);
		model.addAttribute("previous", page==1 ? 1 : page - 1);
		model.addAttribute("next", page + 1);
		
		return "people";
	}
	
	@RequestMapping(path="/followers", method = RequestMethod.GET)
	public String follower(Model model,HttpServletRequest request, @PathVariable int people_id) {
		
		User people = new User();
		people.setUserId(people_id);
		people = userService.findUserByUserId(people);
		if(people==null) {
			return "redirect:/";
		}
		model.addAttribute("people", people);
		
		Follower follower = new Follower();
		follower.setUserId(people_id);
		List<User> followerList = followerService.findFollowerByUserId(follower);
		
		model.addAttribute("followerList", followerList);
		
		return "followers";
	}

}