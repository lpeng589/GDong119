package com.pg.tag;


import java.io.IOException;
import java.io.InputStream;
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

import com.pg.util.Check;
/**
 * 根据权限自动加载状态选项
 * @author Administrator
 *
 */
public class TagStatus implements Tag {

    //接收传递进来的PageContext对象
    private PageContext pageContext;
    private String module;
    public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
    public int doEndTag() throws JspException {
        return 0;
    }

	@Override
    public int doStartTag() throws JspException {
		HttpServletRequest request =(HttpServletRequest) pageContext.getRequest();
		JspWriter out = pageContext.getOut();
	    try {
	    	SortedMap<Integer, String> map = new TreeMap<Integer, String>();
	    	if(Check.checkMoudle(request, module+"_add")){
	    		map.put(1, "status1");
	    		map.put(2, "status2");
	    		map.put(4, "status4");
	    		map.put(13, "status13");
	    	}
	    	if(Check.checkMoudle(request, module+"_update")){
	    		map.put(5, "status5");
	    		map.put(6, "status6");
	    		map.put(7, "status7");
	    		map.put(13, "status13");
	    	}
	    	if(Check.checkMoudle(request, module+"_status")){
	    		map.put(0, "status0");
	    		map.put(3, "status3");
	    	}
	    	if(Check.checkMoudle(request, module+"_list")){
	    		map.put(3, "status3");
	    	}
	    	if(Check.checkMoudle(request, module+"_auditadd")){
	    		map.put(2, "status2");
	    		map.put(13, "status13");
	    	}
	    	if(Check.checkMoudle(request, module+"_auditupdate")){
	    		map.put(6, "status6");
	    		map.put(13, "status13");
	    	}
	    	Properties pps = new Properties();
	    	InputStream in = TagStatus.class.getResourceAsStream("/dict.properties"); 
	        pps.load(in);
	        Set<Entry<Integer, String>> set=map.entrySet();
	        for (Entry<Integer, String> entry : set) {
	        	String value = pps.getProperty(entry.getValue());
	 	        out.write("<option value=\""+entry.getKey()+"\">"+value+"</option>");   
			}
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