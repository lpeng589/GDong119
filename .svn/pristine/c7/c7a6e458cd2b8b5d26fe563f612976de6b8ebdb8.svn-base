<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<title>采购供应一体化平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta name="HandheldFriendly" content="true">
	<link rel="icon" href="../images/favicon.ico">
	<link rel="stylesheet" href="../css/common.css">
	<link rel="stylesheet" href="../css/list.css">
	<script src="../js/jquery.js"></script>
	<script src="../js/common.js"></script>
	<script type="text/javascript">
		var operate="";

		var selectedOperatorId="";
		//添加
    	function add(){
    		operate="add";
    		popWin("add_div");
    	
    	
      	}
		

		//修改
    	function update(id){
    		operate="update";
    		$("#id").val(id);
    		popWin("edit_div");
    		var url="employee.htm?";
    		var obj={
    	    		operate:"updatepre",
    	    		id:id,
    	    	};
    		var callbackfun={
    			success:function(data){
    				if(data.errcode!=null){
    					closeWin();
    					operatePrompt(data.errmsg,data.erroperate);
    					return false;
    				}
    					$("#loginname").val(data.loginname);
    					$("#loginname").attr("readonly",true);
        	    		$("#username").val(data.username);
        	    		$("#password").val(data.password);
        	    		$("#status").val(data.status);
    			}
    		};
    		commitData(url,obj,callbackfun);
      	}
    	//删除
    	function del(){
    		operate="delete";
    		var length=$("input[name='ids']:checked").length;
    		if(length>0){
    			confirms(commitEdit,"你确定要删除这"+length+"条记录吗？");
    		}else{
    			prompt("请先选择要删除的记录");
    		}
    		
      	}
    	//提交增删改数据
    	function commitEdit(){
    		var obj={};
    		if(operate=="add"){
    			if(!checkInput("loginname,username,password")){
    				return false;
    			}
    			obj={
    	    		operate:operate,
    	    		loginname:$("#loginname").val(),
    	    		username:$("#username").val(),
    	    		password:$("#password").val(),
    	    		status:$("#status").val()
    	    	};
    		}else if(operate=="update"){
    			if(!checkInput("username,password")){
    				return false;
    			}
    			obj={
    	    		operate:operate,
    	    		id:$("#id").val(),
    	    		username:$("#username").val(),
    	    		password:$("#password").val(),
    	    		status:$("#status").val()
    	    	};
    		}else if(operate=="delete"){
    			var ids = new Array();
    			$("input[name='ids']:checked").each(function(){
    				ids[ids.length]=this.value;
    			});
    			obj={
    	    		operate:operate,
    	        	ids:ids,
    	        };
    		}
    		var url="employee.htm?";
    		var callbackfun={
    			success:function(data){
    				if(data.errcode==0){
    					operatePrompt(data.errmsg,"document.forms[0].submit()");
    				}else{
    					operatePrompt(data.errmsg,data.erroperate);
    				}
    			}
    		};
    		commitData(url,obj,callbackfun);
    	};
    	
    	function submitAssign()
    	{
       		var obj={};

    			obj={
    	    		operate:"add",
    	    		order_id:$("#add_order_id").val(),
    	    		operator_id:$("#add_operator_id").val(),
    	    		
    	    	};
    		
    		var url="buyerOrderAssignee.htm?";
    		var callbackfun={
    			success:function(data){
    				if(data.errcode==0){
    					operatePrompt(data.errmsg,"document.forms[0].submit()");
    				}else{
    					operatePrompt(data.errmsg,data.erroperate);
    				}
    			}
    		};
    		commitData(url,obj,callbackfun);
    	}
    	
    	
    	function deleteAssign(order_id,operator_id)
    	{
       		var obj={};

    			obj={
    	    		operate:"delete",
    	    		order_id:order_id,
    	    		operator_id:operator_id,
    	    		
    	    	};
    		
    		var url="buyerOrderAssignee.htm?";
    		var callbackfun={
    			success:function(data){
    				if(data.errcode==0){
    					operatePrompt(data.errmsg,"document.forms[0].submit()");
    				}else{
    					operatePrompt(data.errmsg,data.erroperate);
    				}
    			}
    		};
    		commitData(url,obj,callbackfun);
    	}
    	
    </script>
