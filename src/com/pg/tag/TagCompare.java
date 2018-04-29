package com.pg.tag;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.alibaba.fastjson.JSONObject;
import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.util.Check;
/**
 * 根据状态和表明获得状态名称
 * @author Administrator
 *
 */
public class TagCompare implements Tag {

    //接收传递进来的PageContext对象
    private PageContext pageContext;
    private String keyname;

    public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	private String fieldname;
    
    
    
	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	@Override
    public int doEndTag() throws JspException {
        return 0;
    }

	@Override
    public int doStartTag() throws JspException {
		pageContext.getRequest();
		JspWriter out = pageContext.getOut();
	    Properties pps = new Properties();
	    if("".equals(this.keyname) || this.keyname==null || "".equals(this.fieldname) || this.fieldname==null) {
	    	try {
				out.write(fieldname);
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
			}      
	    }
	    try {
//	    	 InputStream in = TagDict.class.getResourceAsStream("/compare.properties"); 
//	         pps.load(in);
	    	 pps.load(new InputStreamReader(TagCompare.class.getClassLoader().getResourceAsStream("compare.properties"), "UTF-8"));      
	         String data = pps.getProperty(keyname);
	 	     if("".equals(data) || data==null){
	 			out.write(fieldname);
				return 0; 
	 	     }
	         Map<String, String> map = new HashMap<String, String>();
	         String[] arraydata = data.split(",");//按“，”将其分为字符数组
	         for (int i = 0; i < arraydata.length; i++) {
		         int j = arraydata[i].indexOf("=");
		         map.put(arraydata[i].substring(0, j), arraydata[i].substring(j+1, arraydata[i].length()));
	         }
	         out.write(map.get(fieldname)==null?fieldname:map.get(fieldname));      
	    }catch (IOException e) {
	         e.printStackTrace();
	    }
        return 0;
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