package com.pg.util;

import javax.servlet.http.HttpSession;

import com.pg.bean.EmployeeBean;

public class SessionUtils 
{
	
	public static Integer getSpOperatorId(HttpSession session)
	{
		EmployeeBean employeeBean = (EmployeeBean)session.getAttribute(SessionKeys.EMPLOYEE_INSTANCE);
		if(employeeBean!=null)
		{
			return employeeBean.getId();
		}
		else
		{
			return null;
		}
	}
	
	public static String getSpOperatorUsername(HttpSession session)
	{
		EmployeeBean employeeBean = (EmployeeBean)session.getAttribute(SessionKeys.EMPLOYEE_INSTANCE);
		if(employeeBean!=null)
		{
			return employeeBean.getUsername();
		}
		else
		{
			return null;
		}
	}

}
