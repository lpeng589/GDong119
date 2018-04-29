<%@page import="com.pg.util.Check"%>
<%@page import="com.pg.util.OSSFileManagerJson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%-- <%@ page import="org.json.simple.*" %> --%>
<%

/**
 * KindEditor JSP
 *
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 *
 */

	OSSFileManagerJson ossManager = new OSSFileManagerJson();
	String json = ossManager.write(request,response);
	response.setContentType("application/json; charset=UTF-8");
	out.println(json);

%>
