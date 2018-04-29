/*版本号1.0 公共js  需引用jquery.js common.css*/
/**
 *  ajax调用 
 * @param url
 * @param obj
 * @param callbackfun
 * @param type
 * @param dataType
 */
function commitAjax(url,obj,callbackfun,type,dataType){
	if(type==null){
		//默认post方式
		type="post";
	}
	if(dataType==null){
		//默认json数据类型
		dataType="json";
	}
	if(callbackfun.error==null){
		//默认输出错误日志
		callbackfun.error=function(data){
			alert("操作异常");
			console.log(obj);
		};
	}
	$.ajax({
		url : url,
		dataType : dataType,
		type : type,
		data : obj,
		beforeSend: function(request) {
             request.setRequestHeader("returntype", "ajax/json");
        },
		traditional:true,
		success : callbackfun.success,
		error:callbackfun.error
	});
}
/**
 *  ajax调用 post json  suceescc后台专用
 * @param url
 * @param obj
 * @param callbackfun
 */
function commitData(url,obj,callbackfun){
	var fun={
			success: function(data){
				if(callbackfun==null){
					alert(data.code+" "+data.msg);
				}else{
					//未登录
					if(data.code==-1000){
						location.href="mlogin.htm";	
					}else if(data.code==-10000){
						location.href="pclogin.htm";	
					}else{
						callbackfun(data);
					}
				}
			},
			error:function(data){
				alert("操作异常");
				console.log(obj);
			}	
	};
	commitAjax(url,obj,fun,"post","json");
}
/**
 * 弹出id为element的框
 * @param element 
 */
function model(element){
	$("#jsmodel").css("top","5%");
	$("#jsmodel").css("left","13%");
	$("#jsmodel").css("width","70%");
	$("#jsmodel").css("z-index","9999");
	$("#jsmodelbody").css("max-height","500px");
	$("#"+element).click();
	$('.modal-backdrop').unbind();
}
/**
 * 弹出提示
 * @param tips  弹出提示语
 */
function tip1(tips){
	alert(tips, { classes: "otip", t: 2000 });
}
/**
 * 操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function confirm1(confirmfun,tip){
	$("#jsconfirmdiv").remove();
	if(tip==null){
		tip="你确定要执行此操作吗？";
	}

	var alert=$("<div id=\"jsconfirmdiv\"><a id=\"jsconfirmbtnpre\" href=\"#jsconfirm\" data-toggle=\"modal\" class=\"hide\">confirm</a>"
			+"<div id=\"jsconfirm\" class=\"modal\">"
     		+"<div class=\"modal-header\">"
     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
     		+"<h3>提示</h3>"
     		+"</div>"
     		+"<div class=\"modal-body\">"
     		+"<p>"+tip+"</p>"
     		+"</div>"
     		+"<div class=\"modal-footer\"> " 
     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">确定</a> "
     		+"<a data-dismiss=\"modal\" class=\"btn\">取消</a> " +
     		+"</div></div><div>");
	
	$("body").prepend(alert);
	if(confirmfun!=null){
		$("#jsconfirmbtn").click(confirmfun);
	}
	$("#jsconfirmbtnpre").click();
}
/**
 * weui样式弹出框
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function weuitip(tip){
	$("#dialog2").remove();
	var alert="<div class=\"weui_dialog_alert\" id=\"dialog2\" style=\";\">"
    +"<div class=\"weui_mask\"></div>"
    +"<div class=\"weui_dialog\">"
        +"<div class=\"weui_dialog_hd\"><strong class=\"weui_dialog_title\">温馨提示</strong></div>"
        +"<div class=\"weui_dialog_bd\">"+tip+"</div>"
        +"<div class=\"weui_dialog_ft\">"
           +"<a onclick=\"javascript:$('#dialog2').hide();\" class=\"weui_btn_dialog primary\">确定</a></div></div></div>";
	$("body").prepend(alert);
}
/**
 * weui样式操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function weuiconfirm(confirm,tip){
	$("#weuiconfirm2").remove();
	var alert="<div class=\"weui_dialog_confirm\" id=\"weuiconfirm2\">"
    +"<div class=\"weui_mask\"></div>"
    +"<div class=\"weui_dialog\">"
        +"<div class=\"weui_dialog_hd\"><strong class=\"weui_dialog_title\">确认提示</strong></div>"
        +"<div class=\"weui_dialog_bd\">"+tip+"</div>"
        +"<div class=\"weui_dialog_ft\">"
            +"<a onclick=\"javascript:$('#weuiconfirm2').hide();\" class=\"weui_btn_dialog default\">取消</a>"
            +"<a id=\"jsconfirmbtn\" onclick=\"javascript:$('#weuiconfirm2').hide();\"class=\"weui_btn_dialog primary\">确定</a></div></div></div>"
    $("body").prepend(alert);  
	if(confirm!=null){
		$("#jsconfirmbtn").click(confirm);
	}
}
/**
 * weui样式操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function weuiconfirmonly(confirm,tip){
	$("#weuiconfirm2").remove();
	var alert="<div class=\"weui_dialog_confirm\" id=\"weuiconfirm2\">"
    +"<div class=\"weui_mask\"></div>"
    +"<div class=\"weui_dialog\">"
        +"<div class=\"weui_dialog_hd\"><strong class=\"weui_dialog_title\">确认提示</strong></div>"
        +"<div class=\"weui_dialog_bd\">"+tip+"</div>"
        +"<div class=\"weui_dialog_ft\">"
            +"<a id=\"jsconfirmbtn\" onclick=\"javascript:$('#weuiconfirm2').hide();\"class=\"weui_btn_dialog primary\">确定</a></div></div></div>"
    $("body").prepend(alert);  
	if(confirm!=null){
		$("#jsconfirmbtn").click(confirm);
		
	}
}
/**
 * weui样式操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function weuiconfirm3(confirm,tip){
	$("#weuiconfirm3").remove();
	var alert="<div class=\"weui_dialog_confirm\" id=\"weuiconfirm3\">"
    +"<div class=\"weui_mask\"></div>"
    +"<div class=\"weui_dialog\">"
        +"<div class=\"weui_dialog_hd\"><strong class=\"weui_dialog_title\">"+tip+"</strong></div>"
        +"<div class=\"weui_dialog_bd\"><input type=\"text\" id=\"confirm3\" style=\"height: 30px;border: 1px solid #9A9797;width: 100%;\"></div>"
        +"<div class=\"weui_dialog_ft\">"
            +"<a onclick=\"javascript:$('#weuiconfirm3').hide();\" class=\"weui_btn_dialog default\">取消</a>"
            +"<a id=\"jsconfirmbtn\" onclick=\"javascript:$('#weuiconfirm2').hide();\"class=\"weui_btn_dialog primary\">确定</a></div></div></div>"
    $("body").prepend(alert);  
	if(confirm!=null){
		$("#jsconfirmbtn").click(confirm);
	}
	$("#confirm3").focus();
}

/**
 * 微信弹窗
 * @param tip
 * @param confirm
 */
