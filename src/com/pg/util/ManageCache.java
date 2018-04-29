package com.pg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pg.bean.MessageBean;
import com.pg.mapper.EmployeeMapper;
import com.pg.mapper.MessageMapper;
import com.pg.mapper.ParamvalueMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.MessageSearchBean;
import com.pg.searchbean.ParamvalueSearchBean;
/**
 * 
 * 缓存管理
 * @author Administrator
 *
 */
public class ManageCache {

	
	/**
	 * 更新消息
	 * @return
	 */
	public static HashMap<Integer, List<MessageBean>>   messageMap = new HashMap();//消息提醒： 职员ID,未读消息list
	public static  void reflashMessage() {
		//职员
		EmployeeMapper employeemapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
		EmployeeSearchBean eBean = new EmployeeSearchBean();
		eBean.setLimitFlag(false);
		eBean.setStatus(1);
		List<EmployeeSearchBean> employees = employeemapper.list(eBean);
		//消息		
		MessageSearchBean bean =  new MessageSearchBean();
    	MessageMapper mapper =ApplicationContextUtil.getMapper(MessageMapper.class);
    	bean.setLimitFlag(true);
    	bean.setStatus(0);
    	bean.setLimitStart(0);
    	bean.setLimitSize(3);//暫時每个角色放3条最新的消息就够了。
//		MessageBean mBean = new MessageBean(); 
		List<MessageBean> list=new ArrayList<>();
		HashMap<Integer, List<MessageBean>>   messageMap2 = new HashMap();
		for (int j = 0; j < employees.size(); j++) {
//			List<MessageBean> list2  =   new ArrayList<>();
			eBean = employees.get(j);
			list=mapper.list(bean);
			/*for (int i = 0,count=0; i < list.size(); i++) {

				mBean = list.get(i);
				if(eBean.getId()==mBean.getEmployeeid()){
					count++;
					list2.add(mBean);
					if(count==3){//每个角色放3条最新的消息就够了。假如需要统计数目，则屏蔽即可
						break;
					}
				}
			}*/
			messageMap2.put(eBean.getId(), list);
		}
		messageMap=messageMap2;
//		System.out.println(messageMap);
	}
	
	
	/**
	 * 更新某个职员消息
	 * @return
	 */
	public static  void reflashMessage(Integer employid) {
		MessageSearchBean bean =  new MessageSearchBean();
    	MessageMapper mapper =ApplicationContextUtil.getMapper(MessageMapper.class);
//    	bean.setLimitFlag(false);//每个角色放N条最新的消息就够了。。假如需要统计数目，则屏蔽即可
    	bean.setStatus(0);
    	bean.setEmployeeid(employid);
		MessageBean mBean = new MessageBean(); 
		List<MessageBean> list=mapper.list(bean);
		messageMap.put(employid, list);
	}
	
	
	/**
	 * 根据request获得自己的消息
	 * @return
	 */
	public static List<MessageBean> getMessage(HttpServletRequest request) {
		return messageMap.get(Check.getEmployee(request).getId());
	}
	
	
	
	
	/**
	 * 参数表缓存
	 */
	public static HashMap<String, List<ParamvalueSearchBean> >   parammap = new HashMap();//tpye,bean
	public static Boolean   paramupdate =false;//是否需要更新:true时候需要更新
	public static  List<ParamvalueSearchBean> getparamByType(String type) {
		if(paramupdate){
			HashMap<String, List<ParamvalueSearchBean> >   parammap2 = new HashMap();
			ParamvalueMapper mapper =ApplicationContextUtil.getMapper(ParamvalueMapper.class);
			ParamvalueSearchBean bean = new ParamvalueSearchBean();
			bean.setLimitFlag(false);
			bean.setStatus(1);
			List<ParamvalueSearchBean> paramlist = mapper.list(bean);
			List<ParamvalueSearchBean> typelist =  mapper.getAllTpye();
			
			for (int i = 0; i < typelist.size(); i++) {
				String typekey = typelist.get(i).getType();
				List<ParamvalueSearchBean> list =  new ArrayList<>();
				for (int j = 0; j < paramlist.size(); j++) {
					if(typekey.equals(paramlist.get(j).getType())){
						list.add(paramlist.get(j));
					}
				}
				parammap2.put(typekey, list);
			}
			paramupdate =false;
			parammap=parammap2;
		}
		
		return parammap.get(type);
	
	}

	
}
