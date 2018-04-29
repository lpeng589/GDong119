package com.pg.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.ModuleBean;
import com.pg.mapper.LogMapper;
import com.pg.mapper.ModuleMapper;
import com.pg.searchbean.LogSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;

@Controller
@RequestMapping("log.htm")
public class LogAction extends BaseAction{
	@HttpAnno(value=HttpAnno.VIEW,module="log_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,LogSearchBean bean){
		ModuleMapper moduleMapper = ApplicationContextUtil.getMapper(ModuleMapper.class);
		List<ModuleBean> modulelist =  moduleMapper.getCategory();
		request.setAttribute("modulelist", modulelist);
		super.getList(request, bean);
		return "/loglist.jsp";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getListData(Object cond) {
		LogMapper mapper = ApplicationContextUtil.getMapper(LogMapper.class);
		return (List)mapper.list((LogSearchBean)cond);
	}

	@Override
	public Integer getListCount(Object cond) {
		LogMapper mapper = ApplicationContextUtil.getMapper(LogMapper.class);
		return mapper.listCount((LogSearchBean)cond);
	}
}