function wechatconfirm(tip,confirm){
	$("#weuialert").remove();
	var alert="<div class=\"js_dialog\" id=\"weuialert\" style=\"display: none;\">"
            +"<div class=\"weui-mask\"></div>"
            +"<div class=\"weui-dialog\">"
            +"<div class=\"weui-dialog__bd\">"+tip+"</div>"
            +"<div class=\"weui-dialog__ft\">"
            +"<a href=\"javascript:$('#weuialert').fadeOut(200);\" class=\"weui-dialog__btn weui-dialog__btn_default\">取消</a>"
            +"<a href=\"javascript:$('#weuialert').fadeOut(200);"+confirm+";\" class=\"weui-dialog__btn weui-dialog__btn_primary\">确认</a>"
            +"</div>"
            +"</div>"
            +"</div>";
    $("body").prepend(alert);  
	$("#weuialert").fadeIn(200);
}

/**
 * 微信弹窗
 * @param tip
 */
function wechatalert(tip,confirm){
	$("#weuialert").remove();
	var alert="<div class=\"js_dialog\" id=\"weuialert\" style=\"display: none;\">"
			+"<div class=\"weui-mask\"></div>"
			+"<div class=\"weui-dialog\">"
			+"<div class=\"weui-dialog__bd\">"+tip+"</div>"
			+"<div class=\"weui-dialog__ft\">"
			+"<a href=\"javascript:$('#weuialert').fadeOut(200);"+confirm+";\" class=\"weui-dialog__btn weui-dialog__btn_primary\">确认</a>"
			+"</div>"
			+"</div>"
			+"</div>";
    $("body").prepend(alert);  
	$("#weuialert").fadeIn(200);
}

/**
*	余额不足，选择是否充值
*/
function moneyconfirm(tip,confirm){
	$("#moneyconfirm").remove();
	var alert="<div class=\"js_mask\" id=\"moneyconfirm\" style=\"display: none;\">"
			+"<div class=\"mask\"></div>"
			+"<div class=\"dialog\">"
			+"<div class=\"dialog_bd\">"+tip+"</div>"
			+"<div class=\"dialog_bt\">"
			+"<a onclick=\"$('#moneyconfirm').fadeOut(200)\" class=\"f1 dialog_bt_lf\">取消</a>"
			+"<a href=\""+confirm+"\" class=\"f1 dialog_bt_rt\">去充值</a>"
			+"</div>"
			+"</div>"
			+"</div>";
    $("body").prepend(alert);  
	$("#moneyconfirm").fadeIn(200);
}
/**
 * 相片层
 */
function showimg(src){
	var json ={
			  "title": "", //相册标题
			  "id": 123, //相册id
			  "start": 0, //初始显示的图片序号，默认0
			  "data": [   //相册包含的图片，数组格式
			    {
			      "alt": "签名",
			      "pid": 666, //图片id
			      "src": src, //原图地址
			      "thumb": src //缩略图地址
			    }
			  ]
			};
  layer.photos({
    photos: json //格式见API文档手册页
    ,anim: 1 //0-6的选择，指定弹出图片动画类型，默认随机
  });
}
/**
 * 
 */
function tip(tip){
	 //提示
	  layer.open({
	    content: tip
	    ,skin: 'msg'
	    ,time: 2 //2秒后自动关闭
	  });
}

/**
*保留小数点两位
*/
function toDecimal(x) {  //保留小数点两位
	    var f = parseFloat(x); 
	    if (isNaN(f)) { 
	       return; 
	    } 
	    f = Math.round(x*100)/100; 
	    return f; 
	} 