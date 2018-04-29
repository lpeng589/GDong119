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
			alert("网络异常");
			console.log("2"+JSON.stringify(data));
		};
	}
	$.ajax({
		url : url,
		dataType : dataType,
		type : type,
		data : obj,
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
					tip(data.code+" "+data.msg);
				}else{
					//未登录
					if(data.code==-1000){
						//window.parent.goLoginPage();
						//tip(data.msg,window.parent.goLoginPage);	
						loction.href="login.htm";
					}else{
						callbackfun(data);
					}
				}
			},
			error:function(data){
				alert("网络异常");
				console.log("1"+JSON.stringify(data));
			}	
	};
	commitAjax(url,obj,fun,"post","json");
}
/**
 * 弹出id为element的框
 * @param element 
 */
function model(element){
	var jsmodel=$("div[class='modal span1 hide']");
	var jsmodel_body=jsmodel.find("div[class='modal-body']");
	jsmodel.css("top","5%");
	jsmodel.css("left","13%");
	jsmodel.css("width","70%");
	jsmodel.css("z-index","9999");
	jsmodel_body.css("max-height","500px");
	$("#"+element).click();
	$('.modal-backdrop').unbind();
}
/**
 * 弹出提示
 * @param tips  弹出提示语
 * @param confirmfun 关闭提示语后执行方法（如果不为空）
 */
function tip(tips,confirmfun){
	$("#jstipdiv").remove();
	var alert=$("<div id=\"jstipdiv\">"
			+"<div id=\"jstip\" class=\"modal hide fade\"  role=\"dialog\">"
     		+"<div class=\"modal-header\">"
     		+"<button id=\"jsclosebtn\" data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
     		+"<h3>提示</h3>"
     		+"</div>"
     		+"<div class=\"modal-body\">"
     		+"<p>"+tips+"</p>"
     		+"</div>"
     		+"<div class=\"modal-footer\"> " 
     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">确定</a> "
     		+"</div></div></div>");
	$("body").prepend(alert);
	$("#jstip").modal("show");
	if(confirmfun!=null){
		$("#jsconfirmbtn").click(confirmfun);
		$("#jsclosebtn").click(confirmfun);
		$(".modal-backdrop").click(confirmfun);
	}
}
/**
 * 操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param tip 提示
 */
function confirm(confirmfun,tip){
	$("#jsconfirmdiv").remove();
	if(tip==null){
		tip="你确定要执行此操作吗？";
	}

	var alert=$("<div id=\"jsconfirmdiv\">"
			+"<div id=\"jsconfirm\" class=\"modal hide fade\" role=\"dialog\">"
     		+"<div class=\"modal-header\">"
     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
     		+"<h3>提示</h3>"
     		+"</div>"
     		+"<div class=\"modal-body\">"
     		+"<p>"+tip+"</p>"
     		+"</div>"
     		+"<div class=\"modal-footer\"> " 
     		+"<a data-dismiss=\"modal\" class=\"btn floatleft\">取消</a> " 
     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">确定</a> "
     		+"</div></div><div>");
	
	$("body").prepend(alert);
	if(confirmfun!=null){
		$("#jsconfirmbtn").click(confirmfun);
	}
	$("#jsconfirm").modal("show");
	
}
/**
 * 操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param jsconfirmbtncancle  拒绝之后执行的函数
 * @param tip 提示
 */
function confirm2(confirmfun,tip,confirmfuncancle){
	$("#jsconfirmdiv").remove();
	if(tip==null){
		tip="你确定要执行此操作吗？";
	}

	var alert=$("<div id=\"jsconfirmdiv\">"
			+"<div id=\"jsconfirm\" class=\"modal hide fade\" role=\"dialog\">"
     		+"<div class=\"modal-header\">"
     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
     		+"<h3>提示</h3>"
     		+"</div>"
     		+"<div class=\"modal-body\">"
     		+"<p>"+tip+"</p>"
     		+"</div>"
     		+"<div class=\"modal-footer\"> " 
     		+"<a id=\"jsconfirmbtncancle\" data-dismiss=\"modal\" class=\"btn floatleft\">拒绝</a> " 
     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">通过</a> "
     		+"</div></div><div>");
	
	$("body").prepend(alert);
	if(confirmfun!=null){
		$("#jsconfirmbtn").click(confirmfun);
		$("#jsconfirmbtncancle").click(confirmfuncancle);
	}
	$("#jsconfirm").modal("show");
}

