package com.pg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pg.bean.Area4levelBean;
import com.pg.bean.MsgBean;
import com.pg.mapper.Area4levelMapper;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.ServletUtil;

@Controller
public class Area4levelAction extends BaseAction{

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Area4levelAction.class);
	/**
	 * 根据父编码获取子列
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getArea.html")
	public String getArea(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String parentId = request.getParameter("parentId");
//		logger.error("...parentId=" + parentId);
		if (StringUtils.isBlank(parentId)) {
			throw new NullPointerException("provinceCode is null");
		}
		Area4levelBean c = new Area4levelBean();
		c.setParentId(Integer.valueOf(parentId));//查询父ID为parentId的列
		Area4levelMapper mapper = ApplicationContextUtil.getMapper(Area4levelMapper.class);
		List<Area4levelBean> areas = mapper.list(c);	
		String jsonStr = JSON.toJSONString(areas);
//		logger.error("jsonStr=" + jsonStr);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonStr);
		return null;
	}
	
	/**
	 * 获取所有省份代码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getProvince.html")
	public void getProvince(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Area4levelBean c = new Area4levelBean();
		Area4levelMapper mapper = ApplicationContextUtil.getMapper(Area4levelMapper.class);
		c.setLevel(1);
		List<Area4levelBean> areas = mapper.list(c);
		request.setAttribute("area4LevelMap", areas);
		ServletUtil.sendJsonBean(response, new MsgBean("0", "province success!"));
	}

	@Override
	protected Integer getListCount(Object cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getListData(Object cond) {
		// TODO Auto-generated method stub
		return null;
	}

}
