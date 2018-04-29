package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.F_departmentBean;
import com.pg.bean.H_workuserBean;
import com.pg.bean.MsgBean;
import com.pg.gzhqy.message.Message;
import com.pg.mapper.EventMapper;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.mapper.P_eventlogMapper;
import com.pg.searchbean.EventSearchBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.searchbean.P_eventlogSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Config;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
@Controller
@RequestMapping("event.htm")
public class EventAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="event_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
	bean.setDeptid(Check.getEmployee(request).getDepartmentid());
	List<Object> list = super.getList(request, bean).getList();
	for (Object object : list) {
		EventSearchBean eventSearchBean = (EventSearchBean)object;
		if(eventSearchBean.getNext_id()!=null&&eventSearchBean.getNext_id().intValue()==bean.getDeptid().intValue()){
			eventSearchBean.setFlag(true);
		}
		if(eventSearchBean.getNext()!=null&& eventSearchBean.getNext()==2){//巡查员--上一级是街道办可以操作
	    	H_workuserMapper H_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
	    	H_workuserSearchBean h_workuserSearchBean = H_workuserMapper.getById(eventSearchBean.getNext_id());
	    	if(h_workuserSearchBean!=null && h_workuserSearchBean.getDeparttype()==3){//上级部门是街道村社，再上级的街道可以操作
		    	if(h_workuserSearchBean.getDepartparentid()!=null&& h_workuserSearchBean.getDepartparentid().intValue()==Check.getEmployee(request).getDepartmentid().intValue()){
		    		eventSearchBean.setFlag(true);
		    	}
	    	}
	    	if(h_workuserSearchBean!=null && h_workuserSearchBean.getDeparttype()==2){//上级部门是街道的，这个街道可以操作
		    	if(h_workuserSearchBean.getDepartmentid()!=null&&h_workuserSearchBean.getDepartmentid().intValue()==Check.getEmployee(request).getDepartmentid().intValue()){
		    		eventSearchBean.setFlag(true);
		    	}
	    		
	    	}
			
		}
		if(Check.getEmployee(request).getDepartmentid().intValue()==-1){//消防总队可以处理所有案件
			eventSearchBean.setFlag(true);
		}
	}
    F_departmentBean f_departmentBean = Check.getEmployee(request).getDepartmentBean();
    H_workuserBean h_workuserBean =Check.getEmployee(request).getWorkuserBean();
    request.setAttribute("depylv", Check.getEmployee(request).getDepylv());// 0总队 1支队2街道办 3村社 99巡查员  null--都不是
    if(f_departmentBean!=null) {
    	H_workuserMapper H_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
    	H_workuserSearchBean H_workuserSearchBean =  new  H_workuserSearchBean();
    	H_workuserSearchBean.setDepartmentid(f_departmentBean.getId());
    	request.setAttribute("workuserlist", H_workuserMapper.list(H_workuserSearchBean));  //所有子部门
    }
    
    if(h_workuserBean!=null){//巡查员
    	H_workuserMapper H_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
    	H_workuserSearchBean H_workuserSearchBean =  new  H_workuserSearchBean();
    	H_workuserSearchBean.setStatus(1);
    	H_workuserSearchBean.setDepartmentid(h_workuserBean.getDepartmentid());
    	H_workuserSearchBean.setLimitFlag(false);
    	request.setAttribute("workuserlist", H_workuserMapper.list(H_workuserSearchBean));  //部门下的所有消防员
    }
    
	
    F_departmentMapper f_departmentMapper = ApplicationContextUtil.getMapper(F_departmentMapper.class);
    F_departmentSearchBean f_departmentSearchBean = new F_departmentSearchBean();
    f_departmentSearchBean.setLimitFlag(false);
