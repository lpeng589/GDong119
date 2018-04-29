<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/pnkootag"  prefix="pk"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>小程序报警</title>
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/deal110.css">
<style type="text/css">
*{padding: 0;margin:0;}
body{background: #fff;}
body,html{
	width:100%;
	height:100%;
}
</style>

</head>
<body>
<!--结案弹出窗  -->
<div class="masktier">
	<div class="contenttier">
		<div class="">
		<label class="masklabe">类型：</label>
			<select class="maskselect" id="type">
			    <option value="0" selected>刑事</option>
			    <option value="1">治安</option>
			    <option value="2">交通</option>
			    <option value="3">求助</option>
			    <option value="4">骚扰</option>
			    <option value="5">其他</option>
			    <option value="6">特殊警情</option>
			    <option value="7">重复报警</option>
			    <option value="8">举报线索</option>
			    <option value="9">工作咨询</option>
			    <option value="10">出警反馈</option>
			</select>
		</div>
		<div class="maskinputbg">
			<label class="masklabe top">备注：</label>
			<textarea id="remark" class="maskinput" type="text" placeholder="请在这里输入你要备注的内容"></textarea>
		</div>
		<div class="masktbn">
			<span class="maskcancel" onclick="complete()">取消</span>
			<span class="maskcomfirm" onclick="replycomfirm()">确定</span>
		</div>
	</div>
</div>
    <div class="container">
        <div class="listleft inline">
                <div class="statusmenu clearfix">
                    <div class="liststatus active" onclick="changlist(this,0)" id="status0">待处理</div>
                    <div class="liststatus" onclick="changlist(this,1)" id="status1">处理中</div>
                    <div class="liststatus" onclick="changlist(this,2)" id="status2">已结案</div>
                </div>
                <div class="userlist" id="userlist"></div>
        </div>
        <div class="listright inline">
            <div class="connectionhead clearfix">
                <div class="connectionuser activeing">
                <a href="main.htm"  target="_blank">
                    <img class="userimg" src="images/a.png" alt="" id="userimg"> </a>
                    <span class="username connectionactive" id="username"></span>
                </div>
                <span class="jieanbtn fr" onclick="complete()" id="endan" style="display:none">结案</span>
            </div>
            <div class="connectionbody" id="contentbody">
            </div>
       		<div class="replyinpdiv">
            	<input class="replyinp" type="text" placeholder="请在这里输入你要回复的内容" id="replyinp"/><span class="replybtn" onclick="dealreply()">回复</span>
            </div>
        </div>
    </div>
    <audio src="../img/system.wav" id="noticeMp3"></audio>
    <audio src="../img/4.mp3" id="detailMp3"></audio>
    <script src="../js/jquery.js"></script>
    <script src="../js/common.js"></script>
	<script src="../js/deal110.js"></script>
	<script type="text/javascript">
	var height1=$(document).height();
	$("#userlist").height(height1-65);
	$("#contentbody").height(height1-130);
	$(".listright").height(height1);
	</script>
    <script type="text/javascript">
    $('#replyinp').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			dealreply();
		}
	});
    var alarmidNow=null;
    var detailidNow=0;
    var status=0;
    var page=1;
    var intervalDetail=null;
    var noticeStatus=0;
    var detailStatus=0;
    function changlist(event,s){
    	$("#userlist").empty();
    	page=1;
    	status = s;
    	$(event).addClass("active");
    	$(event).siblings().removeClass("active");
    	list110(status,page);
    }
    function turndetail(id,s,userimg,username){
    	if(s==0){
    		dealstatus1(id);
    		$("#endan").show();
    	}else if(s==1){
    		$("#endan").show();
    	}else{
    		$("#endan").hide();
    	}
    	$("#userimg").attr("src",userimg);
    	$("#username").text(username);
    	alarmidNow=id;
    	detailidNow=0;
    	if(intervalDetail!=null){
    		clearInterval(intervalDetail);
    	}
    	$("#contentbody").empty();
    	intervalDetail = setInterval(dealdetail,1000);
    }
    function complete(){
    	$(".masktier").toggle();
    }
    function replycomfirm(){
    	var type = $("#type").val();
    	var remark = $("#remark").val();
    	dealstatus2(alarmidNow,type,remark);
    }
    $(function(){
    	list110(status,page);
    	setInterval(dealnot,300000);
    	setInterval(shan,500);
    });
    
    $("#userlist").scroll(function() {
		if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
			list110(status,page);
	    }
	})
    /**
     * 警情列表
     * @param status
     * @param page
     */
    var f = false;
    function list110(status,page2){
    	if(f){
    		return;
    	}else{
    		f = true;
    	}
    	var url = "deallist.htm?";
    	var obj = {
    		status : status,
    		page : page2,
    	};
    	var callbackfun = function(data) {
    		console.log("数据长度"+data.data.length);
    		if (data.code == 0) {
    			//添加警情到列表
    			if(data.data.length>0){
    				page++;
        			userlistAppend(data.data);
        		}
    		}else {
    			alert(data.msg);
    		}
    		f = false;
    	};
    	commitData(url, obj, callbackfun);
    }
    </script>
</body>
</html>