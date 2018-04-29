package com.pg.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.pg.bean.FileBean;
import com.pg.bean.ImgBean;
import com.pg.gzhqy.util.GZHQySetup;
import com.pg.mapper.UploadMapper;
import com.pg.servlet.UploadAction;


public class UploadFile {

	/**
	 * 企业微信   根据媒体ID获得文件
	 * @param sourceId
	 * @return
	 */
	public static String uploadQyWxMedia(String sourceId,HttpServletRequest request) {
		String fileFullName = "";
		try{
			 //文件保存目录路径
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			 String ymd = sdf.format(new Date());
			 //文件保存目录路径
			 String savePath = request.getServletContext().getRealPath("/") + "attached/qywx/"+ymd+"/";//E:\workplace\shop\.metadata\.me_tcat7\webapps\ROOT\attached\
			
			 //检查目录
			 File uploadDir = new File(savePath);
			 if (!uploadDir.exists()) {
				 uploadDir.mkdirs();
		     }	
			 String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=" + GZHQySetup.getToken(Config.getQyWxKey()) + "&media_id=" + sourceId;
			 URL url = new URL(requestUrl);
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			 conn.setRequestMethod("GET");
			 conn.setDoInput(true);
			 conn.setDoOutput(true);
			 InputStream in = conn.getInputStream();
			 String ContentDisposition = conn.getHeaderField("Content-disposition");
			 String weixinServerFileName = ContentDisposition.substring(ContentDisposition.indexOf("filename")+10, ContentDisposition.length() -1);
//			 savePath += weixinServerFileName; 
			 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + weixinServerFileName));
			 byte[] data = new byte[1024];
			 int len = -1;
			 
			 while ((len = in.read(data)) != -1) {
			  bos.write(data,0,len);
			 }
			 
			 bos.close();
			 in.close();
			 conn.disconnect();
			 fileFullName = uploadoss(savePath, weixinServerFileName, "qywx");
		}catch(Exception e){
			e.printStackTrace();
			return fileFullName;
		}
		return fileFullName;
	}
	public static String uploadoss(String savePath,String weixinServerFileName,String dirName) {
		String rootPath="";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			rootPath = "attached/"+dirName+ "/"+ sdf.format(new Date()) + "/"+weixinServerFileName;//云存储目录前缀
			File uploadedFile2 = new File(savePath, weixinServerFileName);
			if("y".equals(Config.getValue("saveoss"))){//上传OSS文件：y是，n否
				OSSObjectSample.save(rootPath, uploadedFile2);
			}
			
			if("n".equals(Config.getValue(Config.CONFIG_OSSSTATUS))){//是否保留本地文件
				UploadAction.deleteFile(savePath+ weixinServerFileName);//删除本地文件
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return "/"+rootPath;
	}
	
	/**
	 * 上传单个文件
	 * @param    fileName 原文件名
	 * file 保存物理位置
	 * code 唯一编码 
	 * type 类型
	 * @return
	 */
	public static FileBean uploadFile(String filename,String file,String code,String type,HttpServletRequest request) {
	   try {
			FileBean bean = new FileBean();
			bean.setFile(file);
			bean.setType(type);
			bean.setCode(code);
			bean.setFilename(filename);
			if(Check.getEmployee(request)!=null)bean.setEmployid(Check.getEmployee(request).getId());
			UploadMapper mapper = ApplicationContextUtil.getMapper(UploadMapper.class);
			int result = mapper.addFile(bean);
			if (result>0) {
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 上传单张图片
	 * @param filename
	 * pic  原图物理地址
	 * smallpic  压缩图物理地址
	 * code  唯一编码
	 * @return
	 */
	public static ImgBean uploadImg(String filename,String pic,String smallpic,String code,HttpServletRequest request ) {
		try {
			ImgBean bean = new ImgBean();
			bean.setPic(pic);
			bean.setCode(code);
			bean.setFilename(filename);
			if(Check.getEmployee(request)!=null)bean.setEmployid(Check.getEmployee(request).getId());
			UploadMapper mapper = ApplicationContextUtil.getMapper(UploadMapper.class);
			int result = mapper.addImg(bean);
			if (result>0) {
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据文件code获得文件信息
	 * @param    fileName 原文件名
	 * file 保存物理位置
	 * code 唯一编码 
	 * type 类型
	 * @return
	 */
	public static FileBean getFileByCode(String code ) {
		UploadMapper mapper = ApplicationContextUtil.getMapper(UploadMapper.class);
		FileBean bean = mapper.getFile(code);
		return bean;
	}

	
	/**
	 * 按照宽度图片缩放
	 * @param srcFile
	 * @param destFile
	 * @param width
	 * @throws Exception
	 */
    private static void zoomImage(File srcFile,File destFile,int width) throws Exception {  //80
        double wr=0;//  
        BufferedImage bufImg = ImageIO.read(srcFile);//
        wr=(double)width/bufImg.getWidth();   
        BufferedImage bi = new BufferedImage(width, (int)((double)width/bufImg.getWidth()*bufImg.getHeight()), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = ((Graphics2D)bi.getGraphics());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(bufImg, AffineTransform.getScaleInstance(wr, wr), null);
        try {  
            ImageIO.write((BufferedImage) bi,"png", destFile);  
        } catch (Exception ex) {  
            ex.printStackTrace();  
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
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地 
     * @param 
     */  
    public static String downloadPicture(HttpServletRequest request,String picurl) {  
        	URL url = null;  
        	String path="";
            try {  
                url = new URL(picurl);  
                DataInputStream dataInputStream = new DataInputStream(url.openStream());  
                String savePath = request.getServletContext().getRealPath("/")+ "attached/";
                String[] i = picurl.split("/"); 
                String imageName = i[i.length-1];
                FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath,imageName));  
                byte[] buffer = new byte[1024];  
                int length;  
  
                while ((length = dataInputStream.read(buffer)) > 0) {  
                    fileOutputStream.write(buffer, 0, length);  
                }  
                dataInputStream.close();  
                fileOutputStream.close(); 
                path = savePath+imageName;
            } catch (MalformedURLException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
            return path;
    } 
    
    
}