</head>
<body>
    <div class="container">
    	<form id="form" method="get" action="buyerOrderAssignee.htm">
			<input type="hidden" name="operate" value="list" />
			<input type="hidden" id="page" name="page" value="${data.cond.page}" />
    		<div align="center">
    			<table class="vgrid" align="center">
    				<tbody>
    					<tr>
    						<th>订单ID</th>
    						<td>
    						   <input readonly="readonly" class="baisc_input"   name="order_id" value="${data.cond.order_id}" placeholder="登录账号"></td>
    						<th>操作员：</th>
    						<td><input class="baisc_input" id="operator_id" name="operator_id" value="${data.cond.operator_id}" onclick="selectOperator('operator_id')"  placeholder="操作员" ></td>

    						<td rowspan="1">
    							<input class="baisc_button" value="查询" type="submit">
    						</td>
    					</tr>
    				</tbody>
    			</table>
    		</div>
    	</form>
    	<table class="hgrid" align="center">
    		<thead>
    			<tr>
    				
    				<th>订单ID</th>
    				<th>操作员登陆名</th>
    				<th>操作员用户名</th>
    				
    				<th>
    					<input class="baisc_button absolute" value="新增" type="button" onclick="add()">
    					
    				</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${data.list}" varStatus="i" var="row">
    			<tr>
    				
    				<td>${row.order_id }</td>
    				<td>${row.operator_loginname }</td>
    				<td>${row.operator_username }</td>
    				
    				
    				<td>
    					<input class="baisc_button absolute" value="删除" type="button" onclick="deleteAssign(${row.order_id },${row.operator_id})">
    				</td>
    			</tr>
    			</c:forEach>
			</tbody>
    	</table>
        <jsp:include page="sortpage.jsp"></jsp:include>
	</div>
	<div id="add_div" class="edit_div">
		
		<table class="vgrid" align="center">
			<thead>
			<tr><td colspan="4"><h3>订单指派</h3></td></tr>
			</thead>
    		<tbody>
    			<tr>
    				<th>订单ID</th>
    				<td>
    				 <input type="hidden"  id="add_order_id" name="add_order_id" value="${data.cond.order_id}" >
    				 <span id="add_order_id_label" >${data.cond.order_id}</span>
    				</td>
    			</tr>
    			<tr>
    				<th>职员</th>
    				<td><input class="baisc_input" id="add_operator_id" name="add_operator_id" value="" placeholder="请输入职员名" onclick="selectOperator('add_operator_id')"  ></td>
    			
    			</tr>

    			<tr>
    				<td colspan="2">
    					<input class="baisc_button listcancel" value="取消" type="button" onclick="closeWin()">
    					<input class="baisc_button listsubmit" value="提交" type="button" onclick="submitAssign();">
    				</td>
    			</tr>
    		</tbody>
    	</table>
	</div>
	
		<div id="operator_select_div" class="edit_div">
		
		<div>
		<iframe src="employee.htm?operate=listForSelect" name="operator_select_iframe" frameborder="0" allowtransparency="true" style="width: 100%; height:100%;" id="operator_select_iframe"> </iframe>
		</div>
		<div>
		<script type="text/javascript">
		function selectOperator(refreshElementId)
		{
			$("#refreshElementId").val(refreshElementId);
			popWin("operator_select_div");
			
		}
		function getSelectedOperatorId()
		{
		 var tempOperatorId=$("input[name='operator_ids']:checked", $('#operator_select_iframe')[0].contentWindow.document).val();
		 if(tempOperatorId==null)
		 {
			 alert("请选择操作员");
		 }
		 else
		 {
			 //更新需要更新的元素
			 
			 $("#"+$("#refreshElementId").val()).val(tempOperatorId);
			 //关闭模式对话框
			 closeWin();
		 }
			
			 
		}
		</script>
		<form id="review_form" method="post" action="buyerOrder.htm">
		<input type="hidden" id="refreshElementId" value="" />
		
		<table class="vgrid" align="center">

    		<tbody>

    			<tr>
    				<td colspan="2">
    				    
    					<input class="baisc_button listcancel" value="取消" type="button" onclick="closeWin()">
    					<input class="baisc_button listsubmit" value="提交" type="button" onclick="getSelectedOperatorId();">
    				</td>
    			</tr>
    		</tbody>
    	</table>
    	</form>
    			</div>
	</div>
	
	
</body>
</html>

