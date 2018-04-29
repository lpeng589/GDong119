package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.bean.PoslinkBean;
import com.pg.mapper.PoslinkMapper;
import com.pg.searchbean.PoslinkSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("poslink.htm")
public class PoslinkAction extends BaseAction
{
	
	
	@HttpAnno(value=HttpAnno.VIEW,module="poslink_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,PoslinkSearchBean bean){
		super.getList(request, bean);
		return "/poslinklist.jsp";
	}
	
	/**
	 * 图片修改前
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="poslink_update",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=updatepre")
	public String updatePre(HttpServletRequest request, HttpServletResponse response,PoslinkSearchBean bean) {
		PoslinkMapper mapper = ApplicationContextUtil.getMapper(PoslinkMapper.class);
		request.setAttribute("bean", mapper.getById(bean.getId()));
		return "/poslinkupdate.jsp";
	}
	
	/**
	 * 图片修改
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="poslink_update")
	@RequestMapping(params = "operate=update")
	public void update(HttpServletRequest request, HttpServletResponse response,PoslinkBean bean) {
		PoslinkMapper mapper = ApplicationContextUtil.getMapper(PoslinkMapper.class);
		int result=mapper.updatePoslink(bean);
		if(result>0){
			ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
			return;
		}
	}
	
	/**
	 * 图片新增
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="poslink_add")
	@RequestMapping(params = "operate=add")
	public void add(HttpServletRequest request, HttpServletResponse response,PoslinkBean bean) {
		PoslinkMapper mapper = ApplicationContextUtil.getMapper(PoslinkMapper.class);
		bean.setType(1);
		int result=mapper.addPoslink(bean);
		if(result>0){
			ServletUtil.sendJsonBean(response, new MsgBean("0", "添加成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "添加失败"));
			return;
		}
	}
	
	/**
	 * 图片删除
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="poslink_delete")
	@RequestMapping(params = "operate=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,PoslinkBean bean) {
		PoslinkMapper mapper = ApplicationContextUtil.getMapper(PoslinkMapper.class);
		int result=mapper.delPoslink(bean.getId());
		if(result>0){
			ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "删除失败"));
			return;
		}
	}
	
	@Override
 	protected Integer getListCount(Object cond)
	{
		PoslinkMapper mapper = ApplicationContextUtil.getMapper(PoslinkMapper.class);
		return mapper.listCount((PoslinkSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		PoslinkMapper mapper = ApplicationContextUtil.getMapper(PoslinkMapper.class);
		 return (List)mapper.list((PoslinkSearchBean)cond);
	}

}