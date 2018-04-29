package com.pg.servlet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.gzhqy.department.Department;
import com.pg.gzhqy.util.BaseResult;
import com.pg.mapper.F_departmentMapper;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.GetLocation;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("f_department.htm")
public class F_departmentAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="f_department_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
	  bean.setParentid(-1);
    super.getList(request, bean);
    return "f_departmentlist.jsp";
  }
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="f_department_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=sonlist")
  public String sonlist(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
    super.getList(request, bean);
    return "f_departmentlist_son.jsp";
  }
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="f_department_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=Grandsonlist")
  public String Grandsonlist(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
	  super.getList(request, bean);
	  return "f_departmentlist_grandson.jsp";
  }
  /**
   * 新增
   */
  @HttpAnno(module="f_department_add",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=add")
  public void add(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
	Map<String, String> map = GetLocation.getItudeByTengxun(bean.getAddress());
	if (map!=null&&map.size()>0) {
		bean.setLatitude(map.get("lat"));
		bean.setLongitude(map.get("lng"));
	}
    int result=mapper.addF_department(bean);
    if(result>0){
      //企业微信新增部门
      Department department = new Department(Config.getQyWxTXZSKey());
      BaseResult baseResult = new BaseResult();
      if(bean.getParentid()!=null&&bean.getParentid()>0){
    	  baseResult = department.create(bean.getName(), bean.getParentid(),bean.getId());	
      }else{
    	  baseResult = department.create(bean.getName(), bean.getId());	
      }
      if (baseResult.getErrcode().equals("0")) {
    	  ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
          return;
      }else {
    	  mapper.delF_department(bean);//删除
    	  ServletUtil.sendJsonBean(response, new MsgBean("-1", "企业微信调用接口失败，请联系技术人员，错误CODE："+baseResult.getErrcode()));
          return;
      }		  
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败"));
      return;
    }
  }
  /**
   * 删除
   */
  @HttpAnno(module="f_department_update")
  @RequestMapping(params = "operate=delete")
  public void delete(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
	  if (bean.getId()==null) {
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "非法请求"));
	      return;
	  }
	  F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
	  F_departmentSearchBean searchBean = mapper.getById(bean);
	  if (searchBean==null||searchBean.getUsercount()>0) {
		  ServletUtil.sendJsonBean(response, new MsgBean("-1", "无法删除"));
	      return;
	  }
	  //企业微信删除部门
      Department department = new Department(Config.getQyWxTXZSKey());
      BaseResult baseResult = department.delete(bean.getId());	  
      if (baseResult.getErrcode().equals("0")) {
    	  int result=mapper.delF_department(bean);
    	    if(result>0){
    	      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
    	      return;
    	    }else{
    	      ServletUtil.sendJsonBean(response, new MsgBean("-1", "企业微信删除成功，后台数据库删除失败，请联系技术人员。"));
    	      return;
    	    }
      }else {
    	  ServletUtil.sendJsonBean(response, new MsgBean("-1", "无法删除，企业微信调用接口失败，请联系技术人员，错误CODE："+baseResult.getErrcode()));
          return;
      }		  
  }
  /**
   * 批量删除
   */
//  @HttpAnno(module="f_department_delete")
//  @RequestMapping(params = "operate=deletemany")
//  public void deletemany(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
//    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
//    int result=mapper.delManyF_department(bean);
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
  @HttpAnno(module="f_department_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
  /**
   * 修改
   */
  @HttpAnno(module="f_department_update")
  @RequestMapping(params = "operate=update")
  public void update(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
    F_departmentSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null) {
    	Map<String, String> map = GetLocation.getItudeByTengxun(bean.getAddress());
    	if (map!=null&&map.size()>0) {
    		bean.setLatitude(map.get("lat"));
    		bean.setLongitude(map.get("lng"));
    	}
       int result=mapper.updateF_department(bean);
       if(result>0){
    	  bean2 = mapper.getById(bean);
    	  //企业微信修改部门
          Department department = new Department(Config.getQyWxTXZSKey());
          BaseResult baseResult = department.update(bean.getName(), bean.getId());
          if (!baseResult.getErrcode().equals("0")) {
        	  ServletUtil.sendJsonBean(response, new MsgBean("-1", "后台数据库修改成功，调取接口修改企业微信失败，请联系技术人员，错误code："+baseResult.getErrcode()));
              return;
		  } 
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
   * 修改二级部门前
   */
  @HttpAnno(module="f_department_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre2")
  public void updatepre2(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
    bean.setLimitFlag(false);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.list(bean)));
  }
  /**
   * 修改二级部门
   */
  @HttpAnno(module="f_department_update")
  @RequestMapping(params = "operate=update2")
  public void update2(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
	  F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
	  bean.setParentid(bean.getId());
	  bean.setId(bean.getBranchid());
	  int result=mapper.updateNeedF_department(bean);
	  if(result>0){
		  ServletUtil.sendJsonBean(response, new MsgBean("0","修改成功"));
		  return;
	  }else{
        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
        return;
      }
  }
  /**
   * 批量修改
   */
//  @HttpAnno(module="f_department_update")
//  @RequestMapping(params = "operate=_updatemany")
//  public void updatemany(HttpServletRequest request, HttpServletResponse response,F_departmentSearchBean bean) {
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
		F_departmentMapper mapper = ApplicationContextUtil.getMapper(F_departmentMapper.class);
		return mapper.listCount((F_departmentSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		F_departmentMapper mapper = ApplicationContextUtil.getMapper(F_departmentMapper.class);
		 return (List)mapper.list((F_departmentSearchBean)cond);
	}

}