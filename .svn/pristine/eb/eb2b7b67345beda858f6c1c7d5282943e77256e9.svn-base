package com.pg.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pg.bean.MsgBean;
import com.pg.mapper.EmployeeMapper;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Config;
import com.pg.util.Crypto;
import com.pg.util.DecUtil;
import com.pg.util.RandomValidateCode;
import com.pg.util.ServletUtil;

/**
 * 职员登录页面
 * @author dzp
 */
@Controller
public class LoginAction{
	/**
	 * 职员登陆页
	 * @param request
	 * @param response
	 */
	@RequestMapping("login.htm")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login.jsp";
	}
	/**
	 * 职员登陆处理
	 * @param request
	 * @param response
	 */
	@RequestMapping("logincheck.htm")
	public void loginCheck(HttpServletRequest request, HttpServletResponse response,@RequestParam("loginname") String loginname,@RequestParam("password") String password,@RequestParam("verifycode")String verifyCode) {
		//先校验验证码
		if(null == verifyCode || (!verifyCode.equalsIgnoreCase((String)request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY))))
		{
//			ServletUtil.sendJsonBean(response, new MsgBean("-2", "验证码错误"));
//			return;
		}
		EmployeeMapper employeeMapper = ApplicationContextUtil.getMapper(EmployeeMapper.class);
		password = new DecUtil().strDec(password, loginname, "", "");  //页面加密的解密
		EmployeeSearchBean bean =employeeMapper.login(loginname);
		if(bean==null){
			ServletUtil.sendJsonBean(response, new MsgBean("-1", "账号不存在或者已被注销"));
			return;
		}else{
			String pass = Crypto.decrypt(bean.getPassword());//解密
			if(!pass.equals(password)){
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "密码错误"));
				return;
			}
			
		}
		if(bean.getDepartmentid()!=null){
		    F_departmentMapper F_departmentMapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
		    F_departmentSearchBean  f_departmentSearchBean= new F_departmentSearchBean();
		    f_departmentSearchBean.setId(bean.getDepartmentid());
		    f_departmentSearchBean = F_departmentMapper.getById(f_departmentSearchBean);
		    bean.setDepartmentBean(f_departmentSearchBean);
		    if(f_departmentSearchBean!=null && f_departmentSearchBean.getType()!=null&&0==f_departmentSearchBean.getType()){  //总队
		    	bean.setDepylv(0);
		    }else if(f_departmentSearchBean!=null && f_departmentSearchBean.getType()!=null&& 1==f_departmentSearchBean.getType()){  //支队
		    	bean.setDepylv(1);
		    }else if(f_departmentSearchBean!=null && f_departmentSearchBean.getType()!=null&&2==f_departmentSearchBean.getType()){  //街道办
		    	bean.setDepylv(2);
		    }else if(f_departmentSearchBean!=null && f_departmentSearchBean.getType()!=null&&3==f_departmentSearchBean.getType()){  //村社
		    	bean.setDepylv(3);
		    }
		}
		if(bean.getWorkuserid()!=null){
			bean.setDepylv(99);
			H_workuserMapper H_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
			H_workuserSearchBean H_workuserSearchBean =  new  H_workuserSearchBean();
			H_workuserSearchBean.setId(bean.getWorkuserid());
			bean.setWorkuserBean(H_workuserMapper.getById( H_workuserSearchBean));
		}
		
		Check.setEmployee(request, bean);
		Check.loadModule(request, bean);
		Check.loadFlowNode(request, bean);
		//判断是否登录，挤掉第一个登录的
		String currentSeesionId= request.getSession().getId();
		Config.userloginMap.put(bean.getLoginname(), currentSeesionId);
		ServletUtil.sendJsonBean(response, new MsgBean("0", "登录成功"));
	}
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("logout.htm")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		Check.clearEmployee(request);
		return "login.jsp";
	}
}
