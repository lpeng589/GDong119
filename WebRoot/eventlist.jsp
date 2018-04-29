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
<title>案件列表</title>
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
a:link {color: #0084FF} /* 未访问的链接 */
a:visited {color: #0084FF} /* 已访问的链接 */
a:hover {color: #FF0000} /* 当有鼠标悬停在链接上 */
a:active {color: #0000FF} /* 被选择的链接 */
.layui-layer-photos .layui-layer-content{
	max-height: 800px;	
	overflow-y: auto !important;
}
.layui-layer-photos .layui-layer-content::-webkit-scrollbar {
    display: none;
}
</style>
</head>
<body class="iframebody">	
   <div class="page-container">
        <!-- start 查询条件 -->
          <form class="form-horizontal" method="post" action="event.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
           <div class="text-c">
            	<input name="name" value="${data.cond.name}" placeholder="请输入要查询的名称" class="input-text" style="width:250px">
            	<input name="phone" value="${data.cond.phone}" placeholder="请输入要查询的号码" class="input-text" style="width:250px">
          		<span class="select-box" style="width:150px">
          			<select class="select" name="logstatus">
                  		<option value=" ">全部</option>
<!--                   		<option value="0">未受理</option> -->
                  		<option value="1">待分拨</option>
                  		<option value="2">待反馈</option>
                  		<option value="3">待处理</option>
<!--                   		<option value="4">已转发</option> -->
<!--                   		<option value="5">已反馈</option> -->
                  		<option value="6">已结案</option>
                  		<option value="7">未整改</option>
                  		<option value="8">无效案件</option>
<!--                   		<option value="9">未操作返回</option> -->
                	</select>
                </span>
                <span class="select-box" style="width:160px">
          			<select class="select" name="type">
          				<option value=" ">案件类型：全部</option>
                  		<option value="1">无消防手续</option>
                  		<option value="2">安全出口数量不足</option>
                  		<option value="3">安全出口、疏散通道被占用、堵塞</option>
                  		<option value="4">消防车通道被占用、堵塞</option>
                  		<option value="5">消防设施器材损</option>
                  		<option value="6">外墙门窗上设置影响逃生、灭火救援的障碍物</option>
                  		<option value="7">消防产品不合格</option>
                  		<option value="8">燃气、电气线路数设不安全</option>
                  		<option value="9">违章动火</option>
                  		<option value="10">违规存储易燃易爆危险品</option>
                  		<option value="11">其它消防违法行为和火灾隐患</option>
                	</select>
                </span>
                 <button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
          	</div>
            <!-- end 查询按钮div-->
          </form>
          <div class="mt-20">
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
<!--                 	<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th> -->
				  <th>举报人</th>
				   <th>电话</th>
				  <th style="width: 100px;">类型</th>
				  <th style="width: 200px;">内容</th>
				  <th>位置</th>
				  <th colspan=3>图片</th>
				  <th>视频</th>
				  <th>提交时间</th>
				  <th>状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
<!--                 	<td><input name="ids" type="checkbox" value="${row.id}" /></td> -->
				  <td>${row.name}</td>
				   <td>${row.phone}</td>
				  <td>
				  <c:if test="${row.type==1}">无消防手续</c:if>
				  <c:if test="${row.type==2}">安全出口数量不足</c:if>
				  <c:if test="${row.type==3}">安全出口、疏散通道被占用、堵塞</c:if>
				  <c:if test="${row.type==4}">消防车通道被占用、堵塞</c:if>
				  <c:if test="${row.type==5}">消防设施器材损坏</c:if>
				  <c:if test="${row.type==6}">外墙门窗上设置影响逃生、灭火救援的障碍物</c:if>
				  <c:if test="${row.type==7}">消防产品不合格</c:if>
				  <c:if test="${row.type==8}">燃气、电气线路数设不安全</c:if>
				  <c:if test="${row.type==9}">违章动火</c:if>
				  <c:if test="${row.type==10}">违规存储易燃易爆危险品</c:if>
				  <c:if test="${row.type==11}">其它消防违法行为和火灾隐患</c:if>
				  </td> 
				  <td style="word-wrap:break-word;word-break:break-all;width: 300px;">${row.content}</td>
				  <td><span style="color:#0000FF;" onclick="showmap('${row.latitude}','${row.longitude}','${row.address}','${row.address}')">${row.address}</span></td>
				  <td nowrap=nowrap colspan=3>
				  <c:if test="${!empty row.pic1}"><img src="${row.pic1}" layer-src="${row.pic1}" onclick="showimgs($(this).parent())" width="40" heigth="40">&nbsp;</c:if> 
				  <c:if test="${!empty row.pic2}"><img src="${row.pic2}" layer-src="${row.pic2}" onclick="showimgs($(this).parent())" width="40" heigth="40">&nbsp;</c:if>
				  <c:if test="${!empty row.pic3}"><img src="${row.pic3}" layer-src="${row.pic3}" onclick="showimgs($(this).parent())" width="40" heigth="40">&nbsp;</c:if>
				  </td>
				  <td>
				  	<c:if test="${!empty row.video}"><a href="${row.video}" target="_blank">点击播放</a></c:if>
				  	<c:if test="${empty row.video}">无</c:if>
				  </td>
				  <td>${row.insertTime}</td>
				   <td <c:if test="${row.logstatus>-1}">onclick="detail(${row.id})" style="color: blue;cursor: pointer;"</c:if> >
				   <c:if test="${row.logstatus==0}">未受理</c:if>
				   <c:if test="${row.logstatus==1}">待分拨</c:if>
				   <c:if test="${row.logstatus==2}">已分拨待反馈</c:if>
				   <c:if test="${row.logstatus==3}">已反馈待处理</c:if>
				   <c:if test="${row.logstatus==4}">已转发</c:if>
				   <c:if test="${row.logstatus==5}">已反馈</c:if>
				   <c:if test="${row.logstatus==6}">已结案</c:if>
				   <c:if test="${row.logstatus==7}">未整改</c:if>
				   <c:if test="${row.logstatus==8}">无效案件</c:if>
				   <c:if test="${row.logstatus==9}">未操作返回</c:if>
				   
                   <td>
                   <c:if test="${row.flag}">
                    <c:if test="${depylv==1||depylv==2||depylv==3||depylv==0}">
<!--                    <c:if test="${row.logstatus==1||row.logstatus==2||row.logstatus==3}"> -->
                   <span class="btn btn-primary size-S radius" onclick="zhipai(${row.id},${row.eventlogid })">指派</span>
<!--                    </c:if> -->
					</c:if>
                   <c:if test="${depylv!=0}">
	                   	<c:if test="${row.logstatus==1||row.logstatus==2||row.logstatus==3}">
	                   <span class="btn btn-primary size-S radius" onclick="uphandle(${row.id},${row.eventlogid })">移交</span>
	                   </c:if>
                   </c:if>
                   </c:if>
                   <c:if test="${depylv==1||depylv==2||depylv==0}">
	                   <span class="btn btn-primary size-S radius" onclick="handle(${row.id},${row.eventlogid })">处理</span>
                   </c:if>
                   </td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            </div>
 
<input type="hidden" id="id">    
 <!-- end 查询列表 -->
<jsp:include page="sortpage.jsp"></jsp:include>
<!-- 指派start -->       
<div class="page-container hide" id="zhipaidiv">
	<form action="#" method="post" class="form form-horizontal" id="form-article-add">
			
			<!-- 总队 -->
			<c:if test="${depylv==0}">
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">消防支队 ：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
					<select id="department1" name="" class="select" onchange="changdept('department1')">
						<option value="">请选择一个部门</option>
						<c:forEach items="${deptlistson}" varStatus="i" var="row">
						<option value="${row.id }">${row.name }</option>
						</c:forEach>
					</select>
				</span></div>
				
				<label class="form-label col-xs-4 col-sm-2">街道办 ：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				 <select id="department2"  class="select"> 
				</select>
				</span></div>
			</div>
			</c:if>
			<!-- 消防支队 -->
			<c:if test="${depylv==1}">
			<div class="row cl">
				
				<label class="form-label col-xs-4 col-sm-2">街道办 ：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select id="department2"  class="select">
					 		<option value="">--请选择--</option>
							<c:forEach items="${deptlistson}" varStatus="i" var="row">
						       <option value="${row.id }">${row.name }</option>
						    </c:forEach>
				
				</select>
				</span></div>
			</div>
			</c:if>
			<!-- 街道办 -->
			
			<c:if test="${depylv==2}">
						<div class="row cl">
				
				<label class="form-label col-xs-4 col-sm-2">下级村社：</label>
							<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
					<select id="department3" name="" class="select" onchange="changdept('department3')">
						<option value="${departmentid}">本部门</option>
						<c:forEach items="${deptlistson}" varStatus="i" var="row">
						<option value="${row.id }">${row.name }</option>
						</c:forEach>
					</select>
				</span></div>
				</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">巡查员 ：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select id="workuserid" class="select">
<!-- 							<option value="">--请选择--</option> -->
							<c:forEach items="${workuserlist}" varStatus="i" var="row">
						   <option value="${row.id }">${row.name }</option>
						   </c:forEach>
							
				</select>
				</span></div>
			</div>
			</c:if>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">图片：</label>
				<input type="button" onclick="uploadImage(this)"    value="浏览图片" class="btn btn-warning"/>
				<input type="text" class="span7" name="picupload"  value="" ccc="imagesInput"  style="width: 207px;readonly:true">
				<span  onclick="uploadMoreImage(this)">+增加上传图片</span> 
				<img id="deal_file" ccc="picurl"   style="max-width: 50px;max-height: 50px;"  val="">
				<span class="flag"></span>
			</div>
						<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>备注：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="deal_opinion" placeholder="请输入备注" value="" maxlength="30" style="width:100%">
								</div>
						</div>
								
								
    		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="zhipaicommit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
			</div>
    </form>
</div>
<!-- 指派end -->


<!-- 案件处理start -->       
<div class="page-container hide" id="handlediv">
	<form action="#" method="post" class="form form-horizontal" id="form-article-add">
			

		   <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">案件处理 ：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select id="handle_status" class="select">
							<option value="6">结案 </option>
							<option value="7">未整改 </option>
							<option value="8">无效案件 </option>
				</select>
				</span></div>
			</div>
						<div class="row cl">
							<label class="form-label col-xs-4 col-sm-2">图片：</label> <!--  name="filemanager"   onclick="uploadImage(this)" -->
							<input type="button" onclick="uploadImage(this)"    value="浏览图片" class="btn btn-warning"/>
							<input type="text" class="span7" name="picupload"  value="" ccc="imagesInput"  style="width: 207px;readonly:true">
							<span  onclick="uploadMoreImage(this)">+增加上传图片</span> 
							<img id="deal_file" ccc="picurl"   style="max-width: 50px;max-height: 50px;"  val="">
							<span id="uploadMoreIma"></span>
						</div>
			
						<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>备注：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="handle_opinion" placeholder="请输入备注" value="" maxlength="30" style="width:100%">
								</div>
						</div>
								
								
    		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="handlecommit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
			</div>
    </form>
</div>
<!-- 案件处理startend -->

<!-- 案件处理start -->       
<div class="page-container hide" id="uphandlediv">
	<form action="#" method="post" class="form form-horizontal" id="form-article-add">
						<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>备注：</label>
								<div class="formControls col-xs-8 col-sm-9">
									<input type="text" class="input-text" id="uphandle_opinion" placeholder="请输入备注" value="" maxlength="30" style="width:100%">
								</div>
						</div>
						<div class="row cl">
							<label class="form-label col-xs-4 col-sm-2">图片：</label>
							<input type="button" onclick="uploadImage(this)"    value="浏览图片" class="btn btn-warning"/>
							<input type="text" class="span7" name="picupload"  value="" ccc="imagesInput"  style="width: 207px;readonly:true">
							<span  onclick="uploadMoreImage(this)">+增加上传图片</span> 
							<img id="deal_file" ccc="picurl"   style="max-width: 50px;max-height: 50px;"  val="">
							<span class="flag"></span>
						</div>
						<div class="row cl">
							<label class="form-label col-xs-4 col-sm-2">移交部门 ：</label>
							<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
							<select id="handle_deptid" class="select">
<!-- 								<option value="">上级部门</option> -->
								<c:if test="${departmentid!=row.id }">
									<c:forEach items="${siblinglist }" var="row">
											<option value="${row.id }">${row.name } </option>
									</c:forEach>
								</c:if>
							</select>
							</span></div>
						</div>
    		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="uphandlecommit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
			</div>
    </form>
</div>
<!-- 案件处理startend -->



</div> 

<div class="page-container hide" id="detaildiv">
	<div class="mt-20">
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
				  <th>当前处理角色</th>
				  <th>处理人</th>
				  <th>状态</th>
				  <th>处理意见</th>
				  <th>附件</th>
				  <th>处理时间</th>
				  <th>下一步处理角色</th>
				  <th>下一步处理人</th>
<!--                   <th>地址</th> -->
                </tr>
              </thead>
              <tbody id="detail">
              
			  </tbody>
			  </table>
</div>
</div>
<!-- 地址信息start -->
<div class="page-container hide" id="mapdetail">
    <iframe id="mapPage" width="100%" height="100%" frameborder=0 src="https://3gimg.qq.com/lightmap/v1/marker/index.html?type=0&marker=coord%3A${bean.latitude}%2C${bean.longitude}%3Btitle%3A${bean.address}%3Baddr%3A${bean.address}&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp">
     	
	</iframe>
</div>
<!-- 地址信息end -->
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
<link rel="stylesheet" href="../js/editor/themes/default/default.css" />
<script src="../js/editor/kindeditor-min.js"></script>
<script src="../js/editor/lang/zh_CN.js"></script>  
<script src="../js/uploadFileImage.js"></script>
<script>
$("select[name='logstatus']").val("${data.cond.logstatus}");
$("select[name='type']").val("${data.cond.type}");
</script>
<!-- 增删改查操作 -->
<script type="text/javascript">
	var operate = "";
	var operate_id = "";
	var eventlogid = "";
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
	function detail(id){
		
		var url = "event.htm?operate=detail";
		var obj={
				eventid : id
		}
		var callback=function(data){
			$("#detail").empty();
			for(var i=0;i<data.data.length;i++){
				var item=data.data[i];
				var dealtype="系统分拨";
				if(item.deal==1){
					dealtype="部门处理";
				}else if(item.deal==2){
					dealtype="巡查员处理";
				}
				var nexttype="";
				if(item.next==1){
					nexttype="部门处理";
				}else if(item.next==2){
					nexttype="巡查员处理";
				}else if(item.next==0){
					nexttype="系统处理";
				}
				var dealstatus="";
				if(item.status==0){
					dealstatus="系统分拨";
				}else if(item.status==1){
					dealstatus="待分拨";
				}else if(item.status==2){
					dealstatus="待反馈";
				}else if(item.status==3){
					dealstatus="待处理";
				}else if(item.status==6){
					dealstatus="已结案";
				}else if(item.status==7){
					dealstatus="未整改";
				}else if(item.status==8){
					dealstatus="无效案件";
				}
				var deal_status="";
				if(item.deal_status==10){
					deal_status="(正常提交)";
				}else if(item.deal_status==6){
					deal_status="(申请结案)";
				}else if(item.deal_status==11){
					deal_status="(非负责区域)";
				}else if(item.deal_status==8){
					deal_status="(无效案件)";
				}else if(item.deal_status==7){
					deal_status="(需整改)";
				}
// 				var deal_file="";
// 				var filearr=new Array();
// 				if(item.deal_file!=undefined){
// 					filearr=item.deal_file.split(",");
// 					for (var i=0;i<filearr.length ;i++) { 
// 						deal_file=deal_file+"<a href=\""+filearr+"\" download=\"filename"+i+"\">"; //分割后的字符输出 
// 					} 
// 				}
				var dealfail="";
				if(item.deal_file!=""&&item.deal_file!=undefined){
					var imgarr=item.deal_file.split(",");
					for(var j=0;j<imgarr.length;j++){
						if(imgarr[j]!=undefined&&imgarr[j]!=""){
							console.info(imgarr[j]);
							dealfail+="<img src=\"${imageRootPath}"+imgarr[j]+"\" layer-src=\"${imageRootPath}"+imgarr[j]+"\" onclick=\"showimgs($(this).parent())\" width=\"40\" heigth=\"40\"> &nbsp ";
						}
					}
				}
				var list ="<tr><td>"+dealtype+"</td><td>"+item.dealname+"</td><td>"+dealstatus+"</td><td>"+deal_status+" "+item.deal_opinion+"</td><td>"+dealfail+"</td>"
				+"<td>"+item.createtime+"</td><td>"+nexttype+"</td><td>"+item.nextname+"</td></tr>"
				$("#detail").append(list);
			}
			layer.open({
			      type: 1,
			      title: '选择指派的消防员',
			      shadeClose: true,
			      shade: false,
			      maxmin: true, //开启最大化最小化按钮
			      area: ['80%', '80%'],
			      content: $("#detaildiv"),
			      zIndex:999,
			});
		}
		commitData(url,obj,callback);
	}
	
	function zhipai(id,eventlogid2){
		operate_id=id;
		operate = "zhipai";
		eventlogid = eventlogid2;
		layer.open({
		      type: 1,
		      title: '选择指派',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['80%', '80%'],
		      content: $("#zhipaidiv"),
		      zIndex:999,
		});
	}
	function zhipaicommit(){
		var depylv ='${depylv}';
		
		
		var filearr = new Array();
		if(depylv!=0&&depylv!=1&&($("#workuserid").val()==null||$("#workuserid").val()==''||$("#workuserid").val()==undefined)){    
	    	layer.alert("请选择巡查员！");
	        return ;
	    }
		$("#zhipaidiv").find("input[name='picupload']").each(function(){
			filearr.push($(this).val());
		})
		var url="event.htm?operate=zhipai";
		var obj={
				eventid : operate_id,
				eventlogid : eventlogid,
				workuserid : $("#workuserid").val(),
				department1 : $("#department1").val(),
				department2 : $("#department2").val(),
				deal_opinion : $("#deal_opinion").val(),
				deal_file : filearr
		}
		var callback=function(data){
			layer.msg(data.msg);
			setTimeout("query()",1300);
		}
		commitData(url,obj,callback);
	}
	function changdept(department){
		var obj={
				parentid : $("#"+department).val()
		}
		var url="event.htm?operate=deptlist";
		var callbackfun=function(data){
			$("#department2").empty();
			$("#department2").append("<option value=\"\">------请选择------</option>");
			for(var i=0;i<data.data.length;i++){
				var item=data.data[i];
				$("#department2").append("<option value=\""+item.id+"\">"+item.name+"</option>");
			}
			changdeptson(department);
		}
		commitData(url,obj,callbackfun);
	}
	function changdeptson(department){
		var obj={
				departmentid : $("#"+department).val()
		}
		var url="event.htm?operate=userlist";
		var callbackfun=function(data){
			$("#workuserid").empty();
			$("#workuserid").append("<option value=\"\">------请选择------</option>");
			for(var i=0;i<data.data.length;i++){
				var item=data.data[i];
				$("#workuserid").append("<option value=\""+item.id+"\">"+item.name+"</option>");
			}
		}
		commitData(url,obj,callbackfun);
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
	
	//案件处理start
	function handle(id,eventlogid2){
		operate_id=id;
		operate = "handle";
		eventlogid = eventlogid2;
		layer.open({
		      type: 1,
		      title: '案件处理',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['80%', '80%'],
		      content: $("#handlediv"),
		      zIndex:999,
		});
	}
	function handlecommit(){
		var imgs= new Array();
		var url="event.htm?operate=handle";
		$("input[name='picupload']").each(function(){
			if(this.value!=''&&this.value!=null&&this.value!=undefined){
				imgs=imgs+this.value+",";
			}
		});
		var obj={
				eventid : operate_id,
				eventlogid : eventlogid,
				status : $("#handle_status").val(),
				deal_opinion : $("#handle_opinion").val(),
				deal_file : imgs,
		}
		var callback=function(data){
			layer.msg(data.msg);
			setTimeout("query()",1300);
		}
		commitData(url,obj,callback);
	}
	//案件处理end
	
	//案件返回上一级start
	function uphandle(id,eventlogid2){
		operate_id=id;
		operate = "uphandle";
		eventlogid = eventlogid2;
		layer.open({
		      type: 1,
		      title: '移交',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['80%', '80%'],
		      content: $("#uphandlediv"),
		      zIndex:999,
		});
	}
	function uphandlecommit(){
		var filearr = new Array();
		$("#uphandlediv").find("input[name='picupload']").each(function(){
			filearr.push($(this).val());
		})
		if($("#handle_deptid").val()==""){
			var obj={
					eventid : operate_id,
					eventlogid : eventlogid,
					deal_opinion : $("#uphandle_opinion").val(),
					deal_file : filearr
			}
		}else{
			var obj={
					eventid : operate_id,
					eventlogid : eventlogid,
					deal_opinion : $("#uphandle_opinion").val(),
					next_id : $("#handle_deptid").val(),
					deal_file : filearr
			}
		}
		var url="event.htm?operate=uphandle";
		var callback=function(data){
			layer.msg(data.msg);
			setTimeout("query()",1300);
		}
		commitData(url,obj,callback);
	}
	//案件返回上一级end
	function showmap(latitude,longitude,address,address){
		layer.open({
		      type: 2,
		      title: '地址信息',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['80%', '80%'],
		      content: 'https://3gimg.qq.com/lightmap/v1/marker/index.html?type=0&marker=coord%3A'+latitude+'%2C'+longitude+'%3Btitle%3A'+address+'%3Baddr%3A'+address+'&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp',
		      zIndex:999,
		});
	}
</script>
</body>
</html>