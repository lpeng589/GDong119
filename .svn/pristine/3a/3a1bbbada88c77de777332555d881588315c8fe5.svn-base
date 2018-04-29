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
<title>轮播图列表</title>
</head>
<body class="iframebody">
<div class="page-container">


	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
                    <th>描述</th>
					<th>图片</th>
					<th>排序</th>
					<th>
						<button type="button" class="btn btn-success <pk:ruleControl module="poslink_add"/>" id="" name="" onclick="add()"><i class="Hui-iconfont"></i> 添加</button>
					</th>
                </tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr class="text-c">
						<td>${row.description}</td>
						<td><img style="max-width: 50px;max-height: 50px;" src="<%=request.getSession().getAttribute("imageRootPath")%>${row.linkurl}"></td>
						<td>${row.rank}</td>
						<td>
                    	<button class="btn btn-primary radius <pk:ruleControl module="poslink_update"/>" type="button" onclick="update(${row.id })">修改</button>
                    	<a href="javascript:;" onclick="delpre(${row.id})" class="btn btn-danger radius <pk:ruleControl module="poslink_delete"/>">删除</a>
                    </td>
              	</tr>
              </c:forEach>
              
			</tbody>
		</table>
	</div>
</div>


<div class="page-container" id="edit" style="display: none;">
	<form action="#" method="post" class="form form-horizontal" id="form-article-add">
	<input type="hidden" id="id">
		
		
		
						<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red"></span>
						描述 ：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" placeholder="请输入描述" id="description"   class="input-text"  style="width: 85%;">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red"></span>
						链接：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" placeholder="请输入链接" id="url"   class="input-text"  style="width: 85%;" value="#">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						图片 :</label>
					<div class="formControls col-xs-8 col-sm-9">
						<button name="filemanager"  class="btn btn-primary radius" type="button">上传图片</button>
						<input type="text" disabled="disabled" id="linkurl" class="input-text"  ccc="imagesInput"  style="width: 65%;">
						<img id="linkurlimg" src="" style="max-width: 50px;max-height: 50px;"  val="${imageRootPath }">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						排序：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="number" placeholder="请输入排序数值" id="rank"   class="input-text"  style="width: 60%;" value="#">注：数值从高到底排序
					</div>
				</div>
				
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="commitEdit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
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


function update(id) {
	
	layer.open({
	      type: 2,
	      title: '修改',
	      shadeClose: true,
	      shade: false,
	      maxmin: true, //开启最大化最小化按钮
	      area: ['80%', '80%'],
	      zIndex:999,
	      content: 'poslink.htm?operate=updatepre&id='+id
	});
	
}

var operate = "";
var id = "";
	//添加
function add() {
	operate = "add";
	layer.open({
	      type: 1,
	      title: '添加轮播图',
	      shadeClose: true,
	      shade: false,
	      maxmin: true, //开启最大化最小化按钮
	      area: ['80%', '80%'],
	      zIndex:999,
	      content: $("#edit")
	});
}
	
//提交增删改数据
function commitEdit() {
	if($("#linkurl").val()==null||$("#linkurl").val()==''||$("#linkurl").val()==undefined){
		layer.alert("请上传图片");
		return;
	}
	if($("#rank").val()==null||$("#rank").val()==''||$("#rank").val()==undefined){
		layer.alert("请输入排序数值");
		return;
	}
	var obj = {
			operate : 'add',
			description : $("#description").val(),
			url:$("#url").val(),
			rank:$("#rank").val(),
			linkurl : $("#linkurl").val()
		};
	var url = "poslink.htm?";
	var callbackfun = function(data) {
		if (data.code == 0) {
			layer.alert(data.msg, function(){
				location.href='poslink.htm?operate=list';
			});
		} else {
			layer.alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}

function delpre(id){
	
	layer.confirm("你确定要删除这张轮播图吗？", {
		  btn: ['确认','取消'] //按钮
		}, function(){
			var url = "poslink.htm?";
			var obj = {
				operate : "delete",
				id : id,
			};
			var callbackfun = function(data) {
				if (data.code == 0) {
					layer.alert(data.msg, function(){
						location.href='poslink.htm?operate=list';
					});
				} else {
					layer.alert(data.msg);
				}
			};
			commitData(url, obj, callbackfun);
		}, function(){
		  
		});
	
}


</script>
<link rel="stylesheet" href="../js/editor/themes/default/default.css" />
<script src="../js/editor/kindeditor-min.js"></script>
<script src="../js/editor/lang/zh_CN.js"></script>  
<span id="pifeSpan" class="input-group-addon" style="display:none"><%=request.getSession().getAttribute("imageRootPath")%></span>
<script>
//删除图片主路径
function clearRootImagePath(picInput){
	var _pifeSpan = $("#pifeSpan").text();
	var _imgVal = picInput.val();
	picInput.val(_imgVal.substring(_imgVal.indexOf("/attached/")));
}


KindEditor.ready(function(K) {
	
	var editor = K.editor({
		cssPath : '../js/editor/plugins/code/prettify.css',
		uploadJson : 'upload_json.htm',
		fileManagerJson : 'file_manager_json.htm',
		allowFileManager : true
	});
	K('button[name=filemanager]').click(function() {
		var imagesInputObj = $(this).parent().children("input[ccc=imagesInput]");
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
// 				imageUrl : K('#url1').val(),
				clickFn : function(url, title, width, height, border, align) {
// 					K('#url1').val(url);
					imagesInputObj.val(url);
					editor.hideDialog();
					clearRootImagePath(imagesInputObj);
					$("#linkurlimg").attr("src",'${imageRootPath}'+imagesInputObj.val());
				}
			});
		});
	});
}); 
</script>
 </body>

</html>
