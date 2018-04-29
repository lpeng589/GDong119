package com.pg.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pg.gzhqy.js.JsConfigBean;
import com.pg.util.Config;
import com.pg.util.ServletUtil;



@Controller
public class QyWxJsAction {
	/**
	 * 微信js初始化接口
	 * @param code
	 * @param type
	 * @param response
	 */
	@RequestMapping("qywxjs.htm")
	public void get(HttpServletResponse response,HttpServletRequest request,@RequestParam("url") String url) {
		JsConfigBean bean =new JsConfigBean(Config.getQyWxKey(), url);
		ServletUtil.sendJsonBean(response, bean);
	}
}
