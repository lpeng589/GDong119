<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>事件详情</title>
<link rel="stylesheet" href="../mobile/css/ionicons.min.css" />
<link rel="stylesheet" href="../mobile/css/layer.css" />
<link rel="stylesheet" href="../mobile/css/eventdetail.css" />
<link href="../mobile/css/style1.css" rel="stylesheet" type="text/css" />
<link href="../mobile/css/font_441279_8so1unjfh41jor.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../mobile/css/weui.min.css?a=11">
<style type="text/css">
.active{
	background: #ee2e2e;
	color: #fff;
}
.author-local2{
	height:250px;
	width:100%;
}
.hide{display: none;}
a:link {color: #0084FF} /* 未访问的链接 */
a:visited {color: #0084FF} /* 已访问的链接 */
a:hover {color: #0084FF} /* 当有鼠标悬停在链接上 */
a:active {color: #0084FF} /* 被选择的链接 */
</style>
</head>
<body>
<div class="hide mask" id="mask" onclick="$('#mask').toggle()">
	<div class="popout"><img src="" style="width:100%;"></div>
</div> 
    <div class="content-box">
        <div class="head-title">
        <span>
					<c:if test="${bean.type==1}">无消防手续</c:if>
				  <c:if test="${bean.type==2}">安全出口数量不足</c:if>
				  <c:if test="${bean.type==3}">安全出口、疏散通道被占用、堵塞</c:if>
				  <c:if test="${bean.type==4}">消防车通道被占用、堵塞</c:if>
				  <c:if test="${bean.type==5}">消防设施器材损坏</c:if>
				  <c:if test="${bean.type==6}">外墙门窗上设置影响逃生、灭火救援的障碍物</c:if>
				  <c:if test="${bean.type==7}">消防产品不合格</c:if>
				  <c:if test="${bean.type==8}">燃气、电气线路数设不安全</c:if>
				  <c:if test="${bean.type==9}">违章动火</c:if>
				  <c:if test="${bean.type==10}">违规存储易燃易爆危险品</c:if>
				  <c:if test="${bean.type==11}">其它消防违法行为和火灾隐患</c:if>
		</span>
        <span>
        	<c:if test="${bean.logstatus==0}">未受理</c:if>
		    <c:if test="${bean.logstatus==1}">待分拨</c:if>
		    <c:if test="${bean.logstatus==2}">已分拨待反馈</c:if>
		    <c:if test="${bean.logstatus==3}">已反馈待处理</c:if>
		    <c:if test="${bean.logstatus==4}">已转发</c:if>
		    <c:if test="${bean.logstatus==5}">已反馈</c:if>
		    <c:if test="${bean.logstatus==6}">已结案</c:if>
		    <c:if test="${bean.logstatus==7}">未整改</c:if>
		    <c:if test="${bean.logstatus==8}">无效案件</c:if>
		    <c:if test="${bean.logstatus==9}">未操作返回</c:if>
        </span>
        </div>
        <c:if test="${bean.jubao != null && bean.jubao != '' }">
        	<div class="author"><i class="icon iconfont icon-arrowright"></i>举报对象：${bean.jubao }</div>
        </c:if>
        <div class="author-detail">
            <div class="author-one"><i class="icon iconfont icon-account"></i>${bean.name }</div>
            <div class="author-phone"><i class="icon iconfont icon-phone"></i>${bean.phone }</div>
        </div>
        <div class="author-time"><i class="icon iconfont icon-time"></i>${bean.inserttime2 }</div>
        <div class="author-local" onclick="mapshow()"><i class="icon iconfont icon-map"></i><a href="javascript:void(0)">${bean.address }</a></div>
        <div class="author-local2 hide">
        	<div style="float:right;"><img alt="" src="/mobile/image/guanbi.png" style="height:30px;width:30px;" onclick="maphide()"></div>
        	<iframe id="mapPage" width="100%" height="100%" frameborder=0 src="https://3gimg.qq.com/lightmap/v1/marker/index.html?type=0&marker=coord%3A${bean.latitude}%2C${bean.longitude}%3Btitle%3A${bean.address}%3Baddr%3A${bean.address}&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp">
        	
			</iframe>
        </div>
        <div class="author-img">
            <ul>
            <c:if test="${bean.pic1!=null&&bean.pic1!='' }">
                <li onclick="bigimg(this)"><img src="${bean.pic1 }" /></li>
            </c:if>
            <c:if test="${bean.pic2!=null&&bean.pic2!='' }">
                <li onclick="bigimg(this)"><img src="${bean.pic2 }" /></li>
            </c:if>
			<c:if test="${bean.pic2!=null&&bean.pic2!='' }">
                <li onclick="bigimg(this)"><img src="${bean.pic3 }" /></li>
            </c:if>
            </ul>
            <c:if test="${bean.video!=null&&bean.video!='' }">
				<div class="evideo" >
					<video src="${bean.video }"  controls="controls" >
						您的浏览器不支持 video标签。
					</video>
				</div>
			</c:if>
			<c:if test="${bean.voice!=null&&bean.voice!='' }">
				<audio src="${bean.voice }" controls="controls">
					您的浏览器不支持 audio 标签。
				</audio>
			</c:if>
        </div>
        <div class="author-text">${bean.content }</div>
    </div>
    <c:if test="${bean.flag }">
    <c:if test="${bean.logstatus==1}">
	    <div class="modify-type">
	        <span>请选择村社</span>
	        <select id="departmentid">
	        
	         <c:forEach items="${departmentlist}" varStatus="i" var="row">
	         	<option value="${row.id}">${row.name}</option>
	         
	          </c:forEach>
	        </select>
	    </div>
	        
	    <div class="input-area">
	    	<div class="add_btn">
				<div class="weui-uploader__hd">
                   <p class="weui-uploader__title">图片上传</p>
                </div>
                <div class="weui-uploader__bd">
                
                    <div class="weui-uploader__input-box" onclick="uploadimg('pic1');" id="pic1"></div>
                    <li class="weui-uploader__file" style="list-style-type:none;display: none;" id="pic1_li"  onclick="uploadimg('pic1');">
                    <img src="" width="77" height="77">
                    </li>
                    
                    <div class="weui-uploader__input-box" onclick="uploadimg('pic2');" id="pic2"></div>
                    <li class="weui-uploader__file" style="list-style-type:none;display: none;" id="pic2_li"  onclick="uploadimg('pic2');">
                    <img src="" width="77" height="77">
                    </li>
                    
                    <div class="weui-uploader__input-box" onclick="uploadimg('pic3');" id="pic3"></div>
                    <li class="weui-uploader__file" style="list-style-type:none;display: none;" id="pic3_li"  onclick="uploadimg('pic3');">
                    <img src="" width="77" height="77">
                    </li>
                    
                </div>
			</div>
	        <textarea cols="10" rows="3" placeholder="请输入内容" id="replypre" onkeydown="change(this)"></textarea>
	        <div class="btn" onclick="add()" style="color:#a91c1c">提交</div>
	    </div>
   	</c:if>
    </c:if>
    <div class="modify-type">
        <span>处理记录</span>
    </div>
    <div class="modify-list">
        <ul id="list">

        </ul>
    </div>

</body>
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="/mobile/js/layer.js"></script>
<script type="text/javascript" src="/mobile/js/common.js"></script> 

  <script src="../mobile/js/weui.min.js?a=111"></script>
  <script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js?a=111"></script>
  <script src="../mobile/js/qywxjssdk.js?a=1111"></script>
  
  
<script type="text/javascript">
var page=1;
var url="wxeventdetailhandle.do?id=${bean.id}";
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
 		var deal_fail="";
 		if(item.deal_file!=null&&item.deal_file!=undefined&&item.deal_file!=""){
 			var arr = item.deal_file.split(",");
 			for(var j=0;j<arr.length;j++){
 				if(arr[j]!=""&&arr[j]!=undefined){
	 				deal_fail=deal_fail+"<div style=\"display:inline-block;margin:4px\" onclick=\"bigimg(this)\"><img src=\"${imageRootPath}"+arr[j]+"\" width=\"50\" heigth=\"50\"></div>";
 				}
 			}
 		}
 		var list ="";
 		var status="";
 		var deal_opinion="";
 		if(item.status==0){
 			status = "待系统分拨";
 		}else if(item.status==1){
 			status = "待手动分拨";
 		}else if(item.status==2){
			status = "已分拨待反馈";
		}else if(item.status==3){
			status = "已反馈待处理";
		}else if(item.status==4){
			status = "已转发";
		}else if(item.status==5){
			status = "已反馈";
		}else if(item.status==6){
			status = "已结案";
		}else if(item.status==7){
			status = "未整改";
		}else if(item.status==8){
			status = "无效案件";
		}else{
			status = "未操作返回";
		}
		if(item.deal_opinion!=null){
			deal_opinion = "<p>"+item.deal_opinion+"</p>";
		}
		list = "<li><div class=\"modify-time\">"+item.createtime2+"<div class=\"circle-one\"><div class=\"circle-two\"></div></div></div>"
        		+"<div class=\"modify-content\">"
            	+"<h4>"+status+"</h4>"
            	+"<div class=\"modify-detail\">"
                +"<div class=\"modify-author\">"+item.dealname+"</div>"
                +"<div class=\"modify-time-one\">"+item.createtime.substring(item.createtime.length-8, item.createtime.length)+"</div></div>"
                +deal_opinion
                +deal_fail
            	+"</div></li>";
		$("#list").append(list);
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
function add(){
	var deal_file = new Array();
// 	if($("#replypre").val()==null||$("#replypre").val()==""||$("#replypre").val()==undefined){
// 		tip("请输入处理内容");
// 		return;
// 	}
	if($("#departmentid").val()==0){
		tip("请选择村社");
		return;
	}
	if($("#pic1").attr("value")!=null||$("#pic1").attr("value")!=""||$("#pic1").attr("value")!=undefined){
		deal_file = $("#pic1").attr("value")+",";
		if($("#pic2").attr("value")!=null||$("#pic2").attr("value")!=""||$("#pic2").attr("value")!=undefined){
			deal_file = deal_file + $("#pic2").attr("value")+",";
			if($("#pic3").attr("value")!=null||$("#pic3").attr("value")!=""||$("#pic3").attr("value")!=undefined){
				deal_file = deal_file + $("#pic3").attr("value")+",";
			}
		}
	}
// 	console.info(deal_file);
	var url="wxeventreplyfenbo.do";	//签到url
	var obj={
			eventid : ${bean.id},
			eventlogid : ${bean.eventlogid},
			deal_opinion :  $("#replypre").val(),
			departmentid : $("#departmentid").val(),
			status : ${bean.logstatus},
			deal_file : deal_file,
		};
		var callbackfun=function(data){
			if (data.code==0) {
				tip(data.msg);
				location.reload();
			}else{
				tip(data.msg);
				location.reload();
			}
		};
		commitData(url,obj,callbackfun);
}
function change(event){
// 	if($(event).val()!=""&&$(event).val().length>1){
// 		$(event).next().addClass("active");
// 	}else{
// 		$(event).next().removeClass("active");
// 	}
}
//上传图片
function uploadimg(value){
	wx.chooseImage({
	    count: 1, // 默认9
	    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) {
// 	    	alert(res.localIds[0]);
// 	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        wx.uploadImage({
	            localId: res.localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
	            isShowProgressTips: 1, // 默认为1，显示进度提示
	            success: function (res2) {
// 	            	alert(JSON.stringify(res2));
	            	$("#"+value+"_li").children().attr("src",res.localIds);
	            	$("#"+value).attr("value",res2.serverId);
	            	$("#"+value).hide();
	            	$("#"+value+"_li").show();
// 	                var serverId = res2.serverId; // 返回图片的服务器端ID
	            }
	        });
	    }
	});
}
function bigimg(event){
	var src=$(event).children().attr("src");
	$("#mask img").attr("src",src);
	$("#mask").toggle();
}
function mapshow(){
	$(".author-local2").show();
}
function maphide(){
	$(".author-local2").hide();
}
</script>
</html>