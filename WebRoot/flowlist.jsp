<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/pnkootag"  prefix="pk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>重庆消防</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="../css/bootstrap.min.css?v=20160407" />
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="../css/uniform.css" />
<link rel="stylesheet" href="../css/select2.css" />
<link rel="stylesheet" href="../css/matrix-style.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/common.css" />
</head>
<body class="iframebody">
   <div>
   		  <form class="form-horizontal" method="post" action="flow.htm">
          	<input type="hidden" name="operate" value="list" />
          </form>
        <!-- start 查询条件 -->
        <div class="widget-box" id="list_div">
          <div class="widget-content " >
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
                	<th></th>
                   	<th>工作流名称</th>
                    <th>简介</th>
                    <th>操作</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                	<td></td>
                	<td>${row.name }</td>
				    <td  class="maxtd">${row.remark }</td>
                     <td>
                     <a class="btn btn-mini btn-info <pk:ruleControl module="flownode_list"/>"  href="flownode.htm?operate=list&flowid=${row.id}&flownodeid=${row.firstnodeid}">详情</a>
                     </td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
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
<script src="../js/jquery.metadata.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- 增删改查操作 -->
<script type="text/javascript">
</script>
 </body>

</html>
