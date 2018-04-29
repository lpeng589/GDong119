<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>重庆消防</title>
<!-- <meta http-equiv="refresh" content="0;url=deal110.htm">  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/matrix-style.css" />
<link rel="stylesheet" href="../css/matrix-media.css" />
<link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel = "Shortcut Icon" href="../img/favicon.ico">
<script type="text/javascript" src="js/drag.js"></script>
<style type="text/css">
	.clearfix:after {
	  content: " ";
	  display: block;
	  clear: both;
	  height: 0;
	}
	.clearfix {
	  zoom: 1;
	}
.hide{
	display:none;
}
/*喇叭区域*/
#adviceList{
	background-color:#fff;
	word-wrap:break-word;
	min-width:300px;
	min-height:50px;
	position:fixed;
	bottom:70px;
	right:89px;
	border:1px solid #ccc;
	padding:15px;
	color:#0080FF;
	border-radius:25px;
	z-index:10;
}
#adviceList i{
	width:20px;
	height:20px;
	 position: absolute;
	 top:0;
	 right:0;
}
.iconmsg_img{
	position:fixed;
	bottom:50px;
	right:60px;
	width:50px;
	height:50px;
	border-radius:50%;
	z-index:99;
}
.iconmsg_text{
	position:fixed;
	bottom:80px;
	right:84px;
	z-index:99;
}

.circle {
	position:absolute;
	top:-25px;
	left:32px;
    background: none repeat scroll 0 0 red;
    border-radius: 50%;
    color: #FFFFFF;
    width:10px;
    height:10px;
    display:inline-block;
    margin-right:3px;
}
#breadcrumb{
	width:300px;
}
#content{
	min-height:300px;
}
</style>
</head>
<body id="body">
<!-- 喇叭 -->		
<div class="laba">
 	<img  id="linkid" alt=""   src="../images/icon/msg.jpg" class="iconmsg_img" onclick="jump('message.htm?operate=list&status=0','消息')"> 
 	<span id="msgid" class="iconmsg_text"> 
 	<i id="circle" class="circle hide"></i> 
	</span>
	<div id="adviceList"  style="display:none;">
		<a id="mass_link"  onclick="jump2()">
		<div  id="mass_title" >    </div>
		<div   id="mass_info" >    </div>
		</a>
		<i id="click"><img alt="" src="../images/guanbi.png"></i>
	</div>
</div>
<audio id="bgMusic">
    <source = src="../img/7.mp3" type="audio/mp3">
    <source = src="../img/7.mp3" type="audio/ogg">
</audio>
<!--Header-part-->
<div id="header">
  <h1><img src="../img/logo2.png" alt="logo" height="47" width="158"></h1>
  <!--sidebar-menu-->
  <div id="sidebar">
  	<ul>
  		<c:forEach items="${menu}" varStatus="i" var="row">
  			<li class="submenu"> 
  	    		<a href="javascript:;">
  	    			<i class="icon icon-th-list"></i> 
  	    			<span>${row.name}</span> 
  	    		</a>
  		      	<ul>
  		      		<c:forEach items="${row.submenu}" varStatus="j" var="subrow">
  						<li>
  							<a href="javascript:;" data-href="${subrow.menuurl}" onclick="openTab(event,'${row.name}','${subrow.name}')">
								${subrow.name}
  							</a>
  						</li>
  					</c:forEach>
  		      </ul>
  		    </li>
  		</c:forEach>
    	</ul>
  </div>
  <!--sidebar-menu-->
</div>
<!--close-Header-part--> 
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li class=""><a title="" href="#"><i class="icon icon-user"></i>  <span class="text">${employee.username}</span></a></li>
    <li class=""><a title="" href="#" onclick="confirm(logout,'你确定要注销吗？')"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
  	<li class=""><a title="" href="#"  onclick="openTab(event,'工作台','我的工作')" data-href="work.htm?operate=list"><i class="icon icon-share-alt"></i> <span class="text">我的工作</span></a></li>
  </ul>

