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
			//alert("操作异常");
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
				//alert("操作异常");
				console.log(obj);
			}	
	};
	commitAjax(url,obj,fun,"post","json");
}


//将form转为AJAX提交
function ajaxSubmit(frm, fn) {
var dataPara = getFormJson(frm);
$.ajax({
    url: frm.action,
    type: frm.method,
    data: dataPara,
    success: fn
});
}

//将form中的值转换为键值对。
function getFormJson(frm) {
var o = {};
var a = $(frm).serializeArray();
$.each(a, function () {
    if (o[this.name] !== undefined) {
        if (!o[this.name].push) {
            o[this.name] = [o[this.name]];
        }
        o[this.name].push(this.value || '');
    } else {
        o[this.name] = this.value || '';
    }
});
return o;
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
function confirm(tip,confirmfun){
	$("#dialog").remove();
	var alert = "<div class=\"dialog\" id=\"dialog\">"
    +"<div class=\"dialog-inner\">"
    +"<div class=\"dialog-inner-ct\">"
    +"  <div class=\"dialog-hd\">确认操作</div>"
    +"  <div class=\"dialog-bd tc\">"+tip+"</div>"
    +"</div>"
    +"<div class=\"dialog-ft\">"
    +"  <span data-dialogid=\"dialog\" id=\"cancelDeleteClaim\" onclick=\"dialogUI.hide('dialog')\">取消</span>"
      +"  <span class=\"dialog-primary\" data-dialogid=\"dialog\" id=\"comfirmDeleteClaim\" data-disabled=\"true\">确定</span>"
	+"</div></div></div>";
	$("body").prepend(alert);
	dialogUI.show("dialog");
	if(confirmfun!=null){
		$("#comfirmDeleteClaim").click(confirmfun);
	}
}

/**
 * 操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function uialert(tip,confirmfun){
	$("#dialog").remove();
	var alert = "<div class=\"dialog\" id=\"dialog\">"
    +"<div class=\"dialog-inner\">"
    +"<div class=\"dialog-inner-ct\">"
    +"<div class=\"dialog-bd tc\">"+tip+"</div>"
    +"</div>"
    +"<div class=\"dialog-ft\">"
    +"<span class=\"dialog-primary\" data-dialogid=\"dialog\" id=\"comfirmDeleteClaim\" data-disabled=\"true\" onclick=\"dialogUI.hide('dialog');"+confirmfun+"\">确定</span>"
	+"</div></div></div>";
	$("body").prepend(alert);
	dialogUI.show("dialog");
}

