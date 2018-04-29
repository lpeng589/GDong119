package com.pg.wxsign;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

/**
 * 工具
 * @author DerrickZheng
 *
 */
public class Util {
	
	
	
	/*
	 * 生成随机字符串
	 * length表示生成字符串的长度
	 */
	public static String getRandomString(int length) { 
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	}   
	
	/*
	 * signature	鉴权签名    请求拉去证件库接口使用
	 * 		把appId，appSecret，nonce，timestamp值进行ASCII码从小到大排序
	 */
	public static String getSignatureSort(String[] args){
		String signature = "";
		
		Arrays.sort(args);
		
		for (String string : args) {
			signature+=string;
		}
		return getSha1(signature);
	}
	
	/*
	 * signature	鉴权签名   实名核身接口使用
	 */
	public static String getSignature(String secretKey, String plainText) 
			throws Exception{
		System.out.println("plainText:"+plainText);
	    byte[] bin = HashHmac_Sha1.getSignature(plainText, secretKey);
		byte[] all = new byte[bin.length + plainText.getBytes().length];
		System.arraycopy(bin, 0, all, 0, bin.length);
		System.arraycopy(plainText.getBytes(), 0, all, bin.length
				, plainText.getBytes().length);
		return new String(Base64.encode(all, Base64.NO_WRAP));
	}
	
	/*
	 * sig串拼接 md5加密后
	 */
	public static String getSig(String[] args) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String s = "";
		for (String string : args) {
			s+=string+"-";
		}
		s+="authkey";
		
		return MD5.md5LowerCase(s);
	}
	
	/*
	 * 生成Unix的时间戳 (不含毫秒)
	 */
	public static Long getCurrentTime() {
        //毫秒时间转成分钟
        double doubleTime = (Math.floor(System.currentTimeMillis() / 60000L));
        //往下取整 1.9=> 1.0
        long floorValue = new Double(doubleTime).longValue();
        return floorValue * 60;
    }
	
	/*
	 * sha1 加密
	 */
	public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
        	System.out.println("sha1异常:  "+e.getMessage());
            return null;
        }
    }
	
	  /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
              //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
              //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        
        //如果有错就返加nulll
        return null;         
    }
    
    
	public static void main(String[] args) {
	    
	    InputStream in = null;
	    
	    String s_xmlpath = "E:\\workplace\\shop\\珠宝平台（信德缘）\\src\\test\\com\\pg\\jxs.sql";  
//		String ="test/com/pg/jxs.sql";
//		InputStream in=ClassLoader.getSystemResourceAsStream(s_xmlpath);
    
	try {
		in = new FileInputStream(s_xmlpath);
		System.out.println();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	     s_xmlpath = "E:\\workplace\\shop\\珠宝平台（信德缘）\\src\\test\\com\\pg\\tb_prov_city_area_street.sql";  
			try {
				in = new FileInputStream(s_xmlpath);
				System.out.println();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
}
