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
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body class="iframebody">
   <div>
        <!-- start 查询条件 -->
        <div class="widget-box" id="list_div">
          <div class="widget-content ">
          <form class="form-horizontal" method="post" action="role.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
            <div class="controls controls-row">
            	<label class="control-label">名称：</label><input name="role_name" value="${data.cond.role_name}" placeholder="请输入要查询的名称" class="span2 m-wrap" maxlength="20">
          		<label class="control-label">描述：</label> <input name="description" value="${data.cond.description}" placeholder="请输入要查询的描述" class="span2 m-wrap">
          		<label class="control-label">角色：</label>
          		<!-- select 空用空格表示-->
          		<div style="width:156px;display:inline-block;margin:0">
          			<select name="status" style="display:none">
                  		<option value=" ">全部</option>
                  		<option value="1">已启用</option>
                  		<option value="0">已注销</option>
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
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
                	<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
                    <th>角色名称</th>
                    <th>角色描述</th>
                    <th>状态</th>
                    <th>创建时间</th>
                <!--     <th>操作</th> -->
                    <th>
						<button class="btn btn-success btn-mini" onclick="add()">新增</button>
						<button class="btn btn-danger btn-mini" onclick="del()">删除</button>
					</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                	<td><input name="ids" type="checkbox" value="${row.id }"/></td>
                    <td>${row.role_name }</td>
                    <td class="minmax">${row.description }</td>
                    <td><c:if test="${row.status==0}">已注销</c:if><c:if test="${row.status==1}">已启用</c:if></td>
                    <td>${row.create_time }</td>
                    <td>
                    	<input class="btn btn-mini btn-info" value="修改" type="button" onclick="update(${row.id })">
                        <a class="btn btn-mini btn-info" href="role.htm?operate=permission&id=${row.id }">设置权限</a>
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
							<form class="form-horizontal">
							<input type="hidden" id="id">
								<div class="control-group">
									<div class="controls">
										<label class="control-label">角色名：</label> 
										<input type="text" class="span7" id="role_name" placeholder="请输入角色名" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">描述 ：</label> 
										<input type="text" class="span7" id="description" placeholder="请输入描述" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">状态 ：</label>
										<div class="select-normal">
											<select id="status" style="display:none">
												<option value="1">启用</option>
												<option value="0">注销</option>
											</select>
										</div>
									</div>
								</div>
						</div>
						<div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="toggleShow();"
								class="btn btn-warn">返回</button>
							<button type="button" onclick="commitEdit();"
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
<script src="../js/common.js"></script>
<script>
$("select[name='status']").val("${data.cond.status}");
</script>
<!-- 增删改查操作 -->
<script type="text/javascript">
	var operate = "";
	function jump(url){
		$("#mainframe").attr("src",url);
	}
		//添加
	function add() {
		operate = "add";
		$("#role_name").val("");
		$("#description").val("");
		$("#status").val(1);
		toggleShow();
	}
		//修改
	function update(id) {
		operate = "update";
		$("#id").val(id);
		toggleShow();
		var url = "role.htm?";
		var obj = {
			operate : "updatepre",
			id : id,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				$("#role_name").val(data.data.role_name);
				$("#role_name").attr("readonly", true);
				$("#description").val(data.data.description);
				$("#status").val(data.data.status);
			} else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	}
		//删除
	function del() {
		operate = "delete";
		var length = $("input[name='ids']:checked").length;
		if (length > 0) {
			confirm(commitEdit, "你确定要删除这" + length + "条记录吗？");
		} else {
			tip("请先选择要删除的记录");
		}

	}
		//提交增删改数据
	function commitEdit() {
		var obj = {};
		if (operate == "add") {
			obj = {
				operate : operate,
				role_name : $("#role_name").val(),
				description : $("#description").val(),
				status : $("#status").val()
			};
		} else if (operate == "update") {
			//	if (!checkInput("username,password")) {
			//		return false;
			//	}
			obj = {
				operate : operate,
				id : $("#id").val(),
				role_name : $("#role_name").val(),
				description : $("#description").val(),
				status : $("#status").val()
			};
		} else if (operate == "delete") {
			var ids = new Array();
			$("input[name='ids']:checked").each(function() {
				ids[ids.length] = this.value;
			});
			obj = {
				operate : operate,
				ids : ids,
			};
		}
			
		var url = "role.htm?";
		var callbackfun = function(data) {
			if (data.code == 0) {
				query();
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
