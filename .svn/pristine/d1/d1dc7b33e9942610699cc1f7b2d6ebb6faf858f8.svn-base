package com.pg.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.pg.bean.MsgBean;
import com.pg.gzh.oauth2.Oauth2;


public class CopyOfPgFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,ServletException {
		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
//		try {
//			String str = request.getRequestURI();
//		//	if (str.endsWith(".htm")||str.endsWith("/")){
////				//test
////				//UserSearchBean bean1=ApplicationContextUtil.getMapper(UserMapper.class).getById(1);
////				//setUser(request, bean1);
////				//
////				//判断登录
////				UserSearchBean bean = getUser(request);
////				if(bean!=null){
////					request.setAttribute("local_user", bean);
////					if(str.endsWith("index.htm")||str.endsWith("/")){
////						//来自于首页
////						request.setAttribute("defaultPage", "discovery");
////						if(bean.getToken()!=null){
////							request.setAttribute("token", bean.getToken());
////						}
////					}
////					chain.doFilter(req, resp);
////					return;
////				}else{
////					//判断是否来自于微信
////					if(broswer.equals(Constant.REQUEST_WECHAT)){
////						String reqUrl = request.getQueryString()==null? request.getRequestURL().toString()  : (request.getRequestURL().toString() + "?"+ request.getQueryString());
////						String codeUrl=new Oauth2(Config.getWxKey()).getCodeUrl(reqUrl, "snsapi_base", "base");
////						response.sendRedirect(codeUrl);
////						return;
////					}
////					if(str.endsWith("index.htm")||str.endsWith("/")){
////						//来自于首页
////						request.setAttribute("defaultPage", "login");
////						chain.doFilter(req, resp);
////						return;
////					}
////				}
////				response.setHeader("Cache-Control", "no-cache");
////				response.setHeader("Pragma", "no-cache");
////				response.setDateHeader("Expires", -1);
//////				String returntype = request.getHeader("returntype");
////				response.setCharacterEncoding("utf-8");
////				response.getWriter().println(new Gson().toJson(new MsgBean("-1000", "尚未登录")));
//////				if (returntype != null && returntype.equals("ajax/json")) {
//////					response.setCharacterEncoding("utf-8");
//////					response.getWriter().println(new Gson().toJson(new MsgBean("-1000", "尚未登录")));
//////				} else{
//////					req.getServletContext().getRequestDispatcher("/index.htm").forward(req, resp);
//////				}
//				return;
//			//}
//			chain.doFilter(req, resp);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	/**
	 * 判断是什么浏览器
	 * @param request
	 */
	public static String broswer(HttpServletRequest request) {
		String info = request.getHeader("user-agent").toLowerCase();
		String broswer=Dict.REQUEST_PC;//默认pc
		if(info.indexOf("micromessenger")>=0){
			//来自于微信
			broswer=Dict.REQUEST_WECHAT;
		}else if (info.indexOf("koron_android")>=0) {
			//来自于安卓
			broswer=Dict.REQUEST_ANDROID;
		}else if (info.indexOf("koron_ios")>=0) {
			//来自于ios
			broswer=Dict.REQUEST_IOS;
		}
		return broswer;
	}
	
}
