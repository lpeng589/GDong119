package com.pg.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MessageBean;
import com.pg.bean.MsgBean;
import com.pg.mapper.MessageMapper;
import com.pg.searchbean.MessageSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.HttpAnno;
import com.pg.util.ManageCache;
import com.pg.util.ServletUtil;

/**
 * 消息操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("message.htm")
public class MessageAction extends BaseAction{
	/**
	 * 消息列表
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=list")
	public String list(HttpServletRequest request, HttpServletResponse response,MessageSearchBean bean) {
		bean.setEmployeeid(Check.getEmployee(request).getId());
		super.getList(request, bean);
		return "messagelist.jsp";
	}
	
	/**
	 * 消息删除
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,MessageSearchBean bean) {
		MessageMapper mapper =ApplicationContextUtil.getMapper(MessageMapper.class);
		bean.setEmployeeid(Check.getEmployee(request).getId());
		int result=mapper.delete(bean);
		if(result>0){
			ManageCache.reflashMessage(Check.getEmployee(request).getId());//刷新自己的消息
			ServletUtil.sendJsonBean(response, new MsgBean("0", "消息删除成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "消息删除失败"));
			return;
		}
	}
	/**
	 * 获取未读消息数量
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=getcount")
	public void getCount(HttpServletRequest request, HttpServletResponse response,MessageSearchBean bean) {
		List<MessageBean> list  = ManageCache.getMessage(request);
		ServletUtil.sendJsonBean(response,new  MsgBean(String.valueOf(list.size()),"", list));
//		ServletUtil.sendJsonBean(response,new  MsgBean(String.valueOf(result),"", list));
/*		MessageMapper mapper =ApplicationContextUtil.getMapper(MessageMapper.class);
		bean.setStatus(0);
		bean.setEmployeeid(Check.getEmployee(request).getId());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date(System.currentTimeMillis()); 
		Calendar calender = Calendar.getInstance();
		calender.setTime(currentDate);
		calender.add(Calendar.SECOND, -bean.getTime());
		bean.setChecktime(simpleDateFormat.format(calender.getTime()));
		List<MessageBean> list=mapper.list(bean);//查询Time 内发过来的信息
		
		int result=mapper.listCount(bean);//查询为读信息
//		if(list>0){
			ServletUtil.sendJsonBean(response,new  MsgBean(String.valueOf(result),"", list));
//			return;
//		}else{
//			ServletUtil.sendJsonBean(response, new MsgBean(0));
//			return;
//		}
*/	}
	/**
	 * 批量设置已读
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=updateAll")
	public void updateAll(HttpServletRequest request, HttpServletResponse response,MessageSearchBean bean) {
		MessageMapper mapper =ApplicationContextUtil.getMapper(MessageMapper.class);
		bean.setEmployeeid(Check.getEmployee(request).getId());
		int result=mapper.updateAllstatus(bean);
		ManageCache.reflashMessage(Check.getEmployee(request).getId());//刷新自己的消息
//		if(result>0){
//				ManageCache.reflashMessage(Check.getEmployee(request).getId());//刷新自己的消息
				ServletUtil.sendJsonBean(response, new MsgBean("0", "操作成功"));
//			return;
//		}else{
//			ServletUtil.sendJsonBean(response, new MsgBean("-1", "操作失败"));
//			return;
//		}
	}
	/**
	 * 设置全部为已读
	 * @param request
	 * @param response
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping(params = "operate=update")
	public void update(HttpServletRequest request, HttpServletResponse response,MessageSearchBean bean) {
		MessageMapper mapper =ApplicationContextUtil.getMapper(MessageMapper.class);
		bean.setStatus(1);
		bean.setEmployeeid(Check.getEmployee(request).getId());
		int result=mapper.updatestatus(bean);
		if(result>0){
				ManageCache.reflashMessage(Check.getEmployee(request).getId());//刷新自己的消息
				ServletUtil.sendJsonBean(response, new MsgBean("0", "操作成功"));
			return;
		}else{
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "操作失败"));
			return;
		}
	}
	
	@Override
	protected Integer getListCount(Object cond) {
		MessageMapper mapper = ApplicationContextUtil.getMapper(MessageMapper.class);
		return mapper.listCount((MessageSearchBean)cond);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Object> getListData(Object cond) {
		MessageMapper mapper = ApplicationContextUtil.getMapper(MessageMapper.class);
		return (List)mapper.list((MessageSearchBean)cond);
	}
}
