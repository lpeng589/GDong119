package com.pg.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.EmployeeBean;
import com.pg.bean.MenuBean;
import com.pg.bean.ModuleBean;
import com.pg.mapper.ModuleMapper;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Tools;

/**
 * 主页面
 * @author dzp
 *
 */
@Controller
public class MainAction{
	/**
	 * 主页面
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("main.htm")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		EmployeeBean bean =Check.getEmployee(request);
		if(bean==null){
			return "login.jsp";
		}
		/**
		 * 返回权限模块用来加载菜单等
		 */
		request.setAttribute("employee", bean);
		List<MenuBean> menu = new ArrayList<>();
		/**
		 * 加载菜单
		 */
		ModuleMapper moduleMapper =ApplicationContextUtil.getMapper(ModuleMapper.class);
		List<ModuleBean> moduleList=null;
//		if(bean.getLoginname().equals("admin")){
//			moduleList = moduleMapper.getAllModule();//获取全部权限
//		}else{
//			moduleList = moduleMapper.getModule(bean.getRoleids()==null?"":bean.getRoleids().substring(1, bean.getRoleids().length()-1));//根据角色获得权限
//		}
		HashMap<String, ModuleBean> moduleMap =Check.getModule(request);//拥有权限
		moduleList = moduleMapper.getAllMenu();//获取全部菜单
		if(moduleMap!=null && moduleMap!=null){
			int menuid=1;
			for (ModuleBean moduleBean : moduleList) {  
				moduleBean.setName(Tools.StringFilter(moduleBean.getName(),"<br>"));
				if(moduleBean.getIsmenu()==1){
					//判断moduleBean的menuidstr里的权限该用户是否拥有
					boolean hasRule = false;
					if(moduleBean.getMenuidstr()!=null){
						String[] mo = moduleBean.getMenuidstr().split(",");
						for (int i = 0; i < mo.length; i++) {
							  if(moduleMap.get(mo[i])!=null){//拥有权限
								  hasRule=true;
								  break;
							  }
						}
					}
					if(hasRule){
						boolean newFlag = true;
						for(MenuBean menuBean : menu){
							if(moduleBean.getCategory().equals(menuBean.getName())){
								menuBean.getSubmenu().add(moduleBean);
								newFlag=false;
								break;
							}
						}
						if(newFlag){
								MenuBean menuBean =new MenuBean();
								menuid++;
								menuBean.setId(menuid++);
								menuBean.setName(moduleBean.getCategory());
								menuBean.getSubmenu().add(moduleBean);
								menu.add(menuBean);
						}
					}
				}
			}
		}
		request.setAttribute("menu",menu);
		return "main.jsp";
	}
}
