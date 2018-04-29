<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的打卡</title>
<meta charset="utf-8">
<meta content="telephone=no, address=no" name="format-detection">
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<link rel="stylesheet" href="../mobile/css/framework7.material.min.css?a=3">
<link rel="stylesheet" href="../mobile/css/weui.min.css" />
<link rel="stylesheet" href="../mobile/css/main.css" />
<link rel="stylesheet" href="../mobile/css/list.css" />
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../mobile/js/framework7.js"></script>
<script type="text/javascript" src="../mobile/js/my-app.js"></script>
<style type="text/css">
.weui-loadmore {
    margin: 1em auto;
}
.list-block ul {
    position: inherit;
}
body, html {
    position: absolute;
}
</style>
</head>
<body>
<div class="list-block" style="margin-bottom: 0;">
  <div class="list">
  <ul id="list">
  		
  </ul>
  </div>
  
  </div>
  
  <div class="turnadd" onclick="location.href='mchanneladd.htm'">
    <img class="addimg" src="../mobile/image/add.png">
  </div>
  
  
	 <div class="page__bd" id="loading" style="display: none;">
        <div class="weui-loadmore">
            <i class="weui-loading"></i>
            <span class="weui-loadmore__tips">正在加载</span>
        </div>
	</div>
	
	<div class="weui-loadmore weui-loadmore_line" id="notdata" style="display: none;">
            <span class="weui-loadmore__tips">没有更多数据</span>
        </div>
	
    <!--BEGIN toast-->
    <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">打卡成功</p>
        </div>
    </div>
    
    <div id="toast2" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">取消成功</p>
        </div>
    </div>
    
    <!--end toast-->

    <!-- loading toast -->
    <div id="loadingToast" style="display:none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">数据加载中</p>
        </div>
    </div>
  
  
  
<script>
var page=1;
var url="wxchannellist.htm?random="+Math.random();
var listening=false;
function getData(){
	var obj={
		page:page,
	};
	$("#loading").show();
	$("#notdata").hide();
	var callbackfun=function(data){
		listening=true;
		if (data.code == 0) {
			if(data.data!=null&&data.data.length>0){
				wareAppend(data.data);
				if(data.data.length<10){
					listening=false;
				}
			}else{
				listening=false;
				page--;                                                               
				$("#notdata").show();
			}
		}
		$("#loading").hide();
	};
	commitData(url,obj,callbackfun);
	page++;
}
function wareAppend(data){
	for(i=0;i<data.length;i++){
		var item=data[i];
		var imgurl="../mobile/image/unsign.png";
		var imgurl2="../mobile/image/unpunch.png";
		if(item.signcount==1){
			imgurl="../mobile/image/issign.png";
			imgurl2="../mobile/image/ispunch.png";
		}
		var html="<li class=\"swipeout\"><div class=\"swipeout-content\"><div class=\"main unsigin\" id="+item.channel_id+" ><div class=\"inline\"><img id=\"img1_"+item.channel_id+"\" class=\"headimg\" src=\""+imgurl+"\"></div>"
			+"<div class=\"name inline\" onclick=\"location.href='mchannendetail.htm?channel_id="+item.channel_id+"'\">"+item.name+"</div>"
			+"<div class=\"punch inline\" onclick=\"sign_c("+item.channel_id+","+item.signcount+");\" id=\"sign_"+item.channel_id+"\" >"
			+"<img class=\"headimg\" src=\""+imgurl2+"\" id=\"img2_"+item.channel_id+"\"></div></div></div><div class=\"swipeout-actions-right\"><a href=\"javascript:wxconfirm('您是否退出该频道？','del("+item.channel_id+");')\">删除</a></div></li>";
		$("#list").append(html);
	}
}
$(function(){
	getData();
	$(window).scroll(function() {
		if (listening&&($(document).scrollTop() >= $(document).height() - $(window).height())) {
	    	getData();
	    }
	});
});
//签到 或 取消签到 
function sign_c(channel_id,signcount){
	var url="wxchannelsignadd.htm";	//签到url
	if(signcount>0){
		url="wxchannelsigndel.htm";	//取消签到url
	}
	var obj={
			channel_id:channel_id,
		};
		var callbackfun=function(data){
			if (data.code == 0) {
				if(signcount>0){
					//如果是签到 到这里就已经是修改成未签到
					var $toast = $('#toast2');
		            if ($toast.css('display') != 'none') return;
		            $toast.fadeIn(100);
		            setTimeout(function () {
		                $toast.fadeOut(100);
		            }, 1000);
		            $("#sign_"+channel_id).attr("onclick","sign_c("+channel_id+",0);");
		            $("#img1_"+channel_id).attr("src","../mobile/image/unsign.png");
		            $("#img2_"+channel_id).attr("src","../mobile/image/unpunch.png");
				}else{
					//如果是未签到 到这里就已经是修改成已签到
					var $toast = $('#toast');
		            if ($toast.css('display') != 'none') return;
		            $toast.fadeIn(100);
		            setTimeout(function () {
		                $toast.fadeOut(100);
		            }, 1000);
		            $("#sign_"+channel_id).attr("onclick","sign_c("+channel_id+",1);");
		            $("#img1_"+channel_id).attr("src","../mobile/image/issign.png");
		            $("#img2_"+channel_id).attr("src","../mobile/image/ispunch.png");
				}
			}else{
				wxtip(data.msg);				
			}
		};
		commitData(url,obj,callbackfun);
}
//退出
function del(channel_id){
	var url="wxchanneluserdel.htm";	//签到url
	var obj={
			channel_id:channel_id,
		};
		var callbackfun=function(data){
			if (data.code == 0) {
				location.href='mchannellist.htm?random='+Math.random();
			}else{
				wxtip(data.msg);				
			}
		};
		commitData(url,obj,callbackfun);
}
</script>
</body>
</html>