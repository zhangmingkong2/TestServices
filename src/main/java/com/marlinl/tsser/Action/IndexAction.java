package com.marlinl.tsser.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.marlinl.tsser.util.ByteArray;

@Controller
public class IndexAction {
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("img") MultipartFile file,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("");
		path += "/temp_img/"+System.currentTimeMillis()+".jpg";
		System.out.println(path);
		File img =new File(path);
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
		return "{'state':'success'}";
	}
	
	@RequestMapping(value="/test")
	public @ResponseBody String test(HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("")+"/temp_img";
		String f_name = path + "/test.jpg";
		File f = new File(f_name);
		try {
			String re = "";
			InputStream input = new FileInputStream(f);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = new byte[1024];
			ByteArray ba = new ByteArray(0);
			int a = 1;
			while (a>0) {
				a = input.read(b);
				ba.add(b);
				re += DigestUtils.md5Hex(ba.getByte());
				
			}
			byte[] bb = md.digest();
			re = DigestUtils.shaHex(bb);
			System.out.println();
			return re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return String.valueOf(f.hashCode());
	}
	

}
