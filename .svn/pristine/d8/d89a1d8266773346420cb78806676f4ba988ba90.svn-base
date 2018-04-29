package com.pg.bean;
import java.sql.Timestamp;
import java.util.HashMap;
public class WXUploadBean extends BaseBean{
	/**
	 * 文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
	 */
	private String name;
	/**
	 * 要上传文件资源的路径
	 */
	private String filePath;
	/**
	 * HTTP 请求中其他额外的 form data
	 */
	private HashMap<String, Object> formData;
	/**
	 * 图片二进制
	 */
	private byte[] wximage;
	/**
	 * 文件二进制
	 */
	private byte[] wxfile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public HashMap<String, Object> getFormData() {
		return formData;
	}
	public void setFormData(HashMap<String, Object> formData) {
		this.formData = formData;
	}
	public byte[] getWximage() {
		return wximage;
	}
	public void setWximage(byte[] wximage) {
		this.wximage = wximage;
	}
	public byte[] getWxfile() {
		return wxfile;
	}
	public void setWxfile(byte[] wxfile) {
		this.wxfile = wxfile;
	}

}