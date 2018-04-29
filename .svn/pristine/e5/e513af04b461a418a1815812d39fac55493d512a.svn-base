package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.AlarmBean;
import com.pg.bean.MsgBean;
import com.pg.mapper.AlarmMapper;
import com.pg.mapper.AlarmdetailMapper;
import com.pg.searchbean.AlarmSearchBean;
import com.pg.searchbean.AlarmdetailSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.PgFilter;
import com.pg.util.ServletUtil;
@Controller
public class Deal110Action
{
	/**
	 * 处理控制台
	 */
	@RequestMapping("deal110.htm")
	public String deal110(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
		if(Check.getEmployee(request)==null){
			return "login.jsp";
		}
		return "deal110.jsp";
	}
  /**
   * 警情列表
   * status 状态
   * page 第几页
   */
  @RequestMapping("deallist.htm")
  public void list110(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
	  if(Check.getEmployee(request)==null){
		  ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));
		  return;
	}
	List<AlarmSearchBean> list = ApplicationContextUtil.getMapper(AlarmMapper.class).list(bean);
    ServletUtil.sendJsonBean(response, new MsgBean(list));
  }
  /**
   * 未处理警情数量
   */
  @RequestMapping("dealnot.htm")
  public void dealnot(HttpServletRequest request, HttpServletResponse response) {
	  if(Check.getEmployee(request)==null){
		  ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));
		  return;
	}
	AlarmSearchBean bean = new AlarmSearchBean();
	bean.setStatus(0);
    Integer count = ApplicationContextUtil.getMapper(AlarmMapper.class).listCount(bean);
    List<AlarmBean> list = ApplicationContextUtil.getMapper(AlarmdetailMapper.class).getNotRead();
    ServletUtil.sendJsonBean(response, new MsgBean("0",count+"",list));
  }
  /**
   * 最新警情详情列表(暂时全部)
   * alarmid 警情id
   * id 最后一个列表的id（第一次调用填0，后面刷新填最后一个id）
   */
  @RequestMapping("dealdetail.htm")
  public void dealdetail(HttpServletRequest request, HttpServletResponse response,AlarmdetailSearchBean bean) {
	  if(Check.getEmployee(request)==null&&PgFilter.getUser(request)==null){
		  ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));
		  return;
	}
	AlarmdetailMapper mapper = ApplicationContextUtil.getMapper(AlarmdetailMapper.class);
    List<AlarmdetailSearchBean> list = mapper.listNew(bean);
    if(list.size()>0&&bean.getSendtype()!=null&&bean.getSendtype()==1){
    	mapper.updateRead(bean.getAlarmid());
    }
    ServletUtil.sendJsonBean(response, new MsgBean(list));
  }
  
  /**
   * 处理状态更改为受理中
   * id 警情id
   */
  @RequestMapping("dealstatus1.htm")
  public void dealstatus1(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
	  if(Check.getEmployee(request)==null){
		  ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));
		  return;
	}
	  AlarmMapper mapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
      bean = mapper.getById(bean);
      if(bean!=null){
    	  if(bean.getStatus()==0){
    		  bean.setStatus(1);
    		  mapper.updateStatus(bean);
    	  }
      }
	  ServletUtil.sendJsonBean(response, new MsgBean("0","修改成功"));
  }
  /**
   * 处理状态更改为结案
   * id 警情id
   * type 0刑事 1治安 2交通 3求助 4骚扰 5其他 6特殊警情 7重复报警 8举报线索 9工作咨询 10出警反馈
   * remark  备注
   */
  @RequestMapping("dealstatus2.htm")
  public void dealstatus2(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
	  if(Check.getEmployee(request)==null){
		  ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));
		  return;
	}
	  AlarmMapper mapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
      bean = mapper.getById(bean);
      if(bean!=null){
    	  if(bean.getStatus()==1){
    		  bean.setStatus(2);
    		  mapper.updateAlarm(bean);
    	  }
      }
	  ServletUtil.sendJsonBean(response, new MsgBean("0","修改成功"));
  }
  /**
   * 添加处理回复（暂时只支持文字）
   * alarmid 警情id
   * content 文字内容
   */
  @RequestMapping("dealreply.htm")
  public void dealreply(HttpServletRequest request, HttpServletResponse response,AlarmdetailSearchBean bean) {
	  if(Check.getEmployee(request)==null){
		  ServletUtil.sendJsonBean(response, new MsgBean("-1000","请先登录"));
		  return;
	}
	  AlarmdetailMapper mapper =ApplicationContextUtil.getMapper(AlarmdetailMapper.class);
	  bean.setSendtype(1);
	  bean.setType(0);
	  int result=mapper.addAlarmdetail(bean);
	  if(result>0){
		  ServletUtil.sendJsonBean(response, new MsgBean("0", "回复成功"));
	  }else{
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "回复失败"));
	  }
  }
  
}