//    System.out.println("-- f_departmentMapper.list(f_departmentSearchBean)-"+ f_departmentMapper.list(f_departmentSearchBean).size());
    f_departmentSearchBean.setParentid(Check.getEmployee(request).getDepartmentid());
    request.setAttribute("deptlistson", f_departmentMapper.list(f_departmentSearchBean));  //所有子部门
    if(f_departmentBean!=null&&f_departmentBean.getParentid()!=null){
    	f_departmentSearchBean.setParentid(f_departmentBean.getParentid());
    	request.setAttribute("siblinglist", f_departmentMapper.list(f_departmentSearchBean));  //所有同级部门
    }
    request.setAttribute("departmentid", Check.getEmployee(request).getDepartmentid());
    return "eventlist.jsp";
  }
  /**
   * 获取部门数据
   */
  @HttpAnno(value=HttpAnno.VIEW,module="event_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=deptlist")
  public void deptlist(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
	  ServletUtil.sendJsonBean(response, new MsgBean(ApplicationContextUtil.getMapper(F_departmentMapper.class).list(bean)));
  }
  /**
   * 获取部门消防员数据
   */
  @HttpAnno(value=HttpAnno.VIEW,module="event_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=userlist")
  public void userlist(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
	  ServletUtil.sendJsonBean(response, new MsgBean(ApplicationContextUtil.getMapper(H_workuserMapper.class).list(bean)));
  }
  /**
   * 删除
   */
  @HttpAnno(module="event_delete")
  @RequestMapping(params = "operate=delete")
  public void delete(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
    EventMapper mapper =ApplicationContextUtil.getMapper(EventMapper.class);
    int result=mapper.delEvent(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("1", "删除失败"));
      return;
    }
  }
  /**
   * 指派
   */
  @HttpAnno(module="event_update")
  @RequestMapping(params = "operate=zhipai")
  public void zhipai(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
	  Integer depylv = Check.getEmployee(request).getDepylv();// 0总队 1支队2街道办4巡查员  null--都不是
	  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
	  if(depylv==0){//指派给支队或者街道  setDepartment1支队  setDepartment2 街道办
		  bean.setStatus(1);//3已反馈待处理 
		  bean.setWorkuserid(null);//巡查员不需要
		  bean.setNext(1);
		  if(bean.getDepartment2()==null){
			  bean.setDeal_opinion("消防总队指派给消防支队处理: "+bean.getDeal_opinion());
			  bean.setNext_id(bean.getDepartment1());
		  }else{
			  bean.setDeal_opinion("消防总队指派给街道办处理: "+bean.getDeal_opinion());
			  bean.setDepartment1(null);//支队不需要
			  bean.setNext_id(bean.getDepartment2());
		  }
	  }else if(depylv==1){//指派给街道办
		  bean.setStatus(1);  //3已反馈待处理 
		  bean.setDeal_opinion("消防支队指派给街道办处理："+bean.getDeal_opinion());
		  bean.setWorkuserid(null);//巡查员不需要
		  bean.setDepartment1(null);//支队不需要
		  bean.setNext(1);
		  bean.setNext_id(bean.getDepartment2());
	  }else if(depylv==2){//指派给巡查员
		  bean.setStatus(2);  // 2已分拨待反馈
		  bean.setDepartment1(null);//支队不需要
		  bean.setDepartment2(Check.getEmployee(request).getDepartmentid());//指派给巡防员街道办要看得到
		  bean.setDeal_opinion("街道办指派给消防员处理："+bean.getDeal_opinion());
		  bean.setNext(2);
		  bean.setNext_id(bean.getWorkuserid());
	  }else{
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "非法请求"));
		  return;
	  }
	  bean.setDeal(1);//当前处理角色：0系统分拨 1部门处理(employeeid)2巡查员处理（h_workuser）
	  bean.setDealid(Check.getEmployee(request).getDepartmentBean().getId());
	  if(p_eventlogMapper.addP_eventlog(bean)>0){
			if(depylv==2){
				EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
				EventSearchBean eventSearchBean =  new EventSearchBean();
				eventSearchBean.setId(bean.getEventid());
				eventSearchBean = mapper.getById(eventSearchBean);
				
				H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
			  	H_workuserSearchBean h_workuserSearchBean = new H_workuserSearchBean();
			  	h_workuserSearchBean.setId(bean.getWorkuserid());
			  	h_workuserSearchBean=h_workuserMapper.getById(h_workuserSearchBean);
			  	String key= Config.getQyWxKey();
			  	Message message = new Message(key);
			  	message.textSingle(h_workuserSearchBean.getCode(), "", "您收到了一条新的案件。\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+bean.getEventid()+"\">点击查看详情</a>", 1000002); 
			}
		    ServletUtil.sendJsonBean(response, new MsgBean("0", "指派成功"));
		    return;
	  }else{
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "系统错误"));
		  return;
	  }
  }
  
  /**
   * 案件详情
   */
  @HttpAnno(module="event_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=detail" )
  public void detail(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper p_eventlogMapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    List<P_eventlogSearchBean> detail = p_eventlogMapper.detail(bean);
    ServletUtil.sendJsonBean(response, new MsgBean(detail));
  }
  
  /**
   * 批量删除
   */
  @HttpAnno(module="event_delete")
  @RequestMapping(params = "operate=deletemany")
  public void deletemany(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
    EventMapper mapper =ApplicationContextUtil.getMapper(EventMapper.class);
    int result=mapper.delManyEvent(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("1", "删除失败"));
      return;
    }
  }
  /**
   * 修改前
   */
  @HttpAnno(module="event_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
    EventMapper mapper =ApplicationContextUtil.getMapper(EventMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
  /**
   * 修改
   */
//  @HttpAnno(module="event_update")
//  @RequestMapping(params = "operate=update")
//  public void update(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
//    EventMapper mapper =ApplicationContextUtil.getMapper(EventMapper.class);
//    EventSearchBean bean2 = mapper.getById(bean);
//    if (bean2!=null) {
//       int result=mapper.updateEvent(bean);
//       if(result>0){
//          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
//          return;
//        }else{
//          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
//          return;
//        }
//    }else{
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "记录不存在"));
//      return;
//    }
//  }
  /**
   * 批量修改
   */
