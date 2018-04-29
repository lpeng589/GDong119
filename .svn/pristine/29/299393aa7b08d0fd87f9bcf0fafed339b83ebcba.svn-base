package com.pg.tag;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.pg.bean.ModuleBean;
import com.pg.util.Check;
/**
 * 权限控制标签 XCR
 * @param module（多个用逗号隔开）
 * 传入 module ，无该权限返回 displaynone
 * @return
 */
public class TagRuleControl implements Tag {

    //接收传递进来的PageContext对象
    private PageContext pageContext;
    private String module;
    @Override
    public int doEndTag() throws JspException {
//        System.out.println("调用doEndTag()方法");
        return 0;
    }

    public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
    public int doStartTag() throws JspException {
//        System.out.println("调用doStartTag()方法");
		HttpServletRequest request =(HttpServletRequest) pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		boolean haverule =  false ;//无权限
        try {
            if(this.module!=null&&this.module.length()>0){
    			String [] modules = this.module.split(",");
    			HashMap<String, ModuleBean> moduleMap =Check.getModule(request);
    			for (int i = 0; i < modules.length; i++) {
    				if(moduleMap !=null && moduleMap.get(modules[i])!=null){
    					haverule=true;//有权限
    					break;
        			}
    			}
    		}
            try {
	        	if(!haverule)out.write("displaynone");//没有权限
	        } catch (IOException p) {
	            throw new RuntimeException(p);
	        }
		 } catch (Exception e) {
			try {//出错后仍能隐藏按钮
				if(!haverule)out.write("displaynone");//没有权限
	        } catch (IOException p) {//捕获IOException异常后继续抛出
	            throw new RuntimeException(p);
	        }
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