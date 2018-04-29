package com.pg.util;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.pg.mapper.QuartzMapper;
import com.pg.searchbean.QuartzSearchBean;

/**
 * 系统启动
 * 
 * @author xcr
 * 
 */
public class SystemTaskManager implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
		//tomcat 关闭时检查 进程是否关闭
		Scheduler sched = Config.getInstance();
		if(sched != null) {  
			try {
				sched.shutdown();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}  
        }  
		try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
	}
	
	public void contextInitialized(ServletContextEvent arg0) {
		Quartz() ;//启动定时任务
		ManageCache.reflashMessage() ;//更新消息
		ManageCache.paramupdate=true;
		ManageCache.getparamByType("");//参数表缓存 
//		ManageCache.productupdate=true;
//		ManageCache.getproductlist();//产品缓存 
	}
	
	public static void Quartz() {
		
		try {
		Scheduler sched = Config.getInstance();
//        	SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
//          Scheduler sched = schedFact.getScheduler();//创建一个调度程序
            sched.start();
			//查询定时任务
            QuartzMapper quartzmapper = ApplicationContextUtil.getMapper(QuartzMapper.class);
            QuartzSearchBean quartzbean = new QuartzSearchBean();
            quartzbean.setStatus(Dict.STATUS_3);//已启用的
	        List<QuartzSearchBean>  list= quartzmapper.list(quartzbean);
	        for (int i = 0; i < list.size(); i++) {
	        	quartzbean = list.get(i);
	        	Class<?> c=Class.forName(quartzbean.getOperatetemplate());
	        	Object obj=c.newInstance();
	        	JobDetail jobDetail = new JobDetail(quartzbean.getJobname(),quartzbean.getJobgroup(), obj.getClass());//任务名，任务组，任务执行类
	            //相同任务的，有不同时间任务，可以设置同组触发器
	        	CronTrigger cronTrigger = new CronTrigger(quartzbean.getTriggername(), quartzbean.getTriggergroup());//触发器名,触发器组名
	        	cronTrigger.setCronExpression(quartzbean.getTriggertime());//触发时间设置
	            sched.scheduleJob(jobDetail, cronTrigger);//任务+触发器
	    	/*	Calendar calendar = Calendar.getInstance();
	    		calendar.set(2014, 7, 4, 17, 15, 20);
	    		JobDetail job = newJob(Config.class).withIdentity( "job" , "group").build();
	    		SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("newTrigger", "group1").startAt(calendar.getTime()).build();
	    		// 将任务和Trigger放入scheduler     
	    		sched.scheduleJob(job, trigger);*/
	        }     
      
      } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
		
	}

	public static void main(String[] args) {
	       
		  Quartz();
	    }

}
