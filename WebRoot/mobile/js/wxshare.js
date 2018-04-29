/**
 * 参数说明
 * _wx_title	:	分享标题
 * _wx_img		:	分享图片
 * _wx_link		:	分享链接
 * _wx_cont		:	分享内容
 * 使用条件	:	1、微信认证 2、JS接口安全域名	3、appid和appser
 **/
function wx_share_out(_wx_title,_wx_img,_wx_cont,_wx_link) {
	if(_wx_link=='') {
		_wx_link = window.location.href;
	}
	if(_wx_img=='' || _wx_img=='0' || _wx_img=='null') {
		var imgs = document.getElementsByTagName("img");
		if(imgs.length>0) {
			var urlm = /http:\/\//i;
			_wx_img = imgs[0].src;
			if(urlm.test(_wx_img)) {} else {
				_wx_img = 'http://'+window.location.host+_wx_img;
			}
		}
	}
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
			jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage', 'chooseWXPay'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
			success: function(res) {
				//alert(res);
				// 以键值对的形式返回，可用的api值true，不可用为false
				// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
			}
		});
		
		//分享到朋友圈
		wx.onMenuShareTimeline({
			title: _wx_cont, // 分享标题
			link: _wx_link, // 分享链接
			imgUrl: _wx_img, // 分享图标
			success: function () { 
				// 用户确认分享后执行的回调函数
				if(afterShareTimeline!=undefined){
					afterShareTimeline();
				}
			},
			cancel: function () { 
				// 用户取消分享后执行的回调函数
			}
		});
		
		//分享给朋友
		wx.onMenuShareAppMessage({
			title: _wx_title, // 分享标题
			desc: _wx_cont, // 分享描述
			link: _wx_link, // 分享链接
			imgUrl: _wx_img, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function () { 
				// 用户确认分享后执行的回调函数
				if(afterShareAppMessage!=undefined){
					afterShareAppMessage();
				}
			},
			cancel: function () { 
				// 用户取消分享后执行的回调函数
			}
		});
		
	});
	
	//分享到QQ
	wx.onMenuShareQQ({
		title: _wx_title, // 分享标题
		desc: _wx_cont, // 分享描述
		link: _wx_link, // 分享链接
		imgUrl: _wx_img, // 分享图标
		success: function () { 
		   // 用户确认分享后执行的回调函数
		},
		cancel: function () { 
		   // 用户取消分享后执行的回调函数
		}
	});
	
	//分享到腾讯微博
	wx.onMenuShareWeibo({
		title: _wx_title, // 分享标题
		desc: _wx_cont, // 分享描述
		link: _wx_link, // 分享链接
		imgUrl: _wx_img, // 分享图标
		success: function () { 
		   // 用户确认分享后执行的回调函数
		},
		cancel: function () { 
			// 用户取消分享后执行的回调函数
		}
	});

	wx.error(function(res){
		//alert(res.errMsg);
	});
}
/**
 * 添加分享链接参数，如果之前有这个参数则覆盖
 * sourceUrl 原链接
 * parameter 参数名称
 * value 参数值
 */
function addLinkParameter(sourceUrl,parameter,value){
	if(sourceUrl==null||parameter==null||value==null){
		return sourceUrl;
	}
	var parameters=sourceUrl.split("&");
	var parameters1=parameters[0].split("?");
	var newUrl=parameters1[0]+"?";
	if(parameters1.length>1){
		parameters[0]=parameters1[1];
	}
	for(var i=0;i<parameters.length;i++){
		if(!parameters[i].length>=parameter.length||parameters[i].substring(0,parameter.length)!=parameter){
			newUrl+="&"+parameters[i];
		}
	}
	newUrl+="&"+parameter+"="+value;
	return newUrl.replace("?&", "?");
}