/**
 * 操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param jsconfirmbtncancle  拒绝之后执行的函数
 * @param tip 提示
 * 增加备注
 */
function confirm3(confirmfun,tip,confirmfuncancle){
	$("#jsconfirmdiv").remove();
	if(tip==null){
		tip="你确定要执行此操作吗？";
	}

	var alert=$("<div id=\"jsconfirmdiv\">"
			+"<div id=\"jsconfirm\" class=\"modal hide fade\" role=\"dialog\">"
     		+"<div class=\"modal-header\">"
     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
     		+"<h3>提示</h3>"
     		+"</div>"
     		+"<div class=\"modal-body\">"
     		+"<p>"+tip+"</p>"
     		+"<p class=\"floatleft\">备注：</p><input type=\"text\" class=\"span5\" id=\"confirm3\">"
     		+"</div>"
     		+"<div class=\"modal-footer\"> " 
     		+"<a id=\"jsconfirmbtncancle\" data-dismiss=\"modal\" class=\"btn floatleft\">拒绝</a> " 
     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">通过</a> "
     		+"</div></div><div>");
	
	$("body").prepend(alert);
	if(confirmfun!=null){
		$("#jsconfirmbtn").click(confirmfun);
		$("#jsconfirmbtncancle").click(confirmfuncancle);
	}
	$("#jsconfirm").modal("show");
}
/**
 * 操作前确认
 * @param confirmfun  确认之后执行的函数
 * @param jsconfirmbtncancle  拒绝之后执行的函数
 * @param tip 提示
 * 增加备注 和 附件上传
 */
function confirm4(confirmfun,tip,confirmfuncancle){
	$("#jsconfirmdiv").remove();
	if(tip==null){
		tip="你确定要执行此操作吗？";
	}

	var alert=$("<div id=\"jsconfirmdiv\">"
			+"<div id=\"jsconfirm\" class=\"modal hide fade\" role=\"dialog\">"
     		+"<div class=\"modal-header\">"
     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
     		+"<h3>提示</h3>"
     		+"</div>"
     		+"<div class=\"modal-body\">"
     		+"<p>"+tip+"</p>"
     		+"<p class=\"floatleft\">备注：</p><input type=\"text\" class=\"confirm4\" id=\"confirm4_option\">"
     		
     		+"<div class=\"control-group\">"
			+"<div class=\"controls\">"
			+"<label class=\"control-label\">上传附件：</label> "
			+"<input type=\"button\" onclick=\"uploadFile(this)\"  class=\"btn btn-warning floatleft\" value=\"选择文件\" />"
			+"<input type=\"text\"   id=\"confirm4_fileupload\"  value=\"\" ccc=\"flieInput\"   />"
//			+"<span  onclick=\"uploadMoreFile()\">+增加上传文件</span>"
			+"<a  ccc=\"fileurl\"  target=\"_blank\" style=\"color:red;text-decoration:underline\"  href=\"\"> </a>"
//			+"<span  id=\"uploadMoreFi\"></span>"
			+"</div>"
		    +"</div>"
     		+"</div>"
     		+"<div class=\"modal-footer\"> " 
     		+"<a id=\"jsconfirmbtncancle\" data-dismiss=\"modal\" class=\"btn floatleft\">上一步</a> " 
     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">下一步</a> "
     		+"</div></div><div>");
	
	$("body").prepend(alert);
	if(confirmfun!=null){
		$("#jsconfirmbtn").click(confirmfun);
		$("#jsconfirmbtncancle").click(confirmfuncancle);
	}
	$("#jsconfirm").modal("show");
}
/**
 * 操作前确认  异常提交使用
 * @param confirmfun  确认之后执行的函数
 * @param jsconfirmbtncancle  拒绝之后执行的函数
 * @param tip 提示
 * 增加备注 和 附件上传 和 下位框
 */
