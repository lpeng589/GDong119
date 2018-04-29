package com.pg.tag;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;

import com.pg.mapper.FlowNodeMapper;
import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Dict;
import com.sun.org.apache.bcel.internal.generic.I2F;
/**
 * 根据状态和表明获得状态名称
 * @author Administrator
 *
 */
public class TagByStatusAndTablename implements Tag {

    //接收传递进来的PageContext对象
    private PageContext pageContext;
    private String status;
    private String tablename;
    private String type;//-1返回上一节点状态；-2返回上上一节点状态；0为现在状态；1为下一节点状态；-2为下下节点状态
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public int doEndTag() throws JspException {
        return 0;
    }

	@Override
    public int doStartTag() throws JspException {
		HttpServletRequest request =(HttpServletRequest) pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		FlowNodeSearchBean flowNodeSearchBean = null;
	    try {
	    	 HashMap<String, FlowNodeSearchBean>  map = Check.getFlownodebyStaAndTableAndId(request);
	    	 flowNodeSearchBean = map.get(this.tablename+this.status);
	    	 if(flowNodeSearchBean==null) return 0;
		     if("-2".equals(this.type)){
		    		 flowNodeSearchBean = map.get(flowNodeSearchBean.getNodepre().toString());
		    		 if(flowNodeSearchBean!=null)flowNodeSearchBean = map.get(flowNodeSearchBean.getNodepre().toString());
		     }else if("-1".equals(this.type)){
			    	 if(flowNodeSearchBean.getIsaudit()==0||flowNodeSearchBean.getIsaudit()==Dict.ISAUDIT_3 || flowNodeSearchBean.getIsaudit()==Dict.ISAUDIT_4 ){
		    			 flowNodeSearchBean = null;
		    		 }else{
		    			 flowNodeSearchBean = map.get(flowNodeSearchBean.getNodepre().toString());
		    		 }
		     }else if("1".equals(this.type)){
		    		 if(flowNodeSearchBean.getIsaudit()==0||flowNodeSearchBean.getIsaudit()==Dict.ISAUDIT_3 || flowNodeSearchBean.getIsaudit()==Dict.ISAUDIT_5 ){
		    			 flowNodeSearchBean = null;
		    		 }else{
		    			 flowNodeSearchBean = map.get(flowNodeSearchBean.getNodenext().toString());
		    		 }
		     }else if("2".equals(this.type)){
		    		 flowNodeSearchBean = map.get(flowNodeSearchBean.getNodenext().toString());
		    		 if(flowNodeSearchBean!=null)flowNodeSearchBean = map.get(flowNodeSearchBean.getNodenext().toString());
		     }
	    	 if(flowNodeSearchBean!=null)
	 	     out.write(flowNodeSearchBean.getDescriptionnow());   
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