$(function() {
  var appid = '';
  var timestamp = '';
  var noncestr = '';
  var signature = '';
  $.ajax({
	  type: "POST",
	  url: "wxjs.htm",
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
		  'chooseWXPay',
		  'openProductSpecificView',
		  'addCard',
		  'chooseCard',
		  'openCard'
		]
	});
	wx.ready(function(){
		//js接口验证
		wx.checkJsApi({
			jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
			success: function(res) {
				// 以键值对的形式返回，可用的api值true，不可用为false
				// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
			}
		});
		
	});
	
	wx.error(function(res){
		//prompt(res.errMsg);
	});
});