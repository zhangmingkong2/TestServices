package com.marlinl.tsser.Action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexAction {
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("img") MultipartFile file,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("");
		System.out.println(path);
		File img =new File("");
		try {
			file.transferTo(img);
		} catch (IllegalStateException | IOException e) {
			if (e instanceof IllegalStateException) {
				return "{'state':'illegalState'}";
			}
			if (e instanceof IOException) {
				return "{'state':'io has exception'}";
			}
		}
		return null;
	}
	

}
