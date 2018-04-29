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
<link rel="stylesheet" href="../css/common.css?a=12" />
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
          <a class="btn btn-warning btn-big auto"  href="product.htm?operate=list">返回</a>
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
               		 <th>错误行</th>
                    <th>错误信息</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${errlist}" varStatus="i" var="row">
              	<tr>
              	 <td>${row.flag_int}</td>
              	 <td>${row.flag_base}</td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
          </div> 
          <div id="importmodel" class="modal hide">
			<div class="modal-header">
				<button data-dismiss="modal" class="close" type="button" onclick="showimport()">×</button>
				<h3>&nbsp;</h3>
			</div>
			<div class="modal-body">
				<div class="widget-box">
					<form class="form-horizontal" id="importForm" method="post" action="product.htm?operate=import" enctype="multipart/form-data">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i></span>
							<h5>编辑</h5>
						</div>
						<div class="widget-content">
							<div class="control-group">
								<div class="controls">
									<label class="control-label">选择文件 ：<span
										style="color:red"> *</span></label> <input type="file" name="filein"/>
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions pagination-right">
						<button type="button" onclick="importSubmitPre()" class="btn btn-success">导入</button>
					</div>
				</div>
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
<script type="text/javascript">
function importSubmitPre(){
	//检查
	importSubmit();
}
function importSubmit(){
	$("#importForm").submit();
}
</script>
 </body>

</html>
