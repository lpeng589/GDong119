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
<link rel="stylesheet" href="../css/common.css?a=12" />
</head>
<body class="iframebody">
   <div>
   		  <form class="form-horizontal" method="post" action="flownode.htm">
          	<input type="hidden" name="operate" value="list" />
          </form>
          <br><br>
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
                   	<th>节点名称</th>
<!--                     <th>节点状态</th> -->
                    <th>角色</th>
                    <th>职员</th>
                    <th>系统节点</th>
                    <th>简介</th>
                    <th>创建时间</th>
                    <th>
<!--                     <button class="btn btn-success btn-mini <pk:ruleControl module="flownode_operete"/>" onclick="add()">新增节点</button> -->
                    </th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${list}" varStatus="i" var="row">
              	<tr>
                	<td></td>
                	<td>${row.flowname }</td>
				    <td>${row.descriptionnow}</td>
				    <td>${row.rolenames }</td>
				    <td>
				    ${row.employeenames }
				    <c:if test="${row.isaudit ==2}">
				    	（录入人审核）
				    </c:if>
				    <c:if test="${row.isaudit ==3}">
				    	（无需审核）
				    </c:if>
				    </td>
				    <td>
				    <c:if test="${row.issys ==0}">系统节点</c:if>
				    <c:if test="${row.issys ==1}">自定义节点</c:if>
				    </td>
				    <td  class="minmax">${row.remark }</td>
				    <td>${row.createtime }</td>
                     <td>
                      <c:if test="${row.isaudit ==1||row.isaudit ==3||row.isaudit ==4||row.isaudit ==5}">
                     <input class="btn btn-mini btn-info  <pk:ruleControl module="flownode_operete"/>" value="设置节点" type="button" onclick="update(${row.id },'${row.flowname }',${row.statusnow },'${row.descriptionnow }','${row.roleid }','${row.employeeid }',${row.issys },'${row.remark }')">
					 </c:if>
					 <c:if test="${row.issys ==1}">
					 <input class="btn btn-mini btn-info  <pk:ruleControl module="flownode_operete"/>" value="删除" type="button" onclick="del(${row.id })">
					 </c:if>
                     </td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <div>温馨提醒：修改了节点以后，其他用户要重新登录才能生效。</div>
            <div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="history.go(-1);"class="btn btn-warn">返回</button>
			</div>
          </div> 
        </div>
        
        	<div id="edit_div" class="hide">
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
										<label class="control-label">节点名称：</label> 
										<input type="text" class="span7" id="descriptionnow" name="descriptionnow" value=""  placeholder="请输入节点名称" >
									</div>
								</div>
								<div class="control-group" >
									<div class="controls">
										<label class="control-label">角色：</label> 
										<div class="select-normal span7" style="width: 670px"> 
											<select id="roleid" style="display:none;width: 100%" multiple> 
											<c:forEach items="${roles }" var="row" varStatus="i">
												<option value="${row.id }">${row.role_name }</option>
											</c:forEach>	
 											</select> 
										</div> 
									</div>
								</div>
								<div class="control-group" >
									<div class="controls">
										<label class="control-label">职员：</label> 
										<div class="select-normal span7" style="width: 670px"> 
											<select id="employeeid" style="display:none;width: 100%" multiple> 
											<c:forEach items="${employees }" var="row" varStatus="i">
												<option value="${row.id }">${row.username }</option>
											</c:forEach>	
 											</select> 
										</div> 
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">简介：</label> 
										<input type="text" class="span7" id="remark" name="remark" value=""  placeholder="请输入简介" >
									</div>
								</div>
						</div>
						<div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="toggleShow();"
								class="btn btn-warn">返回</button>
							<button type="submit" class="btn btn-success">保存</button>
						</div>
						</form>
					</div>
				</div>
			</div>
        
        
        	<div id="add_div" class="hide">
				<div id="jsmodelbody" class="modal-body">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>编辑</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" id="addForm">
							<input type="hidden" id="id">
								<div class="control-group">
										<div class="controls">
					          			<label class="control-label">添加在节点后：</label>
						          			<select id="add_id"  class="select-normal span2" > 
							                  		<c:forEach items="${list }" var="row" varStatus="i">
													<c:if test="${row.nodenext!=0 && row.nodepre!=-1}">
													<option value="${row.id}">${row.descriptionnow }</option>
													</c:if>
												</c:forEach>	
						 					</select> 
										</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">节点名称：</label> 
										<input type="text" class="span7" id="add_descriptionnow" name="add_descriptionnow" value=""  placeholder="请输入节点名称" >
									</div>
								</div>
								<div class="control-group" >
									<div class="controls">
										<label class="control-label">角色：</label> 
										<div class="select-normal span7" style="width: 670px"> 
											<select id="add_roleid" style="display:none;width: 100%" multiple> 
											<c:forEach items="${roles }" var="row" varStatus="i">
												<option value="${row.id }">${row.role_name }</option>
											</c:forEach>	
 											</select> 
										</div> 
									</div>
								</div>
								<div class="control-group" >
									<div class="controls">
										<label class="control-label">职员：</label> 
										<div class="select-normal span7" style="width: 670px"> 
											<select id="add_employeeid" style="display:none;width: 100%" multiple> 
											<c:forEach items="${employees }" var="row" varStatus="i">
												<option value="${row.id }">${row.username }</option>
											</c:forEach>	
 											</select> 
										</div> 
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">简介：</label> 
										<input type="text" class="span7" id="add_remark" name="add_remark" value=""  placeholder="请输入简介" >
									</div>
								</div>
						</div>
						<div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="toggleShow();"
								class="btn btn-warn">返回</button>
							<button type="submit" class="btn btn-success">保存</button>
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
<script src="../js/jquery.metadata.js"></script>
<script src="../js/jquery.validate.js"></script>
<script>
$(document).ready(function() {
	 $("#commentForm").validate(
			 {
					rules: {
						descriptionnow: {
							 required: true,
						},
						
					},
					messages: {
						descriptionnow:{
						    required: "请输入节点名称",
						   },
					},
					errorClass: 'error-tips',
					submitHandler: function() {
							commitEdit();
					}
			 }); 
});

