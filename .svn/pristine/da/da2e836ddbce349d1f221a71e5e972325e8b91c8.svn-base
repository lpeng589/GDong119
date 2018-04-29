<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/pnkootag"  prefix="pk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>盘酷管理后台</title>
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
        <div class="widget-box" id="list_div">
          <div class="widget-content ">
          <form class="form-horizontal" method="post" action="alarm.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
              <div class="controls controls-row">
            	<label class="control-label">昵称：</label><input name="nickname" value="${data.cond.nickname}" placeholder="请输入要查询的昵称" class="span2 m-wrap">
          		<label class="control-label floatleft">状态：</label>
          		<!-- select 空用空格表示-->
          		<div style="float:left;width:156px;display:inline-block;margin:0">
          			<select name="status" style="display:none">
                  		<option value=" ">全部</option>
                  		<option value="2">已完成</option>
                  		<option value="1">受理中</option>
                  		<option value="0">未受理</option>
                	</select>
                </div>
          	</div>
           
          	<!-- end 条件输入框div-->
          	
          	<!-- start 查询按钮div -->
          	<div class="controls controls-row pagination-right">
               <button onclick="query()" class="btn btn-primary">查询</button>
            </div>
            <!-- end 查询按钮div-->
          </form>
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
                	<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
								  <th>报警状态</th>
								  <th>报警人</th>
								  <th>报警时间</th>
								  <th>处理时间</th>
								  <th>处理人</th>
                  <th>操作
<!-- 						  <button class="btn btn-success btn-mini <pk:ruleControl module="alarm_add"/>"  onclick="add()">新增</button> -->
<!-- 					    <button class="btn btn-danger btn-mini <pk:ruleControl module="alarm_delete"/>" onclick="delmany()">删除</button> -->
					</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                	<td><input name="ids" type="checkbox" value="${row.id}" /></td>
								  <th><c:if test="${row.status==0}">未受理</c:if><c:if test="${row.status==1}">已受理</c:if> <c:if test="${row.status==2}">已完成</c:if>  </th>
								  <th>${row.nickname}</th>
								  <th>${row.insertTime}</th>
								  <th>${row.updateTime}</th>
								  <th>${row.username}</th>
                   <td>
                   <c:if test="${row.status==0}"><input class="btn btn-mini btn-info  <pk:ruleControl module="alarm_update"/>" value="受理" type="button" onclick="start(${row.id})"></c:if>
                   <c:if test="${row.status==1}"><input class="btn btn-mini btn-info  <pk:ruleControl module="alarm_update"/>" value="完成受理" type="button" onclick="end(${row.id})"></c:if>
                   <input class="btn btn-mini btn-info  <pk:ruleControl module="alarm_update"/>" value="报警详情" type="button" onclick="location.href='alarmdetail.htm?operate=list&alarmid=${row.id}'">
<!--                     		<input class="btn btn-mini btn-info  <pk:ruleControl module="alarm_update"/>" value="修改" type="button" onclick="update(${row.id})"> -->
<!--                     		<input class="btn btn-mini btn-info  <pk:ruleControl module="alarm_delete"/>" value="删除" type="button" onclick="del(${row.id})"> -->
                   </td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
            <jsp:include page="sortpage.jsp"></jsp:include>
          </div> 
        </div>
