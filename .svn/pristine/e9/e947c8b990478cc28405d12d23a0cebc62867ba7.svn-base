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
<title>消防员列表</title>
</head>
<body class="iframebody">
<div class="page-container">
 <form class="form-horizontal" method="post" action="h_workuser.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
	<div class="text-c">
		<input type="text" name="name" value="${data.cond.name}" placeholder="消防员名称" style="width:250px" class="input-text">
		<input type="text" name="phone" value="${data.cond.phone}" placeholder="手机号码" style="width:250px" class="input-text">
		<span class="select-box" style="width:150px">
			<select class="select" name="departmentid">
					<option value="">所有部门</option>
           			<c:forEach items="${department}" varStatus="i" var="row">
					<option value="${row.id }">${row.name }</option>
					</c:forEach>
			</select>
			</span>
		<span class="select-box" style="width:150px">
			<select class="select" name="status">
					<option value="">所有状态</option>
           			<option value="1" selected>启用</option>
	                <option value="0">注销</option>
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
                    <th>名称</th>
                    <th>部门名称</th>
                    <th>是否管理员</th>
                    <th>手机</th>
                    <th>备注</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>
						<button type="button" class="btn btn-success <pk:ruleControl module="h_workuser_add"/>  " name="" onclick="add()"><i class="Hui-iconfont"></i> 添加</button>
					</th>
                </tr>
			</thead>
			
			<tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr class="text-c">
              		<td><input name="ids" type="checkbox" value="${row.id }"/></td>
                    <td>${row.name }</td>
                    <td>${row.departmentname }</td>
                    <td>
                    <c:if test="${row.ismanager==1}">是</c:if>
                    <c:if test="${row.ismanager==0}">否</c:if>
                    </td>
                    <td>${row.phone }</td>
                    <td>${row.remark }</td>
                    <td>
                    <c:if test="${row.status==0 }">注销</c:if>
                    <c:if test="${row.status==1 }">启用</c:if>
                    </td>
                    <td>${row.createtime }</td>
                    <td>
                    <button class="btn btn-small btn-primary  <pk:ruleControl module="h_workuser_update"/> "  type="button" onclick="update(${row.id })">修改</button>
                    <c:if test="${row.status==0 }"><button class="btn btn-small btn-primary   <pk:ruleControl module="h_workuser_delete"/>"  type="button" onclick="qiyong(${row.id })">启用</button></c:if>
                    <c:if test="${row.status==1 }"><button class="btn btn-small btn-primary   <pk:ruleControl module="h_workuser_delete"/>"  type="button" onclick="zhuxiao(${row.id })">注销</button></c:if>
                    </td>
              	</tr>
              </c:forEach>
			</tbody>
		</table>
	</div>
	 <jsp:include page="sortpage.jsp"></jsp:include>
</div>

<div class="page-container" id="edit" style="display: none;">
	<form action="#" method="post" class="form form-horizontal" id="form-article-add">
			<input type="hidden" id="id">
	        <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>消防员名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="tel" class="input-text" id="name" placeholder="请输入消防员名称" value="" maxlength="30">
			</div>
			</div>
			
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机号码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" class="input-text" id="phone" placeholder="请输入手机号码" value="" maxlength="11" style="width: 300px;">
			</div>
			</div>
			
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">部门名称 ：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width: 24%"> <span class="select-box">
				<select id="departmentid" name="" class="select" onchange="changedept(this)">
					<option value="">选择所属部门</option>
					<c:forEach items="${department}" varStatus="i" var="row">
					<option value="${row.id }">${row.name }</option>
					</c:forEach>
				</select>
				</span></div>
				<div class="formControls col-xs-8 col-sm-9" style="width: 24%"> <span class="select-box">
				<select id="departmentid2" name="" class="select" onchange="changedept(this)">
				</select>
				</span></div>
				<div class="formControls col-xs-8 col-sm-9" style="width: 24%"> <span class="select-box">
				<select id="departmentid3" name="" class="select">
				</select>
				</span></div>
			</div>
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否管理员 ：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width: 24%"> <span class="select-box">
				<select id="ismanager" name="" class="select">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				</span></div>
			</div>
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="remark" placeholder="请输入备注" value="" maxlength="255">
			</div>
			</div>
			
    		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="commitEdit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
<!-- 				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> -->
			</div>
			</div>
    </form>
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
<!-- 增删改查操作 -->
<script type="text/javascript">
$(".dataTables_info").hide();
$(".dataTables_paginate").hide();
$("#DataTables_Table_0_filter").css("margin-bottom","20px");
$("select[name='status']").val("${data.cond.status}");
$("select[name='departmentid']").val("${data.cond.departmentid}");
var operate = "";
var id = "";
	//添加
function add() {
	operate = "add";
	$("#name").val("");
	$("#phone").val("");
	$("#remark").val("");
	layer.open({
	      type: 1,
	      title: '添加消防员',
	      shadeClose: true,
	      shade: false,
	      maxmin: true, //开启最大化最小化按钮
	      area: ['80%', '80%'],
	      content: $("#edit"),
	      zIndex:999,
	});
}
	//修改
