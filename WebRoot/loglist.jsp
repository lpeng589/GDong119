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
          <form class="form-horizontal" method="post" action="log.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
            <div class="controls controls-row">
            	<label class="control-label">操作人：</label><input name="loginname"  placeholder="请输入操作人账号" value="${data.cond.loginname }" class="span2 m-wrap">
            	<label class="control-label">开始时间：</label><input type="text" style="width: 156px" data-date-format="yyyy-mm-dd" class="datepicker span11" name="starttime"  value="${data.cond.starttime }">
                <label class="control-label">结束时间：</label><input  type="text" style="width: 156px" data-date-format="yyyy-mm-dd" class="datepicker span11" name="endtime" value="${data.cond.endtime }" >
            	<label class="control-label">所属模块：</label>
          		<!-- select 空用空格表示-->
          		<div style="width:156px;display:inline-block;margin:0">
                	<select name="category" style="display:none">
                  			<option value ="">全部</option>
                  			 <c:forEach items="${modulelist}" varStatus="i" var="row">
							<option value ="${row.category}">${row.category}</option>
							</c:forEach>
                	</select>
                	
                </div>
                
                <!-- select-->
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
                		<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
                   		<th>所属模块</th>
						<th>相关联ID</th>
						<th>操作描述</th>
						<th>操作人</th>	
						<th>操作人IP</th>
						<th>操作时间</th>
						<th style="width:20%">改后值</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                		<td><input name="ids" type="checkbox" value="${row.id }"/></td>
                   		<td>${row.category}</td>
						<td>${row.relatedid}</td>
						<td class="minmax">${row.description}</td>
						<td>${row.loginname}</td>
						<td>${row.ip}</td>
						<td>${row.operatetime}</td> 
						<td class="minmax">${row.value}</td>   	
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
</body>
<script>$("select[name='category']").val("${data.cond.category}");</script>
</html>

