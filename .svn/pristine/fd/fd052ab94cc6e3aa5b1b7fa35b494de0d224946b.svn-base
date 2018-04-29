<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>设置权限</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="../css/uniform.css" />
<link rel="stylesheet" href="../css/select2.css" />
<link rel="stylesheet" href="../css/matrix-style.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<style>
.main{
	padding:50px 5%;
}
</style>
<body>
   <div>
        <!-- start 查询条件 -->
        <div class="widget-box" >
          <div class="widget-content ">
          <!-- end 查询条件-->
              <!-- start 查询列表 -->
            <table class="table table-bordered table-striped with-check">
              <thead>
                <tr>
                	<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox"/></th>
                    <th>主模块</th>
                    <th>相关权限</th>
                    <th>
                    <input class="btn btn-success" style="height:30px" value="提交" type="button" onclick="commitEdit();">
               			<button type="button"  onclick="history.go(-1);"class="btn btn-warn">返回</button>
               			</th>
                </tr>
              </thead>
              <tbody>
              <c:set var="tmp" value=""/>
              <c:forEach items="${data}" varStatus="i" var="row">  
              	    <c:if test="${tmp ne row.category }">
              			<tr>
              		</c:if>                	
                	<%-- <c:if test="${tmp ne row.category and i.index ne 0}"></td></tr></c:if> --%>
					<c:if test="${tmp ne row.category }">
							<td><input name="ids"  class="checkall" type="checkbox" /></td>
							<td class="bluebg" nowrap="nowrap">${row.category }</td>
					</c:if>
					<c:if test="${tmp ne row.category}">
							<td>
					</c:if>
						<c:if test="${row.ismenu==1}">
								<span style="color:blue">${row.name }</span>
						</c:if>
						<c:if test="${row.ismenu!=1}">
								${row.name }
						</c:if>
					<c:if test="${row.moduleid == null}">
						<input type="checkbox" name="moduleid" value="${row.id}" />  &nbsp; &nbsp; 
					</c:if>
					<c:if test="${row.moduleid != null}">
						<input type="checkbox" name="moduleid" value="${row.id}" checked="checked"/> &nbsp;&nbsp; 
					</c:if>
					<c:set var="tmp" value="${row.category }"/>	
					<c:if test="${tmp ne row.category }">
						</td></tr>			
              		</c:if>
              </c:forEach>
              </tbody>
            </table>
                                      <div>
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
<script>
//选择全部
$(function(){
	$("#title-table-checkbox").change(function(){
		if($(this).prop("checked")){
			$(".checkall").parents("tr").find("input").attr("checked", "checked");
			$(".checkall").parents("tr").find("input").parents("span").attr("class","checked");
		}else{
			$(".checkall").parents("tr").find("input").removeAttr("checked");
			$(".checkall").parents("tr").find("input").parents("span").removeAttr("class");
		}
	});
	$(".checkall").change(function(){
		if($(this).prop("checked")){
			$(this).parents("tr").find("input").attr("checked", "checked");
			$(this).parents("tr").find("input").parents("span").attr("class","checked");
		}else{
			$(this).parents("tr").find("input").removeAttr("checked");
			$(this).parents("tr").find("input").parents("span").removeAttr("class");
		}
	});
	function boxChange(){
		
	}
});

//提交增删改数据
function commitEdit(){
	var moduleid = new Array();
	$("input[name='moduleid']:checked").each(function(){
		moduleid[moduleid.length]=this.value;
	});
	var obj={
			operate:"updatepri",
			role_id:"${role.id}",
			moduleid:moduleid
    	};
	var url="role.htm?";
	var callbackfun=function(data){
		if(data.code==0){
			tip(data.msg,goBack);
		}else{
			tip(data.msg);
		}
	};
	commitData(url,obj,callbackfun);
};
function goBack(){
	history.go(-1);
}


</script>
</body>
</html>