function update(id) {
	operate = "update";
	$("#id").val(id);
	var url = "h_workuser.htm?";
	var obj = {
		operate : "updatepre",
		id : id,
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			layer.open({
			      type: 1,
			      title: '修改消防员',
			      shadeClose: true,
			      shade: false,
			      maxmin: true, //开启最大化最小化按钮
			      area: ['80%', '80%'],
			      content: $("#edit"),
			      zIndex:999,
			});
			$("#name").val(data.data.name);
			$("#phone").val(data.data.phone);
			$("#remark").val(data.data.remark);
			$("#ismanager").val(data.data.ismanager);
			changedeptup(data.data.departtype,data.data.departparentid,data.data.departmentid);
		} else {
			layer.alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}
function changedeptup(deptype,departparentid,departmentid){
	if(deptype==1){
		$("#departmentid").val(departmentid);
		return
	}else{
		var obj={
				parentid:departparentid,
		}
		var url="f_department.htm?operate=updatepre2";
		var callback=function(data){
			$("#departmentid"+deptype).empty();
			var temp="<option value=\"\">选择部门</option>";
			var parent=-1;
			for(var i=0;i<data.data.length;i++){
				var item=data.data[i];
				parent=item.parent2id;
				temp+="<option value=\""+item.id+"\">"+item.name+"</option>";
			}
			$("#departmentid"+deptype).append(temp);
			$("#departmentid"+deptype).val(departmentid);
			deptype--;
			changedeptup(deptype,parent,departparentid)
		}
		commitData(url,obj,callback); 
	}
}
//启用
function qiyong(id) {
	operate = "qiyong";
	$("#id").val(id);
	layer.confirm("你确定要启用这个用户吗？", {
	  btn: ['确认','取消'] //按钮
	}, function(){
		commitEdit();
	}, function(){
	  
	});

}
//注销
function zhuxiao(id) {
	operate = "zhuxiao";
	$("#id").val(id);
	layer.confirm("你确定要注销个用户吗？", {
	  btn: ['确认','取消'] //按钮
	}, function(){
		commitEdit();
	}, function(){
	  
	});
}

	//提交增删改数据
function commitEdit() {
		var departmentid = $("#departmentid").val();
		if($("#departmentid2").val()!=""&&$("#departmentid2").val()>0){
			departmentid=$("#departmentid2").val();
			if($("#departmentid3").val()!=""&&$("#departmentid3").val()>0){
				departmentid=$("#departmentid3").val();
			}
		}
	var obj = {};
	if (operate == "add") {
		if($("#name").val()==null||$("#name").val()==''||$("#name").val()==undefined){    
	    	layer.alert("请输入消防员名称！");
	        return ;
	    }
		if($("#phone").val()==null||$("#phone").val()==''||$("#phone").val()==undefined){    
	    	layer.alert("请输入手机号码！");
	    	return ;
	    }
		if($("#departmentid").val()==null||$("#departmentid").val()==''||$("#departmentid").val()==undefined){    
	    	layer.alert("请选择部门！");
	    	return ;
	    }
		obj = {
			operate : operate,
			name : $("#name").val(),
			phone : $("#phone").val(),
			remark : $("#remark").val(),
			departmentid : departmentid,
			ismanager : $("#ismanager").val()
		};
	} else if (operate == "update") {
		if($("#name").val()==null||$("#name").val()==''||$("#name").val()==undefined){    
	    	layer.alert("请输入消防员名称！");
	        return ;
	    }
		if($("#phone").val()==null||$("#phone").val()==''||$("#phone").val()==undefined){    
	    	layer.alert("请输入手机号码！");
	    	return ;
	    }
		if(departmentid==null||departmentid==''||departmentid==undefined){    
	    	layer.alert("请选择部门！");
	    	return ;
	    }
		obj = {
			operate : operate,
			id : $("#id").val(),
			name : $("#name").val(),
			phone : $("#phone").val(),
			remark : $("#remark").val(),
			departmentid : departmentid,
			ismanager : $("#ismanager").val()
		};
	} else if (operate == "qiyong") {
		obj = {
			operate : operate,
			id : $("#id").val(),
		};
	} else if (operate == "zhuxiao") {
		obj = {
				operate : operate,
				id : $("#id").val(),
			};
		}
	var url = "h_workuser.htm?";
	var callbackfun = function(data) {
		if (data.code == 0) {
			layer.alert(data.msg, function(){
				document.forms[0].submit();
			});
		} else {
			layer.alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
};
function changedept(event){
	var obj={
			parentid:$(event).val(),
	}
	var url="f_department.htm?operate=updatepre2";
	var callback=function(data){
		var target=$(event).parent().parent().next().find("select");
		$(target).empty();
		var temp="<option value=\"\">选择部门</option>";
		for(var i=0;i<data.data.length;i++){
			var item=data.data[i];
			temp+="<option value=\""+item.id+"\">"+item.name+"</option>";
		}
		$(target).append(temp);
	}
	commitData(url,obj,callback);
}
</script>
</body>

</html>
