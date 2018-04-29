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
<title>部门列表</title>
</head>
<body class="iframebody">
<div class="page-container">
 <form class="form-horizontal" method="post" action="f_department.htm">
          	<input type="hidden" name="operate" value="sonlist" />
          	<input type="hidden" id="parentid" name="parentid" value="${data.cond.parentid}" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
	<div class="text-c">
		<input type="text" name="name" value="${data.cond.name}" placeholder="部门名称" style="width:250px" class="input-text">
		<button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
	</div>
</form>
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
			<tr class="text-c">
                	<th width="25"><input type="checkbox" name="" value=""></th>
                    <th>部门名称</th>
                    <th>部门地址</th>
                    <th>部门成员数量</th>
				    <th>创建时间</th>
				    <th>最后修改时间</th>
                    <th>
						<button type="button" class="btn btn-success <pk:ruleControl module="f_department_add"/>  " name="" onclick="adddept()"><i class="Hui-iconfont"></i> 添加</button>
					</th>
                </tr>
			</thead>
			
			<tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr class="text-c">
              		<td><input name="ids" type="checkbox" value="${row.id }"/></td>
                    <td  onclick="layer_show('部门成员','h_workuser.htm?operate=list&departmentid=${row.id}','1100')" style="color: blue;">${row.name}</td>
                    <td>${row.address}</td>
                    <td>${row.usercount}</td>
				    <td>${row.createtime}</td>
				    <td>${row.updateTime}</td>
                    <td>
                    <button class="btn btn-small btn-primary  <pk:ruleControl module="f_department_update"/>" type="button" onclick="update(${row.id })">修改</button>
                    <c:if test="${row.usercount==0 }"><button class="btn btn-small btn-primary <pk:ruleControl module="f_department_update"/>" type="button" onclick="del(${row.id})">删除</button></c:if>
                    <button class="btn btn-small btn-primary  <pk:ruleControl module="f_department_update"/>" type="button" onclick="update2(${row.id })">修改三级部门</button>
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
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>部门名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="name" placeholder="请输入部门名称" value="" maxlength="30">
			</div>
			</div>
	        <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>部门地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="address" placeholder="请输入部门地址" value="" maxlength="30">
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

<div class="page-container" id="edit2" style="display: none;">
	<form action="#" method="post" class="form form-horizontal" id="form-article-add">
			<input type="hidden" id="id">
	        <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>三级部门名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select id="branchid" name="branchid">
	     			<option value="">--请选择--</option>
					<c:forEach items="${data.list}" varStatus="i" var="row">
	     				<option value="${row.id}">${row.name}</option>
	     			</c:forEach>
				</select>
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
	<div class="wrapper hide" id="map">
		 <iframe width="100%" height="100%" frameborder=0 src="http://apis.map.qq.com/tools/locpicker?search=1&type=1&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp"></iframe> 
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

var operate = "";
var id = "";
	//添加
function adddept() {
	operate = "add";
	$("#name").val("");
	$("#address").val("");
	layer.open({
	      type: 1,
	      title: '添加部门',
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
	var url = "f_department.htm?";
	var obj = {
		operate : "updatepre",
		id : id,
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			layer.open({
			      type: 1,
			      title: '修改部门',
			      shadeClose: true,
			      shade: false,
			      maxmin: true, //开启最大化最小化按钮
			      area: ['80%', '80%'],
			      content: $("#edit"),
			      zIndex:999,
			});
			$("#name").val(data.data.name);
			$("#address").val(data.data.address);
		} else {
			layer.alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}
//修改三级部门
function update2(id) {
	layer.open({
	      type: 2,
	      title: '修改部门',
	      shadeClose: true,
	      shade: false,
	      maxmin: true, //开启最大化最小化按钮
	      area: ['100%', '100%'],
	      content: 'f_department.htm?operate=Grandsonlist&parentid='+id,
	      zIndex:999
	});
}
//启用
function del(id) {
	operate = "delete";
	$("#id").val(id);
	layer.confirm("你确定要删除这个部门吗？", {
	  btn: ['确认','取消'] //按钮
	}, function(){
		commitEdit();
	}, function(){
	  
	});

}

	//提交增删改数据
function commitEdit() {
	var obj = {};
	if (operate == "add") {
		if($("#name").val()==null||$("#name").val()==''||$("#name").val()==undefined){    
	    	layer.alert("请输入二级部门名称！");
	        return ;
	    }
		if($("#address").val()==null||$("#address").val()==''||$("#address").val()==undefined){    
	    	layer.alert("请输入部门地址！");
	        return ;
	    }
		obj = {
			operate : operate,
			name : $("#name").val(),
			address : $("#address").val(),
			parentid : $("#parentid").val(),
			type : 2
		};
	} else if (operate == "update") {
		if($("#name").val()==null||$("#name").val()==''||$("#name").val()==undefined){    
	    	layer.alert("请输入部门名称！");
	        return ;
	    }
		if($("#address").val()==null||$("#address").val()==''||$("#address").val()==undefined){    
	    	layer.alert("请输入部门地址！");
	        return ;
	    }
		obj = {
			operate : operate,
			id : $("#id").val(),
			name : $("#name").val(),
			address : $("#address").val(),
		};
	}else if (operate == "update2") {
		obj = {
			operate : operate,
			id : $("#id").val(),
			branchid : $("#branchid").val(),
		};
	}else if (operate == "delete") {
		obj = {
			operate : operate,
			id : $("#id").val(),
		};
	} 
	var url = "f_department.htm?";
	var callbackfun = function(data) {
		if (data.code == 0) {
			layer.alert(data.msg, function(){
				document.forms[0].submit();
			});
		}else {
			layer.alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
};
</script>
 </body>

</html>
