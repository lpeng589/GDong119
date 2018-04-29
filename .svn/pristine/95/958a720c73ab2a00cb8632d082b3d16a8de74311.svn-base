package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.F_streetMapper;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.F_streetSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("f_street.htm")
public class F_streetAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="f_street_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
    super.getList(request, bean);
    F_departmentMapper f_departmentMapper = ApplicationContextUtil.getMapper(F_departmentMapper.class);
    F_departmentSearchBean f_departmentSearchBean = new F_departmentSearchBean();
    f_departmentSearchBean.setLimitFlag(false);
    request.setAttribute("department", f_departmentMapper.list(f_departmentSearchBean));
    return "f_streetlist.jsp";
  }
  /**
   * 新增
   */
  @HttpAnno(module="f_street_add",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=add")
  public void add(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
    F_streetMapper f_streetMapper = ApplicationContextUtil.getMapper(F_streetMapper.class);
    f_streetMapper.addF_street(bean);
    ServletUtil.sendJsonBean(response, new MsgBean("0","添加成功！"));
  }
//  /**
//   * 删除
//   */
//  @HttpAnno(module="f_street_delete")
//  @RequestMapping(params = "operate=delete")
//  public void delete(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
//	  
//  }
  /**
   * 批量删除
   */
  @HttpAnno(module="f_street_delete")
  @RequestMapping(params = "operate=deletemany")
  public void deletemany(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
	F_streetMapper mapper =ApplicationContextUtil.getMapper(F_streetMapper.class);
    int result=mapper.delManyF_street(bean);
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
  @HttpAnno(module="f_street_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
    F_streetMapper mapper =ApplicationContextUtil.getMapper(F_streetMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
  /**
   * 修改
   */
  @HttpAnno(module="f_street_update")
  @RequestMapping(params = "operate=update")
  public void update(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
	  F_streetMapper mapper =ApplicationContextUtil.getMapper(F_streetMapper.class);
	  int result = mapper.updateF_street(bean);
	  if(result>0){
		  ServletUtil.sendJsonBean(response,new MsgBean("0", "修改成功"));
		  return;
	  }else{
	      ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
	      return;
	    }
  }
  
  /**
   * 批量修改
   */
//  @HttpAnno(module="f_street_update")
//  @RequestMapping(params = "operate=_updatemany")
//  public void updatemany(HttpServletRequest request, HttpServletResponse response,F_streetSearchBean bean) {
//   F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
//     int result=mapper.updateManyF_department(bean);
//     if(result>0){
//        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
//        return;
//      }else{
//        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
//        return;
//      }
//  }
	@Override
 	protected Integer getListCount(Object cond)
	{
		F_streetMapper mapper = ApplicationContextUtil.getMapper(F_streetMapper.class);
		return mapper.listCount((F_streetSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		F_streetMapper mapper = ApplicationContextUtil.getMapper(F_streetMapper.class);
		 return (List)mapper.list((F_streetSearchBean)cond);
	}

}