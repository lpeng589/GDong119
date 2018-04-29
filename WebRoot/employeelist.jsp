<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/pnkootag"  prefix="pk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>重庆消防</title>
</head>
<body class="iframebody">
<div class="page-container">
 <form class="form-horizontal" method="post" action="employee.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
	<div class="text-c">
		<input type="text" name="loginname" value="${data.cond.loginname}" placeholder="请输入要查询的账号" style="width:250px" class="input-text">
		<input type="text" name="username" value="${data.cond.username}" placeholder="请输入要查询的职员名" style="width:250px" class="input-text">
		<span class="select-box" style="width:150px">
		<select class="select" name="status">
       		<option value=" ">全部</option>
       		<option value="1">已启用</option>
       		<option value="0">已注销</option>
     	</select>
		</span>
		<span class="select-box" style="width:150px">
		 <select class="select" name="roleids">
			 <option value="">全部</option>
			<c:forEach items="${roles}" varStatus="j" var="item">
					<option value=",${item.id},">${item.role_name}</option>
			</c:forEach>
		</select>
		</span>
		<button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
	</div>
</form>

   <div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
			<tr class="text-c">
                	<th width="25"><input type="checkbox" name="" value=""></th>
                    <th>登录账号</th>
                    <th>职员名</th>
                    <th>角色</th>
                    <th>部门</th>
                    <th>职位</th>
<!--                     <th>电话</th> -->
                    <th>状态</th>
                    <th>创建时间</th>
              <!--       <th>操作</th> -->
                    <th>
						<button class="btn btn-success btn-mini" onclick="add()">新增</button>
<!-- 						<button class="btn btn-danger btn-mini" onclick="del()">删除</button> -->
					</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<c:if test="${row.loginname!='admin'}">
              		<tr class="text-c">
                	<td><input name="ids" type="checkbox" value="${row.id }"/></td>
                    <td>${row.loginname }</td>
                    <td>${row.username }</td>
                    <td>${row.role_name }</td>
                    <td>
                    	<c:if test="${row.department==null }">${row.department1 }</c:if>
                    	<c:if test="${row.department!=null }">${row.department }</c:if>
                    </td>
                    <td>${row.position }</td>
<!--                     <td>${row.phone }</td> -->
                    <td><c:if test="${row.status==0}">已注销</c:if><c:if test="${row.status==1}">已启用</c:if></td>
                    <td>${row.createtime }</td>
                    <td>					
                    	<input class="btn btn-link" value="修改" type="button" onclick="update(${row.id })">
                    </td>
              	</tr>
              	</c:if>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
            <jsp:include page="sortpage.jsp"></jsp:include>
          </div>
</div>
	<div class="page-container" id="edit_div" style="display: none;">
				<div id="jsmodelbody" class="modal-body">
					<div class="widget-box">
						<div class="widget-content nopadding">
							<form action="#" method="post" class="form form-horizontal" id="form-article-add">
							<input type="hidden" id="id">
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>账号：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="loginname" placeholder="请输入帐号" value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="username" placeholder="请输入名称" value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">角色 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="roleids" name="" class="select" >
										<c:forEach items="${roles }" var="row" varStatus="i">
											<option value="${row.id }">${row.role_name }</option>
										</c:forEach>	
									</select>
									</span></div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>密码：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="password" class="input-text" id="password" placeholder="输入密码" value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>所属一级部门 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="department1" name="" class="select" onchange="change(1)">
										<option value=""> -- 请选择 -- </option>
										<c:forEach items="${department}" varStatus="i" var="row">
											<option value="${row.id }">${row.name }</option>
										</c:forEach>
									</select>
									</span></div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">所属二级部门 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="department2" name="" class="select" onchange="change(2)">
										
									</select>
									</span></div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">所属三级部门 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="department3" name="" class="select" onchange="change(3)">
										
									</select>
									</span></div>
								</div>
								<div class="row cl" >
								<label class="form-label col-xs-4 col-sm-2">部门职员 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="workuserid" class="select">
									
									</select>
									</span></div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">职位：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="position" placeholder="请输入职位" value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">电话：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="number" class="input-text" id="phone" placeholder="请输入电话" value="" maxlength="30" style="width:100%">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">邮箱：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="email" placeholder="请输入邮箱 " value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">传真：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="faxnumber" placeholder="请输入传真 " value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">状态 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="status" name="" class="select">
										<option value="1">启用</option>
										<option value="0">注销</option>	
									</select>
									</span></div>
								</div>
						</div>
						<div class="row cl">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
							<button onClick="commitEdit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
						</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
 </div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script src="../js/common.js"></script>
