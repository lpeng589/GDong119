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
 * 定时任务3
 * @author xcr
 */
public class QuartzProductAlert3 implements Job {
	public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(date.getHours()>=10&&date.getMinutes()>=30);
	}
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	try {
    		synchronized (this) {
            	H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
            	EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
            	EventSearchBean eventSearchBean =  new EventSearchBean();
            	P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
        		List<P_eventlogSearchBean> list = p_eventlogMapper.getOperate();
        		
        		Calendar timenow = Calendar.getInstance(); 
        		Calendar timenow1 = Calendar.getInstance(); 
        		Calendar timenow2 = Calendar.getInstance(); 
        		timenow1.add(Calendar.HOUR_OF_DAY, -1); //前一小时
        		timenow.add(Calendar.MINUTE, -30); //前30分钟
        		timenow2.add(Calendar.DAY_OF_MONTH, -1); //前一天
        		
        		
//        		timenow1.add(Calendar.MINUTE, -4); //读过消息且超过一小时
//        		timenow.add(Calendar.MINUTE, -2); //未读且超过半小时
//        		timenow2.add(Calendar.MINUTE, -6); // //读过消息且超过一天
        		
        		for (P_eventlogSearchBean list2 : list) {
        			Timestamp   ts   =   list2.getCreatetime(); 
        			Calendar   cal   =   Calendar.getInstance(); 
        			cal.setTime(ts);
        			H_workuserSearchBean workuserSearchBean  = h_workuserMapper.getById(list2.getNext_id());
        			eventSearchBean.setId(list2.getEventid());
        			eventSearchBean = mapper.getById(eventSearchBean);
        			String key= Config.getQyWxKey();
        		  	Message message = new Message(key);
        		  	if(workuserSearchBean==null){
        		  		continue;
        		  	}
        			if(list2.getOperate2()==0 && cal.compareTo(timenow)<0){ //未读且超过半小时
        				//发消息提醒
        				Calendar calendar =Calendar.getInstance();
        				Date date = calendar.getTime();
        				if(date.getHours()>=9&&date.getHours()<=18){
        					if(date.getHours()==9){
        						if(date.getMinutes()>=30){
        							message.textSingle(workuserSearchBean.getCode(), "", "您有一条未读案件,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000002); 
                    			  	list2.setOperate2(1);
                    				p_eventlogMapper.updateNeedP_eventlog(list2);
        						}
        					}else{
        						message.textSingle(workuserSearchBean.getCode(), "", "您有一条未读案件,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000002); 
                			  	list2.setOperate2(1);
                				p_eventlogMapper.updateNeedP_eventlog(list2);
        					}
        				}
        			}else if(list2.getOperate2()==1 && cal.compareTo(timenow1)<0){ //发过消息且超过一小时
        				//发消息提醒并将事件转交上一级
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
            				eventlog.setDeal_opinion("巡查员没查阅案件，一小时后系统自动移交回上级部门");
            				p_eventlogMapper.addP_eventlog(eventlog);
            				  
            			  	message.textSingle(workuserSearchBean.getCode(), "", "您有一条案件一小时没查阅已移交给上级,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000002);
            			  	/*System.out.println("======="+h_workuserMapper.getById(list2.getDealid()).getCode());*/
            			  	/*message.textSingle("FD5015F5A0A42102502E9E9854DA4D28", "", "您有一条案件已移交给上级", 1000002);*/ 
            			  	list2.setOperate2(2);
            				p_eventlogMapper.updateNeedP_eventlog(list2);
            			}else if(list2.getOperate2()==-1 && cal.compareTo(timenow2)<0){ //读过消息且超过一天
            				//发消息提醒并将事件转交上一级
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
            				eventlog.setDeal_opinion("巡查员查阅案件后24小时没处理，系统自动分拨回上级部门");
            				p_eventlogMapper.addP_eventlog(eventlog);
            				  
            			  	message.textSingle(workuserSearchBean.getCode(), "", "您有一条案件查阅案件后24小时没处理，已移交给上级,\n案件地点:"+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+list2.getEventid()+"\">点击查看详情</a>", 1000002);
            			  	/*System.out.println("======="+h_workuserMapper.getById(list2.getDealid()).getCode());*/
            			  	/*message.textSingle("FD5015F5A0A42102502E9E9854DA4D28", "", "您有一条案件已移交给上级", 1000002);*/ 
            			}
        				}
        		}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}