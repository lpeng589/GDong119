<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>重庆消防</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="../css/uniform.css" />
<link rel="stylesheet" href="../css/select2.css" />
<link rel="stylesheet" href="../css/matrix-style.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/js/des.js" language="javascript"></script>
</head>
<body class="iframebody">
   <div>
   <div class="container-fluid">
  <hr>
      <div class="widget-box" style="width: 80%">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5>修改密码</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="#" method="get" class="form-horizontal">
            <div class="control-group">
              <label class="control-label" style="float: left;width: 160px;padding-top: 15px;text-align: right;">现在的密码 :</label>
              <div class="controls">
                <input type="password" class="span11"  style="width: 40%" id="password" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" style="float: left;width: 160px;padding-top: 15px;text-align: right;">请输入新的密码 :</label>
              <div class="controls">
                <input type="password" class="span11"  style="width: 40%" id="password1"  />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" style="float: left;width: 160px;padding-top: 15px;text-align: right;">请再次输入密码 :</label>
              <div class="controls">
                <input type="password"  class="span11"   style="width: 40%" id="passrowd2"  />
              </div>
            </div>
            <div class="form-actions">
              <button type="button" class="btn btn-success" style="margin-left: 40%" onclick="commitEdit();">保存</button>
           </div>
          </form>
        </div>
      </div> <div >温馨提示：新密码只能输入6-20位必须包含至少一个字母、一个数字和一个字符!@#$%^&*</div>
      <!--  <button type="button" class="btn btn-success" onclick="importSubmitPre('hjjsupplier_import.htm');">导入供应商</button>
       <button type="button" class="btn btn-success" onclick="importSubmitPre('hjjcustom_import.htm');">导入用户</button>
       <button type="button" class="btn btn-success" onclick="importSubmitPre('hjjstock_import.htm');">导入库存</button> -->
      </div>
      
      
      	 <div id="importmodel" class="modal hide">
			<div class="modal-header">
				<button data-dismiss="modal" class="close" type="button">×</button>
				<h3>&nbsp;</h3>
			</div>
			<div class="modal-body">
				<div class="widget-box">
					<form class="form-horizontal" id="importForm" method="post" action="#" enctype="multipart/form-data">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i></span>
							<h5>编辑</h5>
						</div>
						<div class="widget-content">
							<div class="control-group">
								<div class="controls">
									<label class="control-label">选择文件 ：<span
										style="color:red"> *</span></label> <input type="file" name="filein"/>
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions pagination-right">
						<button type="button" onclick="importSubmit()" class="btn btn-success">导入</button>
					</div>
				</div>
			</div>
		</div>
      </div>
		
 <input type="hidden" id="loginname"  value="${loginname}"/>			
<script src="../js/jquery.min.js"></script> 
<script src="../js/bootstrap.min.js"></script> 
<script src="../js/jquery.uniform.js"></script> 
<script src="../js/select2.min.js"></script> 
<script src="../js/jquery.dataTables.min.js"></script> 
<script src="../js/matrix.js"></script> 
<script src="../js/matrix.tables.js"></script>
<script src="../js/common.js"></script>
<!-- 增删改查操作 -->
<script type="text/javascript">
	
	function commitEdit() {
		if(check()){
			return ;
		}
		var operate = "modifys";
	    var password = strEnc($("#password").val(),$("#loginname").val(),'','');  
	    var password1 = strEnc($("#password1").val(),$("#loginname").val(),'','');  
		var	obj = {
				operate : operate,
				password : password,
				loginname : $("#loginname").val(),
				password1 : password1
			};
		var url = "employee.htm?";
		var callbackfun = function(data) {
			if (data.code == 0) {
// 				tip(data.msg);
				window.parent.goLoginPage();
			} else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
	};
	
	
	function check(){
		if($("#password").val().trim().length==0){    
	    	tip("请输入密码！");
	    	return true;
	    } 
	    if($("#password1").val().trim().length==0){    
	    	tip("请输入新密码！");
	    	return true;
	    } 
	    /* if($("#password1").val().trim().length>8){    
	    	tip("请输入新密码,新密码长度不能大于8位！");
	    	return true;
	    } */
	    
// 	    var patrn=/^(\w){6,20}$/; 
		   var regStr = /[a-zA-Z]/;
          var regNum = /[0-9]/;
          var regSign = /[!@#$%^&*]/;
          var complex = 0;
          if (!regStr.test($("#password1").val())) {
              ++complex;
          }
          if (!regNum.test($("#password1").val())) {
        	  ++complex;
          }
          if (!regSign.test($("#password1").val())) {
        	  ++complex;
          }
          if($("#password1").val().trim().length<6 || $("#password1").val().trim().length>20){    
        	  ++complex;
    	  }
          if (complex!=0) {
        	  tip("新密码只能输入6-20位必须包含至少一个字母、一个数字和一个字符!@#$%^&*");
  	    	  return true ;
          }
          
		/*  var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,19}$/;  
	    if (!patrn.exec($("#password1").val())) {
	    	tip("新密码只能输入6-20个以字母开头，可以包含字母、数字、下划线！");
	    	return true ;
	    }  */
	    if($("#passrowd2").val().trim().length==0){    
	    	tip("请再次输入新密码！");
	    	return true;
	    } 
	    if($("#password1").val()!=$("#passrowd2").val()){
	    	tip("两次密码输入不一致！");
	    	return true;
	    }
	    return false;
	}
	function importSubmitPre(actionmane){
		$("#importForm").attr("action",actionmane);
		$('#importmodel').modal('show');
		
	}
	function importSubmit(){
		$("#importForm").submit();
	}
	
</script>

<link rel="stylesheet" href="../js/editor/themes/default/default.css" />
<script src="../js/editor/kindeditor-min.js"></script>
<script src="../js/editor/lang/zh_CN.js"></script>  
<span id="pifeSpan" class="input-group-addon" style="display:none"><%=request.getSession().getAttribute("imageRootPath")%></span>
 </body>
</html>
