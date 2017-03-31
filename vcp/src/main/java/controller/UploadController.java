package controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import config.Config;
import entity.Picture;
import service.PictureService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		
		return "upload";
	}
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(MultipartHttpServletRequest request) {
		if(!(boolean) request.getAttribute("isLogin")) {
			return "redirect:/login";
		}
		int userId = (int) request.getAttribute("userId");
		
		int fail = 0;
		
		List<MultipartFile> files = request.getFiles("file");
//        Map<String, String> imgs = new HashMap<String, String>();
		C:
        for (int i = 0; i < files.size(); i++) {
        	MultipartFile file = files.get(i);
        	
        	if (!file.isEmpty()) {
        		
        		String type = ".file";
        		String contentType = file.getContentType();
        		if(contentType==null) {
        			fail++;
	        		continue;
        		}
        		switch(contentType) {
        			case "image/jpeg":
        				type = ".jpg";
        				break;
        			case "image/png":
        				type = ".png";
        				break;
        			default:
        				fail++;
        				continue C;
        		}
        		
        		String uuid = UUID.randomUUID().toString().replaceAll("-","");
	        	Picture picture = new Picture();
	        	picture.setUserId(userId);
	        	picture.setUuid(uuid);
	        	picture.setSize(file.getSize());
	        	picture.setContentType(contentType);
	        	picture.setSuffix(type);
//	        	picture.setAuthorize(1);  //private
	        	picture.setAuthorize(2);  //public
	        	picture.setOriginalName(files.get(i).getOriginalFilename());
	        	picture.setName(files.get(i).getOriginalFilename());
	        	picture.setDescription("");
	        	
	        	try {
		        	if(!pictureService.addPicture(picture)) {
		        		fail++;
		        		continue;
		        	}
	        	}
	        	catch(Exception e) {
	        		e.printStackTrace();
	        		fail++;
	        		continue;
	        	}
	        	
	        	//System.out.println(files.get(i).getOriginalFilename());
        	
                try {  
                    // 文件保存路径  
                	
                	String filePath = Config.PICTURE_PATH + uuid + type;
                    // 转存文件
                    file.transferTo(new File(filePath));  
                } catch (Exception e) {  
                    e.printStackTrace();
                    fail++;
                }
            }
        	
        	
       }  
        
        
//       request.setAttribute("imgs", imgs);
       
		
		return "redirect:/people/" + request.getAttribute("userId");
	}

}