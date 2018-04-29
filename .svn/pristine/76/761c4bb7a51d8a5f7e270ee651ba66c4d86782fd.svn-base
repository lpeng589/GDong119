package com.pg.servlet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.bean.RoleBean;
import com.pg.mapper.EmployeeMapper;
import com.pg.mapper.FlowNodeMapper;
import com.pg.mapper.RoleMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.searchbean.RoleSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Dict;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
@Controller
@RequestMapping("flownode.htm")
public class FlowNodeAction extends BaseAction
{
	/**
	 * 节点列表 XCR
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="flownode_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,FlowNodeSearchBean bean) {
		//角色
		RoleMapper rolemapper = ApplicationContextUtil.getMapper(RoleMapper.class);
		RoleSearchBean sBean = new RoleSearchBean();
		sBean.setLimitFlag(false);
		sBean.setStatus(1);
		List<RoleBean> roles = rolemapper.list(sBean);
		request.setAttribute("roles", roles);
		//职员
		EmployeeMapper employeemapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeSearchBean eBean = new EmployeeSearchBean();
		eBean.setLimitFlag(false);
		eBean.setStatus(1);
		List<EmployeeSearchBean> employees = employeemapper.list(eBean);
		request.setAttribute("employees", employees);
		
		FlowNodeMapper mapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean flowNodeSearchBean = new FlowNodeSearchBean();
		List<FlowNodeSearchBean>  flownodelist = mapper.list(bean);
		List<FlowNodeSearchBean>  list = new ArrayList<FlowNodeSearchBean>();
		//把list转成map
		for (int i = 0; i < flownodelist.size(); i++) {
			flowNodeSearchBean = (FlowNodeSearchBean) flownodelist.get(i);
			if(flowNodeSearchBean.getIsaudit()!=Dict.ISAUDIT_0){
				list.add(flowNodeSearchBean);
				if(!"".equals(flowNodeSearchBean.getRoleid()) && flowNodeSearchBean.getRoleid()!=null){
					flowNodeSearchBean.setRolenames(getRoleNames(flowNodeSearchBean.getRoleid().split(","),roles));
				}
				if(!"".equals(flowNodeSearchBean.getEmployeeid()) && flowNodeSearchBean.getEmployeeid()!=null){
					flowNodeSearchBean.setEmployeenames(getEmployeeNames(flowNodeSearchBean.getEmployeeid().split(","),employees));
				}
			
			}
		}
		
		request.setAttribute("list", list);
		return "flownodelist.jsp";
	}
	

	private static String getRoleNames(String str[],List<RoleBean> list) {
		String s ="";
		for (int i = 0; i < str.length; i++) {
			RoleBean roleBean =  new RoleBean();
			for (int j = 0; j < list.size(); j++) {
				roleBean = list.get(j);
				if(str[i].equals(roleBean.getId().toString())){
					s=s+roleBean.getRole_name()+"  ";	
					break;
				}
			}
		}
		return s;
	}

	private static String getEmployeeNames(String str[],List<EmployeeSearchBean> list) {
		String s ="";
		for (int i = 0; i < str.length; i++) {
			EmployeeSearchBean employeeBean =  new EmployeeSearchBean();
			for (int j = 0; j < list.size(); j++) {
				employeeBean = list.get(j);
				if(str[i].equals(employeeBean.getId().toString())){
					s=s+employeeBean.getUsername()+"  ";
					break;
				}
			}
		}
		return s;
	}
	/**
	 * 自定义节点修改  XCR
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="flownode_operete")
	@RequestMapping(params = "operate=update")
	public void update(HttpServletRequest request, HttpServletResponse response,FlowNodeSearchBean bean) {
		FlowNodeMapper mapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		if (bean.getId()!=null) {
			int result=mapper.updateFlowNode(bean);
			if(result>0){
				Check.loadFlowNode(request, Check.getEmployee(request));
				ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
				return;
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
				return;
			}
		}
	}
	
	/**
	 * 节点新增 XCR
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="flownode_operete")
	@RequestMapping(params = "operate=add")
	public void add(HttpServletRequest request, HttpServletResponse response,FlowNodeSearchBean bean) {
		FlowNodeMapper mapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean beanpre =mapper.getById(bean.getId());
		FlowNodeSearchBean beannext =mapper.getById(beanpre.getNodenext());
		if (beanpre!=null && beannext!=null) {
			Integer statusnow = Integer.valueOf(Tools.getRandomCode());
			//检查 statusnow 是否有了
			if(mapper.getStatusCount(statusnow, beanpre.getTablename())>1){
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败"));
				return;
			}
			//新增自定义节点
			bean.setNodepre(beanpre.getId());
			bean.setStatusnow(statusnow);
			bean.setNodenext(beannext.getId());
			bean.setFlowid(beanpre.getFlowid());
			bean.setTablename(beanpre.getTablename());
			bean.setValuetemplate(beanpre.getValuetemplate());
			bean.setLinkurl(beanpre.getLinkurl());
			bean.setIsaudit(1);//是否需要审核：0否，1是
			int result=mapper.addFlowNode(bean);
			if(result>0){
				//前节点中的 下一节点修改 
				FlowNodeSearchBean updatepre = new FlowNodeSearchBean();
				updatepre.setNodenext(bean.getId());//插入后数据的id为  下一节点
				updatepre.setId(beanpre.getId());
				mapper.updateFlowNode(updatepre);
				//后节点中的 上一节点修改 
				FlowNodeSearchBean updatenext = new FlowNodeSearchBean();
				updatenext.setNodepre(bean.getId());//插入后数据的id为 上一节点
				updatenext.setId(beannext.getId());
				mapper.updateFlowNode(updatenext);
				Check.loadFlowNode(request, Check.getEmployee(request));//
				ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
				return;
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败"));
				return;
			}
		}
	}
	/**
	 * 节点删除 XCR
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="flownode_operete")
	@RequestMapping(params = "operate=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,FlowNodeSearchBean bean) {
		FlowNodeMapper mapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean beanpre =mapper.getById(bean.getId());
		int result=mapper.delFlowNode(bean);
		if(result>0){
			//前节点中的 下一节点修改 
			FlowNodeSearchBean updatepre = new FlowNodeSearchBean();
			updatepre.setNodenext(beanpre.getNodenext());
			updatepre.setId(beanpre.getNodepre());
			mapper.updateFlowNode(updatepre);
			//后节点中的 上一节点修改 
			FlowNodeSearchBean updatenext = new FlowNodeSearchBean();
			updatenext.setNodepre(beanpre.getNodepre());
			updatenext.setId(beanpre.getNodenext());
			mapper.updateFlowNode(updatenext);
			Check.loadFlowNode(request, Check.getEmployee(request));
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
		FlowNodeMapper mapper = ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		return mapper.listCount((FlowNodeSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		FlowNodeMapper mapper = ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		 return (List)mapper.list((FlowNodeSearchBean)cond);
	}

}