package com.pg.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pg.bean.LogBean;
import com.pg.bean.ModuleAttrBean;
import com.pg.mapper.LogMapper;
import com.pg.mapper.ModuleMapper;

public class Log {
	private static HashMap<String, List<ModuleAttrBean>> moduleAttr =new HashMap<>();
	
	/**
	 * 添加日志
	 * @param moduleName 模块名字
	 * @param description 模块描述
	 * @param userName 操作用户名
	 * @param value  bean的值
	 * @param request 
	 */
	public static void addLog(String moduleName,String description,String loginname,HttpServletRequest request,String value,String ids) {
		LogBean logBean =new LogBean();
		logBean.setLoginname(loginname);
		logBean.setDescription(description);
		logBean.setMacaddress("");//暂时不知道如何取
		logBean.setIp(request.getRemoteHost());
		logBean.setModuleidstr(moduleName);
		logBean.setOperatetime(new Timestamp(System.currentTimeMillis()));
		logBean.setValue(value);
		String relatedId="0";
		if(request.getParameter("id")!=null){//有操作id时取操作id，默认为0
			try {
				relatedId=request.getParameter("id");
			} catch (Exception e) {
				relatedId="0";
			}
		}
		if("0".equals(relatedId) && ids!=null){
			relatedId=ids;	
		}
		logBean.setRelatedid(relatedId);
		logBean.setOperatetime(new Timestamp(System.currentTimeMillis()));
		LogMapper mapper = ApplicationContextUtil.getMapper(LogMapper.class);
		mapper.add(logBean);
	}
}
