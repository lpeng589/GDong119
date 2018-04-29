package com.pg.util;
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
 
public class MyMultipartResolver extends CommonsMultipartResolver {
 
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        String dir = request.getParameter("dir");
        if(dir!=null){  // kindeditor 上传图片的时候 不进行request 的转换
        	this.setMaxUploadSize(1000000);
            return false;
        }
        return super.isMultipart(request);
    }
 
}