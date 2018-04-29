<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增打卡</title>
<meta charset="utf-8">
<meta content="telephone=no, address=no" name="format-detection">
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<link rel="stylesheet" href="../mobile/css/weui.min.css" />
<link rel="stylesheet" href="../mobile/css/main.css" />
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<style type="text/css">
.page input {
    padding: 0px 0px;
}
</style>
</head>
<body>
<div class="page">

<div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" placeholder="请输入打卡频道名称" maxlength="20" id="name"/>
                </div>
            </div>
        </div>
        
<div class="weui-cells weui-cells_form" style="margin-top: 0.5em;">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入打卡频道简介" rows="3" id="describe"></textarea>
<!--                     <div class="weui-textarea-counter"><span>0</span>/200</div> -->
                </div>
            </div>
        </div>

<div class="page__bd page__bd_spacing" style="margin-top: 1em;">
    <a href="javascript:add();" class="weui-btn weui-btn_primary" style="background-color: #337AB0">提交</a>
</div>

<!-- 弹窗 -->
<div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">创建成功</p>
        </div>
    </div>
    
</div>
  
  
<script>
function add(){
	if($("#name").val()==null||$("#name").val()==""||$("#name").val()==undefined){
		wxtip("请输入频道名称");
		return;
	}
	var url="wxchanneladd.htm";	//签到url
	var obj={
			name : $("#name").val(),
            describe :  $("#describe").val(),
		};
		var callbackfun=function(data){
			if (data.code == 0) {
				var $toast = $('#toast');
	            if ($toast.css('display') != 'none') return;
	            $toast.fadeIn(100);
	            setTimeout(function () {
	                $toast.fadeOut(100);location.href="mchannellist.htm?random="+Math.random();
	            }, 1000);
			}else{
				wxtip(data.msg);				
			}
		};
		commitData(url,obj,callbackfun);
}

</script>
</body>
</html>