</div>
<!--close-top-Header-menu-->

<!--main-container-part-->
<div id="content">
<!-- start breadcrumbs -->
        
        <div id="content-header">
                <div id="breadcrumb"> <a href="#"><i class="icon-home"></i> 我的工作</a></div>
        		<div id="navtabs">
        			<div class="last"><a onclick="closeTabs()" href="javascript:;" >关闭所有</a></div>
        		</div>
        </div>
<!-- end breadcrumbs -->

<!--Start-body-->
<div class="framesBox">
	<div class="frameList show">
	 	<iframe  frameborder="0" name="mainframe_0" id="mainframe_0" src="../work.htm?operate=list" allowtransparency="true" style="width: 100%; "></iframe>
	 </div>
 </div>
<!--End-body-->
</div>


<!--end-main-container-part-->

<!--Footer-part-->

<div class="row-fluid">
<!--   <div id="footer" class="span12"> 2016 &copy; Pnkoo Admin. Brought to you by <a href="http://pnkoo.com/">Pnkoo.com</a> </div> -->
</div>

<!--end-Footer-part-->

<script src="../js/jquery.min.js"></script> 
<script src="../js/bootstrap.min.js"></script> 
<script src="../js/matrix.js"></script> 
<script src="../js/common.js"></script> 
<script type="text/javascript">
var audio = document.getElementById("bgMusic");
$("#content").height($("#body").height()-95);
$("#mainframe_0").css("height",($("#content").height()-45)+"px");
$(document).ready(function() {
	msgcount();
});
//退出登录
function logout(){
	location.href="logout.htm";
}
function welcome(){
	location.href="/work.htm";
}

var fadetime ;
function showmessage(title,info,link){
	$("#mass_title").html(title);
	$("#mass_info").html(info);
if(link==''||link==null){
	$("#mass_link").attr("link","message.htm?operate=list&status=0");
}else{
	$("#mass_link").attr("link",link);
}
	$("#adviceList").show();
	fadetime = self.setInterval("fademessage()",6000000);//隐藏
}

function fademessage(){
	$("#adviceList").hide();
	clearInterval(fadetime);
}

//其它iframe页面跳转到登录页面调用
function goLoginPage(){
	location.href="login.htm";
}
function goMainPage(){
	location.href="main.htm";
}
function headChange(name,subName){
	var html="<a href=\"#\">"
    		+"<i class=\"icon-home\"></i>"+name+"</a>"
    		+"<a href=\"#\" class=\"current\">"+subName+"</a>";
	$("#breadcrumb").html(html);
}
function jump(url,crumb){
	$("#mainframe_0").attr("src",url).parent().addClass('show').siblings().removeClass("show");
	$("#navtabs a").removeClass('active');
	$("#breadcrumb").html('<a href="#"><i class="icon-home"></i>'+crumb+'</a>');
}

function jump2(){
	var url=$("#mass_link").attr("link");
	$("#mainframe_0").attr("src",url).parent().addClass('show').siblings().removeClass("show");
	$("#navtabs a").removeClass('active');
	$("#breadcrumb").html('<a href="#"><i class="icon-home"></i>'+'消息'+'</a>');
}

