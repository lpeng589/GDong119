<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<title></title>
</head>
<body> 
<script src="../mobile/js/wxcommon.js"></script>
<script src="../js/jquery.min.js"></script>
<script src="../mobile/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../mobile/js/jweixin-1.0.0.js"></script>
<!-- <script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script> -->
<script type="text/javascript">

$(function() {
	  var appid = '';
	  var timestamp = '';
	  var noncestr = '';
	  var signature = '';
	  $.ajax({
		  type: "POST",
		  url: "wxjs.html",
		  dataType: "json",
		  data:{url:window.location.href},
		  async: false,
		  success:function(data) {
			appid = data.appid;
			timestamp = data.timestamp;
			noncestr = data.noncestr;
			signature = data.signature;
		  }
		});
	  wx.config({
			appId: appid,
			timestamp: timestamp,
			nonceStr: noncestr,
			signature: signature,
			jsApiList: [
			  'checkJsApi',
			  'onMenuShareTimeline',
			  'onMenuShareAppMessage',
			  'onMenuShareQQ',
			  'onMenuShareWeibo',
			  'hideMenuItems',
			  'showMenuItems',
			  'hideAllNonBaseMenuItem',
			  'showAllNonBaseMenuItem',
			  'translateVoice',
			  'startRecord',
			  'stopRecord',
			  'playVoice',
			  'pauseVoice',
			  'stopVoice',
			  'uploadVoice',
			  'downloadVoice',
			  'chooseImage',
			  'previewImage',
			  'uploadImage',
			  'downloadImage',
			  'openLocation',
			  'getLocation',
			  'hideOptionMenu',
			  'showOptionMenu',
			  'closeWindow',
			  'scanQRCode',
			  'addCard',
			  'chooseCard',
			  'openCard'
			]
		});
      console.log("11----------");
		wx.ready(function(){
			//js接口验证
			      console.log("22----------");
			wx.checkJsApi({
				jsApiList: ['getLocation','closeWindow'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				success: function(res) {
					wx.getLocation({
					    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
					    success: function (res) {
					        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
					        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
					        
					        console.log("longitude----------"+longitude);
					    }
					});
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				}
			});
			
		});
		
		wx.error(function(res){
		      console.log("33---------");
			console.log(res.errMsg);
		});
	});
	
	
</script>
</body>
</html>