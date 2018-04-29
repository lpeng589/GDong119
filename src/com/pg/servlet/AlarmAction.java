package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.mapper.AlarmMapper;
import com.pg.searchbean.AlarmSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("alarm.htm")
public class AlarmAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="alarm_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
    super.getList(request, bean);
    return "alarmlist.jsp";
  }
  /**
   * 新增
   */
//  @HttpAnno(module="alarm_add",log=HttpAnno.UNLOG)
//  @RequestMapping(params = "operate=add")
//  public void add(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
//    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
//    int result=mapper.addAlarm(bean);
//    if(result>0){
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
//      return;
//    }else{
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "新增失败"));
//      return;
//    }
//  }
  /**
   * 删除
   */
//  @HttpAnno(module="alarm_delete")
//  @RequestMapping(params = "operate=delete")
//  public void delete(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
//    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
//    int result=mapper.delAlarm(bean);
//    if(result>0){
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
//      return;
//    }else{
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除失败"));
//      return;
//    }
//  }
  /**
   * 批量删除
   */
//  @HttpAnno(module="alarm_delete")
//  @RequestMapping(params = "operate=deletemany")
//  public void deletemany(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
//    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
//    int result=mapper.delManyAlarm(bean);
//    if(result>0){
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
//      return;
//    }else{
//      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除失败"));
//      return;
//    }
//  }
  /**
   * 修改前
   */
//  @HttpAnno(module="alarm_update",log=HttpAnno.UNLOG)
//  @RequestMapping(params = "operate=updatepre")
//  public void updatepre(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
//    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
//    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
//  }
  /**
   * 修改
   */
//  @HttpAnno(module="alarm_update")
//  @RequestMapping(params = "operate=update")
//  public void update(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
//    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
//    AlarmSearchBean bean2 = mapper.getById(bean);
//    if (bean2!=null) {
//       int result=mapper.updateAlarm(bean);
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
//  @HttpAnno(module="alarm_update")
//  @RequestMapping(params = "operate=_updatemany")
//  public void updatemany(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
//   AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
//     int result=mapper.updateManyAlarm(bean);
//     if(result>0){
//        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
//        return;
//      }else{
//        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
//        return;
//      }
//  }
  /**
   * 结束报警
   * @param request
   * @param response
   * @param bean
   */
  @HttpAnno(module="alarm_update")
  @RequestMapping(params = "operate=end")
  public void end(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
    AlarmSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null&&bean2.getStatus()==1) {
    	bean.setStatus(2);
       int result=mapper.updateStatus(bean);
       if(result>0){
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
          return;
        }else{
          ServletUtil.sendJsonBean(response, new MsgBean("1", "修改失败"));
          return;
        }
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("1", "请求错误，请刷新后重试"));
      return;
    }
  }
  /**
   * 处理报警
   * @param request
   * @param response
   * @param bean
   */
  @HttpAnno(module="alarm_update")
  @RequestMapping(params = "operate=start")
  public void start(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean) {
    AlarmMapper mapper =ApplicationContextUtil.getMapper(AlarmMapper.class);
    AlarmSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null&&bean2.getStatus()==0) {
    	bean.setStatus(1);
       int result=mapper.updateStatus(bean);
       if(result>0){
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
          return;
        }else{
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
          return;
        }
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("0", "记录不存在"));
      return;
    }
  }
  
  
	@Override
 	protected Integer getListCount(Object cond)
	{
		AlarmMapper mapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		return mapper.listCount((AlarmSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		AlarmMapper mapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		 return (List)mapper.list((AlarmSearchBean)cond);
	}

}