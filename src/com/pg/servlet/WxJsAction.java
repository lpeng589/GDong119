package com.pg.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pg.gzh.js.JsConfigBean;
import com.pg.util.Config;
import com.pg.util.ServletUtil;



@Controller
public class WxJsAction {
	/**
	 * 微信js初始化接口
	 * @param code
	 * @param type
	 * @param response
	 */
	@RequestMapping("wxjs.html")
	public void get(HttpServletResponse response,HttpServletRequest request,@RequestParam("url") String url) {
		JsConfigBean bean =new JsConfigBean(Config.getWxKey(), url);
		ServletUtil.sendJsonBean(response, bean);
	}
}
