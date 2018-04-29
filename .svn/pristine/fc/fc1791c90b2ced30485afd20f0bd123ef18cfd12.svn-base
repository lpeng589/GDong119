package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.gzhqy.user.User;
import com.pg.gzhqy.user.UserResultBean;
import com.pg.gzhqy.util.BaseResult;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
@Controller
@RequestMapping("h_workuser.htm")
public class H_workuserAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="h_workuser_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
	bean.setType(1);  
    super.getList(request, bean);
    F_departmentSearchBean departmentSearchBean =  new F_departmentSearchBean();
    F_departmentMapper departmentMapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
    departmentSearchBean.setLimitFlag(false);
    departmentSearchBean.setParentid(-1);
    request.setAttribute("department", departmentMapper.list(departmentSearchBean));
    return "h_workuserlist.jsp";
  }
  /**
   * 新增
   */
  @HttpAnno(module="h_workuser_add",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=add")
  public void add(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
    H_workuserMapper mapper =ApplicationContextUtil.getMapper(H_workuserMapper.class);
    H_workuserSearchBean workuserSearchBean = mapper.getByPhone(bean.getPhone());
    if(workuserSearchBean!=null){
        ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败-手机号已存在"));
        return;
    }
    bean.setType(1);
//    bean.setDepartmentid(Integer.valueOf(Config.getValue("departmentid")));
    int result=mapper.addH_workuser(bean);
    if(result>0){
    	//添加CODE
    	String code = Tools.MD5(bean.getId()+"_workuser_"+System.currentTimeMillis());
    	bean.setCode(code);
    	mapper.UpdateCode(bean);
    	//企业微信新增用户
    	User User = new User(Config.getQyWxTXZSKey());
    	UserResultBean userbean = new UserResultBean();
    	userbean.setUserid(code);
    	userbean.setName(bean.getName());
    	userbean.setMobile(bean.getPhone());
    	Integer department[]={bean.getDepartmentid()};
    	userbean.setDepartment(department);
    	userbean.setEnable(1);
        BaseResult baseResult = User.createUser(userbean);		  
        if (baseResult.getErrcode().equals("0")) {
      	    ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
            return;
        }else {
        	mapper.delH_workuser(bean);
      	    ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败，企业微信调用接口失败，请联系技术人员，错误Errmsg："+baseResult.getErrmsg()));
            return;
        }	
    }else{
        ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败"));
        return;
    }
  }
  /**
   * 修改前
   */
  @HttpAnno(module="h_workuser_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
    H_workuserMapper mapper =ApplicationContextUtil.getMapper(H_workuserMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
  /**
   * 修改
   */
  @HttpAnno(module="h_workuser_update")
  @RequestMapping(params = "operate=update")
  public void update(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
    H_workuserMapper mapper =ApplicationContextUtil.getMapper(H_workuserMapper.class);
    H_workuserSearchBean bean3 = mapper.getByPhone(bean.getPhone());
    if(bean3!=null && bean3.getId().intValue()!=bean.getId().intValue()){
        ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败-手机号已存在"));
        return;
    }
    H_workuserSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null) {
       int result=mapper.updateH_workuser(bean);
       if(result>0){
    	   //企业微信修改部门
	    	User User = new User(Config.getQyWxTXZSKey());
	       	UserResultBean userbean = new UserResultBean();
	       	userbean.setUserid(bean2.getCode());
	       	userbean.setName(bean.getName());
	    	userbean.setMobile(bean.getPhone());
	       	Integer department[]={bean2.getDepartmentid()};
	       	userbean.setDepartment(department);
//	       	userbean.setEnable(bean.getStatus());
	        BaseResult baseResult = User.updateUser(userbean);	
            if (!baseResult.getErrcode().equals("0")) {
        	   ServletUtil.sendJsonBean(response, new MsgBean("-1", "调取接口修改企业微信失败，请联系技术人员，错误Errmsg："+baseResult.getErrmsg()));
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
   * 注销
   */
  @HttpAnno(module="h_workuser_delete")
  @RequestMapping(params = "operate=zhuxiao")
  public void zhuxiao(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
    H_workuserMapper mapper =ApplicationContextUtil.getMapper(H_workuserMapper.class);
    H_workuserSearchBean bean3 = mapper.getById(bean);
    if(bean3==null||bean3.getStatus()==0){
        ServletUtil.sendJsonBean(response, new MsgBean("-1", "请求错误，请刷新后重试"));
        return;
    }
    bean.setStatus(0);
   int result=mapper.updateStatus(bean);
   if(result>0){
	   //企业微信 删除该用户
	    User User = new User(Config.getQyWxTXZSKey());
        BaseResult baseResult = User.deleteUser(bean3.getCode());
        if (!baseResult.getErrcode().equals("0")) {
           bean.setStatus(1);
           mapper.updateStatus(bean);
     	   ServletUtil.sendJsonBean(response, new MsgBean("-1", "调取接口删除企业微信用户失败，请联系技术人员，错误Errmsg："+baseResult.getErrmsg()));
           return;
		}else {
			ServletUtil.sendJsonBean(response, new MsgBean("0", "注销成功"));
		      return;
		} 
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "注销失败"));
      return;
    }
}
  /**
   * 启用
   */
  @HttpAnno(module="h_workuser_delete")
  @RequestMapping(params = "operate=qiyong")
  public void qiyong(HttpServletRequest request, HttpServletResponse response,H_workuserSearchBean bean) {
    H_workuserMapper mapper =ApplicationContextUtil.getMapper(H_workuserMapper.class);
    H_workuserSearchBean bean3 = mapper.getById(bean);
    if(bean3==null||bean3.getStatus()==1){
        ServletUtil.sendJsonBean(response, new MsgBean("-1", "请求错误，请刷新后重试"));
        return;
    }
    bean.setStatus(1);
   int result=mapper.updateStatus(bean);
   if(result>0){
	   	//企业微信 重新添加该用户
    	User User = new User(Config.getQyWxTXZSKey());
    	UserResultBean userbean = new UserResultBean();
    	userbean.setUserid(bean3.getCode());
    	userbean.setName(bean3.getName());
    	userbean.setMobile(bean3.getPhone());
    	Integer department[]={bean3.getDepartmentid()};
    	userbean.setDepartment(department);
    	userbean.setEnable(1);
        BaseResult baseResult = User.createUser(userbean);		  
        if (baseResult.getErrcode().equals("0")) {
      	    ServletUtil.sendJsonBean(response, new MsgBean("0", "启用成功"));
            return;
        }else {
        	bean.setStatus(0);
        	mapper.updateStatus(bean);
      	    ServletUtil.sendJsonBean(response, new MsgBean("-1", "企业微信调用接口失败，请联系技术人员，错误errmsg："+baseResult.getErrmsg()));
            return;
        }
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "启用失败"));
      return;
    }
}
	@Override
 	protected Integer getListCount(Object cond)
	{
		H_workuserMapper mapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
		return mapper.listCount((H_workuserSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		H_workuserMapper mapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
		 return (List)mapper.list((H_workuserSearchBean)cond);
	}

}