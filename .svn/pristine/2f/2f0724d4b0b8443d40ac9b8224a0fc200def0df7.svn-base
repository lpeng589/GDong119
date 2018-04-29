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
</head>
<body class="iframebody">
   <div>
   		
        <!-- start 查询条件 -->
        <div class="widget-box" id="list_div">
          <div class="widget-content ">
          <form class="form-horizontal" method="post" action="alarmdetail.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <input type="hidden" name="alarmid" id="alarmid" value="${data.cond.alarmid}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
                      <div class="controls controls-row">
          		<label class="control-label floatleft">状态：</label>
          		<!-- select 空用空格表示-->
          		<div style="float:left;width:156px;display:inline-block;margin:0">
          			<select name="sendtype" style="display:none">
                  		<option value=" ">全部</option>
                  		<option value="1">回复</option>
                  		<option value="0">请求	</option>
                	</select>
                </div>
               <label class="control-label floatleft">内容类型：</label>
              <div style="float:left;width:156px;display:inline-block;margin:0">
          			       <select name="type"  style="display:none">
                  				 <option value="">全部</option>
                  				 <option value="0">文本</option>
                  				 <option value="1">语音</option>
                  				 <option value="2">视频</option>
                  				 <option value="3">图片</option>
                  				 <option value="4">地理</option>
                			</select>
                </div>
          	</div>
           
          	<!-- end 条件输入框div-->
          	
          	<!-- start 查询按钮div -->
          	<div class="controls controls-row pagination-right">
               <button onclick="query()" class="btn btn-primary">查询</button>
               <button onclick="history.go(-1)" class="btn">返回</button>
            </div>
            <!-- end 查询按钮div-->
          </form>
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
                	<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
								  <th>时间</th>
								  <th>类型</th>
								  <th>内容</th>
<!-- 								  <th>方向</th> -->
<!--                   <th> -->
<!-- 						  <button class="btn btn-success btn-mini <pk:ruleControl module="alarmdetail_add"/>"  onclick="add()">新增</button> -->
<!-- 					    <button class="btn btn-danger btn-mini <pk:ruleControl module="alarmdetail_delete"/>" onclick="delmany()">删除</button> -->
<!-- 					</th> -->
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                	<td><input name="ids" type="checkbox" value="${row.id}" /></td>
                					<th>${row.insertTime}</th>
                					<th>
                					<c:if test="${row.type==0}">文本</c:if>
                					<c:if test="${row.type==1}">语音</c:if>
                					<c:if test="${row.type==2}">视频</c:if>
                					<c:if test="${row.type==3}">图片</c:if>
                					<c:if test="${row.type==4}">位置</c:if>
                					</th>
                					<th>
                					<c:if test="${row.type==0||row.type==4}">${row.content}</c:if>	
                					<c:if test="${row.type==3}"><img src="${row.content}" width="40" height="40" onclick="showBigImg(this)"></c:if>
                					<c:if test="${row.type==2 ||row.type==1 }"><a href="${row.content}" target="_blank">点击播放</a></c:if>
                					</th>
								  
<!-- 								  <th><c:if test="${row.sendtype==0}">请求</c:if> -->
<!--                 					<c:if test="${row.sendtype==1}">警员回复</c:if></th> -->
								  
<!--                    <td> -->
<!--                     		<input class="btn btn-mini btn-info  <pk:ruleControl module="alarmdetail_update"/>" value="修改" type="button" onclick="update(${row.id})"> -->
<!--                     		<input class="btn btn-mini btn-info  <pk:ruleControl module="alarmdetail_delete"/>" value="删除" type="button" onclick="del(${row.id})"> -->
<!--                    </td> -->
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
										<label class="control-label">content：</label> 
										<input type="text" class="span7" id="content" name="content" placeholder="请输入content" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">insertTime：</label> 
										<input type="text" class="span7" id="insertTime" name="insertTime" placeholder="请输入insertTime" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">sendtype：</label> 
										<input type="text" class="span7" id="sendtype" name="sendtype" placeholder="请输入sendtype" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">type：</label> 
										<input type="text" class="span7" id="type" name="type" placeholder="请输入type" value="">
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="control-label">alarmid：</label> 
										<input type="text" class="span7" id="alarmid" name="alarmid" placeholder="请输入alarmid" value="">
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
$("select[name='sendtype']").val("${data.cond.sendtype}");
$("select[name='type']").val("${data.cond.type}")
</script>
<script>
$(document).ready(function() {
	 $("#commentForm").validate(
			 {
					rules: {
						content: {
							 required: true,
						},
						insertTime: {
							 required: true,
						},
						sendtype: {
							 required: true,
						},
						type: {
							 required: true,
						},
						alarmid: {
							 required: true,
						},
					},
					messages: {
						content:{
						    required: "请输入content",
						   },
						insertTime:{
						    required: "请输入insertTime",
						   },
						sendtype:{
						    required: "请输入sendtype",
						   },
						type:{
						    required: "请输入type",
						   },
						alarmid:{
						    required: "请输入alarmid",
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
		$("#content").val("");
		$("#insertTime").val("");
		$("#sendtype").val("");
		$("#type").val("");
		$("#alarmid").val("");
		toggleShow();
	}
	//修改
	function update(id,status) {
		operate_id =id;
		toggleShow();
		operate = "update";
		var url = "alarmdetail.htm?";
		var obj = {
			operate : "updatepre",
			id : id,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				$("#content").val(data.data.content);
				$("#insertTime").val(data.data.insertTime);
				$("#sendtype").val(data.data.sendtype);
				$("#type").val(data.data.type);
				$("#alarmid").val(data.data.alarmid);
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
	function delmany() {
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
	}
	
	//提交增删改数据
	function commitEdit(id) {
		var obj = {};
		var ids = getIds("ids");
		if (operate == "add") {//新增
			obj = {
				operate : operate,
				content : $("#content").val(),
				insertTime : $("#insertTime").val(),
				sendtype : $("#sendtype").val(),
				type : $("#type").val(),
				alarmid : $("#alarmid").val(),
			};
		}else if (operate == "update") {
			obj = {
					operate : operate,
					id:operate_id,
					content : $("#content").val(),
					insertTime : $("#insertTime").val(),
					sendtype : $("#sendtype").val(),
					type : $("#type").val(),
					alarmid : $("#alarmid").val(),
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
		}
		var url = "alarmdetail.htm?";
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