//  @HttpAnno(module="event_update")
//  @RequestMapping(params = "operate=_updatemany")
//  public void updatemany(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
//   EventMapper mapper =ApplicationContextUtil.getMapper(EventMapper.class);
//     int result=mapper.updateManyEvent(bean);
//     if(result>0){
//        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
//        return;
//      }else{
//        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
//        return;
//      }
//  }
  
  /**
   *  返回给上一级
   */
  @HttpAnno(module="event_update")
  @RequestMapping(params = "operate=uphandle")
  public void uphandle(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
	  Integer depylv = Check.getEmployee(request).getDepylv();// 0总队 1支队2街道办3社区99巡查员  null--都不是
		 if(depylv==null||depylv==0){
			  ServletUtil.sendJsonBean(response, new MsgBean("-1", "非法请求"));
			  return;
		 } 
		 if(depylv==99){//巡查员
			  ServletUtil.sendJsonBean(response, new MsgBean("-1", "巡查员请在手机端操作"));
			  return;
			 /*H_workuserSearchBean workuserBean=	 Check.getEmployee(request).getWorkuserBean();
			  bean.setStatus(3);  // 3已反馈待处理
			  if(workuserBean.getDeparttype()==3){//上级是村社
				  bean.setDepartment1(null);//支队不需要
				  bean.setDepartment2(workuserBean.getDepartparentid());
			  }else if(workuserBean.getDeparttype()==2){//上级街道办
				  bean.setDepartment1(null);//支队不需要
				  bean.setDepartment2(workuserBean.getDepartmentid());//街道办
			  }else if(workuserBean.getDeparttype()==1){//上级支队
				  bean.setDepartment1(workuserBean.getDepartmentid());//街道办
				  bean.setDepartment2(null);//街道办不需要
			  }
			  bean.setWorkuserid(workuserBean.getId());
			  bean.setDeal(2);//当前处理角色：0系统分拨 1部门处理(employeeid)2巡查员处理（h_workuser）
			  if(bean.getNext_id()!=null && !"".equals(bean.getNext_id())){//移交给某个部门
				  bean.setDeal_opinion("巡查员移交给其他巡查员："+bean.getDeal_opinion());
				  bean.setNext(2);
				  bean.setNext_id(bean.getNext_id());
			  }else{
				  bean.setNext(1);
				  if(workuserBean.getDeparttype()==3){//上级是村社
					  bean.setNext_id(workuserBean.getDepartparentid());
				  }else {//上级街道办
					  bean.setNext_id(workuserBean.getDepartmentid());//街道办
				  }
				  bean.setDeal_opinion("巡查员移交街道办："+bean.getDeal_opinion());
			  }
			  bean.setDealid(workuserBean.getId());*/
			 
		 }else if(depylv==3){//社区
			  ServletUtil.sendJsonBean(response, new MsgBean("-1", "社区不能操作"));
			  return;
		 }else if(depylv==2){//街道办
			 F_departmentBean departmentBean =  Check.getEmployee(request).getDepartmentBean();
			 bean.setStatus(1);  // 3已反馈待处理
			 
			 bean.setDeal(1);//当前处理角色：0系统分拨 1部门处理(employeeid)2巡查员处理（h_workuser）
			 if(bean.getNext_id()!=null && !"".equals(bean.getNext_id())){//移交给某个部门
				  bean.setNext_id(bean.getNext_id());
				  bean.setDepartment2(bean.getNext_id());
				  bean.setDeal_opinion("街道办移交给其他街道办"+bean.getDeal_opinion());
			  }else{//移交给上级
				  bean.setDepartment1(departmentBean.getParentid());
			      bean.setNext_id(departmentBean.getParentid());
				  bean.setDeal_opinion("街道办移交消防支队："+bean.getDeal_opinion());
			  }
			 bean.setNext(1);
			 bean.setDealid(departmentBean.getId());
		 }else if(depylv==1){//支队
			 F_departmentBean departmentBean =  Check.getEmployee(request).getDepartmentBean();
			 bean.setStatus(1);  // 3已反馈待处理
			 bean.setDeal(1);//当前处理角色：0系统分拨 1部门处理(employeeid)2巡查员处理（h_workuser）
			 if(bean.getNext_id()!=null && !"".equals(bean.getNext_id())){
				  bean.setDepartment1(departmentBean.getId());
				  bean.setNext_id(bean.getNext_id());
				  bean.setDeal_opinion("消防支队移交给其他消防支队："+bean.getDeal_opinion());
			  }else{
//				  bean.setDeal_opinion("消防支队移交消防总队："+bean.getDeal_opinion());
				  ServletUtil.sendJsonBean(response, new MsgBean("-1", "支队不能移交给总队"));
				  return;
				  
			  }
			 bean.setNext(1);
			 bean.setDealid(departmentBean.getId());
		 }
		 if(request.getParameter("next_id")!=null){
			 bean.setNext_id(Integer.parseInt(request.getParameter("next_id")));
		 }
//		 System.out.println(bean.getDeal_file());
		P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
	    if(p_eventlogMapper.addP_eventlog(bean)>0){
		    ServletUtil.sendJsonBean(response, new MsgBean("0", "移交成功"));
		    return;
	  }else{
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "系统错误"));
		  return;
	  }
	  
  }
  
  /**
   *案件处理
   */
  @HttpAnno(module="event_update")
  @RequestMapping(params = "operate=handle")
  public void handle(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
	  Integer depylv = Check.getEmployee(request).getDepylv();// 0总队 1支队2街道办4巡查员  null--都不是
		 if(depylv==null||depylv==3||depylv==99){
			  ServletUtil.sendJsonBean(response, new MsgBean("-1", "非法请求(村社、巡查员不能处理案件)"));
			  return;
		 } 
		 if(bean.getStatus()!=6&&bean.getStatus()!=7&&bean.getStatus()!=8){
			  ServletUtil.sendJsonBean(response, new MsgBean("-1", "非法请求"));
			  return;
		 } 
		 
		F_departmentBean departmentBean =  Check.getEmployee(request).getDepartmentBean();
		bean.setStatus(bean.getStatus());  // 3已反馈待处理
		bean.setDeal(1);//当前处理角色：0系统分拨 1部门处理(employeeid)2巡查员处理（h_workuser）
		bean.setDeal_opinion(bean.getDeal_opinion());
		bean.setDealid(departmentBean.getId());
		if(depylv==1){//支队
			bean.setDepartment1(Check.getEmployee(request).getDepartmentid());
		}else if(depylv==2){//街道办
			bean.setDepartment2(Check.getEmployee(request).getDepartmentid());
		}
		P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
	    if(p_eventlogMapper.addP_eventlog(bean)>0){
	    	P_eventlogSearchBean bean2=new P_eventlogSearchBean();
	    	bean2.setEventid(bean.getEventid());
	    	bean2.setNext(2);
	    	List<P_eventlogSearchBean> list2 =p_eventlogMapper.listsendMessage(bean2);
	    	String key= Config.getQyWxKey();
	    	Message message = new Message(key);
	    	for (int i = 0; i < list2.size(); i++) {
	    		bean2=list2.get(i);
	    		H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
			  	H_workuserSearchBean h_workuserSearchBean = new H_workuserSearchBean();
			  	h_workuserSearchBean.setId(bean2.getNext_id());
			  	h_workuserSearchBean=h_workuserMapper.getById(h_workuserSearchBean);
			  	message.textSingle(h_workuserSearchBean.getCode(), "", "您处理过的案件已经结案处理。\n案件地址："+bean2.getP_address()+"\n案件内容："+bean2.getP_content()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+bean.getEventid()+"\">点击查看详情</a>", 1000002); 
	    	} 
	    	 //发送消息给小程序
	    	Tools.sendxcxMessagd(bean.getEventid());
		  	
		    ServletUtil.sendJsonBean(response, new MsgBean("0", "处理成功"));
		    return;
	  }else{
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "系统错误"));
		  return;
	  }
	  
  }
  
  
	@Override
 	protected Integer getListCount(Object cond)
	{
		EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
		return mapper.listCount((EventSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
		 return (List)mapper.list((EventSearchBean)cond);
	}

}