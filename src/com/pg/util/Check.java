package com.pg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.pg.bean.BaseBean;
import com.pg.bean.EmployeeBean;
import com.pg.bean.ModuleBean;
import com.pg.bean.MsgBean;
import com.pg.mapper.FlowNodeMapper;
import com.pg.mapper.ModuleMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.FlowNodeSearchBean;


/**
 * 检查登陆，权限
 * @author Administrator
 *
 */
@Component
@Aspect
public class Check {	
	private static final Logger log = Logger.getLogger(Check.class);
	@Around(value="@annotation(anno) && args(request,response,bean,..) ",argNames="module,anno,request,response,bean")
	public Object cut(ProceedingJoinPoint pjp,HttpAnno anno,HttpServletRequest request,HttpServletResponse response,BaseBean bean) throws Throwable
	{
		debuglog(request,bean);//debug时候打印的一些后台日志
		//检查是否登录
 		EmployeeBean employee =getEmployee(request);
 		request.setAttribute("employee_local", employee);
		if(employee==null){		
			if(anno.value().equals(HttpAnno.AJAX)){
				ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));//ajax提交的返回未登录messagebean
				return null;
			}
			else{
				request.setAttribute("message", "<script>parent.goLoginPage()</script>");
				return "message.jsp";
			}
		}
		//检查是否被登录
