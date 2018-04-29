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
<title>街道管理</title>
</head>
<body class="iframebody">
<div class="page-container">
 <form class="form-horizontal" method="post" action="f_street.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
	<div class="text-c">
		<input type="text" name="name" value="${data.cond.name}" placeholder="请输入要查询的街道" style="width:250px" class="input-text">
		<input type="text" name="department" value="${data.cond.department}" placeholder="请输入要查询的部门" style="width:250px" class="input-text">
		<button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
	</div>
</form>

   <div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
			<tr class="text-c">
                	<th width="25"><input type="checkbox" name="" value=""></th>
                    <th>街道名称</th>
                    <th>部门名称</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
              <!--       <th>操作</th> -->
                    <th>
						<button class="btn btn-success btn-mini" onclick="add()">新增</button>
 						<button class="btn btn-danger btn-mini" onclick="del()">删除</button> 
					</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              		<tr class="text-c">
                	<td><input name="ids" type="checkbox" value="${row.id }"/></td>
                    <td>${row.name }</td>
                    <td>${row.department }</td>
                    <td>${row.createtime }</td>
                    <td>${row.updateTime }</td>
                    <td>					
                    	<input class="btn btn-link" value="修改" type="button" onclick="update(${row.id })">
                    </td>
              	</tr>
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
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>街道名称：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="name" placeholder="请输入街道名称" value="" maxlength="30">
								</div>
								</div>
								<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2">部门 ：</label>
								<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
									<select id="department" name="" class="select" >
										<option value=""> -- 请选择 -- </option>
										<c:forEach items="${department}" varStatus="i" var="row">
											<option value="${row.id }">${row.name }</option>
										</c:forEach>
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
<!-- 增删改查操作 -->
<script type="text/javascript">
	var operate = "";
	//添加
	function add() {
		operate = "add";
		$("#name").val("");
		$("#department").val("");
		layer.open({
		      type: 1,
		      title: '添加街道',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['80%', '80%'],
		      content: $("#edit_div"),
		      zIndex:999,
		});
	}
	//修改
	function update(id) {
		operate = "update";
		$("#id").val(id);
		var url = "f_street.htm?";
		var obj = {
			operate : "updatepre",
			id : id,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				$("#name").val(data.data.name);
				$("#department").val(data.data.departmentid); 
				layer.open({
				      type: 1,
				      title: '修改街道',
				      shadeClose: true,
				      shade: false,
				      maxmin: true, //开启最大化最小化按钮
				      area: ['80%', '80%'],
				      content: $("#edit_div"),
				      zIndex:999,
				});
			} else {
				layer.alert(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	}
	//删除
	function del() {
		operate = "deletemany";
		var length = $("input[name='ids']:checked").length;
		if (length > 0) {
			layer.confirm("你确定要删除这" + length + "条记录吗？", {
				  btn: ['确认','取消'] //按钮
				}, function(){
					commitEdit();
				}, function(){
				  
				});
		} else {
			layer.alert("请先选择要删除的记录");
		}

	}
	//提交增删改数据
	function commitEdit() {
		var obj = {};
		if (operate == "add") {
			obj = {
				operate : operate,
				name : $("#name").val(),
				departmentid : $("#department").val()
			};
		} else if (operate == "update") {
			obj = {
				operate : operate,
				id : $("#id").val(),
				name : $("#name").val(),
				departmentid : $("#department").val()
			};
		} else if (operate == "deletemany") {
			var ids = new Array();
			$("input[name='ids']:checked").each(function() {
				ids[ids.length] = this.value;
			});
			obj = {
				operate : operate,
				ids : ids,
			};
		}
			
		var url = "f_street.htm?";
		var callbackfun = function(data) {
			if (data.code == 0) {
				document.forms[0].submit();
			} else {
				layer.alert(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	};
</script>  

<link rel="stylesheet" href="../js/editor/themes/default/default.css" />
<script src="../js/editor/kindeditor-min.js?a=v20160403"></script>
<script src="../js/editor/lang/zh_CN.js"></script>  
<span id="pifeSpan" class="input-group-addon" style="display:none"><%=request.getSession().getAttribute("imageRootPath")%></span>
<script src="../js/uploadFileImage.js?a=20160404"></script>
 </body>

</html>
