package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.mapper.FlowMapper;
import com.pg.searchbean.FlowSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;
@Controller
@RequestMapping("flow.htm")
public class FlowAction extends BaseAction
{
	
	/**
	 * 工作流列表 XCR
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="flow_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,FlowSearchBean bean) {
		super.getList(request, bean);
		return "flowlist.jsp";
	}
	
	
	@Override
 	protected Integer getListCount(Object cond)
	{
		FlowMapper mapper = ApplicationContextUtil.getMapper(FlowMapper.class);
		return mapper.listCount((FlowSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		FlowMapper mapper = ApplicationContextUtil.getMapper(FlowMapper.class);
		 return (List)mapper.list((FlowSearchBean)cond);
	}

}