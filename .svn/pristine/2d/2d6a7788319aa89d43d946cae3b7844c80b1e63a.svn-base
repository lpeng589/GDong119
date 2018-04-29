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

import com.google.gson.Gson;
import com.pg.bean.MsgBean;
import com.pg.bean.UserBean;
import com.pg.mapper.H_workuserMapper;
import com.pg.mapper.UserMapper;
import com.pg.searchbean.H_workuserSearchBean;


public class PgFilter implements Filter {
	@Override
	public void destroy() {
	}
	/**
	 * 存放小程序与第三方服务器的通讯数据
	 */
	//public static HashMap<String, UserBean> WXMAP = new HashMap<>();
	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,ServletException {
		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
//		System.out.println("...........................................");
		try {
			String str = request.getRequestURI();
//			if (str.endsWith(".htm")||str.endsWith("/")){
				String _code=request.getParameter("_code");
				String token = request.getParameter("logintoken");
				
//				System.out.println("--------_code-------"+_code);
//				System.out.println("--------token-------"+token);
//				H_workuserSearchBean userSearchBean2 = ApplicationContextUtil.getMapper(H_workuserMapper.class).getByCode("22F4621206B5209BB1BB1358CF323039");
//				setWorkUser(request, userSearchBean2);
				
				if(_code==null||_code.isEmpty()){
					if(broswer(request).equals(Constant.REQUEST_WECHAT)){
						// 获取授权回调后的用户信息
//						String state=request.getParameter("state");
						String[] states=request.getParameterValues("state");
						String[] codes=request.getParameterValues("code");
//						System.out.println("--------states-------"+states);
//						System.out.println("--------codes-------"+codes);
//						String code=request.getParameter("code");
						if(states!=null&&codes!=null&&states.length>0&&codes.length>0){
							String state=states[states.length-1];
							String code = codes[codes.length-1];
//							System.out.println("--------state-------"+state);
//							System.out.println("--------code-------"+code);
							if(state.equals("workbase")&&getWorkUser(request)==null){
								com.pg.gzhqy.oauth2.Oauth2 qyoauth2= new com.pg.gzhqy.oauth2.Oauth2(Config.getQyWxKey());
								com.pg.gzhqy.oauth2.OauthUser oauthuser= qyoauth2.getOauth(code);
								if(oauthuser!=null&&oauthuser.getErrcode().equals("0")){
//									System.out.println("------oauthuser.getUserId()--"+oauthuser.getUserId());
									H_workuserSearchBean userSearchBean = ApplicationContextUtil.getMapper(H_workuserMapper.class).getByCode(oauthuser.getUserId());
									if(userSearchBean==null||userSearchBean.getStatus()==0){
										response.sendRedirect("error.html");
										return;
									}
//									System.out.println("-------------success--------------------");
									setWorkUser(request, userSearchBean);
								}else {
									response.sendRedirect("error.html");
									return;
								}
							}
						}else {
							if(str.endsWith(".do")&&getWorkUser(request)==null){
								String reqUrl = request.getQueryString()==null? request.getRequestURL().toString()  : (request.getRequestURL().toString() + "?"+ request.getQueryString());
//								System.out.println("-------------reqUrl--------------------"+reqUrl);
								String codeUrl=new com.pg.gzhqy.oauth2.Oauth2(Config.getQyWxKey()).getCodeUrl(reqUrl, "workbase");
//								System.out.println("-------------codeUrl--------------------"+codeUrl);
								response.sendRedirect(codeUrl);
								return;
							}
						}
					}
				}else {
//					if(getOpenid(request)==null||"".equals(getOpenid(request))){
						if(!checkcode(request,response,_code))
							return;//微信小程序
//					}
					
				}
				request.setAttribute("url", Config.getValue("url"));
				if(str.endsWith(".do")){
					checkWorkUser(request, response, chain);
				}else{
					chain.doFilter(req, resp);
				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	/**
	 * 企业微信用户 
	 * @param request
	 * @param response
	 * @param chain
	 */
	public void checkWorkUser(HttpServletRequest request,HttpServletResponse response, FilterChain chain){
		try {
//			System.out.println("-------------checkWorkUser--------------------");
			//test
//			H_workuserSearchBean bean1= ApplicationContextUtil.getMapper(H_workuserMapper.class).getByPhone("13450256178");
//			setWorkUser(request, bean1);
			
//			H_workuserSearchBean bean2= ApplicationContextUtil.getMapper(H_workuserMapper.class).getByPhone("15815040187");
//			setWorkUser(request, bean2);
//			H_workuserSearchBean bean3= ApplicationContextUtil.getMapper(H_workuserMapper.class).getByPhone("15310475536");
//			setWorkUser(request, bean3);
			
			//判断登录
			H_workuserSearchBean bean = getWorkUser(request);
			if(bean!=null){
				//判断是否登陆过
				if(bean.getIslogin()==null||bean.getIslogin()==0){
					H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
					bean.setIslogin(1);
					h_workuserMapper.updateH_workuser(bean);
				}
				request.setAttribute("local_workuser", bean);
				chain.doFilter(request, response);
				return;
			}else{
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", -1);
				String returntype = request.getHeader("returntype");
				if (returntype != null && returntype.equals("ajax/json")) {
					response.setCharacterEncoding("utf-8");
					response.getWriter().println(new Gson().toJson(new MsgBean("-1000", "尚未登录")));
					return;
				}else{
					response.sendRedirect("error.html");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断是不是小程序
	 * @throws UnsupportedEncodingException 
	 */
	public static Boolean checkcode(HttpServletRequest request,HttpServletResponse response,String code) throws UnsupportedEncodingException{
//		UserBean bean = getUser(request);
		UserBean bean = new UserBean();
		UserMapper userMapper=ApplicationContextUtil.getMapper(UserMapper.class);
//		if(bean==null){
			String token = request.getParameter("logintoken");
//			token = "5EAE76F232AE12D79003EE5B336EB4CB";
//			System.out.println("token:-----------------"+token);
			if (token==null||"".equals(token)) {
				ServletUtil.sendJsonBean(response, new MsgBean("-1000","logintoken为空"));

				return false;
			}
			bean = userMapper.getByToken(token);
			bean.setNickname(new String(bean.getNickname().getBytes("iso8859-1"),"utf-8"));
			if (bean==null) {
				ServletUtil.sendJsonBean(response, new MsgBean("-1000","logintoken错误"));
				return false;
			}
			if (bean.getTokentime().getTime()<System.currentTimeMillis()) {
				ServletUtil.sendJsonBean(response, new MsgBean("-1000","logintoken已过期"));
				return false;
			}
			setUser(request, bean);
		/*}else {
			if (bean.getTokentime().getTime()<System.currentTimeMillis()) {
				ServletUtil.sendJsonBean(response, new MsgBean("-1","logintoken已过期"));
				return false;
			}
		}*/
		return true;
	}
	
	
	/**
	 * 判断是什么浏览器
	 * @param request
	 */
	public static String broswer(HttpServletRequest request) {
		String info = request.getHeader("user-agent").toLowerCase();
		String broswer=Constant.REQUEST_PC;//默认pc
		if(info.indexOf("micromessenger")>=0){
			//来自于微信
			broswer=Constant.REQUEST_WECHAT;
		}else if (info.indexOf("koron_android")>=0) {
			//来自于安卓
			broswer=Constant.REQUEST_ANDROID;
		}else if (info.indexOf("koron_ios")>=0) {
			//来自于ios
			broswer=Constant.REQUEST_IOS;
		}
		return broswer;
	}
	
	/**
	 * 设置openid到session
	 * @param request
	 * @param bean
	 */
	public static void setOpenid(HttpServletRequest request,String openid){
		request.getSession().setAttribute("mobile_openid_common",openid);
	}
	/**
	 * 返回openid session
	 * @param request
	 * @return
	 */
	public static String getOpenid(HttpServletRequest request){
		return (String)request.getSession().getAttribute("mobile_openid_common");
	}
	
	/**
	 * 返回用户信息session
	 * @param request
	 * @return
	 */
	public static UserBean getUser(HttpServletRequest request){
		String logintoken = request.getParameter("logintoken");
		if(logintoken==null||logintoken.isEmpty()){
			return (UserBean) request.getSession().getAttribute("mobile_user_map");
		}else {
			UserMapper userMapper=ApplicationContextUtil.getMapper(UserMapper.class);
			return userMapper.getByToken(logintoken);
		}
		
	}
	
	/**
	 * 设置用户信息到session
	 * @param request
	 * @param bean
	 */
	@SuppressWarnings("unchecked")
	public static void setUser(HttpServletRequest request,UserBean bean){
		request.getSession().setAttribute("mobile_user_map",bean);
	}
	
	
	/**
	 * 过滤掉utf8mb4
	 * @param text
	 * @return
	 */
	 public static String filterOffUtf8Mb4(String text){	
		if(text==null||text.equals("")){
			return "";
		}
		 try {
			byte[] bytes;
			bytes = text.getBytes("utf-8");
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[i++]);
					continue;
				}
				b += 256;
				if ((b ^ 0xC0) >> 4 == 0) {
					buffer.put(bytes, i, 2);
					i += 2;
				} else if ((b ^ 0xE0) >> 4 == 0) {
					buffer.put(bytes, i, 3);
					i += 3;
				} else if ((b ^ 0xF0) >> 4 == 0) {
					i += 4;
				}
			}
			buffer.flip();
			return new String(buffer.array(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";	
	}
		/**
		 * 刷新session企业微信用户信息
		 * @param request
		 */
		public static void refreshWorkUser(HttpServletRequest request){
			H_workuserSearchBean bean = (H_workuserSearchBean) request.getSession().getAttribute("mobile_workuser_common");
			H_workuserMapper mapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
			setWorkUser(request, mapper.getById(bean));
		}
		/**
		 * 设置企业微信用户信息到session
		 * @param request
		 * @param bean
		 */
		public static void setWorkUser(HttpServletRequest request,H_workuserSearchBean bean){
			request.getSession().setAttribute("mobile_workuser_common",bean);
		}
		/**
		 * 清空企业微信用户信息session
		 * @param request
		 * @param bean
		 */
		public static void removeWorkUser(HttpServletRequest request){
			request.getSession().removeAttribute("mobile_workuser_common");
		}
		/**
		 * 返回企业微信用户信息session
		 * @param request
		 * @return
		 */
		public static H_workuserSearchBean getWorkUser(HttpServletRequest request){
			return (H_workuserSearchBean) request.getSession().getAttribute("mobile_workuser_common");
		}
}
