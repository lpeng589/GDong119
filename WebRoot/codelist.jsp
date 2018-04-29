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
<style type="text/css">
/*img遮罩层  */
.imgview{
    background-color: #808080;
    width: 100%;  
    height: 100%;
    opacity: 1.0;
    position: absolute;
    z-index: 999;
    text-align: center;
}
  /*img遮罩层内div  */
.imgviewson{
  margin-top:2%;	
  width: 500px;
  height: 500px;
  z-index: 1000;
}
img{
	max-width: 1440px;
}
</style>
<body class="iframebody">
   <div>
   		
        <!-- start 查询条件 -->
        <div class="widget-box" id="list_div">
          <div class="widget-content ">
          <form class="form-horizontal" method="post" action="code.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
           
             <div class="controls controls-row">
          		<label class="control-label">备注：</label><input name="remark" value="${data.cond.remark}" placeholder="请输入要查询的备注" class="span2 m-wrap">
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
								  <th>备注</th>
								    <th>扫描次数</th>
								    <th>二维码</th>
								   <th>创建时间</th>
								   <th>修改时间</th>
                  <th>
						  <button class="btn btn-success btn-mini <pk:ruleControl module="code_add"/>"  onclick="add()">新增</button>
<!-- 					    <button class="btn btn-danger btn-mini <pk:ruleControl module="code_delete"/>" onclick="delmany()">删除</button> -->
					</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                	<td><input name="ids" type="checkbox" value="${row.id}" /></td>
								  <th>${row.remark}</th>
								  <th>
								  <c:if test="${empty row.num}">0</c:if>
								   <c:if test="${not empty row.num}">${row.num}</c:if>
								  </th>
								  
								   <th><img width="40" alt="" src="${row.url}" onclick="showBigImg(this)">  </th>
								   <th>${row.insertTime}</th>
								    <th>${row.updateTime}</th>
                   <td>
                    		<input class="btn btn-mini btn-info  <pk:ruleControl module="code_update"/>" value="修改" type="button" onclick="update(${row.id})">
                    		<input class="btn btn-mini btn-info  <pk:ruleControl module="code_delete"/>" value="删除" type="button" onclick="del(${row.id})">
                   <input class="btn btn-mini btn-info  <pk:ruleControl module="alarm_update"/>" value="详情" type="button" onclick="location.href='codedetail.htm?operate=list&codeid=${row.id}'">
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
					<button id="jsclosebtn" data-dismiss="modal" class="close" type="button" onclick="toggleShow();">返回</button>
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
										<label class="control-label">备注：</label> 
										<input type="text" class="span7" id="remark" name="remark" placeholder="请输入备注" value="">
									</div>
								</div>
						</div>
						<div class="form-actions pagination-right" style="text-align:center">
						<button type="button"  onclick="toggleShow();" class="btn btn-warn">返回</button>
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
						remark: {
							 required: true,
						},
					},
					messages: {
						remark:{
						    required: "请输入备注",
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
	function add() {
		operate = "add";
		$("#remark").val("");
		toggleShow();
	}
	//修改
	function update(id,status) {
		operate_id =id;
		toggleShow();
		operate = "update";
		var url = "code.htm?";
		var obj = {
			operate : "updatepre",
			id : id,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				$("#remark").val(data.data.remark);
			}else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	}
	//批量
	function del(id) {
		operate_id=id;
		operate = "delete";
		confirm(commitEdit, "你确定要删除这条记录吗？");
	}
	//批量删除
/* 	function delmany() {
		operate = "deletemany";
		var checks= getChecks("ids");
		length=0;
		for(var i=0;i<checks.length;i++){
			length++;
		}
		if (length > 0) {
			confirm(commitEdit, "你确定要删除这" + length + "条记录吗？");
		} else {
			tip("请勾选要删除的记录");
		}
	} */
	
	//提交增删改数据
	function commitEdit(id) {
		var obj = {};
		var ids = getIds("ids");
		if (operate == "add") {//新增
			obj = {
				operate : operate,
// 				key : $("#key").val(),
				remark : $("#remark").val(),
			};
		}else if (operate == "update") {
			obj = {
					operate : operate,
					id:operate_id,
					remark : $("#remark").val(),
				};
		}else if (operate == "delete") {
			obj = {
					operate : operate,
					id : operate_id,
				};
		}
		var url = "code.htm?";
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

	function showBigImg(even){
		var imgurl = $(even).attr("src");
		$("#showbigdiv").remove();
		var alert="<div id=\"showbigdiv\" class=\"imgview\">"
			+"<img class=\"imgviewson\" onclick=\"javascript:$('#showbigdiv').remove();\" src=\""+imgurl+"\">" 
			+"</div>"
		$("body").prepend(alert);
	}
	$(function(){
		$("img.showbigimg").dblclick(function(){
			    showBigImg(this);
		});
	});
</script>
 </body>

</html>
