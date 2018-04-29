package com.pg.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pg.gzhqy.message.Message;
import com.pg.mapper.EventMapper;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.mapper.P_eventlogMapper;
import com.pg.searchbean.EventSearchBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.searchbean.P_eventlogSearchBean;
/**
 * 定时任务4
 * @author xcr
 */
public class QuartzProductAlert4 implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	try {
    	  synchronized (this) {
    	   	P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
        	List<P_eventlogSearchBean> list = p_eventlogMapper.getfenbolistThread();
        	H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
        	EventSearchBean eventSearchBean = new EventSearchBean();
        	EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
        	
        	Calendar timenow = Calendar.getInstance(); 
        	Calendar timenow1 = Calendar.getInstance(); 
        	timenow1.add(Calendar.HOUR_OF_DAY, -1); //前一小时
        	timenow.add(Calendar.MINUTE, -30); //前30分钟
        	
//        	timenow1.add(Calendar.HOUR_OF_DAY, -1); //前一小时
//        	timenow1.add(Calendar.MINUTE, -4); //前一小时
//        	timenow.add(Calendar.MINUTE, -2); //前30分钟
        	
        	for (P_eventlogSearchBean list2 : list) {
        		Timestamp   ts   =   list2.getCreatetime(); 
        		Calendar   cal   =   Calendar.getInstance(); 
        		cal.setTime(ts);
    			H_workuserSearchBean workuserSearchBean  = h_workuserMapper.getById(list2.getNext_id());
    			String key= Config.getQyWxKeyFenbo();
    		  	Message message = new Message(key);
    			eventSearchBean.setId(list2.getEventid());
    			eventSearchBean = mapper.getById(eventSearchBean);
        		if(list2.getOperate2()==0 && cal.compareTo(timenow)<0){ //未读且超过半小时
        			//发消息提醒
    				Calendar calendar =Calendar.getInstance();
    				Date date = calendar.getTime();
    				if(date.getHours()>=9&&date.getHours()<=18){
    					if(date.getHours()==9){
    						if(date.getMinutes()>=30){
    							message.textSingle(workuserSearchBean.getCode(), "", "您有一条未分拨案件,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailfenbo.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000003); 
    		        		  	list2.setOperate2(1);
    		    				p_eventlogMapper.updateNeedP_eventlog(list2);
    						}else{
    							message.textSingle(workuserSearchBean.getCode(), "", "您有一条未分拨案件,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailfenbo.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000003); 
    		        		  	list2.setOperate2(1);
    		    				p_eventlogMapper.updateNeedP_eventlog(list2);
    						}
    					}	
    				}
        			//发消息提醒
        		}else if(list2.getOperate2()==1 && cal.compareTo(timenow1)<0){ //发过消息且超过一小时
        			//发消息提醒并将事件移交给街道办
        			Calendar calendar =Calendar.getInstance();
    				Date date = calendar.getTime();
    				if(date.getHours()>=10&&date.getHours()<=18){
    					P_eventlogSearchBean eventlog = new P_eventlogSearchBean();
        				F_departmentMapper departmentMapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
        				F_departmentSearchBean f_departmentSearchBean = new F_departmentSearchBean();
        				f_departmentSearchBean.setId(workuserSearchBean.getDepartmentid());
        				f_departmentSearchBean = departmentMapper.getById(f_departmentSearchBean);
        				if(f_departmentSearchBean.getType()==3){//上级是村社的，更改为街道办
        					eventlog.setDepartment2(f_departmentSearchBean.getParentid());//
        					eventlog.setNext_id(f_departmentSearchBean.getParentid());
        				}else if(f_departmentSearchBean.getType()==2){//上级是街道办
        					eventlog.setDepartment2(workuserSearchBean.getDepartmentid());
        					eventlog.setNext_id(workuserSearchBean.getDepartmentid());
        				}else if(f_departmentSearchBean.getType()==1){
        					eventlog.setDepartment1(workuserSearchBean.getDepartmentid());//上级是支队
        					eventlog.setNext_id(workuserSearchBean.getDepartmentid());
        				}
        				eventlog.setEventid(list2.getEventid());
        				eventlog.setEventlogid(list2.getId());
        				eventlog.setDeal(0);//系统处理
        				eventlog.setDealid(null);
        				eventlog.setStatus(1);//待分拨
        				eventlog.setNext(1);//部门
        				eventlog.setDeal_opinion("管理员一小时后没分拨案件，系统自动移交回上级部门");
        				p_eventlogMapper.addP_eventlog(eventlog);
            			
            		  	message.textSingle(workuserSearchBean.getCode(), "", "您有一条案件超过一小时未分拨，已移交给街道办,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailfenbo.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000003);
            		  	list2.setOperate2(2);
        				p_eventlogMapper.updateNeedP_eventlog(list2);
    				}
        		}
        	}
    	  }	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}