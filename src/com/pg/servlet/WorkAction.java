package com.pg.servlet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.F_departmentBean;
import com.pg.bean.ModuleBean;
import com.pg.mapper.FlowNodeMapper;
import com.pg.mapper.P_eventlogMapper;
import com.pg.mapper.WorkMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.searchbean.WorkSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Tools;
@Controller
@RequestMapping("work.htm")
public class WorkAction extends BaseAction
{
	/**
	 * 我的工作列表
	 * @param request
	 * @param response
	 * @param bean
	 * @author dfq
	 * @return
	 */
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,WorkSearchBean bean) {
		//基础资料审核
		WorkMapper mapper = ApplicationContextUtil.getMapper(WorkMapper.class);
		bean.setLimitFlag(false);
		List<WorkSearchBean> list = mapper.list(bean);
		List<WorkSearchBean> worklist = new ArrayList<>();
		if(list!=null){
			HashMap<String, ModuleBean> moduleMap =Check.getModule(request);
			for (WorkSearchBean workSearchBean : list) {
				if(moduleMap.get(workSearchBean.getIdstr()) != null || "commonrule".equals(workSearchBean.getIdstr())){
					workSearchBean.setEmployid_base(Check.getEmployee(request).getId());
					workSearchBean.setWorkcount(mapper.listCount(workSearchBean));
					worklist.add(workSearchBean);
				}
			}
		}
		request.setAttribute("list", worklist);
		
		//工作流审核
		FlowNodeSearchBean flowNodeSearchBean = new FlowNodeSearchBean();
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		List<FlowNodeSearchBean>  nodelist = Check.getFlowNode(request);
		List<FlowNodeSearchBean>  flownodelist =  new ArrayList<FlowNodeSearchBean>();
		List<HashMap>  list2 =  new ArrayList<HashMap>();
		/*for (int i = 0; i < nodelist.size(); i++) {
			flowNodeSearchBean = (FlowNodeSearchBean) nodelist.get(i);
			list2 = flowNodeMapper.getCounts(flowNodeSearchBean.getTablename(),flowNodeSearchBean.getId());
			int count=list2.size();
			for (int j = 0; j < list2.size(); j++) {////待采购的只在采购负责人显示
				if("4".equals(list2.get(j).get("flowid").toString()) || "5".equals(list2.get(j).get("flowid").toString())){//4.5 为 销售订单和服务订单表
					Integer buyerpersonid = Integer.valueOf(list2.get(j).get("buyerpersonid").toString());
					Integer status = Integer.valueOf(list2.get(j).get("status").toString());
					if(status==Dict.ORDER_6){//待采购的
						if(buyerpersonid!=Check.getEmployee(request).getId()){
							count--;continue;
						}
						//已经做过采购单“非草稿中”的也去掉--销售订单 服务订单
						OrderbuydetailMapper OrderbuydetailMapper = ApplicationContextUtil.getMapper(OrderbuydetailMapper.class);
						OrderbuydetailSearchBean orderbuySearchBean = new OrderbuydetailSearchBean();
						orderbuySearchBean.setPlansaleid(Integer.valueOf(list2.get(j).get("id").toString()));
						if(list2.get(j).get("code").toString().contains("SA")){
							orderbuySearchBean.setType("2");
						}else{
							orderbuySearchBean.setType("3");
						}
						if(OrderbuydetailMapper.statusCount(orderbuySearchBean)>0){
							count--;
						}
					}
					   
				}
			}
			flowNodeSearchBean.setCount(count);
			//替换状态  help.htm?operate=list&status=#{statusnow}&type=3
			Map<String, Object> map = Tools.changeBeanToMap(flowNodeSearchBean);
			map.put("buyerpersonid", Check.getEmployee(request).getId());
			String valueafter =Tools.formatContent(flowNodeSearchBean.getLinkurl(),map);//替换值
			flowNodeSearchBean.setLinkurl(valueafter);
			flownodelist.add(flowNodeSearchBean);
		}*/
		request.setAttribute("flownodelist", flownodelist);
		
		P_eventlogMapper eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		request.setAttribute("status1", eventlogMapper.CountStatus(1));
		request.setAttribute("status2", eventlogMapper.CountStatus(2));
		request.setAttribute("status3", eventlogMapper.CountStatus(3));
		
		Date d = new Date();  
        String day = new SimpleDateFormat("yyyy-MM-dd").format(d);  
        String month = new SimpleDateFormat("yyyy-MM").format(d);  
        
		if (Check.getEmployee(request).getDepartmentBean()!=null) {
			F_departmentBean departmentBean = Check.getEmployee(request).getDepartmentBean();
			if (departmentBean.getType()==0) {
				request.setAttribute("day", eventlogMapper.CountNum(day));
				request.setAttribute("month", eventlogMapper.CountNum(month));
				request.setAttribute("all", eventlogMapper.CountNum(""));
			}
			if (departmentBean.getType()==1) {
				request.setAttribute("day", eventlogMapper.CountDepartment1(departmentBean.getId(),day));
				request.setAttribute("month", eventlogMapper.CountDepartment1(departmentBean.getId(),month));
				request.setAttribute("all", eventlogMapper.CountDepartment1(departmentBean.getId(),""));
			}
			if (departmentBean.getType()==2) {
				request.setAttribute("day", eventlogMapper.CountDepartment2(departmentBean.getId(),day));
				request.setAttribute("month", eventlogMapper.CountDepartment2(departmentBean.getId(),month));
				request.setAttribute("all", eventlogMapper.CountDepartment2(departmentBean.getId(),""));
			}
		}
		if (Check.getEmployee(request).getWorkuserBean()!=null) {
			request.setAttribute("day", eventlogMapper.CountWorkuserid(Check.getEmployee(request).getWorkuserBean().getId(),day));
			request.setAttribute("month", eventlogMapper.CountWorkuserid(Check.getEmployee(request).getWorkuserBean().getId(),month));
			request.setAttribute("all", eventlogMapper.CountWorkuserid(Check.getEmployee(request).getWorkuserBean().getId(),""));
		}
		
		
		return "welcome.jsp";
	}
	@Override
 	protected Integer getListCount(Object cond)
	{
		WorkMapper mapper = ApplicationContextUtil.getMapper(WorkMapper.class);
		return mapper.listCount((WorkSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		WorkMapper mapper = ApplicationContextUtil.getMapper(WorkMapper.class);
		 return (List)mapper.list((WorkSearchBean)cond);
	}

}