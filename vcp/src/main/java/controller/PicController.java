package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.Config;

@Controller
@RequestMapping("/pic")
public class PicController {
	
	@RequestMapping(path="/{uuidName}", method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable String uuidName) {
		Path file = Paths.get(Config.PICTURE_PATH, request.getRequestURI().split("/")[2]);
        try {
            Files.copy(file, response.getOutputStream());
        } catch (IOException e) {
//        	e.printStackTrace();
        }
	}

}
