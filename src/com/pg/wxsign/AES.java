package com.pg.wxsign;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.Security;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.SecureRandom;

public class AES {

	public static String encrypt(byte[] contentBytes, byte[] keyBytes) {
		try {
			fixKeyLength();
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] tmp = cipher.doFinal(contentBytes);

			return new String(Base64.encode(tmp, Base64.DEFAULT));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static byte[] decrypt(byte[] contentBytes, byte[] keyBytes) {
		try {
			fixKeyLength();
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] tmp = cipher.doFinal(contentBytes);
//			return new String(tmp, "utf-8");
			return tmp;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void fixKeyLength() {
		String errorString = "Failed manually overriding key-length permissions.";
		int newMaxKeyLength;
		try {
			if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
				Class<?> c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
				Constructor<?> con = c.getDeclaredConstructor();
				con.setAccessible(true);
				Object allPermissionCollection = con.newInstance();
				Field f = c.getDeclaredField("all_allowed");
				f.setAccessible(true);
				f.setBoolean(allPermissionCollection, true);

				c = Class.forName("javax.crypto.CryptoPermissions");
				con = c.getDeclaredConstructor();
				con.setAccessible(true);
				Object allPermissions = con.newInstance();
				f = c.getDeclaredField("perms");
				f.setAccessible(true);
				((Map) f.get(allPermissions)).put("*", allPermissionCollection);

				c = Class.forName("javax.crypto.JceSecurityManager");
				f = c.getDeclaredField("defaultPolicy");
				f.setAccessible(true);
				Field mf = Field.class.getDeclaredField("modifiers");
				mf.setAccessible(true);
				mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
				f.set(null, allPermissions);

				newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
			}
		} catch (Exception e) {
			throw new RuntimeException(errorString, e);
		}
		if (newMaxKeyLength < 256)
			throw new RuntimeException(errorString); // hack failed
	}
    public static String base64Encode(byte[] bytes){  
        return new BASE64Encoder().encode(bytes);  
    }  
    public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }          
	/**
	 * 根据密文获得数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
    public static String getdecryptData(String data,String key)  {  
		String dedata = "";
    	try {
    		dedata = new String(decrypt(decryptBASE64(data),key.getBytes()),"UTF-8");
			System.out.println();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        return dedata;               
    }          

    
    
	public static void main(String[] args) {
		
		System.out.println(encrypt("aaaaaa".getBytes(),"26c8d82cfdce7bc8b9bff1671023a7bf".getBytes()));
		try {
			System.out.println(decrypt(decryptBASE64("0OFUnplOuSA0tgBDUs7Z0A=="),"26c8d82cfdce7bc8b9bff1671023a7bf".getBytes()));
		
			System.out.println(new String(decrypt(decryptBASE64("W9JqyxsOLiPhv2YWlU4285p17puOqXQfgDezI5YtXjsemZHs6YneUwA1DzhAx8oa6eLSCtLLiZ5ohbSROVAczzyfkKRvZxxGE0d9pHBz/xubC/BC0Zl2sEM3/owOosHeQyRZaOtYCGAh9+vAlv1F9H+4FqVdxD8xnGgCBWTA2cv4nLt2iVXgnuEE/y8IlZe+NebvSm9Lt2TnhtK3kji0CHnuy0vd6EAYI4/naEPqJa2OLWrvLfZkbuOV9DKHKcvKOHu/siLyXSzHpe7rRJMUh01GkVv/jq+t9dRY/M7tO+Hl30ETLHtlcinrz1fy5piiIq3ZXFk9gCYM5nr4RnuMkVQMbiMduVdJEo+VFUtmra2/Ax6KxV8MyeKTIkjA//DtFxuRklrZr6gP5aFEukjxMeqF51mhMn/uqlb9ABO/f7H+n07msTjOXkaD62Oocpgdb+zxVlZ2+jMN+4pom/C8zma+2l0I2sEr197QXSSeCM7Wz3IykCR60repfGxA6tpDh+N2ju44+81dn9Xf9cLjMlWfOEEsG5SHyTaKrQ6Tud4q+S0fNXF+xd0Qcb6BmcdV"),"26c8d82cfdce7bc8b9bff1671023a7bf".getBytes()),"UTF-8"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