$(document).ready(function() {
	 $("#addForm").validate(
			 {
					rules: {
						add_descriptionnow: {
							 required: true,
						},
					},
					messages: {
						add_descriptionnow:{
						    required: "请输入节点名称",
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
	var toggleFlag=0;
	function toggleShow(){
		$("#edit_div").hide();
		$("#add_div").hide();
		if(toggleFlag==0){
			$("#list_div").hide();
			toggleFlag=1;
		}else{
			$("#list_div").show();
			toggleFlag=0;
		}
	}
	var u_id="";
	function update(id,flowname,statusnow,descriptionnow,roleid,employeeid,issys,remark){
		u_id=id;
		operate = "update";
		toggleFlag=1;
		$("#list_div").hide();
		$("#edit_div").show();
		$("#descriptionnow").val(descriptionnow);
		if(issys==1){
			$("#descriptionnow").attr("readonly", false);
		}else{
			$("#descriptionnow").attr("readonly", true);
		}
        if(roleid!=null&& roleid!=''&& roleid!=undefined){
            var strs= new Array();
            strs = roleid.split(",");
            $("#roleid").select2("val",strs);
        }  else{
        	$("#roleid").select2("val","");
        }
        if(employeeid!=null&& employeeid!=''&& employeeid!=undefined){
            var strs= new Array();
            strs = employeeid.split(",");
            $("#employeeid").select2("val",strs);
        }  else{
        	$("#employeeid").select2("val","");
        }
		$("#remark").val(remark);
	}
	
	function add(){
		operate = "add";
		toggleFlag=1;
		$("#list_div").hide();
		$("#add_div").show();
	}
	//删除
	function del(id) {
		u_id=id;
		operate = "delete";
		confirm(commitEdit, "你确定要删除这条记录吗？");
	}
	
	
	//提交增删改数据
	function commitEdit(id) {
		
		var obj = {};
		if (operate == "update") {
			var roleid = $("#roleid").val();
			if(roleid!="" && roleid!=null) {
				roleid = roleid.join(",");
			}else{
				roleid="";
			}
			var employeeid = $("#employeeid").val();
			if(employeeid!="" && employeeid!=null) {
				employeeid = employeeid.join(",");
			}else{
				employeeid="";
			}
			if(roleid=="" && employeeid==""){
				tip("角色和职员必须一个有值！");
				return;
			}
			obj = {
					operate : operate,
					id : u_id,
					descriptionnow : $("#descriptionnow").val(),
					roleid : roleid,
					employeeid : employeeid,
					remark : $("#remark").val()
				};
		}else if (operate == "delete") {
			obj = {
				operate : operate,
				id : u_id,
			};
		} else if (operate == "add") {
			var add_roleid = $("#add_roleid").val();
			if(add_roleid!="" && add_roleid!=null) {
				add_roleid = add_roleid.join(",");
			}else{
				add_roleid="";
			}
			var add_employeeid = $("#add_employeeid").val();
			if(add_employeeid!="" && add_employeeid!=null) {
				add_employeeid = add_employeeid.join(",");
			}else{
				add_employeeid="";
			}
			if(add_roleid=="" && add_employeeid==""){
				tip("角色和职员必须一个有值！");
				return;
			}
			obj = {
				operate : operate,
				id :  $("#add_id").val(),
				descriptionnow : $("#add_descriptionnow").val(),
				roleid : add_roleid,
				employeeid : add_employeeid,
				remark : $("#add_remark").val()
			};
		} 
		var url = "flownode.htm?";
		var callbackfun = function(data) {
			if (data.code == 0) {
				location.reload();
				
			} else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	};
</script>
 </body>

</html>
