package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.Config;
import entity.Picture;
import service.PictureService;

@Controller
@RequestMapping("/explore")
public class ExploreController {
	
	@Autowired
	private PictureService pictureService;
	
	//只有照片为public才能在explore显示
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @RequestParam(value="page", required=true, defaultValue="1") int page) {
		if(page<=0) {
			return "redirect:/explore";
		}
		int max = Config.DEFAULT_PICTURE_MAX;
		
		List<Picture> pictureList = 
				pictureService.searchAllByFirstAndMax((page-1) * max, max);
		model.addAttribute("allPictureList", pictureList);
		model.addAttribute("previous", page==1 ? 1 : page - 1);
		model.addAttribute("next", page + 1);
		return "explore";
	}

}