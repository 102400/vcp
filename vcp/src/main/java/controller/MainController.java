package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;
import service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String home() {
    	
//    	User user = new User();
//    	user.setEmail(new Random().nextInt(100) + "abc@abc.com");
//    	user.setFollowers(new Random().nextInt(10));
//    	user.setFollowing(new Random().nextInt(10));
//    	user.setNickname(new Random().nextInt(10) + "a");
//    	user.setPassword("password");
//    	user.setUsername(new Random().nextInt(100) + "username");
//    	
//    	int a = userService.saveUser(user);
//    	System.out.println("!:" + a);
    	
        return "index";
    }

//    @RequestMapping("/json")
//    @ResponseBody
//    public List<User> json(){
//      return userService.getAllUsernames();
//    }

}
