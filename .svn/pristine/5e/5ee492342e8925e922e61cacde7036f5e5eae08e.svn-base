package com.pg.tag;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
/**
 * 权限控制标签 XCR
 * @param module（多个用逗号隔开）
 * 传入 module ，无该权限返回 displaynone
 * @return
 */
public class TagDict implements Tag {

    //接收传递进来的PageContext对象
    private PageContext pageContext;
    private String key;
    @Override
    public int doEndTag() throws JspException {
        return 0;
    }
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	@Override
    public int doStartTag() throws JspException {
		pageContext.getRequest();
		JspWriter out = pageContext.getOut();
	    Properties pps = new Properties();
	    if("".equals(this.key) || this.key==null) return 0;
	    try {
	    	 InputStream in = TagDict.class.getResourceAsStream("/dict.properties"); 
	         pps.load(in);
	         String value = pps.getProperty(key);
	         out.write(value==null?"":value);      
	    }catch (IOException e) {
	         e.printStackTrace();
	    }
        return 0;
    }
	public String changeChineseCode(String oldStr){
		String value=null;
		try {
		value =new String(oldStr.getBytes("ISO-8859-1"),"UTF-8"); 
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		} 
		return value;
	}

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public void release() {
//        System.out.println("调用release()方法");
    }

    @Override
    public void setPageContext(PageContext pageContext) {
//        System.out.println("setPageContext(PageContext pageContext)");
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag arg0) {

    }

}