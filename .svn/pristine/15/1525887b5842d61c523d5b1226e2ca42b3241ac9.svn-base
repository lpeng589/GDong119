function userlistAppend(data){
	for(i=0;i<data.length;i++){
		var item=data[i];
		item.nickname=item.nickname.replace("'","\\'");
		var user = "<div class=\"user\" id=\"user_"+item.id+"\" onclick=\"turndetail("+item.id+","+item.status+",'"+item.headimgurl+"','"+item.nickname+"')\">"
        +"<div class=\"userimgdiv\"><img class=\"userimg\" src=\""+item.headimgurl+"\" alt=\"\"></div>"
        +"<span class=\"username\">"+item.nickname+"</span><span class=\"username lasttime\">"+lastTime(item.lastTime)+"</span>"
		+"<span class=\"detai_count\"></span></div>";
        $("#userlist").append(user);
	}
}
/**
 * 获取未处理警情数量
 */
function dealnot(){
	var url = "dealnot.htm?";
	var obj = {
	
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			if(parseInt(data.msg)>0){
				$("#status0").html("<span style=\"color:red;font-weight:bold\">待处理 "+data.msg+"</span>");
				if((noticeStatus%2)==0){
					document.getElementById("noticeMp3").play();
				}
				noticeStatus++;
			}else{
				$("#status0").html("待处理");
				noticeStatus=0;
			}
			var count=0;
			for(var i=0;i<data.data.length;i++){
				var item = data.data[i];
				if(item.status==1){
					count++;
				}
				$("#user_"+item.id).find(".detai_count").html("&nbsp;"+item.count);
				$("#user_"+item.id).find(".lasttime").html(lastTime(item.lastTime));
				shanAdd("user_"+item.id);
			}
			if(count>0){
				audioFlag=true;
				$("#status1").html("<span style=\"color:red;font-weight:bold\">处理中 "+count+"</span>");
			}else{
				$("#status1").html("处理中");
			}
			if(count>0){
				if((detailStatus%2)==0){
					document.getElementById("detailMp3").play();
				}
				detailStatus++;
			}else{
				detailStatus=0;
			}
		}else {
			alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}
/**
 * 最新警情详情列表(暂时全部)
 * @param alarmid 警情id
 * @param id 最后一个列表的id（第一次调用填0，后面刷新填最后一个id）
 */
function dealdetail(){
	var alarmid=alarmidNow;
	var id=detailidNow;
	var url = "dealdetail.htm?";
	var obj = {
		alarmid : alarmid,
		id : id,
		sendtype:1
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			//添加警情详情
			$("#user_"+alarmid).find(".detai_count").html("");
			shanDel("user_"+alarmid);
			detailListAppend(data.data);
		}else {
			alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}
function detailListAppend(data,first){
	var notice=false;
	if(detailidNow!=0){
		notice=true;
	}
	for(i=0;i<data.length;i++){
		var item=data[i];
		var sendtype="";
		if(item.sendtype==1){
			sendtype="fr reply";
		}else{
			if(notice){
				document.getElementById("detailMp3").play();
			}
		}
		var musicdownload="";
		if(item.type==1){
			musicdownload="onclick=\"window.open('"+item.content+"')\"";
		}
		var html = "<div class=\"message\" "+musicdownload+">"
				 + "<div class=\"massagetime center\"><span>"+item.insertTime+"</span></div>"
		         + "<p class=\""+sendtype+"\">";
		if(item.type==0){
    	   //文字
			html+=item.content;
       }else if(item.type==1){
    	   //语音
    	 html+= " <audio controls=\"controls\">"
              + "<source src=\""+item.content+"\" type=\"audio/silk\">"
              + "</audio>";
       }else if(item.type==2){
    	   //视频
    	   html+="<video src=\""+item.content+"\" controls=\"controls\"></video>";
       }else if(item.type==3){
    	   //图片
    	   html+="<img class=\"connecttionimg\" src=\""+item.content+"\" alt=\"\">";
       }else if(item.type==4){
    	   //位置
    	   html+="最新位置:"+item.content;
       }
		html+="</p></div>";
		if(sendtype!=""){
			html+="<div style=\"clear:both\"></div>";
		}
		$("#contentbody").append(html);
        detailidNow=item.id;
	}
	var div = document.getElementById('contentbody');
	if(data.length>0){
		div.scrollTop = div.scrollHeight;
	}
}
/**
 * 处理状态更改为受理中
 * @param id 警情id
 */
function dealstatus1(id){
	var url = "dealstatus1.htm?";
	var obj = {
		id : id,
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			$("#status1").click();
		}
	};
	commitData(url, obj, callbackfun);
}
/**
 * 处理状态更改为结案
 * @param id 警情id
 * @param type 0刑事 1治安 2交通 3求助 4骚扰 5其他 6特殊警情 7重复报警 8举报线索 9工作咨询 10出警反馈
 * @param remark  备注
 */
function dealstatus2(id,type,remark){
	var url = "dealstatus2.htm?";
	var obj = {
		id : id,
		type : type,
		remark:remark
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			$("#endan").hide();
			alert("结案成功");
			$("#status2").click();
			complete();
		}else {
			alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}
/**
 * 添加处理回复（暂时只支持文字）
 * alarmid 警情id
 * content 文字内容
 */
function dealreply(alarmid,content){
	var alarmid = alarmidNow;
	if(alarmid==null){
		return;
	}
	var content = $("#replyinp").val();
	if(content==null||content==""){
		alert("请输入回复内容");
		return;
	}
	var url = "dealreply.htm?";
	var obj = {
		alarmid : alarmid,
		content : content,
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			//回复成功 
			$("#replyinp").val("");
		}else {
			alert(data.msg);
		}
	};
	commitData(url, obj, callbackfun);
}
function lastTime(time){
	return (time+"").substring(2);
}
var shans= new Array();
function shanAdd(id){
	var flag=true;
	for(var i=0;i<shans.length;i++){
		if(shans[i]==id){
			flag=false;
			break;
		}
	}
	if(flag){
		shans[shans.length]=id;
	}
}
function shanDel(id){
	var shansNew= new Array();
	var j=0;
	for(var i=0;i<shans.length;i++){
		if(shans[i]!=id){
			shansNew[++j]=shans[i];
		}else{
			$("#"+shans[i]).find(".userimg").show();
		}
	}
	shans=shansNew;
}
function shan(){
	for(var i=0;i<shans.length;i++){
		$("#"+shans[i]).find(".userimg").toggle();
	}
}