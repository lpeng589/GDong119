

$(function() {
	
	 var appid = '';
	 var timestamp = '';
	 var noncestr = '';
	 var signature = '';
 	  $.ajax({
 		  type: "POST",
 		  url: "qywxjs.htm",
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
	 			debug: false,
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
	 			  'onRecordEnd',
	 			  'playVoice',
	 			  'pauseVoice',
	 			  'stopVoice',
	 			  'uploadVoice',
	 			  'downloadVoice',
	 			  'chooseImage',
	 			  'previewImage',
	 			  'uploadImage',
	 			  'downloadImage',
	 			  'getNetworkType',
	 			  'openLocation',
	 			  'getLocation',
	 			  'hideOptionMenu',
	 			  'showOptionMenu',
	 			  'closeWindow',
	 			  'scanQRCode',
	 			  'openProductSpecificView',
	 			  'addCard',
	 			  'chooseCard',
	 			  'openCard'
	 			]
	 		});
	 		
	 		wx.ready(function(){
	 			//js接口验证
	 			wx.checkJsApi({
	 				jsApiList: ['startRecord','stopRecord', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	 				success: function(res) {
//	 					alert('返回检查api的结果'+JSON.stringify(res));
	 					// 以键值对的形式返回，可用的api值true，不可用为false
	 					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	 					//JS接口配置成功，生成cardExt的signature
	 					
	 				}
	 			});
	 			
	 			
	 			
	 		});
	 		

	 		wx.error(function(res){
//	 			alert(JSON.stringify(res));
	 		});
	 		
	 		
});