function confirm5(confirmfun,tip,confirmtype){
	$("#jsconfirmdiv").remove();
	if(tip==null){
		tip="你确定要执行此操作吗？";
	}
	var url = "ordererrortype.htm?";
		var obj = {
				operate : "select",
				type : confirmtype,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				var c="";
				if(data.data!=null&&data.data.length>0){
					for(i=0;i<data.data.length;i++){
						var item=data.data[i];
						c=c+"<option value=\""+item.id+"\">"+item.name+"</option>";
					}
				}
//				
//				var b="<label class=\"control-label\">状态：</label>"
//					+"<div style=\"width:156px;display:inline-block;margin:0\">"
//					+"<select name=\"confirm_select\" id=\"confirm_select\" style=\"display:none\">"
//					+"<option value="">全部</option>"
//					+"</select>"
//					+"</div>";
				var alert=$("<div id=\"jsconfirmdiv\">"
						+"<div id=\"jsconfirm\" class=\"modal hide fade\" role=\"dialog\">"
			     		+"<div class=\"modal-header\">"
			     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
			     		+"<h3>提示</h3>"
			     		+"</div>"
			     		+"<div class=\"modal-body\">"
			     		+"<p>"+tip+"</p>"
			     		+"<p class=\"floatleft\">备注：</p><input type=\"text\" class=\"confirm4\" id=\"confirm5_text\">"
			     		
			     		+"<div class=\"control-group\">"
						+"<div class=\"controls\">"
						+"<label class=\"control-label\">上传附件：</label> "
						+"<input type=\"button\" onclick=\"uploadFile(this)\"  class=\"btn btn-warning floatleft\" value=\"选择文件\" />"
						+"<input type=\"text\"   id=\"confirm5_fileupload\"  value=\"\" ccc=\"flieInput\"   />"
//						+"<span  onclick=\"uploadMoreFile()\">+增加上传文件</span>"
						+"<a  ccc=\"fileurl\"  target=\"_blank\" style=\"color:red;text-decoration:underline\"  href=\"\"> </a>"
//						+"<span  id=\"uploadMoreFi\"></span>"
						+"</div>"
						+"</div>"
						+"<div class=\"control-group\">"
						+"<div class=\"controls\">"
						+"<label class=\"control-label\">异常类型：</label>"
						+"<div style=\"width:156px;display:inline-block;margin:0\">"
						+"<select name=\"confirm5_select\" id=\"confirm5_select\" >"+c
						+"</select>"
						+"</div>"
						+"</div>"
					    +"</div>"
			     		+"</div>"
			     		+"<div class=\"modal-footer\"> " 
			     		+"<a data-dismiss=\"modal\" class=\"btn floatleft\">取消</a> " 
			     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">确定</a> "
			     		+"</div></div><div>");
				$("body").prepend(alert);
				if(confirmfun!=null){
					$("#jsconfirmbtn").click(confirmfun);
				}
				$("#jsconfirm").modal("show");
			}else {
				tip(data.msg);
			}
		};
		commitData(url, obj, callbackfun);
}





