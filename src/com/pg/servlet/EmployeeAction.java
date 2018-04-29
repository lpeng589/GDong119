package com.pg.servlet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.EmployeeBean;
import com.pg.bean.ModuleBean;
import com.pg.bean.MsgBean;
import com.pg.mapper.EmployeeMapper;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.mapper.ModuleMapper;
import com.pg.mapper.RoleMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.searchbean.RoleSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Crypto;
import com.pg.util.DecUtil;
import com.pg.util.HttpAnno;
import com.pg.util.ManageCache;
import com.pg.util.ServletUtil;


/**
 * 职员操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("employee.htm")
public class EmployeeAction extends BaseAction{
	/**
	 * 职员列表
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="employee_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		super.getList(request, bean);
		
		F_departmentSearchBean departmentSearchBean =  new F_departmentSearchBean();
	    F_departmentMapper departmentMapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
	    departmentSearchBean.setLimitFlag(false);
	    departmentSearchBean.setParentid(-1);
	    request.setAttribute("department", departmentMapper.list(departmentSearchBean));
	    
	    RoleMapper roleMapper = ApplicationContextUtil.getMapper(RoleMapper.class);
	    RoleSearchBean roleSearchBean = new RoleSearchBean();
	    roleSearchBean.setStatus(1);
	    request.setAttribute("roles", roleMapper.list(roleSearchBean));
	    
		return "employeelist.jsp";
	}
	
	/**
	 * 获取部门
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="employee_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=getdepartment")
	public void getdepartment(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		F_departmentSearchBean departmentSearchBean =  new F_departmentSearchBean();
	    F_departmentMapper departmentMapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
	    departmentSearchBean.setLimitFlag(false);
	    departmentSearchBean.setParentid(bean.getDepartmentid());
//	    request.setAttribute("workuser", h_workuserMapper.getByDepartmentid(bean.getDepartmentid()));
	    ServletUtil.sendJsonBean(response, new MsgBean(departmentMapper.list(departmentSearchBean)));
	}
	/**
	 * 获取部门人员
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="employee_list",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=departmentemp")
	public void departmentemp(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
		H_workuserSearchBean h_workuserSearchBean = new H_workuserSearchBean();
		h_workuserSearchBean.setDepartmentid(bean.getDepartmentid());
		ServletUtil.sendJsonBean(response, new MsgBean(h_workuserMapper.list(h_workuserSearchBean)));
	}
	
	/**
	 * 职员添加
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="employee_add")
	@RequestMapping(params = "operate=add")
	public void add(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		EmployeeMapper mapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeBean oldBean = mapper.getByName(bean.getLoginname());
		if(oldBean!=null){
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "此登录账号已存在，请重新设置"));
			return;
		}
		
		String password = new DecUtil().strDec(bean.getPassword(), bean.getLoginname(), "", "");  //页面加密的解密
		bean.setPassword(Crypto.encrypt(password));
		int result=mapper.add(bean);
		if(result>0){
			ManageCache.reflashMessage() ;//更新消息
			ServletUtil.sendJsonBean(response, new MsgBean("0", "职员添加成功"));
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "职员添加失败"));
		}
	}
	
	/**
	 * 职员删除
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="employee_delete")
	@RequestMapping(params = "operate=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,EmployeeBean bean) {
		EmployeeMapper mapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		int result=mapper.delete(bean);
		if(result>0){
			ManageCache.reflashMessage() ;//更新消息
			ServletUtil.sendJsonBean(response, new MsgBean("0", "职员删除成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "职员删除失败"));
			return;
		}
	}
	
	/**
	 * 职员修改前
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="employee_update",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=updatepre")
	public void updatePre(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		EmployeeMapper mapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeSearchBean employeeBean = mapper.getById(bean.getId().toString());
		employeeBean.setPassword(Crypto.decrypt(employeeBean.getPassword()));
		ServletUtil.sendJsonBean(response,new MsgBean(employeeBean));
	}
	/**
	 * 职员修改
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="employee_update")
	@RequestMapping(params = "operate=update")
	public void update(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		EmployeeMapper mapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeBean oldBean = mapper.getById(bean.getId()==null?null:bean.getId().toString());
		String password = new DecUtil().strDec(bean.getPassword(), bean.getLoginname(), "", "");  //页面加密的解密
		bean.setPassword(Crypto.encrypt(password));
		int result=mapper.update(bean);
		if(result>0){
			ManageCache.reflashMessage() ;//更新消息
			ServletUtil.sendJsonBean(response, new MsgBean("0", "职员修改成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "职员修改失败"));
			return;
		}
	}

	/**
	 * 修改密码前
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="employee_modify",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=modify")
	public String modify(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		request.setAttribute("loginname", Check.getEmployee(request).getLoginname());
	
		//修改 产品表的物流方案id
	/*	Map<String, String> map  =  new HashMap<String, String>();
		map.put("德国", "A16081935,A16081927,A16081952");
		map.put("日本", "A16081953,A16081957,A16081931");
		map.put("奥地利", "A16081952");
		map.put("澳大利亚", "A16081956");
		map.put("比利时", "A16081952");
		map.put("丹麦", "A16081952");
		map.put("法国", "A16081952,A16081936,A16081908");
		map.put("芬兰", "A16081934,A16081952");
		map.put("荷兰", "A16081933");
		map.put("加拿大", "A16081951");
		map.put("马来西亚", "A16081955");
		map.put("卡塔尔", "");
		map.put("日本", "A16081953,A16081957,A16081931");
		map.put("瑞典", "A16081952");
		map.put("瑞士", "A16081952");
		map.put("美国", "");
		map.put("意大利", "A16081952,A16081939,A16081930");
		map.put("英国", "A16081937,A16081928,A16081952");
		map.put("新加坡", "A16081955");
		map.put("西班牙", "A16081952,A16081932");
//		韩国？
		
		LogisticsMapper logisticsMapper = ApplicationContextUtil.getMapper(LogisticsMapper.class);
		LogisticsSearchBean logisticsSearchBean = new LogisticsSearchBean();
		logisticsSearchBean.setLimitFlag(false);
		List<LogisticsSearchBean> logisticlist = logisticsMapper.list(logisticsSearchBean);
		HashMap<String,Integer>  logisticMap = new HashMap();
		for (int j = 0; j < logisticlist.size(); j++) {
			logisticsSearchBean = logisticlist.get(j);
			logisticMap.put(logisticsSearchBean.getCode(), logisticsSearchBean.getId());//物流方案code 和id
		}
		
		HashMap<String,String>  logisticidMap = new HashMap();
	     for (String key : map.keySet()) {
			   String[] codeString =  map.get(key).split(",");
			   String idString = "";
			   for (int i = 0; i < codeString.length; i++) {
				if(logisticMap.get(codeString[i])!=null){
					if(!"".equals(idString))idString=idString+",";
					idString=idString+logisticMap.get(codeString[i]);	
				}
			   }
			   System.out.println("-----11-----"+idString);
			   logisticidMap.put(key, idString);// 国别  + 物流方案id
			   
	     }
	 
	     ProductMapper productMapper = ApplicationContextUtil.getMapper(ProductMapper.class);
	     ProductSearchBean ProductSearchBean = new    ProductSearchBean();
		 SupplierMapper supplierMapper = ApplicationContextUtil.getMapper(SupplierMapper.class);
			//供应商map
			SupplierSearchBean SupplierSearchBean1 = new SupplierSearchBean();
			SupplierSearchBean1.setLimitFlag(false);
			List<SupplierSearchBean> SupplierSearchlist = supplierMapper.list(SupplierSearchBean1);
			for (int j = 0; j < SupplierSearchlist.size(); j++) {
				SupplierSearchBean1 = SupplierSearchlist.get(j);
				ProductSearchBean.setSupplierid(SupplierSearchBean1.getId());
				ProductSearchBean.setLogisticsid(logisticidMap.get(SupplierSearchBean1.getCountry()));
				System.out.println("----------"+SupplierSearchBean1.getId()+"---"+SupplierSearchBean1.getCountry()+"--"+logisticidMap.get(SupplierSearchBean1.getCountry()));
				if(logisticidMap.get(SupplierSearchBean1.getCountry())!=null){
					productMapper.updatelogisticsid(logisticidMap.get(SupplierSearchBean1.getCountry()), SupplierSearchBean1.getId());	
				}
			}*/
		return "employeemodify.jsp";
	}
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param bean
	 */
	@RequestMapping(params = "operate=modifys")
	public void modifys(HttpServletRequest request,HttpServletResponse response,EmployeeSearchBean bean){
		EmployeeMapper mapper = ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeBean employeeBean = Check.getEmployee(request);
		String password = new DecUtil().strDec(request.getParameter("password"), bean.getLoginname(), "", "");  //页面加密的解密--旧密码
		String password1= new DecUtil().strDec(request.getParameter("password1"), bean.getLoginname(), "", "");  //页面加密的解密--新密码
		if (!password.equals(Crypto.decrypt(employeeBean.getPassword()))) {
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "密码输入错误，无法修改！"));
			return;
		}
		employeeBean.setPassword(Crypto.encrypt(password1));
		int result = mapper.update(employeeBean);
		if(result>0){
			ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
			return;
		}			
	}
	
	
	@Override
	protected Integer getListCount(Object cond) {
		EmployeeMapper mapper = ApplicationContextUtil.getMapper(EmployeeMapper.class);
		return mapper.listCount((EmployeeSearchBean)cond);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Object> getListData(Object cond) {
		EmployeeMapper mapper = ApplicationContextUtil.getMapper(EmployeeMapper.class);
		return (List)mapper.list((EmployeeSearchBean)cond);
	}
	
	
	/**
	 * 修改表单负责人
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="employee_service")
	@RequestMapping(params = "operate=employee_service")
	public void employee_service(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		EmployeeMapper mapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		String tab[]={"hjjorder","hjjorderbuy","hjjorderbuydetail","hjjordersale","hjjorderservice","hjjplan","hjjplansale","hjjplanservice","hjjrequire","hjjrequiredetail"};
		if(bean.getEmployid()==null){
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "请选择修改的职员"));
			return;
		}
		if(bean.getTablename()!=null  && Arrays.asList(tab).contains(bean.getTablename())){//主表
			bean.setTablename(bean.getTablename());
			int result = mapper.updateSevrice(bean);
			if(result>0){
				if(bean.getTablename2()!=null && Arrays.asList(tab).contains(bean.getTablename2()) && bean.getParentidname()!=null){//修改子表的
					mapper.updateSevriceChild(bean);
					ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
					return;
				}
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败或只能修改自己的表单"));
				return;
			}
			
			
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
			return;
		}
		
	}
	
	/**
	 * 获得可以修改负责人的角色
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=getemployees")
	public void getemployees(HttpServletRequest request, HttpServletResponse response,EmployeeSearchBean bean) {
		EmployeeMapper employeemapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeSearchBean eBean = new EmployeeSearchBean();
		eBean.setStatus(1);
		eBean.setLimitFlag(false);
		List<EmployeeSearchBean> employees = employeemapper.list(eBean);
		ModuleMapper moduleMapper =ApplicationContextUtil.getMapper(ModuleMapper.class);
		List<ModuleBean> moduleList=null;
		for (int j = 0; j < employees.size(); j++) {
			HashMap<String, String> moduleMap = new HashMap<String,String>();
			eBean=employees.get(j);
			if(eBean.getRoleids()==null||"".equals(eBean.getRoleids())){
				moduleList=null;
			}else{
				moduleList = moduleMapper.getModule(eBean.getRoleids().substring(1, eBean.getRoleids().length()-1));
			}
			if(moduleList!=null){
				for (ModuleBean moduleBean : moduleList) {
					moduleMap.put(moduleBean.getIdstr(), "");
				}
			}
			if(!moduleMap.containsKey(bean.getIdstr())){//无权限
				employees.remove(j);
				j--;
			}		
		}
		ServletUtil.sendJsonBean(response, new MsgBean("0", "",employees));
	}
}
