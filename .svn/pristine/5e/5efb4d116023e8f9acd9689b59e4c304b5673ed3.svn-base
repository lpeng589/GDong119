package com.pg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.GsonBuilder;
import com.pg.mybatis.SessionFactory;


/**
 * 封装了一些Http常用方法
 * 
 * @author swan
 * 
 */
public class ServletUtil {

	public static final ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();

	/**
	 * 设置页面无缓存，以及编码为utf-8
	 * 
	 * @param response
	 */
	public static final void expireData(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -10);
		response.setCharacterEncoding("utf-8");
	}
	/**
	 * 返回json内容
	 * @param response
	 * @param o
	 */
	public static final void sendJsonBean(HttpServletResponse response, Object o) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", -10);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(o));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回json内容--保留null的
	 * @param response
	 * @param o
	 */
	public static final void sendJsonBeanNull(HttpServletResponse response, Object o) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", -10);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create().toJson(o));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回html内容
	 * 
	 * @param response
	 * @param content
	 * @throws IOException
	 */
	public static final void sendContent(HttpServletResponse response, String content) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", -10);
			response.setCharacterEncoding("utf-8");
			response.setContentType("html;charset=UTF-8");
			response.getWriter().print(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

	/**
	 * 获取mybatis连数据库的元素
	 * 
	 * @param request
	 * @return
	 * @see SessionFactory
	 */
	public static final SessionFactory getFactory(HttpServletRequest request) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		return (SessionFactory) context.getBean("sqlSessionFactory");
	}

	public static final SessionFactory getFactory() {
		WebApplicationContext context2 = WebApplicationContextUtils.getWebApplicationContext(context);
		return (SessionFactory) context2.getBean("sqlSessionFactory");
	}
	public static Object excuteSql(Class<?> c, String method, Object... params) 
	{
			Object mapper = ApplicationContextUtil.getApplicationContext().getBean(c);
			Class<?>[] parameterTypes = null;
			if (params != null) {
				parameterTypes = new Class<?>[params.length];
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					parameterTypes[i] = param.getClass();
				}
			}
			Method excuteMethod;
			try {
				excuteMethod = mapper.getClass().getMethod(method, parameterTypes);
				Object data;
				data = excuteMethod.invoke(mapper, params);
				return data;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 

	}
	/**
	 * 读取post过来的内容
	 * @param request
	 * @return
	 */
	public static String readStreamParameter(HttpServletRequest request) {		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			ServletInputStream in=request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
}
