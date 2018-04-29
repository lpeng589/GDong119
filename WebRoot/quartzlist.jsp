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
</head>
<body class="iframebody">
   <div>
   		
        <!-- start 查询条件 -->
        <br>
        <div class="widget-box" id="list_div" >
          <div class="widget-content ">
                  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5>定时任务设置</h5>
        </div>
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
               		 <th></th>
                    <th>任务名称 </th>
                    <th>描述</th>
                    <th>触发时间</th>
                    <th>状态</th>
<!--                     <th>更新职员</th> -->
                    <th>更新时间</th>
                    <th>操作</th> 
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr><th></th>
                    <td>${row.name }</td>
                    <td>${row.description }</td>
                    <td>${row.triggertime }</td>
                    <td>
                    <c:if test="${row.status==0}">已注销</c:if>
                    <c:if test="${row.status==3}">已启用</c:if>
                    </td>
<!--                     <td>${row.employeeid }</td> -->
                    <td>${row.updatetime }</td>
                    <td>
                    	<input class="btn btn-mini btn-info   <pk:ruleControl module="quartz_update"/>" value="修改" type="button" onclick="update(${row.id })">
                    </td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
            <jsp:include page="sortpage.jsp"></jsp:include>
          </div> 
        </div>
			<div id="edit_div" class="hide">
				<div class="modal-header">
					<button id="jsclosebtn" data-dismiss="modal" class="close"
						type="button" onclick="toggleShow();">返回</button>
					<h3>&nbsp;</h3>
				</div>
				<div id="jsmodelbody" class="modal-body">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>编辑</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" id="commentForm">
							<input type="hidden" id="id">
								<div class="control-group">
									<div class="controls">
										<label class="control-label">任务名称：</label> 
										<input type="text" class="span7" id="name" name="name" placeholder="请输入任务名称" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">描述：</label> 
										<input type="text" class="span7" id="description" placeholder="请输入描述" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">触发时间：</label> 
										<input type="text" class="span7" id="triggertime" placeholder="请输入触发时间" value="" disabled="disabled">
									</div>
								</div>
												<div class="control-group">
									<div class="controls">
										<label class="control-label">状态 ：</label>
										<div class="select-normal">
											<select id="status" style="display:none" >
												<option value="3">启用</option>
												<option value="0">注销</option>
											</select>
										</div>
									</div>
								</div>
						</div>
						<div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="toggleShow();"
								class="btn btn-warn">返回</button>
							<button type="submit" 
								class="btn btn-success">保存</button>
						</div>
						</form>
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
<script src="../js/common.js?a=1"></script>
<script src="../js/jquery.metadata.js"></script>
<script src="../js/jquery.validate.js"></script>
<script>
$(document).ready(function() {
	 $("#commentForm").validate(
			 {
					rules: {
						name: {
							 required: true,
						},
						triggertime: {
							 required: true,
						},
					},
					messages: {
						name:{
						    required: "请输入任务名称",
						   },
						   triggertime:{
							    required: "请输入触发时间",
						},
					},
					
					errorClass: 'error-tips',

					submitHandler: function() {
						commitEdit();
					}
			 }); 
});
</script>
<!-- 增删改查操作 -->
<script type="text/javascript">
	var operate = "";
	function jump(url){
		$("#mainframe").attr("src",url);
	}

		//修改
	function update(id) {
		operate = "update";
		$("#id").val(id);
		toggleShow();
		var url = "quartz.htm?";
		var obj = {
			operate : "updatepre",
			id : id,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				$("#name").val(data.data.name);
				$("#description").val(data.data.description);
				$("#triggertime").val(data.data.triggertime);
				if(data.data.status==0){
					$("#status").prev().find("span").text("注销");
				}else{
					$("#status").prev().find("span").text("启用");
				}
			} else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	}
	
		//提交增删改数据
	function commitEdit() {
		var obj = {};
		if (operate == "update") {
			obj = {
				operate : operate,
				id : $("#id").val(),
				name : $("#name").val(),
				description : $("#description").val(),
				triggertime : $("#triggertime").val(),
				status : $("#status").val()
			};
		}
			
		var url = "quartz.htm?";
		var callbackfun = function(data) {
			if (data.code == 0) {
                window.location.href = "quartz.htm?operate=list";
			} else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	};
	
	var toggleFlag=0;
	function toggleShow(){
		if(toggleFlag==0){
			$("#list_div").hide();
			$("#edit_div").show();
			toggleFlag=1;
		}else{
			$("#edit_div").hide();
			$("#list_div").show();
			toggleFlag=0;
		}
	}
</script>
 </body>

</html>
