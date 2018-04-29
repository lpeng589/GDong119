package com.pg.servlet;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.EmployeeBean;
import com.pg.bean.ModuleBean;
import com.pg.bean.MsgBean;
import com.pg.bean.RoleBean;
import com.pg.mapper.RoleMapper;
import com.pg.mapper.RoleModuleMapper;
import com.pg.searchbean.RoleSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;


/**
 * 
 * role管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("role.htm")
public class RoleAction extends BaseAction{
	
	
	/**
	 * 角色列表
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="role_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,RoleSearchBean bean) 
	{
		super.getList(request, bean);
		return "/rolelist.jsp";
	}

	/**
	 * 角色添加
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="role_add")
	@RequestMapping(params = "operate=add")
	public void add(HttpServletRequest request, HttpServletResponse response,RoleBean bean) {
		EmployeeBean employeeBean = Check.getEmployee(request);
		bean.setEmployee_id(employeeBean.getId());
		RoleMapper mapper = ApplicationContextUtil.getMapper(RoleMapper.class);
		RoleBean oldBean = mapper.getByName(bean.getRole_name());
		if(oldBean!=null){
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "此角色名已存在，请重新设置"));
			return;
		}
		int result=mapper.add(bean);
		if(result>0){
			ServletUtil.sendJsonBean(response, new MsgBean("0", "角色添加成功"));
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "角色添加失败"));
		}
	}
	
	/**
	 * 角色删除
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="role_delete")
	@RequestMapping(params = "operate=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,RoleBean bean) {
			String[] ids = request.getParameterValues("ids");
		    //检查改角色是否被使用
			/*EmployeeMapper employeeMapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
			int num= employeeMapper.getByRoleId(bean.getId()+",");//使用该角色的用户数
			if(num>1){
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "改角色已被使用，不能删除"));
				return;
			}*/
			RoleMapper mapper =ApplicationContextUtil.getMapper(RoleMapper.class);
			int result=mapper.delete(Arrays.asList(ids));
			if(result>0){
				RoleModuleMapper roleModuleMapper =ApplicationContextUtil.getMapper(RoleModuleMapper.class);
				roleModuleMapper.delete(Arrays.asList(ids));
				ServletUtil.sendJsonBean(response, new MsgBean("0", "角色删除成功"));
				return;
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "角色删除失败"));
				return;
			}	
	}
	
	/**
	 * 角色修改前
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="role_update",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=updatepre")
	public void updatePre(HttpServletRequest request, HttpServletResponse response,RoleBean bean) {
		RoleMapper mapper = ApplicationContextUtil.getMapper(RoleMapper.class);
		RoleBean sbean = mapper.getById(Integer.valueOf(bean.getId()));
		ServletUtil.sendJsonBean(response,new MsgBean(sbean));
	}
	
	/**
	 * 角色修改
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="role_update")
	@RequestMapping(params = "operate=update")
	public void update(HttpServletRequest request, HttpServletResponse response,RoleBean bean) {
		/*if(bean.getStatus()==0){//注销角色：检查改角色是否被使用
			EmployeeMapper employeeMapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
			int num= employeeMapper.getByRoleId(","+bean.getId()+",");//使用该角色的用户数
			if(num>1){
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "改角色已被使用，不能注销"));
				return;
			}
		}*/
		RoleMapper mapper = ApplicationContextUtil.getMapper(RoleMapper.class);
		int result= mapper.update(bean);
		if(result>0){
			ServletUtil.sendJsonBean(response, new MsgBean("0", "角色修改成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "角色修改失败"));
			return;
		}
	}
	
	/**
	 * 角色权限设置
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@HttpAnno(module="role_update",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=permission")
	public String permission(HttpServletRequest request,HttpServletResponse response,RoleBean bean){
		RoleMapper mapper =ApplicationContextUtil.getMapper(RoleMapper.class);
		RoleBean role =mapper.getById(bean.getId());
		List<ModuleBean> list=mapper.moduleList(bean.getId());
		request.setAttribute("data", list);
		request.setAttribute("role", role);		
		
		return "/rolemodule.jsp";
	}
	
	
	/**
	 * 更用户所拥有的模块
	 * @param request
	 * @param response
	 * @param bean
	 * @param id
	 */
	@HttpAnno(module="role_update")
	@RequestMapping(params="operate=updatepri")
	public void updatePri(HttpServletRequest request,HttpServletResponse response,RoleSearchBean bean) {
			String role_id=request.getParameter("role_id");
			String[] moduleId = request.getParameterValues("moduleid");
			RoleMapper mapper =ApplicationContextUtil.getMapper(RoleMapper.class);
			int ret=mapper.moduleDel(role_id);
			if(moduleId!=null){
				ret = mapper.moduleSet(role_id, Arrays.asList(moduleId));
				if (ret > 0) {
					ServletUtil.sendJsonBean(response, new MsgBean("0", "角色权限修改成功"));
				} else {
					ServletUtil.sendJsonBean(response, new MsgBean("-1", "角色权限修改失败"));
				}
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean("0", "角色权限修改成功"));
			}
	}
	@Override
	protected Integer getListCount(Object cond) {
		RoleMapper mapper = ApplicationContextUtil.getMapper(RoleMapper.class);
		return mapper.listCount((RoleSearchBean)cond);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Object> getListData(Object cond) {
		RoleMapper mapper = ApplicationContextUtil.getMapper(RoleMapper.class);
		return (List)mapper.list((RoleSearchBean)cond);
	}

}
