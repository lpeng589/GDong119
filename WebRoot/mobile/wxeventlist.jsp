<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="description" content="重庆消防" />
	<meta name="keywords" content="重庆消防" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta content="telephone=no" name="format-detection" />
	<title>案件列表</title>
	<link href="../mobile/css/style1.css" rel="stylesheet"l type="text/css" />
	<link href="../mobile/css/font_441279_8so1unjfh41jor.css" rel="stylesheet"l type="text/css" />
	<script src="../mobile/js/js.js" type="text/javascript"></script>
</head>
<body>
    <div class="head-nav">
        <ul class="head-ul">
            <li >待反馈</li>
            <li>已处理</li>
            <li>已结案</li>
            <li class="active">全部</li>
        </ul>
    </div>
    <div class="content">
        <ul class="content-ul">
            
        </ul>
    </div>
</body>
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="/mobile/js/layer.js"></script>
<script type="text/javascript" src="/mobile/js/common.js"></script> 
<script type="text/javascript">
var page=1;
var url="qywxeventlistget.do";
var listening=false;
function getData(){
	var obj={
		page:page,
	};
	var callbackfun=function(data){
		if(data.code==0){
			if(data.data!=null){
				page++;
				orderAppend(data.data);
				listening=true;
			}else{
				listening=false;
				console.log(page);
				if(page==1){
					$(".emptynews").show();
				}
			}
		}
	};
	commitData(url,obj,callbackfun);
}
function orderAppend(data){
		for(i=0;i<data.length;i++){
	 		var item=data[i];
	 		var list ="";
	 		var status;
	 		var type;
	 		var pic="";
			if(item.logstatus==2){
				list = "<li class=\"type0\">";
			}else if(item.logstatus==3){
				list = "<li class=\"type1\">";
			}else if(item.logstatus==6){
				list = "<li class=\"type2\">";
			}else{
				list = "<li class=\"type3\">";
			}
			if(item.logstatus==0){
				status = "未受理";
			}else if(item.logstatus==1){
				status = "待分拨";
			}else if(item.logstatus==2){
				status = "已分拨待反馈";
			}else if(item.logstatus==3){
				status = "已反馈待处理";
			}else if(item.logstatus==4){
				status = "已转发";
			}else if(item.logstatus==5){
				status = "已反馈";
			}else if(item.logstatus==6){
				status = "已结案";
			}else if(item.logstatus==7){
				status = "未整改";
			}else if(item.logstatus==8){
				status = "无效案件";
			}else if(item.logstatus==9){
				status = "未操作返回";
			}
			if(item.type==1){
	 			type = "无消防手续";
	 		}else if(item.type==2){
	 			type = "安全出口数量不足";
			}else if(item.type==3){
				type = "安全出口、疏散通道被占用、堵塞";
			}else if(item.type==4){
				type = "消防车通道被占用、堵塞";
			}else if(item.type==5){
				type = "消防设施器材损坏";
			}else if(item.type==6){
				type = "外墙门窗上设置影响逃生、灭火救援的障碍物";
			}else if(item.type==7){
				type = "消防产品不合格";
			}else if(item.type==8){
				type = "燃气、电气线路数设不安全";
			}else if(item.type==9){
				type = "违章动火";
			}else if(item.type==10){
				type = "违规存储易燃易爆危险品";
			}else if(item.type==11){
				type = "其它消防违法行为和火灾隐患";
			}
			if(item.pic1!=undefined||item.pic2!=undefined||item.pic3!=undefined){
				pic = "<ul class=\"img\">";
				if(item.pic1 != undefined){
					pic+"<li><img src=\""+item.pic1+"\" /></li>";
				}
				if(item.pic2 != undefined){
					pic+"<li><img src=\""+item.pic2+"\" /></li>";
				}
				if(item.pic3 != undefined){
					pic+"<li><img src=\""+item.pic3+"\" /></li>";
				}
				pic+"</ul>"; 
			}
			var str=item.content;//原始字符串
			var s = str;//要展示的字符串
			if(str.length>35){
			  s=str.substring(0,35)+"......";
			}
			var flag=false;
			if(item.flag==true){
				flag=true;
			}
			list2 = list+"<a href=\"wxeventdetailpage.do?id="+item.id+"&flag="+flag+"\">"
	        +"<h3>事件类型-"+type+"</h3>"
	        +"<div class=\"detail\">"
	        +"<div class=\"time\"><i class=\"icon iconfont icon-time\"></i>"+item.insertTime+"</div>"
	        +"<div class=\"location\"><i class=\"icon iconfont icon-map\"></i>"+item.address+"</div></div>"
	        +pic
	        +"<p class=\"content2\">"+s+"</p>"
	        +"<div class=\"type\">"+status+"</div>"
	        +"</a></li>";
			$(".content-ul").append(list2);
	 	}
}
$(function(){
 	getData();
 	$(".content").scroll(function() {
 		if (listening&&($(document).scrollTop() >= $(document).height() - $(window).height())) {
 	    	getData();
 	    }
 	});
});

</script>

</html>