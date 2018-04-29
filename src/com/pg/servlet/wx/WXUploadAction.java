package com.pg.servlet.wx;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.pg.bean.FileBean;
import com.pg.bean.ImgBean;
import com.pg.bean.MsgBean;
import com.pg.util.Config;
import com.pg.util.DeCoder;
import com.pg.util.OSSObjectSample;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
import com.pg.util.UploadFile;
@Controller
public class WXUploadAction
{
	  /**
	   * 上传图片，返回URL
	 * @throws IOException 
	   */
	  @RequestMapping("wxupload.htm")
	  public void wxupload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  ModelAndView mav = new ModelAndView();  
		  // 转型为MultipartHttpRequest  
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		  // 获得上传的文件（根据前台的name名称得到上传的文件）  
		  MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();  
		  String name =  request.getParameter("name2");
		  MultipartFile uploadstr = multipartRequest.getFile(name);  
		  ImgBean  imgBean = null;
		  FileBean flieBean = null;
		  String filePath =  request.getParameter("filePath2");//要上传文件资源的路径
		  String dirName = "wximage";
		  if ("wximage".equals(name)) {//图片
			  dirName = "wximage";
			  if(uploadstr==null || "".equals(uploadstr)){
				 ServletUtil.sendJsonBean(response, new MsgBean("1", "图片为空"));
				 return; 
			  } 
		  }else if("wxfile".equals(name)){//文件
			  dirName = "wxfile";
			  if(uploadstr==null || "".equals(uploadstr)){
				  ServletUtil.sendJsonBean(response, new MsgBean("1", "文件为空"));
				  return; 
			   } 
		  }else{
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
			//文件保存目录路径
		    String savePath = request.getServletContext().getRealPath("/")+ "attached/";//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached\
//			System.out.println("---------savePath-------------"+savePath);
		    //文件保存目录URL
		    String saveUrl  = Config.getValue(Config.CONFIG_IMAGEROOTPATH) +"/attached/";
		    //定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
			//最大文件大小
			long maxSize = 1000000;
			//检查目录
			File uploadDir = new File(savePath);
//			if(!uploadDir.isDirectory()){
//				ServletUtil.sendJsonBean(response, new MsgBean("-1", "上传目录不存在。"));
//				return;
//			}
//			//检查目录写权限
//			if(!uploadDir.canWrite()){
//				ServletUtil.sendJsonBean(response, new MsgBean("-1", "上传目录没有写权限。"));
//				return;
//			}
			//创建文件夹
			savePath += dirName + "/";//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached/image/shop1/
			saveUrl += dirName + "/";//http://pnkooshop.oss-cn-shenzhen.aliyuncs.com/attached/image/shop1/
			File saveDirFile = new File(savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached\image\shop1
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached\image\20151123
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String fileExt = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			String newFileName2 = null;//原图
			String newFileName0 = null;
			synchronized(OSSObjectSample.lock){
				newFileName0 = String.valueOf(System.currentTimeMillis());
				newFileName0 = Tools.MD5(newFileName0);
				newFileName2 = ""+newFileName0 + "." + fileExt;
			}
			String rootPath = "attached/"+dirName+"/"+ df.format(new Date()) + "/";//云存储目录前缀
			File uploadedFile2 = new File(savePath, newFileName2);//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached/image/shop1/201601/  4F0B0B7B99B03CE7861EE7D8C911D73E.jpg 
			try{
				InputStream ins = uploadstr.getInputStream();
				FileOutputStream fous = new FileOutputStream(uploadedFile2);
				int c = -1;
				while ((c = ins.read()) != -1)
					fous.write(c);
/*				if(fileExt.equals("silk")){
					//将文件转换为MP3
					String pcm = (savePath+newFileName2).replace(".silk", ".pcm");
        			String mp3 = pcm.replace(".pcm", ".mp3");
					boolean b = DeCoder.getPcm(savePath+newFileName2,pcm);
//			        System.out.println(b);
			        if (b)
			        	DeCoder.getMp3(pcm,mp3);
			        
//			        System.out.println(newFileName0+".mp3");
			        File uploadedFile3 = new File(savePath, newFileName0+".mp3");
//			        uploadedFile2 = uploadedFile3;
			        newFileName2=newFileName2.replace(".silk", ".mp3");
			        ins = uploadstr.getInputStream();
				    fous = new FileOutputStream(uploadedFile2);
					 c = -1;
					 while ((c = ins.read()) != -1)
						fous.write(c);
			        fileExt=".mp3";
				}*/
				ins.close();
				fous.flush();  
				fous.close();
				OSSObjectSample.save(rootPath + newFileName2, uploadedFile2);//uploadedFile2上传OSS
				
				if("n".equals(Config.getValue(Config.CONFIG_OSSSTATUS))){//是否保留本地文件
					UploadFile.deleteFile(savePath+ newFileName2);//删除本地文件
				}
				//写入数据库
				if("wximage".equals(dirName)){
					imgBean  = UploadFile.uploadImg(newFileName2,rootPath+newFileName2,null,newFileName0,request);
					ServletUtil.sendJsonBean(response, new MsgBean("0", Config.getValue(Config.CONFIG_IMAGEROOTPATH)+"/"+imgBean.getPic())); //返回OSS的URL
					return;
				}else if("wxfile".equals(dirName)){
					flieBean = UploadFile.uploadFile(filePath,rootPath+newFileName2,newFileName0,fileExt,request);
					if("mp3".equals(fileExt)){//录音在本地
						flieBean = UploadFile.uploadFile(filePath,rootPath+newFileName2,newFileName0,fileExt,request);
						ServletUtil.sendJsonBean(response, new MsgBean("0", Config.getValue(Config.CONFIG_IMAGEROOTPATH)+"/"+flieBean.getFile())); //返回OSS的URL
						return;
					}else{
						ServletUtil.sendJsonBean(response, new MsgBean("0", Config.getValue(Config.CONFIG_IMAGEROOTPATH)+"/"+flieBean.getFile())); //返回OSS的URL
						return;
					}
				}
			}catch(Exception e){
			  e.printStackTrace();
			
			}
	     
	  }
	  
	  
}