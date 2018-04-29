<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>消息列表</title>
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
.circle {
    background: none repeat scroll 0 0 red;
    border-radius: 50%;
    color: #FFFFFF;
    width:6px;
    height:6px;
    display:inline-block;
    margin-right:3px;
}
.time{
	float:right;
}
.content{
	display:none;
	font-weight:bold;
}
.title-read{
}
.title-noread{
	font-weight:bold;
}
</style>
</head>
<body class="iframebody" >
 <div>
        <!-- start 查询条件 -->
        <div class="widget-box" id="brow">
          <div class="widget-content ">
          <form class="form-horizontal" method="post" action="message.htm">
          	<input type="hidden" name="operate" value="list" />
            <input type="hidden" name="page" id="page" value="${data.cond.page}" />
            <!-- start 条件输入框div超过四个再复制多一份 -->
            <div class="controls controls-row">
          		<!-- select 空用空格表示-->
          		<label class="control-label">状态：</label>
          		<div style="width:156px;display:inline-block;margin:0">
          			<select name="status" style="display:none">
                  		<option value="">全部</option>
                  		<option value="0">未读</option>
                  		<option value="1">已读</option>
                	</select>
                </div>
               <!--select -->
          	</div>
          	<!-- end 条件输入框div-->
          	
          	<!-- start 查询按钮div -->
          	<div class="controls controls-row pagination-right">
              <button type="submit" class="btn btn-primary">查询</button>
              <input type="button" class="btn btn-success" onclick="read()" value="设置已读"></input><!-- 可删除自己的草稿中1和审核失败4状态的-->
              <input type="button" onclick="allread()" class="btn btn-danger"  value="设置全部为已读"></input><!-- 可删除自己的草稿中1和审核失败4状态的-->
<!--               <button class="btn btn-danger" onclick="del()">删除</button>可删除自己的草稿中1和审核失败4状态的 -->
            </div>
            <!-- end 查询按钮div-->
          </form>
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
                	<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
                    <th>
					</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${data.list}" varStatus="i" var="row">
              	<tr>
                	<td><input name="ids" type="checkbox" value="${row.id }" status="${row.status }"/></td> 
                    <td style="cursor:pointer" onclick="toggle(${row.id},${row.status},this)">
                <c:if test="${not empty row.link}">    <a href="${row.link}">  </c:if> 
                    <c:if test="${row.status==0}">
                    	<p class="title-noread"><i class="circle"></i>${row.title }<span class="time">${row.createtime }</span></p>
                    </c:if>
                    <c:if test="${row.status==1}">
                    	<p class="title-read">${row.title }<span class="time">${row.createtime }</span></p>
                    </c:if>
                    
                    
                    <p class="content">
                   <c:if test="${empty row.link}">
                   	${row.content }
                   </c:if> 
                   <c:if test="${not empty row.link}">
                   	<a href="${row.link }" >${row.content }</a>
                   </c:if> 
                     </p>  
                     
                    </td>
              	</tr>
              </c:forEach>
              </tbody>
            </table>
            <!-- end 查询列表 -->
            <jsp:include page="sortpage.jsp"></jsp:include>
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
<script>
$("select[name='status']").val("${data.cond.status}");
</script>
<script type="text/javascript">
var operate="";
	function toggle(id,status,event){
		$(event).find("p[class='content']").toggle();
		if(status==0){
			$(event).find("i[class='circle']").remove();
			$(event).find("p[class='title-noread']").attr("class","title-read");
			update(id);
		}
	}
	function update(id){
		operate = "update";
		commitEdit(id);
	}
	//删除
	function del() {
		operate = "delete";
		var checks= getChecks("ids");
		for(var i=0;i<checks.length;i++){
			length++;
		}
		if (length > 0) {
			commitEdit("delete");
		} else {
			tip("请勾选要删除的记录");
		}
	}
	//已读
	function read() {
		operate = "update";
		var checks= getChecks("ids");
		for(var i=0;i<checks.length;i++){
			length++;
		}
		if (length > 0) {
			commitEdit("read");
		} else {
			tip("请勾选要设置的记录");
		}
	}
	function allread(){
		operate = "updateAll";
		confirm(commitEdit, "确定要将所有未读信息设置为已读？");
		//event.stopPropagation();
	}
	var ids;
	//提交增删改数据
	function commitEdit(id) {
		if (operate == "update") { 
			if(id=="read"){
				ids = getIds("ids");
			}else{
				ids = [id];
			}
			obj = {
				operate : operate,
				ids : ids,
			};
		}else if (operate == "updateAll") {
			obj = {
					operate : operate,
				};
		}else if (operate == "delete") {
			ids = getIds("ids");	
			obj = {
					operate : operate,
					ids : ids,
				};
		};
		
		var url = "message.htm?";
		var callbackfun = function(data) {
			if (data.code == 0){
				if(operate=="update"&&ids.length>1){
					query();
				}
				if(operate == "updateAll"){
					query();
				}
			}else{
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	}
</script>
</body>
</html>