<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="com.pg.util.Check"%>
<%@page import="com.pg.util.Tools"%>
<%@page import="com.pg.util.OSSFileManagerJson"%>
<%@page import="com.pg.util.OSSObjectSample"%>
<%@page import="com.pg.util.UploadFile"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="com.pg.util.ImageUtils"%>
<%@page import="com.pg.util.Config"%>

<%
Logger logger = LoggerFactory.getLogger(OSSObjectSample.class);

	//文件保存目录路径
	String savePath = pageContext.getServletContext().getRealPath("/") + "attached/";
	//文件保存目录URL
	//String saveUrl  = systemSetting.getImageRootPath()+"/attached/";//request.getContextPath() + "/attached/";
// 	String saveUrl  = shopBean.getImageRootPath() +"/attached/";
	out.println("savePath:"+savePath);
  	String saveUrl =  Config.getValue(Config.CONFIG_IMAGEROOTPATH);//lO5k6anESQXkK8dY
	out.println("saveUrl:"+saveUrl);
	//定义允许上传的文件扩展名
	HashMap<String, String> extMap = new HashMap<String, String>();
	extMap.put("image", "gif,jpg,jpeg,png,bmp");
	extMap.put("flash", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	//最大文件大小
	long maxSize = 1000000;
// 	session.setAttribute("ajax_upload", 1);
	response.setContentType("text/html; charset=UTF-8");
	if(!ServletFileUpload.isMultipartContent(request)){
		out.println(getError("请选择文件。"));
		return;
	}
	//检查目录
	File uploadDir = new File(savePath);
	if(!uploadDir.isDirectory()){
		out.println(getError("上传目录"+Config.getValue(Config.CONFIG_UPLOADPATH) +"不存在。"));
		return;
	}
	//检查目录写权限
	if(!uploadDir.canWrite()){
		out.println(getError("上传目录没有写权限。"));
		return;
	}

	String dirName = request.getParameter("dir");//image
	if (dirName == null) {
		dirName = "image";
	}
	if(!extMap.containsKey(dirName)){
		out.println(getError("目录名不正确。"));
		return;
	}
	//创建文件夹
	savePath += dirName + "/";//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached/image/
	saveUrl += dirName + "/";//http://bhx.oss-cn-shenzhen.aliyuncs.com/attached/image/
// 	System.out.println("savePath   "+savePath);
// 	System.out.println("saveUrl    "+saveUrl);
	File saveDirFile = new File(savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached\image
	if (!saveDirFile.exists()) {
		saveDirFile.mkdirs();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	String ymd = sdf.format(new Date());
	savePath += ymd + "/";
	saveUrl += ymd + "/";
//	System.out.println("savePath    "+savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached/image/201603/
//	System.out.println("saveUrl    "+saveUrl);
	File dirFile = new File(savePath);//E:\workplace\shop\.metadata\.me_tcat7\webapps\bhx\attached\image\20151123
	if (!dirFile.exists()) {
		dirFile.mkdirs();
	}
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setHeaderEncoding("UTF-8");

	List items = upload.parseRequest(request);
	
	//session.setAttribute("ajax_upload", null);
	Iterator itr = items.iterator();
	System.out.println("items    "+items);
	while (itr.hasNext()) {
		FileItem item = (FileItem) itr.next();
		String fileName = item.getName();
		long fileSize = item.getSize();
		if (!item.isFormField()) {
			//检查文件大小
			if(item.getSize() > maxSize){
				out.println(getError("上传文件大小超过限制。"));
				return;
			}
			//检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
				return;
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			String newFileName1 = null;//小图
			String newFileName2 = null;//原图
			String newFileName0 = null;
			synchronized(OSSObjectSample.lock){
			    newFileName0 = String.valueOf(System.currentTimeMillis());
				newFileName0 = Tools.MD5(newFileName0);
				logger.error("newFileName0="+newFileName0);
				newFileName1 = "s_"+newFileName0 + "." + fileExt;
				newFileName2 = ""+newFileName0 + "." + fileExt;
			}
			String rootPath = "attached/"+dirName+ "/"+ df.format(new Date()) + "/";//云存储目录前缀
			
			File uploadedFile2 = new File(savePath, newFileName2);
			try{
				item.write(uploadedFile2);//uploadedFile2写入本地
				//等比列缩放原图，并且存储
				if("image".equals(dirName)){
					File uploadedFile1 = new File(savePath, newFileName1);
					ImageUtils.ratioZoom2(uploadedFile2,uploadedFile1,Double.valueOf(Config.getValue(Config.CONFIG_IMAGE_1_W)));//缩图，并且uploadedFile1写入本地
					OSSObjectSample.save(rootPath + newFileName1, uploadedFile1);//uploadedFile1上传OSS
				}
				OSSObjectSample.save(rootPath + newFileName2, uploadedFile2);//uploadedFile2上传OSS
				//写入数据库
				if("image".equals(dirName)){
					UploadFile.uploadImg(fileName,rootPath+newFileName2,rootPath+newFileName1,newFileName0);
				}else{
					UploadFile.uploadFile(fileName,rootPath+newFileName2,newFileName0,fileExt);
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("上传文件异常："+e.getMessage());
				out.println(getError("上传文件失败。"));
				return;
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName2);
			out.println(obj.toJSONString());
		}
	}


%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toJSONString();
}
%>