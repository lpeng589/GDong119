package com.pg.mapper;

import com.pg.bean.FileBean;
import com.pg.bean.ImgBean;


/**
 * Adminmapper
 * 
 * @author Administrator
 * 
 */
public interface UploadMapper {
	/**
	 * 添加图片
	 * @author Administrator 
	 */
	public static String addImg="addImg";
	/**
	 * 添加图片
	 * @param bean
	 * @return
	 */
	public int addImg(ImgBean bean);
	/**
	 * 获取图片
	 * @author Administrator 
	 */
	public static String getImg="getImg";
	/**
	 * 获取图片
	 * @param code
	 * @return
	 */
	public ImgBean getImg(String code);
	/**
	 * 添加文件
	 * @author Administrator 
	 */
	public static String addFile="addFile";
	/**
	 * 添加文件
	 * @param bean
	 * @return
	 */
	public int addFile(FileBean bean);
	/**
	 * 获取文件
	 * @author Administrator 
	 */
	public static String getFile="getFile";
	/**
	 * 获取文件
	 * @param code
	 * @return
	 */
	public FileBean getFile(String code);
}