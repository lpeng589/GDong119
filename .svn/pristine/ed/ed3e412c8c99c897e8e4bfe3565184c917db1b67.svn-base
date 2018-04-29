package com.pg.tag;


import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.util.Check;
/**
 * 根据状态和表明获得状态名称
 * @author Administrator
 *
 */
public class TagFlowNodeStatus implements Tag {

    //接收传递进来的PageContext对象
    private PageContext pageContext;
    private String tablename;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
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
	    	SortedMap<Integer, String> map = new TreeMap<Integer, String>();
	    	List<FlowNodeSearchBean> list = Check.getFlowNodeAll(request);
	    	for (int i = 0; i < list.size(); i++) {
				flowNodeSearchBean = list.get(i);
				if(this.tablename.equals(flowNodeSearchBean.getTablename())){
					map.put(flowNodeSearchBean.getStatusnow(), flowNodeSearchBean.getDescriptionnow());
				}
			}
	    	Set<Entry<Integer, String>> set=map.entrySet();
	        for (Entry<Integer, String> entry : set) {
	 	        out.write("<option value=\""+entry.getKey()+"\">"+entry.getValue()+"</option>");   
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