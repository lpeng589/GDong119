<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>重庆消防</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel = "Shortcut Icon" href="../img/favicon.ico">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/matrix-login.css" />
<script type="text/javascript" src="/js/des.js" language="javascript"></script>
</head>
<body>
<!-- 正文 -->
<div id="loginbox">  
	<div class="row">
        <div class="text">
            <h1>重庆消防</h1>
        </div>
    </div>   
    <div class="row">
    	<div class="form-box">
    	<!-- 	<div class="form-top" >
				<h3><img  src="../img/logo2.png" alt="Logo"></h3>
       		</div> -->
       		<div class="form-bottom">
       			<form id="loginform" class="login-form" method="post" action="login.htm">
					<div class="form-group">
						<label for="loginname" class="form_label"><span class="add-on"><i class="icon-user"></i></span>账号</label>
				    	<input type="text" id="loginname" class="form-username form-control" placeholder="请输入账号"/>
				    </div>
				    <div class="form-group">
				    	<label for="password" class="form_label"><span class="add-on"><i class="icon-lock"></i></span>密码</label>
	                	<input type="password" id="password" class="form-password form-control" placeholder="请输入密码"/>
	                </div>
	                <div class="form-group">
	                	<label for="verifycode" class="form_label"><span class="add-on"><i class="icon-key"></i></span>验证码</label>
	                	<input type="text" id="verifycode" class="form-verifycode form-control" placeholder="请输入验证码"/><div class="verifycodeBox"><img id="verifyCodeImg" title="点击更换" onclick="javascript:refreshVerifyCode(this);" src="../verifyCode.htm?-1"></div>
	                </div>
	                <input class="btn" type="button" onclick="login()" value="登&nbsp;&nbsp;录"/>
	            </form>
	        </div>
	    </div>
	</div>    
</div>
<!-- <div id="loginbox">    
	<div class="control-group normal_text"> <h3><img src="../img/logo.png" alt="Logo" /></h3></div>
		<form id="loginform"  action="login.htm">
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
	                	<span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" id="loginname" placeholder="请输入账号" />
	              	</div>
	           	</div>
	       	</div>
	        <div class="control-group">
	           	<div class="controls">
	            	<div class="main_input_box">
	                	<span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" id="password" placeholder="请输入密码" />
	                </div>
	           	</div>
	        </div>     
	        <div class="control-group">
	           	<div class="controls">
	            	<div class="main_input_box">
	                	<span class="add-on bg_lb" style="text-align:center;"><i class="icon-key"></i></span><input style="width:40%;" type="text" id="verifycode" placeholder="请输入验证码" />
	                	<div style="display:inline-block;width:35%;"><img id="verifyCodeImg" title="点击更换" style="cursor:pointer" onclick="javascript:refreshVerifyCode(this);" src="../verifyCode.htm?-1"></div>
	                </div>
	                	                	
	                
	           	</div>
	        </div>
	        <div class="form-actions">
	           	<span style="display:block;width:100%;text-align:center;"><input type="button" class="btn btn-success btn-large" onclick="login()" value="登&nbsp;&nbsp;录"/></span>
	        </div>
        </form>
</div> -->
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script> 
<script src="../js/matrix.js"></script> 
<script src="../js/common.js"></script> 
<script>
function refreshVerifyCode(obj) {
	$(obj).attr("src", "../verifyCode.htm?"+Math.random());
}
function login(){
	var url="logincheck.htm?";
    var password = strEnc($("#password").val(),$("#loginname").val(),'','');  
	var obj={
		loginname:$("#loginname").val(),
		password:password,
		verifycode:$("#verifycode").val()
	};
	var callbackfun=function(data){
			if(data.code==0){
				location.href="main.htm";
			}else{
				refreshVerifyCode($("#verifyCodeImg"));
				tip(data.msg);
			}
	};
	commitData(url,obj,callbackfun);
};

</script>
</body>
</html>