/*		String currentSeesionId= request.getSession().getId();
		if(!currentSeesionId.equals(Config.userloginMap.get(employee.getLoginname()))){//其他地方登录了
//			ServletUtil.sendJsonBean(response, new MsgBean("-1000","该账户已在其他地方登录，请重新登录"));//ajax提交的返回未登录messagebean
			request.setAttribute("message", "该账户已在其他地方登录，请重新登录");
			return "message.jsp";
		}*/
		//检查是否为初始密码：123456
		/*if ("123456".equals(Crypto.decrypt(employee.getPassword()))) {
			request.setAttribute("loginname", employee.getLoginname());
			request.setAttribute("message", "请修改初始密码！");
			return "employeemodify.jsp";
		}*/
		
		request.getSession().setAttribute("imageRootPath", Config.getValue(Config.CONFIG_IMAGEROOTPATH));//图片根路径
		//节点审核的查看。。根据节点，找到状态  用户有待客户确认 审核权限，但是没有查看其他状态的权限、。。
		HashMap<String, FlowNodeSearchBean> flownodemap = getFlowNodeMap(request);
		FlowNodeSearchBean flowNodeSearchBean =flownodemap.get(request.getParameter("flownodeid"));
		if(flowNodeSearchBean!=null){
			StringBuilder  selectsql=new StringBuilder();
			flowNodeSearchBean.getStatusnow();
			selectsql.append("or status="+flowNodeSearchBean.getStatusnow());
			if(selectsql.length()>0){
				bean.setSelectsql(" and (1!=1 "+selectsql.toString()+")");
			}
			return pjp.proceed();
		}
		//公用权限。无权限的都使用这个，只检查是否登录
		if(!anno.module().equals("commonrule.htm")){
			boolean norule =  true ;
			if(anno.module()!=null&&anno.module().length()>0){
				String moduleString = anno.module();
				String [] modules = moduleString.split(",");
				HashMap<String, ModuleBean> moduleMap =getModule(request);
				StringBuilder  selectsql=new StringBuilder();
				StringBuilder  updatesql=new StringBuilder();
				if(moduleMap!=null){
					for (int i = 0; i < modules.length; i++) {
						ModuleBean moduleBean = moduleMap.get(modules[i]);
						if(moduleBean!=null){
							norule=false;//有权限
							if(moduleBean.getSelectsql()!=null&&!moduleBean.getSelectsql().equals("")){
								selectsql.append(" or (" + moduleBean.getSelectsql()+") ");
							}
							if(moduleBean.getUpdatesql()!=null&&!moduleBean.getUpdatesql().equals("")){
								updatesql.append(" or (" + moduleBean.getUpdatesql()+") ");
							}
						}
					}
					
				}			
				if(norule){//一个权限都没有
					//写操作日志
					if(anno.log().equals(HttpAnno.WILLLOG)){
						Log.addLog(modules[0], "无权限操作", employee.getLoginname(), request,"","");
					}
					if(anno.value().equals(HttpAnno.AJAX)){
						ServletUtil.sendJsonBean(response, new MsgBean("-1001","对不起，您没权限进行此操作"));//ajax提交的返回无权限messagebean
						return null;
					}else{
						request.setAttribute("message", "<script>tip('对不起，您没权限进行此操作')</script>");
						return "message.jsp";
					}
				}else{
					//写操作日志
					if(anno.log().equals(HttpAnno.WILLLOG)){
						String valueString = Tools.changeBeanToString(bean, moduleMap.get(modules[0]).getLogname());
						Log.addLog(modules[0], moduleMap.get(modules[0]).getDescription(), employee.getLoginname(), request,valueString,bean.getIds()==null?null:bean.getIds().toString());
					}
					//添加sql的操作条件  合并多个权限的 modulesql
					if(updatesql.length()>0){
						bean.setUpdatesql("and (1!=1"+updatesql.toString()+")");
					}
					if(selectsql.length()>0){
						bean.setSelectsql("and (1!=1"+selectsql.toString()+")");
					}
					bean.setEmployid_base(getEmployee(request).getId());
				}
			}
		}
		//执行操作
		return pjp.proceed();
	}

	/**
	 * 设置职员和店信息
	 * @param request
	 * @param bean
	 */
	public static void setEmployee(HttpServletRequest request,EmployeeSearchBean bean) {
		request.getSession().setAttribute("employee",bean);
	}
	/**
	 * 获取登录职员信息
	 * @param request
	 * @return
	 */
	public static EmployeeSearchBean getEmployee(HttpServletRequest request) {
		return (EmployeeSearchBean) request.getSession().getAttribute("employee");
	}
	/**
	 * 获取模块信息信息
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, ModuleBean> getModule(HttpServletRequest request) {
		return (HashMap<String, ModuleBean>) request.getSession().getAttribute("module");
	}
	/**
	 * 清除登录职员信息
	 * @param request
	 */
	public static void clearEmployee(HttpServletRequest request) {
		request.getSession().removeAttribute("employee");
	}
	/**
	 * 加载权限
	 * @param request
	 * @param userid
	 */
	public static void loadModule(HttpServletRequest request,final EmployeeBean bean){
		List<ModuleBean> moduleList=null;
		ModuleMapper moduleMapper =ApplicationContextUtil.getMapper(ModuleMapper.class);
		if(bean.getLoginname().equals("admin")){
			moduleList = moduleMapper.getAllModule();
		}else{
			if(bean.getRoleids()==null||"".equals(bean.getRoleids())){
				moduleList=null;
			}else{
				moduleList = moduleMapper.getModule(bean.getRoleids());
			}
		}
		HashMap<String, ModuleBean> moduleMap = new HashMap<String,ModuleBean>();
		if(moduleList!=null){
			for (ModuleBean moduleBean : moduleList) {
				moduleMap.put(moduleBean.getIdstr(), moduleBean);
			}
		}
		request.getSession().setAttribute("module",moduleMap);
	}
	/**
	 * 根据模块判断是否有权限
	 * @param request
	 * @param module
	 * @return
	 */
	public static boolean checkMoudle(HttpServletRequest request,String module){
		HashMap<String, ModuleBean> moduleMap =getModule(request);
		if(moduleMap!=null&&moduleMap.get(module)!=null){
			return true;
		}
		return false;
	}
	/**
	 * 字符串数组包含字符串
	 * @param arr
	 * @param targetValue
	 * @return
	 */
	public static boolean useLoop(String[] arr, String targetValue) {
	    for(String s: arr){
	        if(s.equals(targetValue))
	            return true;
	    }
	    return false;
	}
	/**
	 * 根据（角色ID或职员id）+ 权限 + isaudit为1  去查询可以做的工作流节点
	 * @param request
	 * @param userid
	 */
	public static void loadFlowNode(HttpServletRequest request, EmployeeBean bean){
		//全部查出
		FlowNodeMapper mapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean flowNodeSearchBean =  new  FlowNodeSearchBean();
		flowNodeSearchBean.setIsaudits(" t.isaudit!=0 ");
		flowNodeSearchBean.setLimitFlag(false);
		List<FlowNodeSearchBean> flownodelist = mapper.list(flowNodeSearchBean);//有权限的节点
		
		flowNodeSearchBean.setIsaudits(null);
		List<FlowNodeSearchBean> flownodelist2 = mapper.list(flowNodeSearchBean);//所有节点
		List<FlowNodeSearchBean> list = new ArrayList<>();
		String roleids = bean.getRoleids();
		String employeeid = null; 
		String roles[] = null; 
		String role = null; 
	//	String flowidstrs[] = null;
	//	String flowidstr = null;
	//	HashMap<String, ModuleBean> moduleMap =Check.getModule(request);
		HashMap<String, FlowNodeSearchBean> flownodemap  =  new HashMap<String, FlowNodeSearchBean>();
		HashMap<String, FlowNodeSearchBean> flownodebyStaAndTableAndId  =  new HashMap<String, FlowNodeSearchBean>();
		request.getSession().setAttribute("flownodelist",flownodelist);
		request.getSession().setAttribute("flownodelistall",flownodelist2);
		request.getSession().setAttribute("flownodemap",flownodemap);
		request.getSession().setAttribute("flownodebyStaAndTableAndId",flownodebyStaAndTableAndId);//所有节点
		for (int i = 0; i < flownodelist2.size(); i++) {
			flowNodeSearchBean = flownodelist2.get(i);
			flownodebyStaAndTableAndId.put(flowNodeSearchBean.getTablename()+flowNodeSearchBean.getStatusnow(), flowNodeSearchBean);
			flownodebyStaAndTableAndId.put(flowNodeSearchBean.getId().toString(), flowNodeSearchBean);
		}
		if(bean.getLoginname().equals("admin")){
			for (int i = 0; i < flownodelist.size(); i++) {
				flowNodeSearchBean = flownodelist.get(i);
				flownodemap.put(flowNodeSearchBean.getId().toString(), flowNodeSearchBean);
			}
			return;
		}
		//过滤取出有符合条件的
		for (int i = 0; i < flownodelist.size(); i++) {
			flowNodeSearchBean = flownodelist.get(i);
			employeeid = flowNodeSearchBean.getEmployeeid();
			role = flowNodeSearchBean.getRoleid();
//			flowidstr = flowNodeSearchBean.getFlowidstr();
			if(employeeid!=null && employeeid!=""){
				if(useLoop(employeeid.split(","), bean.getId().toString())){//包含
					list.add(flowNodeSearchBean);
					flownodemap.put(flowNodeSearchBean.getId().toString(), flowNodeSearchBean);
					continue;
				}
			} 
			
			if(role!=null && role!="" && roleids!=null && roleids !=""){
				roles =  role.split(",");
				for (int j = 0; j < roles.length; j++) {
					if(roleids.indexOf(","+roles[j]+",") != -1)  {
						list.add(flowNodeSearchBean);
						flownodemap.put(flowNodeSearchBean.getId().toString(), flowNodeSearchBean);
						continue;
					}
				}
				
			} 
//			if(flowidstr!=null && flowidstr!="" && moduleMap!=null){
//				flowidstrs =  flowidstr.split(",");
//				for (int j = 0; j < flowidstrs.length; j++) {
//					if(moduleMap.containsKey(flowidstrs[j])){
//						list.add(flowNodeSearchBean);
//						continue;
//					}
//				}
//			} 
		}
		request.getSession().setAttribute("flownodelist",list);
	}
	/**
	 * 获取工作流节点
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<FlowNodeSearchBean> getFlowNode(HttpServletRequest request) {
		return (List<FlowNodeSearchBean>) request.getSession().getAttribute("flownodelist");
	}		
	/**
	 * 获取所有工作流节点
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<FlowNodeSearchBean> getFlowNodeAll(HttpServletRequest request) {
		return (List<FlowNodeSearchBean>) request.getSession().getAttribute("flownodelistall");
	}
	/**
	 * 获取工作流节点
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, FlowNodeSearchBean> getFlowNodeMap(HttpServletRequest request) {
		return  (HashMap<String, FlowNodeSearchBean>) request.getSession().getAttribute("flownodemap");
	}
	/**
	 * 获取工作流节点:所有节点！！！
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, FlowNodeSearchBean> getFlownodebyStaAndTableAndId(HttpServletRequest request) {
		return  (HashMap<String, FlowNodeSearchBean>) request.getSession().getAttribute("flownodebyStaAndTableAndId");
	}
	
	
	private void debuglog(HttpServletRequest request,BaseBean bean) {
		
		try {
			log.debug("-------------------RequestURL : "+request.getRequestURL()+"?"+request.getQueryString());//URL
	 		Map<String, String[]> map = request.getParameterMap();
	 		Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
	 		String parameter="{";
	 		while (iter.hasNext()){
	 			Entry<String, String[]> e = (Entry) iter.next();
		 		String key = (String) e.getKey();
		        String  value[] = e.getValue();
		        parameter=parameter+key + ":" + value[0]+";";
	 		}
	 		log.debug("-------------------parameters : "+parameter+"}");//参数
//	 		log.debug("-------------------bean : "+Tools.changeBeanToMap(bean));//bean
	 		
		} catch (Exception e) {
			log.debug("-------------------Check.debuglog error");
		}
	}
	

	
	
	
}