<!-- 			<div id="edit_div" class="hide"> -->
<!-- 				<div class="modal-header"> -->
<!-- 					<button id="jsclosebtn" data-dismiss="modal" class="close" type="button" onclick="toggleShow();">返回</button> -->
<!-- 					<h3>&nbsp;</h3> -->
<!-- 				</div> -->
<!-- 				<div id="jsmodelbody" class="modal-body"> -->
<!-- 					<div class="widget-box"> -->
<!-- 						<div class="widget-title"> -->
<!-- 							<span class="icon"> <i class="icon-align-justify"></i> -->
<!-- 							</span> -->
<!-- 							<h5>编辑</h5> -->
<!-- 						</div> -->
<!-- 						<div class="widget-content nopadding"> -->
<!-- 							<form class="form-horizontal" id="commentForm"> -->
<!-- 							<input type="hidden" id="id"> -->
								
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="control-label">status：</label>  -->
<!-- 										<input type="text" class="span7" id="status" name="status" placeholder="请输入status" value=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="control-label">type：</label>  -->
<!-- 										<input type="text" class="span7" id="type" name="type" placeholder="请输入type" value=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="control-label">insertTime：</label>  -->
<!-- 										<input type="text" class="span7" id="insertTime" name="insertTime" placeholder="请输入insertTime" value=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="control-label">updateTime：</label>  -->
<!-- 										<input type="text" class="span7" id="updateTime" name="updateTime" placeholder="请输入updateTime" value=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="control-label">employid：</label>  -->
<!-- 										<input type="text" class="span7" id="employid" name="employid" placeholder="请输入employid" value=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="control-label">userid：</label>  -->
<!-- 										<input type="text" class="span7" id="userid" name="userid" placeholder="请输入userid" value=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 						</div> -->
<!-- 						<div class="form-actions pagination-right" style="text-align:center"> -->
<!-- 						<button type="button"  onclick="toggleShow();" class="btn btn-warn">返回</button> -->
<!-- 						<button type="submit" class="btn btn-success">保存</button> -->
<!-- 						</div> -->
<!-- 						</form> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
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
<script>
$("select[name='status']").val("${data.cond.status}");
</script>
<script>
$(document).ready(function() {
	 $("#commentForm").validate(
			 {
					rules: {
						status: {
							 required: true,
						},
						type: {
							 required: true,
						},
						insertTime: {
							 required: true,
						},
						updateTime: {
							 required: true,
						},
						employid: {
							 required: true,
						},
						userid: {
							 required: true,
						},
					},
					messages: {
						status:{
						    required: "请输入status",
						   },
						type:{
						    required: "请输入type",
						   },
						insertTime:{
						    required: "请输入insertTime",
						   },
						updateTime:{
						    required: "请输入updateTime",
						   },
						employid:{
						    required: "请输入employid",
						   },
						userid:{
						    required: "请输入userid",
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
	var operate_id = "";
	//添加
// 	function add() {
// 		operate = "add";
// 		$("#status").val("");
// 		$("#type").val("");
// 		$("#insertTime").val("");
// 		$("#updateTime").val("");
// 		$("#employid").val("");
// 		$("#userid").val("");
// 		toggleShow();
// 	}
	//修改
// 	function update(id,status) {
// 		operate_id =id;
// 		toggleShow();
// 		operate = "update";
// 		var url = "alarm.htm?";
// 		var obj = {
// 			operate : "updatepre",
// 			id : id,
// 		};
// 		var callbackfun = function(data) {
// 			if (data.code == 0) {
// 				$("#status").val(data.data.status);
// 				$("#type").val(data.data.type);
// 				$("#insertTime").val(data.data.insertTime);
// 				$("#updateTime").val(data.data.updateTime);
// 				$("#employid").val(data.data.employid);
// 				$("#userid").val(data.data.userid);
// 			}else {
// 				tip(data.msg);
// 			}
// 		};
// 		commitData(url, obj, callbackfun);
// 	}
	//批量
// 	function del(id) {
// 		operate_id=id;
// 		operate = "delete";
// 		confirm(commitEdit, "你确定要删除这条记录吗？");
// 	}
	//批量删除
// 	function delmany() {
// 		operate = "deletemany";
// 		var checks= getChecks("ids");
// 		length=0;
// 		for(var i=0;i<checks.length;i++){
// 			length++;
// 		}
// 		if (length > 0) {
// 			confirm(commitEdit, "你确定要删除这" + length + "条记录吗？");
// 		} else {
// 			tip("请勾选要删除的记录");
// 		}
// 	}
	
	//提交增删改数据
	function commitEdit(id) {
		var obj = {};
		var ids = getIds("ids");
		if (operate == "add") {//新增
			obj = {
				operate : operate,
				status : $("#status").val(),
				type : $("#type").val(),
				insertTime : $("#insertTime").val(),
				updateTime : $("#updateTime").val(),
				employid : $("#employid").val(),
				userid : $("#userid").val(),
			};
		}else if (operate == "update") {
			obj = {
					operate : operate,
					id:operate_id,
					status : $("#status").val(),
					type : $("#type").val(),
					insertTime : $("#insertTime").val(),
					updateTime : $("#updateTime").val(),
					employid : $("#employid").val(),
					userid : $("#userid").val(),
				};
		}else if (operate == "deletemany") {
			obj = {
					operate : operate,
					ids : ids,
				};
		}else if (operate == "delete") {
			obj = {
					operate : operate,
					id : operate_id,
				};
		}else if (operate == "start") {
			obj = {
					operate : operate,
					id : operate_id,
				};
		}else if (operate == "end") {
			obj = {
					operate : operate,
					id : operate_id,
				};
		}
		
		var url = "alarm.htm?";
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
		$("#status_div").hide();
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
	
	function start(id){
		operate_id=id;
		operate = "start";
		confirm(commitEdit, "是否受理该报警？");
	}
	
	function end(id){
		operate_id=id;
		operate = "end";
		confirm(commitEdit, "是否结束该报警？");
	}
</script>
 </body>

</html>