//消息轮询
var k=0;
// var time=6000;
var nowid=0;//消息的id
var countInterval=self.setInterval("msgcount()",300000);
function msgcount(){
		obj = {
			operate : "getcount",
// 			time : time/1000,
		};
	var url = "message.htm?";
	var callbackfun = function(data) {
// 		if (data.code == 0){
			 if(data.data.length>0){//新消息提示
// 				for(var i=0;i<data.data.length;i++){
					var term =  data.data[0];
					if( nowid<term.id){//第一条ID未变大，说明没有新消息提醒
						showmessage(term.title,term.content,term.link);
						audio.play();
					}
					nowid = term.id;
// 				}
			 }	
			 
			 if(data.code>0){
				 $("#circle").show();
					if(k==0){
						k=1;
						showImg();	
					}
			 }else{
					k=9;
					$("#circle").hide();
			}
//  		}else{
// 			tip(data.msg);
// 		}
	};
	commitData(url, obj, callbackfun);   
}
function showImg() 
{ 
	if(k==9){
		msgid.style.visibility = "visible";
		return;
	} 
    if(msgid.style.visibility == "visible")    //如果可见，则隐藏
    	msgid.style.visibility = "hidden"; 
    else 
    	msgid.style.visibility = "visible";    //设置图像可见
    setTimeout('showImg()',300);               //间隔的毫秒
} 
/*导航选项卡*/
var tabList=[];
function delTabs(event){
	//删除一个标签导航
	event.preventDefault();
	var $this = $(event.target);
	var text = $.trim($this.prev().text());
	tabList.splice($.inArray(text,tabList),1);
	if(tabList.length>0 && $this.prev().hasClass('active')){
		$this.parent().remove();
		$("#navtabs a").eq(tabList.length).trigger("click");
	}else{
		$this.parent().remove();
	}
	var index = $this.parent().index();
	$("#mainframe_"+index).parent().remove();
	closeTabCtrl();
}
function closeTabs(){
	//删除所有标签导航
	$("#navtabs .last").hide().nextAll().remove(); 
	for (var i = tabList.length ; i >= 1; i--) {
		$(".frameList").eq(i).remove();
	};
	tabList=[];
	jump('work.htm?operate=list','我的工作');
}
function openTab(event,name,subName){
	//添加标签导航及新iframe
	var $this = $(event.target);
	if(name=="工作台"){
		if($this[0].localName=="span"||$this[0].localName=="i"){
			$this = $(event.target).parent();
		}
	}
	if(tabList.indexOf(subName)<0){
		tabList.push(subName);
		var index = tabList.length;
		$(".framesBox").append('<div class="frameList">'+
		'<iframe id="mainframe_'+index+'" frameborder="0" style="width: 100%; height: '+($("#content").height()-45)+'px"+";" allowtransparency="true" src="" name="mainframe_'+index+'">'+
		'</div>');
		if(name=="工作台"){
			var k = $this.clone();
				k[0].innerHTML="<span class=\"text\">我的工作</span>";
				k.appendTo($("#navtabs")).wrap("<div></div>").addClass('active').parent().append('<i class="icon icon-remove" onclick="delTabs(event)"></i>').siblings().find('a').removeClass('active');
		}else{
			$this.clone().appendTo($("#navtabs")).wrap("<div></div>").addClass('active').parent().append('<i class="icon icon-remove" onclick="delTabs(event)"></i>').siblings().find('a').removeClass('active');			
		}
		var url = $this.attr('data-href');
		$("#mainframe_"+(index)).attr("src",url).parent().addClass("show").siblings().removeClass("show");
	}else{
		//标签切换
		var index = parseInt(tabList.indexOf(subName))+1;
		$("#mainframe_"+index).parent().addClass("show").siblings().removeClass("show");
		$("#navtabs a").eq(index).addClass('active').parent().siblings().find('a').removeClass('active');
		//若点下拉导航则强制刷新iframe
		if($this.parents("#sidebar").length==1){
// 			$("#mainframe_"+index).attr('src', $("#mainframe_"+index).attr('src'));
			$("#mainframe_"+index).attr('src', $this.attr('data-href'));
		}
	}
	if(name=="工作台"){
		$("#mainframe_"+index).attr('src', $this.attr('data-href'));
	}
	closeTabCtrl();
	headChange(name,subName);
}
function closeTabCtrl(){
	//两个标签以上显示 关闭所有
	if(tabList.length>2){
	  $("#navtabs .last").show();
	}else{
	  $("#navtabs .last").hide();
	}
}

window.onload=function(){

var oAdviceList=document.getElementById('adviceList');
	document.getElementById('click').onclick=function(){
		//alert('1');
		oAdviceList.style.display="none";
	}
}
</script>
</body>
</html>
