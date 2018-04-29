package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.mapper.FlowLogMapper;
import com.pg.mapper.FlowMapper;
import com.pg.searchbean.FlowLogSearchBean;
import com.pg.searchbean.FlowSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("flowLog.htm")
public class FlowLogAction extends BaseAction
{
	
	
	/**
	 * 职员修改前
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=log")
	public void log(HttpServletRequest request, HttpServletResponse response,FlowLogSearchBean bean) {
		FlowLogMapper flowLogMapper =ApplicationContextUtil.getMapper(FlowLogMapper.class);
		bean.setLimitFlag(false);
		List<FlowLogSearchBean> list = flowLogMapper.list(bean);
		ServletUtil.sendJsonBean(response,new MsgBean(list));
	}
	/**
	 * 工作流日志列表
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="flowlog_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,FlowLogSearchBean bean) {
	/*	if(Check.getEmployee(request).getUsername().equals("admin")){
			bean.setEmployeeid(null);
		}else{
			bean.setEmployeeid(bean.getEmployid_base());
		}*/
		FlowMapper flowMapper = ApplicationContextUtil.getMapper(FlowMapper.class);
		List<FlowSearchBean> flowlist =  flowMapper.list(new FlowSearchBean());
		if(flowlist!=null){
			request.setAttribute("flowlist", flowlist);
		}
		super.getList(request, bean);
		return "flowloglist.jsp";
	}
	@Override
 	protected Integer getListCount(Object cond)
	{
		FlowLogMapper mapper = ApplicationContextUtil.getMapper(FlowLogMapper.class);
		return mapper.listCount((FlowLogSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		FlowLogMapper mapper = ApplicationContextUtil.getMapper(FlowLogMapper.class);
		 return (List)mapper.list((FlowLogSearchBean)cond);
	}

}