package com.pg.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.pg.bean.FileBean;
import com.pg.bean.ImgBean;
import com.pg.bean.OSSBean;
import com.pg.mapper.UploadMapper;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.HttpAnno;
import com.pg.util.OSSFileManagerJson;
import com.pg.util.OSSObjectSample;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
import com.pg.util.UploadFile;

@Controller
public class UploadAction {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UploadAction.class);
	/**
	 * 图片上传
	 * @param request
	 * @param response
	 * @author XCR
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping("upload_json.htm")
	public void upload_json(HttpServletRequest request, HttpServletResponse response) {
		//为空值时候是上传，有值时候是覆盖 在 url : 'upload_json.htm?dir=file&kindeditor_file='+file, file为文件名称 ： attached/image/201611/6DEF98ECB6EAD701E8292349D40508F3.jpeg
		String oldfile = request.getParameter("kindeditor_file");
		String newfilepath = request.getParameter("kindeditor_filepath");//新文件夹下面
		//文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + "attached/";
		//文件保存目录URL
	  	String saveUrl =  Config.getValue(Config.CONFIG_IMAGEROOTPATH)+"/attached/";
		
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,css,js");
		//最大文件大小
		long maxSize = 1000000;
//		session.setAttribute("ajax_upload", 1);
		response.setContentType("text/html; charset=UTF-8");
		if(!ServletFileUpload.isMultipartContent(request)){
			ServletUtil.sendJsonBean(response, getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			ServletUtil.sendJsonBean(response, getError("上传目录"+Config.getValue(Config.CONFIG_UPLOADPATH) +"不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			ServletUtil.sendJsonBean(response, getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");//image
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			ServletUtil.sendJsonBean(response, getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += dirName + "/";//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached/image/
		saveUrl += dirName + "/";//http://bhx.oss-cn-shenzhen.aliyuncs.com/attached/image/
		if(newfilepath!=null && !"".equals(newfilepath)){
			savePath = savePath + newfilepath + "/"  ;
			saveUrl = saveUrl + newfilepath + "/"  ;
		}
		File saveDirFile = new File(savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached\image
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached/image/201603/
		saveUrl += ymd + "/";//http://huijiajie.oss-cn-shenzhen.aliyuncs.comimage/201603/
		File dirFile = new File(savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached\image\20151123
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		//session.setAttribute("ajax_upload", null);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					ServletUtil.sendJsonBean(response, getError("上传文件大小超过限制。"));
					return;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					ServletUtil.sendJsonBean(response, getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
				String newFileName1 = null;//小图
				String newFileName2 = null;//原图
				String newFileName0 = null;
				if(oldfile==null ||"".equals(oldfile)){//上传
					synchronized(OSSObjectSample.lock){
					    newFileName0 = String.valueOf(System.currentTimeMillis());
						newFileName0 = Tools.MD5(newFileName0);
//						logger.error("newFileName0="+newFileName0);
	//					newFileName1 = "s_"+newFileName0 + "." + fileExt;
						newFileName2 = ""+newFileName0 + "." + fileExt;
					}
				}else{//修改覆盖
					newFileName0="";
					newFileName2=oldfile.substring(oldfile.lastIndexOf("/")+1, oldfile.length());
				}
				String rootPath = "attached/"+dirName+ "/"+ df.format(new Date()) + "/";//云存储目录前缀
				if(newfilepath!=null && !"".equals(newfilepath)){//特殊文件夹
					 rootPath = "attached/"+dirName+ "/"+ newfilepath + "/"+ df.format(new Date()) + "/";//云存储目录前缀
				}
				File uploadedFile2 = new File(savePath, newFileName2);
				try{
					item.write(uploadedFile2);//uploadedFile2写入本地
					//等比列缩放原图，并且存储
					/*if("image".equals(dirName)){
						File uploadedFile1 = new File(savePath, newFileName1);
						ImageUtils.ratioZoom2(uploadedFile2,uploadedFile1,Double.valueOf(Config.getValue(Config.CONFIG_IMAGE_1_W)));//缩图，并且uploadedFile1写入本地
						
						OSSObjectSample.save(rootPath + newFileName1, uploadedFile1);//uploadedFile1上传OSS
					}*/
					OSSObjectSample.save(rootPath + newFileName2, uploadedFile2);//uploadedFile2上传OSS
					if("n".equals(Config.getValue(Config.CONFIG_OSSSTATUS))){//是否保留本地文件
						deleteFile(savePath+ newFileName2);//删除本地文件
					}
					//写入数据库
					if(oldfile==null ||"".equals(oldfile)){
						
						if("image".equals(dirName)){
							UploadFile.uploadImg(fileName,rootPath+newFileName2,rootPath+newFileName1,newFileName0,request);
						}else{
							UploadFile.uploadFile(fileName,rootPath+newFileName2,newFileName0,fileExt,request);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					logger.error("上传文件异常："+e.getMessage());
					ServletUtil.sendJsonBean(response, getError("上传文件失败。"));
					return;
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName2);
				obj.put("title", fileName);
				obj.put("realfilename", fileName);
				try {
					response.getWriter().write(obj.toJSONString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	/**
	 * 读取OSS
	 * @author XCR
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping("file_manager_json.htm")
	public void file_manager_json(HttpServletRequest request, HttpServletResponse response) {
		OSSFileManagerJson ossManager = new OSSFileManagerJson();
		String json = ossManager.write(request,response);
		response.setContentType("application/json; charset=UTF-8");
		//循环 对 文件名进行翻译成真实文件名称
		Gson gson = new Gson();
		OSSBean  map  =gson.fromJson(json, OSSBean.class);
		ArrayList list = map.getFile_list();
		String dirName = request.getParameter("dir");//image
		String filename =null;
		UploadMapper mapper =ApplicationContextUtil.getMapper(UploadMapper.class);
		for (int i = 0; i < list.size(); i++) {
			Map map2 = (Map) list.get(i);
			//循环查询 真实名称
			filename = (String) map2.get("filename");
			if(!(Boolean)map2.get("is_dir")){//非文件夹：翻译
				filename = filename.substring(0,filename.indexOf("."));
				if("image".equals(dirName)){//这里有资源浪费问题：tblimg和tblfile的数据会越来越多！
					 ImgBean imgBean =   mapper.getImg(filename);
					 if(imgBean!=null){
						 map2.put("realfilename", imgBean.getFilename());
					 }
				}else{
					FileBean fileBean =   mapper.getFile(filename);
					 if(fileBean!=null){
						 map2.put("realfilename", fileBean.getFilename());
					 }
				}
			}else{
				map2.put("realfilename", filename);
			}
			
		}
		try {
			response.getWriter().write(gson.toJson(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
    	boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
    public boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    
	/**
	 * 根据url返回图片流
	 * @param code
	 * @param type 
	 * @param response
	 */
	@RequestMapping(value="img.htm")
	public void showImg(HttpServletResponse response,String imgurl) {
		
		if(imgurl==null) return;
			try {//"http://huijiajie.oss-cn-shenzhen.aliyuncs.com/attached/image/201604/EE99BCAC4F3D37E5BEFBBFD664534361.png"
				URL url = new URL(imgurl);
				OutputStream ous = response.getOutputStream();
		        java.io.BufferedInputStream ins = new BufferedInputStream(url.openStream());
				int tmp = -1;
				while((tmp=ins.read())!=-1)
				{
					ous.write(tmp);
				}
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
