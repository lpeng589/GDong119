package com.pg.servlet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pg.bean.MsgBean;
import com.pg.bean.P_eventlogBean;
import com.pg.mapper.P_eventlogMapper;
import com.pg.searchbean.P_eventlogSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("p_eventlog.htm")
public class P_eventlogAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="p_eventlog_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    super.getList(request, bean);
    return "p_eventloglist.jsp";
  }
  /**
   * 新增
   */
  @HttpAnno(module="p_eventlog_add",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=add")
  public void add(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    int result=mapper.addP_eventlog(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败"));
      return;
    }
  }
  /**
   * 删除
   */
  @HttpAnno(module="p_eventlog_delete")
  @RequestMapping(params = "operate=delete")
  public void delete(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    int result=mapper.delP_eventlog(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "删除失败"));
      return;
    }
  }
  /**
   * 批量删除
   */
  @HttpAnno(module="p_eventlog_delete")
  @RequestMapping(params = "operate=deletemany")
  public void deletemany(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    int result=mapper.delManyP_eventlog(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "删除失败"));
      return;
    }
  }
  /**
   * 修改前
   */
  @HttpAnno(module="p_eventlog_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
  /**
   * 修改
   */
  @HttpAnno(module="p_eventlog_update")
  @RequestMapping(params = "operate=update")
  public void update(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    P_eventlogSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null) {
       int result=mapper.updateP_eventlog(bean);
       if(result>0){
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
          return;
        }else{
          ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
          return;
        }
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "记录不存在"));
      return;
    }
  }
  /**
   * 批量修改
   */
  @HttpAnno(module="p_eventlog_update")
  @RequestMapping(params = "operate=_updatemany")
  public void updatemany(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
   P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
     int result=mapper.updateManyP_eventlog(bean);
     if(result>0){
        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
        return;
      }else{
        ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
        return;
      }
  }
  /**
   * 详情
   */
  @HttpAnno(module="p_eventlog_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=detail")
  public void detail(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
    P_eventlogMapper mapper =ApplicationContextUtil.getMapper(P_eventlogMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
	@Override
 	protected Integer getListCount(Object cond)
	{
		P_eventlogMapper mapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		return mapper.listCount((P_eventlogSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		P_eventlogMapper mapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		 return (List)mapper.list((P_eventlogSearchBean)cond);
	}

}