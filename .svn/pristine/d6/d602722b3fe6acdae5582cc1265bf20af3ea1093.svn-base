package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.mapper.QuartzMapper;
import com.pg.searchbean.QuartzSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("quartz.htm")
public class QuartzAction extends BaseAction
{
	
	/**
	 * 定时任务列表 XCH
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(value=HttpAnno.VIEW,module="quartz_list,quartz_update",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,QuartzSearchBean bean) {
		super.getList(request, bean);
		return "quartzlist.jsp";
	}
	

	/**
	 * 定时任务修改前  XCH
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="quartz_update",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=updatepre")
	public void updatePre(HttpServletRequest request, HttpServletResponse response,QuartzSearchBean bean) {
		QuartzMapper mapper =ApplicationContextUtil.getMapper(QuartzMapper.class);
		ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
	}
	
	/**
	 * 定时任务修改  XCH
	 * @param request
	 * @param response
	 * @param bean
	 */
	@HttpAnno(module="quartz_update")
	@RequestMapping(params = "operate=update")
	public void update(HttpServletRequest request, HttpServletResponse response,QuartzSearchBean bean) {
		QuartzMapper mapper =ApplicationContextUtil.getMapper(QuartzMapper.class);
		QuartzSearchBean bean2 = mapper.getById(bean);
		if (bean2!=null) {
			int result=mapper.updateQuartz(bean);
			if(result>0){
				//修改系统定时任务
				Scheduler sched = Config.getInstance();
				try {
					if(bean.getStatus()!=3){
						sched.pauseTrigger(bean2.getTriggername(), bean2.getTriggergroup());//暂停
					}else{
			            //相同任务的，有不同时间任务，可以设置同组触发器
			        	Trigger trigger =  sched.getTrigger(bean2.getTriggername(), bean2.getTriggergroup());  
			        	if(trigger != null){  //已有的重启
			               CronTrigger  ct = (CronTrigger)trigger;  
			               ct.setCronExpression(bean2.getTriggertime());  //修改时间  
			               sched.resumeTrigger(bean2.getTriggername(), bean2.getTriggergroup());   //重启触发器  
			            } else{//没有添加任务
			            	Class<?> c=Class.forName(bean2.getOperatetemplate());
				        	Object obj=c.newInstance();
				        	JobDetail jobDetail = new JobDetail(bean2.getJobname(),bean2.getJobgroup(), obj.getClass());//任务名，任务组，任务执行类
				            //相同任务的，有不同时间任务，可以设置同组触发器
				        	CronTrigger cronTrigger = new CronTrigger(bean2.getTriggername(), bean2.getTriggergroup());//触发器名,触发器组名
				        	cronTrigger.setCronExpression(bean2.getTriggertime());//触发时间设置
				            sched.scheduleJob(jobDetail, cronTrigger);//添加 任务+触发器
			            	
			            } 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				ServletUtil.sendJsonBean(response, new MsgBean("0", "定时任务修改成功"));
				return;
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "定时任务修改失败"));
				return;
			}
		}
	}
	
	
	
	
	@Override
 	protected Integer getListCount(Object cond)
	{
		QuartzMapper mapper = ApplicationContextUtil.getMapper(QuartzMapper.class);
		return mapper.listCount((QuartzSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		QuartzMapper mapper = ApplicationContextUtil.getMapper(QuartzMapper.class);
		 return (List)mapper.list((QuartzSearchBean)cond);
	}

}