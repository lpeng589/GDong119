package com.pg.util;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/*加密
 * encrypt ：
 * decrypt ：
 * 
 * **/
public class Crypto {
	private static Logger logWriter = LogManager.getLogger(Crypto.class);
	private static Key key;
	private final static String SEPARATOR = "|#";
	 // 根据算法对应的密钥
	private final static String KEYPATH = "/paskey";
	// 算法，如DES
	private final static String PADDING = "AES/ECB/PKCS5Padding";
	public Crypto() {
		super();
	}
	static {
//		FileInputStream fis = null;
		ObjectInputStream ois = null;
		InputStream fis =null;
		try {
	    	fis = Crypto.class.getResourceAsStream(KEYPATH); 
//			fis = new FileInputStream(KEYPATH);
			ois = new ObjectInputStream(fis);
			key = (Key) ois.readObject();
			ois.close();
		} catch (Exception e) {
			logWriter.error(e);
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (Exception e1) {
				}
			if (fis != null)
				try {
					fis.close();
				} catch (Exception e1) {
				}
		}
	}
	public static String encrypt(String plainText) {//加密
		try {
			// 生成Cipher对象
			Cipher cipher = Cipher.getInstance(PADDING);
			 // 用密钥加密明文(plainText),生成密文(cipherText)
			cipher.init(Cipher.ENCRYPT_MODE, key);// 操作模式为加密ENCRYPT_MODE,key为密钥
			// 加随机数和时间进要加密的plainText，更加保险，也可以plainText 直接加密 cipherText = cipher.doFinal(plainText.getBytes()); 
			Random rand = new Random();
			byte[] b = new byte[8];
			rand.nextBytes(b);
			String randStr = new String(b);
			String time = "" + System.currentTimeMillis();
			// 得到加密后的字节数组 cipherText
			byte[] cipherText = cipher.doFinal((plainText + SEPARATOR + time + SEPARATOR + randStr).getBytes());
			//cipherText 密文 再次 经过 bytes2HexStr算法 加密
			String cipherTextHex = Tools.bytes2HexStr(cipherText);
			//返回密文
			return cipherTextHex;
		} catch (Exception e) {
			logWriter.error("encrypt()", e);
		}
		return null;
	}
	public static String decrypt(String cipherTextHex) {//解密
		try {
			// 生成Cipher对象
			Cipher cipher = Cipher.getInstance(PADDING);
			cipher.init(Cipher.DECRYPT_MODE, key);// 操作模式为解密DECRYPT_MODE,key为密钥
			//cipherTextHex 使用算法 hexStr2Bytes 得到 cipherText 密文
			byte[] cipherText = Tools.hexStr2Bytes(cipherTextHex);
			//cipher.doFinal 解密 得到密文
			String plainText0 = new String(cipher.doFinal(cipherText));
			//111111|#1415173566815|#��ucz[  密码|# 时间 |# 随机数 
			//截取密码
			int i = plainText0.indexOf(SEPARATOR);
			String formerText = plainText0.substring(0, i);
			//返回密码
			return formerText;
		} catch (Exception e) {
			logWriter.error(e); //把错误写进日志
		}
		return null;
	}
}