package com.pg.wxsign;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	/**
	 * md5 32位 小写
	 */
	public static String md5LowerCase(String str) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		  
		MessageDigest md5 = MessageDigest.getInstance("MD5");  
		md5.update((str).getBytes("UTF-8"));  
		byte b[] = md5.digest();  
		  
		int i;  
		StringBuffer buf = new StringBuffer("");  
		  
		for(int offset=0; offset<b.length; offset++){  
		    i = b[offset];  
		    if(i<0){  
		        i+=256;  
		    }  
		    if(i<16){  
		        buf.append("0");  
		    }  
		    buf.append(Integer.toHexString(i));  
		}  
		  
		return buf.toString();  
	}
}