<script src="../js/des.js"></script>
<script>
$("select[name='roleids']").val("${data.cond.roleids}");
$("select[name='status']").val("${data.cond.status}"); 
</script>
<!-- 增删改查操作 -->
<script type="text/javascript">
	var operate = "";
	var id_role = "";
	var val_role ="";
		//添加
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
	
	function add() {
		operate = "add";
		$("#loginname").val("");
		$("#username").val("");
		$("#password").val("");
		$("#roleids").select("");
		$("#department").val("");
		$("#workuserid").val("");
		$("#workuserid2").val("");
		$("#branchname").val("");
		$("#position").val("");
		$("#phone").val("");
		$("#email").val("");
		$("#faxnumber").val("");
		$("#status").val(1);
		$("#loginname").attr("readonly", false);
		layer.open({
		      type: 1,
		      title: '添加消防员',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['80%', '80%'],
		      content: $("#edit_div"),
		      zIndex:999,
		});
	}
	var departmentid;
	var update_workuserid;
		//修改
	function update(id) {
		operate = "update";
		$("#id").val(id);
		var url = "employee.htm?";
		var obj = {
			operate : "updatepre",
			id : id,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				$("#loginname").val(data.data.loginname);
				$("#loginname").attr("readonly", true);
				$("#username").val(data.data.username);
				$("#password").val(data.data.password);
				$("#position").val(data.data.position);
				$("#phone").val(data.data.phone);
				$("#email").val(data.data.email);
				$("#faxnumber").val(data.data.faxnumber);
				$("#status").val(data.data.status);
				$("#roleids").val(data.data.roleids);
				departmentid=data.data.departmentid;
				console.log(data.data.type);
				if(data.data.departtype==1){
					$("#department1").val(data.data.departmentid);
				}else if(data.data.departtype==2){
					$("#department1").val(data.data.departmentid1);
					changeupdate(1,data.data.departmentid,data.data.workuserid);
				}else if(data.data.departtype==3){
					$("#department1").val(data.data.departmentid2);
					changeupdate(1,data.data.departmentid1,data.data.workuserid);
					setTimeout("changeupdate(2,"+data.data.departmentid+","+data.data.workuserid+")",300);
				}
				layer.open({
				      type: 1,
				      title: '修改消防员',
				      shadeClose: true,
				      shade: false,
				      maxmin: true, //开启最大化最小化按钮
				      area: ['80%', '80%'],
				      content: $("#edit_div"),
				      zIndex:999,
				});
				
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
		if($("#loginname").val() == ''){
			layer.alert("请输入账号");
			return;
		}
	    var password = strEnc($("#password").val(),$("#loginname").val(),'','');  
		if (operate == "add") {
				if(!check()){
					return;
				}
				if(departmentid == ''){
					layer.alert("请选择部门");
					return;
				}
			obj = {
				operate : operate,
				loginname : $("#loginname").val(),
				username : $("#username").val(),
				password : password,
				position : $("#position").val(),
				phone : $("#phone").val(),
				email : $("#email").val(),
				faxnumber : $("#faxnumber").val(),
				departmentid : departmentid,
				workuserid : $("#workuserid").val(),
				roleids : $("#roleids").val(),
				status : $("#status").val()
			};
		} else if (operate == "update") {
			if(!check()){
				return;
			}
			obj = {
				operate : operate,
				id : $("#id").val(),
				loginname : $("#loginname").val(),
				username : $("#username").val(),
				password : password,
				position : $("#position").val(),
				phone : $("#phone").val(),
				email : $("#email").val(),
				faxnumber : $("#faxnumber").val(),
				departmentid : departmentid,
				workuserid : $("#workuserid").val(),
				roleids : $("#roleids").val(),
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
			
		var url = "employee.htm?";
		var callbackfun = function(data) {
			if (data.code == 0) {
				document.forms[0].submit();
			} else {
				layer.alert(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	};
	//部门联动	
	function changeupdate(type,deptid,wid){
		var obj={
				departmentid : $("#department"+type).val(),
				operate : "getdepartment",
		};
		var url = "employee.htm?";
		var callbackfun = function(data) {
			var tid=type+1;
			$("#department"+tid).empty();
			var list = "<option value=\"\">--请选择--</option>";
			if(data.data.length==0){
				list = "<option value=\"\">--暂无部门--</option>";
			}
			for(var i=0;i<data.data.length;i++){
				var item=data.data[i];
				list += "<option value=\""+item.id+"\">"+item.name+"</option>";
			}
			$("#department"+tid).append(list);
			console.log(type+":"+deptid);
			$("#department"+tid).val(deptid);
			changetemp2(deptid,wid);
		};
		commitData(url, obj, callbackfun);
	}
	//职员修改
	function changetemp2(depeid,wid){
		var obj={
				departmentid : depeid,
				operate : "departmentemp",
		}
		var url = "employee.htm?";
		var callbackfun = function(data) {
			var list2 = "<option value=\"\">--请选择--</option>";
			$("#workuserid").empty();
			if(data.data.length==0){
				list2 = "<option value=\"\">--暂无职员--</option>";
			}
			for(var i=0;i<data.data.length;i++){
				var item=data.data[i];
				list2 += "<option value=\""+item.id+"\">"+item.name+"</option>";
			}
			$("#workuserid").append(list2);
			$("#workuserid").val(wid);
		};
		commitData(url, obj, callbackfun);
	}
	
//部门联动	
function change(type){
	if($("#department"+type).val()==""){
		if(type==1){
			$("#department2").empty();
			$("#department3").empty();
		}
		if(type==2){
			$("#department3").empty();
		}
		type--;
	}
	var obj={
			departmentid : $("#department"+type).val(),
			operate : "getdepartment",
	};
	var url = "employee.htm?";
	var callbackfun = function(data) {
		var tid=type+1;
		$("#department"+tid).empty();
		var list = "<option value=\"\">--请选择--</option>";
		if(data.data.length==0){
			list = "<option value=\"\">--暂无部门--</option>";
		}
		for(var i=0;i<data.data.length;i++){
			var item=data.data[i];
			list += "<option value=\""+item.id+"\">"+item.name+"</option>";
		}
		$("#department"+tid).append(list);
		changetemp();
	};
	commitData(url, obj, callbackfun);
}
//职员修改
function changetemp(){
	departmentid=$("#department1").val();
	if($("#department2").val()!=""&&$("#department2").val()>0){
		departmentid=$("#department2").val();
		if($("#department3").val()!=""&&$("#department3").val()>0){
			departmentid=$("#department3").val();
		}
	}
	if(departmentid==""){
		$("#workuserid").empty();
		return;
	}
	var obj={
			departmentid : departmentid,
			operate : "departmentemp",
	}
	var url = "employee.htm?";
	var callbackfun = function(data) {
		var list2 = "<option value=\"\">--请选择--</option>";
		$("#workuserid").empty();
		if(data.data.length==0){
			list2 = "<option value=\"\">--暂无职员--</option>";
		}
		for(var i=0;i<data.data.length;i++){
			var item=data.data[i];
			list2 += "<option value=\""+item.id+"\">"+item.name+"</option>";
		}
		$("#workuserid").append(list2);
	};
	commitData(url, obj, callbackfun);
}

function check(){
	if($("#department").val() == ''){
		layer.alert("请选择部门");
		return false;
	}
	if($("#username").val() == ''){
		layer.alert("请输入名称");
		return false;
	}
	if($("#password").val() == ''){
		layer.alert("请输入密码");
		return false;
	}
// 	if($("#position").val() == ''){
// 		layer.alert("请输入职位");
// 		return false;
// 	}
// 	if($("#phone").val() == ''){
// 		layer.alert("请输入电话");
// 		return false;
// 	}
// 	if($("#email").val() == ''){
// 		layer.alert("请输入邮箱");
// 		return false;
// 	}
// 	if($("#faxnumber").val() == ''){
// 		layer.alert("请输入传真");
// 		return false;
// 	}
	return true;
}
</script>  

<link rel="stylesheet" href="../js/editor/themes/default/default.css" />
<script src="../js/editor/kindeditor-min.js?a=v20160403"></script>
<script src="../js/editor/lang/zh_CN.js"></script>  
<span id="pifeSpan" class="input-group-addon" style="display:none"><%=request.getSession().getAttribute("imageRootPath")%></span>
<script src="../js/uploadFileImage.js?a=20160404"></script>
 </body>

</html>
