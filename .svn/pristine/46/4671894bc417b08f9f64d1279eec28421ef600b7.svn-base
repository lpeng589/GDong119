<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>重庆消防</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="../css/uniform.css" />
<link rel="stylesheet" href="../css/select2.css" />
<link rel="stylesheet" href="../css/matrix-style.css" />
<link rel="stylesheet" href="../css/datepicker.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body class="iframebody">
<div>
        <!-- start 查询条件 -->
        <div class="widget-box" >
          <div class="widget-content ">
          <form class="form-horizontal" method="post" action="flowLog.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
            <div class="controls controls-row">
            	<label class="control-label">节点简介：</label><input name="remark"  placeholder="节点简介" value="${data.cond.remark }" class="span2 m-wrap">
            	<label class="control-label">开始时间：</label><input type="text" style="width: 156px" data-date-format="yyyy-mm-dd" class="datepicker span11" name="starttime"  value="${data.cond.starttime }">
                <label class="control-label">结束时间：</label><input type="text" style="width: 156px" data-date-format="yyyy-mm-dd" class="datepicker span11" name="endtime" value="${data.cond.endtime }" >
            	<label class="control-label">节点类型：</label>
          		<!-- select 空用空格表示-->
          		<div style="width:156px;display:inline-block;margin:0">
          			<select name="issys" style="display:none">
                  			<option value =""></option>
							<option value ="0">系统节点</option>
							<option value ="1">用户自定义节点</option>
                	</select>
                </div>
                <!-- select-->
          	</div>
          	 <div class="controls controls-row">
          	 <label class="control-label">工作流类型：</label>
          		<!-- select 空用空格表示-->
          		<div style="width:222px;display:inline-block;margin:0">
          			<select name="flowid" style="display:none">
                  			<option value ="">全部</option>
                  			 <c:forEach items="${flowlist}" varStatus="i" var="row">
							<option value ="${row.id}">${row.name}</option>
							</c:forEach>
                	</select>
                </div>
          	 </div>
          	<!-- end 条件输入框div-->
          	
          	<!-- start 查询按钮div -->
          	<div class="controls controls-row pagination-right">
              <button type="submit" class="btn btn-primary">查询</button>
            </div>
            <!-- end 查询按钮div-->
          </form>
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check" style="word-wrap: break-word; word-break: break-all;">
              <thead>
                <tr>
                		<th style="white-space:nowrap">工作流类型</th>
                   		<th>状态</th>
						<th>审核或提交职员</th>
						<th>审核或提交意见</th>
						<th>节点类型</th>
						<th>节点简介</th>
						<th>节点描述</th>
						<th>附件</th>
						<th>创建时间</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
              			<td style="white-space:nowrap">${row.flowname}</td>
                   		<td>
							<c:if test="${row.status==0 }">首次提交</c:if>
							<c:if test="${row.status==1 }">审核通过</c:if>
							<c:if test="${row.status==2 }">审核不通过</c:if>
						</td>
						<td>${row.username}</td>
						<td  class="minmax">${row.opinion}</td>
						<td>
							<c:if test="${row.issys==0 }">系统节点</c:if>
							<c:if test="${row.issys==1 }">用户自定义节点</c:if>
						</td>
						<td  class="minmax">${row.remark}</td> 
						<td  class="minmax">${row.descriptionnow}</td>  
						<td>
						<c:if test="${not empty row.file}">
						<a ccc="fileurl" style="color:blue;font-size:12px;text-decoration:underline" href="<%=request.getSession().getAttribute("imageRootPath")%>${row.file}">
						查看附件
						</a>
						</c:if>
						 
						</td> 
						<td>${row.createtime}</td>   	
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
           <jsp:include page="sortpage.jsp"></jsp:include>
          </div>
        </div>
        </div>
<script src="../js/jquery.min.js"></script> 
<script src="../js/bootstrap.min.js"></script> 
<script src="../js/jquery.uniform.js"></script> 
<script src="../js/select2.min.js"></script> 
<script src="../js/jquery.dataTables.min.js"></script> 
<script src="../js/matrix.js"></script> 
<script src="../js/matrix.tables.js"></script>
<script src="../js/common.js"></script>
<script src="../js/bootstrap-datepicker.js"></script> 
<script src="../js/bootstrap-colorpicker.js"></script> 
<script src="../js/matrix.form_common.js"></script> 
<script>$("select[name='issys']").val("${data.cond.issys}");</script>
</body>
</html>

