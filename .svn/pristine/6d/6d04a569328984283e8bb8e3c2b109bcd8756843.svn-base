<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<title>采购供应一体化平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta name="HandheldFriendly" content="true">
	<link rel="icon" href="../images/favicon.ico">
	<link rel="stylesheet" href="../css/common.css">
	<link rel="stylesheet" href="../css/list.css">
	<script src="../js/jquery.js"></script>
	<script src="../js/common.js"></script>
</head>
<body>
    <div class="container">
    	<form id="form" method="post" action="role.htm">
			<input type="hidden" name="operate" value="roleListForSpForSelect" />
			<input type="hidden" id="page" name="page" value="${data.cond.page}" />
    		<div align="center">
    			<table class="vgrid" align="center">
    				<tbody>
    					<tr>
    						<th>ID</th>
    						<td><input class="baisc_input" name="id" value="${data.cond.id}" placeholder="ID"></td>
    						<th>角色名称</th>
    						<td><input class="baisc_input" name="role_name" value="${data.cond.role_name}" placeholder="角色名称"></td>
    						<th>状态：</th>
    						<td>
    							<select class="baisc_select" name="status">
    								<option value="">全部</option>
    								<option value="1">正常使用</option>
    								<option value="0">已禁用</option>
    							</select>
    							<script>$("select[name='status']").val("${data.cond.status}");</script>
    						</td>
    						<td rowspan="1">
    							<input class="baisc_button" value="查询" type="submit">
    						</td>
    					</tr>
    				</tbody>
    			</table>
    		</div>
    	</form>
    	<table class="hgrid" align="center">
    		<thead>
    			<tr>
    				<th></th>
    				<th>角色ID</th>
    				<th>角色名称</th>
    				<th>角色描述</th>
    				<th>状态</th>
    				<th>创建时间</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${data.list}" varStatus="i" var="row">
    			<tr>
    				<td><input name="role_ids" type="radio" value="${row.id }"/></td>
    				<td>${row.id }</td>
    				<td>${row.role_name }</td>
    				<td>${row.description }</td>
    				<td><c:if test="${row.status==0}">已禁用</c:if><c:if test="${row.status==1}">已启用</c:if></td>
    				<td>${row.create_time }</td>

    			</tr>
    			</c:forEach>
			</tbody>
    	</table>
        <jsp:include page="sortpage.jsp"></jsp:include>
	</div>
	
</body>
</html>