var common_operate;
$(function(){
	common_operate=$("input[name='operate']").val();
});
function exportExcel(operate){
	if(operate==undefined||operate==null){
		operate="export";
	}
	$("input[name='operate']").val(operate);
	document.forms[0].submit();
}
function query(operate){
	if(operate==undefined||operate==null){
		operate=common_operate;
	}
	$("input[name='operate']").val(operate);
	document.forms[0].submit();
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
 * 获得ids数组id
 * @param idsName
 */
function getIds(idsName){
	var ids = new Array();
	$("input[name='"+idsName+"']:checked").each(function() {
		ids[ids.length] = this.value;
	});
	return ids;
}
/**
 * 获得ids选中元素数组
 * @param idsName
 */
function getChecks(idsName){
	var ids = new Array();
	$("input[name='"+idsName+"']:checked").each(function() {
		ids[ids.length] = $(this);
	});
	return ids;
}
/**
 * 返回工作流日志
 * @param tableid 
 * @param tablename
 */ 
function showlog(tableid,tablename){
		$("#jsflowlog").remove();
	 	var url = "flowLog.htm?";
		var obj = {
			operate : "log",
			tableid : tableid,
			tablename : tablename,
		};
		var callbackfun = function(data) {
			if (data.code == 0) {
				var divstring ="<div><div id=\"jsflowlog\" class=\"modal hide fade\"  role=\"dialog\" style=\"width:60%;left:30%;text-align: center;height:400px;overflow:auto;\">"
			     		+"<div class=\"modal-header\">"
			     		+"<button id=\"jsclosebtn\" data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
			     		+"<h3>日志</h3>"
			     		+"</div>"
			     		+"<div class=\"modal-body\">"
			     		+"<table class=\"table table-bordered table-striped with-check\">"
			     		+"<thead><tr>"
			     		+"<th></th>"
			     		+"<th>节点</th>"
			     		+"<th>操作人</th>"
			     		+"<th>操作</th>"
			     		+"<th>操作人意见</th>"
			     		+"<th>附件</th>"
			     		+"<th>时间</th>"
			     		+"</tr> </thead><tbody>";
				for (var i = 0; i < data.data.length; i++) {
					var isreturn = "下一步";
					if(data.data[i].isreturn==2){
						isreturn="上一步";
	 			     }
					divstring = divstring+"<tr><td></td>"
 			     		+"<td>"+data.data[i].descriptionnow+"</td>"
 			     		+"<td>"+data.data[i].username+"</td>"
 			     		+"<td>"+isreturn+"</td>"
 			     		+"<td>"+data.data[i].opinion+"</td>";
 			     divstring = divstring+"<td><a   target=\"_blank\" style=\"color:red;text-decoration:underline\"  href="+$("#pifeSpan").text()+data.data[i].file +">";
 			     if(data.data[i].file!=null && data.data[i].file!=""){
 			    	divstring = divstring+ "下载";
 			     }
 			    divstring = divstring+ "</a></td>";
 			    divstring = divstring+"<td>"+data.data[i].createtime+"</td></tr>";
				}
				divstring = divstring
						+"</tbody> </table>"
			     		+"</div>"
			     		+"<div class=\"modal-footer\"> " 
			     		+"<a id=\"jsconfirmbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">确定</a> "
			     		+"</div></div></div>";
			     		
			    var alert=$(divstring);		
			     		
				$("body").prepend(alert);
				$("#jsflowlog").modal("show");
			} else {
				tip(data.msg);
			}
		}; 
		commitData(url, obj, callbackfun);
	}

//保留两位小数   
//功能：将浮点数四舍五入，取小数点后2位  
function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
}  
//只能输入 数字和减号
function isNaN2(obj)
{
	 var str = obj.value;
//	    var reg=/^([0-9]|-?)+([0-9]*|\.{1})$/;
    var reg=/^([0-9]|-?)+([0-9]*)+(\.*)+([0-9]*)$/;
    document.onkeyup=function()
    {
        if(reg.test(str))
        {
        }
        else
        {
				$(obj).val(str.substring(0,str.length-1));
        }
    } 
}
$(function(){
	$("#list_div").height($("body").height()-1);
	$("#detail_div").find("div").first().height($(".iframebody").height()-4);
	$(".iframebody").find("d#list_diviv").height($("#detail_div").find("div").first().height()-2);
});


