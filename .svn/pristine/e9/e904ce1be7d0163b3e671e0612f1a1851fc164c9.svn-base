<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/pnkootag"  prefix="pk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重庆消防指挥中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="newindex/css/css.css">
</head>
<body style="background:#092045">
	<!-- <div class="header-box">
		<div class="header"><img src="newindex/images/logo.jpg" alt=""></div>
	</div> -->
	<div class="content-box">
		<div class="content">
		</div>
		<div class="login-box">
			<input type="text" class="input username" id="loginname">
			<input type="password" class="input password" id="password">
			<input type="text" class="input verification" id="verifycode">
			<div class="getverification" style="left: 281px;top: 249px;">
			<img class="fr" id="verifyCodeImg" title="点击更换" onclick="javascript:refreshVerifyCode(this);" src="../verifyCode.htm?-1" style="height: 100%">
			</div>
			<div class="btn" onclick="login()"></div>
			<div class="check" id="checked"></div>
			<div id="check" style="color: #8b9ead" onclick="forget()">忘记密码</div>
		</div>
	</div>
	<div class="footer-box">
		<div class="footer">
		<!-- <img src="newindex/images/logo2.jpg" alt="">
		<div class="text">
			<p style="margin-bottom:5px;">服务热线：0755-86571717</p>
			<p>公司地址：广东省深圳市福田区国际创新中心C座19楼</p>	
		</div> -->
		</div>
	</div>
</body>
<script src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script src="../js/common.js"></script> 
	<script type="text/javascript" src="/js/des.js" language="javascript"></script>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript">
	function forget(){
		layer.open({
			  type: 2,
			  title: '忘记密码',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['880px', '60%'],
			  zIndex : 999,
			  content: 'pcforgetpre.html' //iframe的url
			}); 
	}
	function refreshVerifyCode(obj) {
		$(obj).attr("src", "../verifyCode.htm?"+Math.random());
	}
	function login(){
		var url="logincheck.htm?";
		if( $("#loginname").val()==""){
			layer.msg("账户不能为空");
			$("#loginname").focus();
			return;
		}
		if($("#password").val()=="" ){
			layer.msg("密码不能为空");
			$("#password").focus();
			return;
		}
		
	    var password = strEnc($("#password").val(),$("#loginname").val(),'','');  
		var obj={
			loginname:$("#loginname").val(),
			password:password,
			verifycode:$("#verifycode").val()
		};
		var callbackfun=function(data){
				console.log(data);
				if(data.code==0){
					location.href="main.htm";
				}else{
					refreshVerifyCode($("#verifyCodeImg"));
					layer.msg(data.msg);
				}
		};
		commitData(url,obj,callbackfun);
	};
	$(function(){
		$('.login li.auto_login label').click(function(){
			$(this).toggleClass('on');
		})
	})
	
	$(document).keypress(function(e) {
        var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
        if (eCode == 13){
        	login();
        }
	})

	</script>
</html>
