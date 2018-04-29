<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Pnkoo Demo</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="../css/uniform.css" />
<link rel="stylesheet" href="../css/select2.css" />
<link rel="stylesheet" href="../css/matrix-style.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/common.css" />
<script src="../js/jquery.min.js"></script> 
<script src="../js/bootstrap.min.js"></script> 
<script src="../js/jquery.uniform.js"></script> 
<script src="../js/select2.min.js"></script> 
<script src="../js/jquery.dataTables.min.js"></script> 
<script src="../js/matrix.js"></script> 
<script src="../js/matrix.tables.js"></script>
<script src="../js/common.js"></script>
</head>
<style>
.iframebody {
    background: none repeat scroll 0 0 #040b1c;
}
</style>
<body class="iframebody" style="background-image: url(./images/backgrounds/workbg2.jpg);background-repeat:no-repeat;background-size:100%">
 </body>
 <%-- <h1 style="color:#27a9e3;margin-top:40px;padding-left:10%;">新的一天</h1>
 <div style="margin-top:20px;padding-left:10%;">
 	<div style="margin-top:50px;">
		<c:forEach items="${list}" varStatus="i" var="row">
			<c:if test="${row.workcount!=0}">
			<span style="font-size:22px;color:#0789c3;">您共有</span>
			<span style="font-size:22px;color:#606060;">${row.name}</span>
			<span style="font-size:22px;color:#0789c3;">数据</span>
			<span style="font-size:32px;color:red;">${row.workcount}</span>
			<span style="font-size:22px;color:#0789c3;">条待受理，</span>
			<a href="${row.menuurl}"><span style="font-size:22px;color:#fd8637;">点击进入</span></a><br/>
			</c:if>
		</c:forEach>
	</div> --%>
	<!-- 工作流审核 -->
	<%-- <div style="margin-top:50px;">
		<c:forEach items="${flownodelist}" varStatus="i" var="row">
			<c:if test="${row.count!=0}">
			<span style="font-size:22px;color:#0789c3;">您共有</span>
			<span style="font-size:22px;color:#606060;">${row.remark}</span>
			<span style="font-size:22px;color:#0789c3;">数据</span>
			<span style="font-size:32px;color:red;">${row.count}</span>
			<span style="font-size:22px;color:#0789c3;">条待审核，</span>
			<a  href="${row.linkurl}${row.statusnow}"><span style="font-size:22px;color:#fd8637;">点击进入</span></a><br/>
			</c:if>
		</c:forEach>
	</div> --%>
	
</div>
</html>
