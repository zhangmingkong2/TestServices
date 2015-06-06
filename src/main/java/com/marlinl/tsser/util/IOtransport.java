package com.marlinl.tsser.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

public class IOtransport {
	
	private File file;
	
	public IOtransport(File f){
		this.file = f;
	}
	
	public String getUUID(){
		try {
			InputStream input = new FileInputStream(file);
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] b = new byte[1024];
			while (input.read(b)>0) {
				md.update(b);	
			}
			return DigestUtils.shaHex(md.digest());
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
	


}
