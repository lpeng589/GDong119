<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/pnkootag"  prefix="pk"%>
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
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/datepicker.css" />
<style>
.auto{
	margin-left:40%;
}
</style>
</head>
<body class="iframebody">
   <div>
   		
        <!-- start 查询条件 -->
        <div class="widget-box" id="list_div">
          <div class="widget-content ">
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
               		 <th style="width:80px;">字段名称</th>
                	<th>修改前</th>
                   	<th>修改后</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${list}" varStatus="i" var="row">
              	<tr>
              		<td><pk:getCompareName keyname="${keyname}" fieldname="${row.valuekey}"/></td>
              		<td><pk:getCompareValue fieldvalue="${row.valuepre}" fieldname="${row.valuekey}"/></td>
                	<td><pk:getCompareValue fieldvalue="${row.value}" fieldname="${row.valuekey}"/></td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
          </div> 
          	<div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="closes();"
								class="btn btn-warn">关闭</button>
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
<script type="text/javascript">
function closes(){
	window.opener=null;
	window.open('','_self');
	window.close();
}


</script>
 </body>

</html>