//有操作权限的职员	  
function selectemployee(tablename,tablename2,parentidname,idstr) {
		var checks= getChecks("ids");
		if(checks.length==0||checks.length<0){
			tip("请勾选要操作的记录");
			return;
		}
 		var url = "employee.htm?";
 		var obj = {
 					operate : "getemployees",
 					idstr : idstr,//需要的权限
 		};
 		var callbackfun = function(data) {
 			if (data.code == 0) {
						var   option =  "";
 					for(var k=0;k<data.data.length;k++){
						 option=option+"<option value=\""+data.data[k].id+"\">"+data.data[k].username+"</option>";
					  	}
					  $("#jsassigndiv").remove();
			    	  var alert=$("<div id=\"jsassigndiv\">"
			    				+"<div id=\"jsassign\" class=\"modal hide fade\" role=\"dialog\">"
			    	     		+"<div class=\"modal-header\">"
			    	     		+"<button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>"
			    	     		+"<h3>把表单转给哪个职员？（修改后无法再改回）</h3>"
			    	     		+"</div>"
			    	     		+"<div class=\"modal-body\">"
			    	     		+"<div class=\"select-normal span7\"> "
			    	     		+"<select id=\"employeeid\"> "
			    	     		+option
			    	     		+"	</select> "
			    	     		+"</div> "
			    	     		+"</div>"
			    	     		+"<div class=\"modal-footer\"> " 
			    	     		+"<a  id=\"jsassignbtncancle\"   data-dismiss=\"modal\"  class=\"btn floatleft\">取消</a> " 
			    	     		+"<a id=\"jsassignbtn\" data-dismiss=\"modal\" class=\"btn btn-primary\">确定</a> "
			    	     		+"</div></div><div>"); 
			    	$("body").prepend(alert);
			    	 $("#jsassignbtn").on('click', function () { //多层绑定
			    		 employee_service(tablename,tablename2,parentidname,$("#employeeid").val());
			    	 });
			    	 
			    	$("#jsassign").modal("show");
 			} else {
 				tip(data.msg);
 			}
 		};
 		commitData(url, obj, callbackfun);
}	

//修改负责人
function employee_service(tablename,tablename2,parentidname,employid){
	var url = "employee.htm?";
	var obj = {
				operate : "employee_service",
				ids : getIds("ids"),//主表ID
				tablename : tablename,//主表
				tablename2 :tablename2,//子表，无是为""
				parentidname : parentidname,//子表修改的id的name
				employid : employid,//修改后的职员id
	};
	var callbackfun = function(data) {
		if (data.code == 0) {
			location.reload();
		} else {
			tip(data.msg);
		}
	};
	commitData(url, obj, callbackfun);	  
		  
}


/**
 * 弹出提示
 * @param tips  弹出提示语
 */
function wxtip(tips,confirmfun){
	$("#androidDialog1").remove();
	var alert=$("<div class=\"js_dialog\" id=\"androidDialog1\" style=\"display: none;\">"
            +"<div class=\"weui-mask\"></div>"
            +"<div class=\"weui-dialog weui-skin_android\">"
            +"<div class=\"weui-dialog__hd\"><strong class=\"weui-dialog__title\">提示</strong></div>"
            +"<div class=\"weui-dialog__bd\">"+tips+"</div>"
            +"<div class=\"weui-dialog__ft\">"
            +"<a href=\"javascript:$('#androidDialog1').fadeOut();"+confirmfun+"\" class=\"weui-dialog__btn weui-dialog__btn_default\">关闭</a>"
            +"</div>"
            +"</div>"
            +"</div>");
	$("body").prepend(alert);
	$androidDialog1 = $('#androidDialog1');
	$androidDialog1.fadeIn(200);
}

/**
 * 弹出提示
 * @param tips  弹出提示语
 * @param confirmfun 关闭提示语后执行方法（如果不为空）
 */
function wxconfirm(tips,confirmfun){
	$("#androidDialog2").remove();
	var alert=$("<div class=\"js_dialog\" id=\"androidDialog2\" style=\"display: none;\">"
            +"<div class=\"weui-mask\"></div>"
            +"<div class=\"weui-dialog weui-skin_android\">"
            +"<div class=\"weui-dialog__hd\"><strong class=\"weui-dialog__title\">提示</strong></div>"
            +"<div class=\"weui-dialog__bd\">"+tips+"</div>"
            +"<div class=\"weui-dialog__ft\">"
            +"<a href=\"javascript:$('#androidDialog2').fadeOut();\" class=\"weui-dialog__btn weui-dialog__btn_default\">取消</a>"
            +"<a href=\"javascript:$('#androidDialog2').fadeOut();"+confirmfun+"\" class=\"weui-dialog__btn weui-dialog__btn_primary\">确认</a>"
            +"</div>"
            +"</div>"
            +"</div>");
	$("body").prepend(alert);
	$androidDialog1 = $('#androidDialog2');
	$androidDialog1.fadeIn(200);
}

function showimgs(even){
	layer.photos({
		  photos: $(even)
		  ,anim: 1 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
		  ,area: ['800px', 'auto']
	}); 
}