package com.pg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.protocol.Protocol;

import com.google.gson.Gson;
/**
 * http请求
 * @author Administrator
 *
 */
public class HttpRequest {
	/**
	 * 以post方式提交请求到https链接
	 * @param url 链接地址
	 * @param postStr post的内容
	 * @return
	 */
	public static String post(String url, 	Part[] parts,String header) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod post = new PostMethod(url);
		String respStr = "";
		try {
			post.setRequestHeader("signature",header);
	        post.setRequestEntity(new MultipartRequestEntity(parts, post
	                .getParams()));  
			post.getParams().setContentCharset("utf-8");
			client.executeMethod(post);
			respStr = post.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.releaseConnection();
		return respStr;
	}
	/**
	 * 以get方式提交请求到https链接
	 * @param url 链接地址
	 * @return
	 */
	public static String get(String url) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod get = new GetMethod(url);
		String respStr = "";
		try {
			get.getParams().setContentCharset("utf-8");
			client.executeMethod(get);
			respStr = get.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		get.releaseConnection();
		return respStr;
	}
	/**
	 * 以post方式提交文件到https链接
	 * @param url 链接地址
	 * @param file post的文件
	 * @return
	 */
	public static String post(String url, File file) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod post = new PostMethod(url);
		String respStr = "";
		try {
			FilePart fp = new FilePart("media", file); 
			Part[] parts = { fp };  
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, post.getParams());  
			post.setRequestEntity(mre);    
			post.getParams().setContentCharset("utf-8");
			client.executeMethod(post);
			respStr = post.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.releaseConnection();
		return respStr;
	}
	/**
	 * get方式获取文件流 并保存
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static BaseResult getFile(String url,String filePath) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod get = new GetMethod(url);
		try {
			get.getParams().setContentCharset("utf-8");
			client.executeMethod(get);
			String respStr = get.getResponseBodyAsString();
			if(respStr.startsWith("{\"errcode\"")){
				BaseResult bean = new Gson().fromJson(respStr, BaseResult.class);			
				return bean;			
			}
			InputStream ins = get.getResponseBodyAsStream();
			File file=new File(filePath);
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
			    os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (HttpException e) {
			return BaseResult.generate("1", e.getMessage());
		} catch (IOException e) {
			return BaseResult.generate("2", e.getMessage());
		}
		get.releaseConnection();
		return BaseResult.generate("0", "success");
	}
}
