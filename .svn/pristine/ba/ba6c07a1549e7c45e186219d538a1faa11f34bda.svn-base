//上传图片和文件  
//1、导入一下文件
//<link rel="stylesheet" href="../js/editor/themes/default/default.css" />
//<script src="../js/editor/kindeditor-min.js?a=v20160403"></script>
//<script src="../js/editor/lang/zh_CN.js"></script>  
//<span id="pifeSpan" class="input-group-addon" style="display:none"><%=request.getSession().getAttribute("imageRootPath")%></span>
//<script src="../js/uploadFileImage.js"></script>
//2、图片上传
//								<div class="control-group">
//									<div class="controls">
//										<label class="control-label">图片：</label> <!--  name="filemanager"   onclick="uploadImage(this)" -->
//										<input type="button" onclick="uploadImage(this)"    value="浏览图片" class="btn btn-warning"/>
//										<input type="text" class="span7" name="picupload"  value="" ccc="imagesInput"  style="width: 207px">
//										<span  onclick="uploadMoreImage()">+增加上传图片</span> <!-- 单图片上传屏蔽此句 -->
//										<img id="picurl" ccc="picurl"   style="max-width: 50px;max-height: 50px;"  val="">
//										<span  id="uploadMoreIma"></span>
//									</div>
//								</div>
//文件上传
//								<div class="control-group">
//									<div class="controls">
//										<label class="control-label">文件：</label> 
//										 <input type="button" onclick="uploadFile(this)"  class="btn btn-warning" value="选择文件" />
//											<input type="text" name="fileupload" value="" ccc="flieInput"   />
//											<span  onclick="uploadMoreFile()">+增加上传文件</span> <!-- 单文件上传屏蔽此句 -->
//											<a  ccc="fileurl" style="color:red;text-decoration:underline"  href=""> </a>
//											<span  id="uploadMoreFi"></span>
//									</div>
//								</div>
//3、图片上传取值： name="picupload" ；文件上传 name="fileupload" 


var editor;
KindEditor.ready(function(K) {
	 editor = K.editor({
		cssPath : '../js/editor/plugins/code/prettify.css',
		uploadJson : 'upload_json.htm',
		fileManagerJson : 'file_manager_json.htm',
		allowFileManager : true
	});
});

//删除主路径
function clearRootImagePath(picInput){
	var _pifeSpan = $("#pifeSpan").text();
	var _imgVal = picInput.val();
	picInput.val(_imgVal.substring(_imgVal.indexOf("/attached/")));
}


/* KindEditor.ready(function(K) {
	var editor = K.editor({
		cssPath : '../js/editor/plugins/code/prettify.css',
		uploadJson : 'upload_json.htm',
		fileManagerJson : 'file_manager_json.htm',
		allowFileManager : true
	});
	K('input[name=filemanager]').click(function() {
		var imagesInputObj = $(this).parent().children("input[ccc=imagesInput]");
		var picurlObj = $(this).parent().children("img[ccc=picurl]");
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
				clickFn : function(url, title, width, height, border, align) {
					imagesInputObj.val(url);
					editor.hideDialog();
					clearRootImagePath(imagesInputObj);//截断url的头部
					picurlObj.attr("src",url);
				}
			});
		});
	});
}); */ 


/**
 * 上传多图片
 */
function uploadImage(obj){
	var imagesInputObj = $(obj).parent().children("input[ccc=imagesInput]");
	var picurlObj = $(obj).parent().children("img[ccc=picurl]");
	editor.loadPlugin('image', function() {
		editor.plugin.imageDialog({
			clickFn : function(url, title, width, height, border, align) {
				imagesInputObj.val(url);
				editor.hideDialog();
				clearRootImagePath(imagesInputObj);//截断url的头部
				imagesInputObj.attr("value",imagesInputObj.val());
				picurlObj.attr("src",url);
			}
		});
	});
}
function uploadMoreImage(event){
	var main="<div class=\"row cl\" ><div class=\"controls\"><label class=\"form-label col-xs-4 col-sm-2\">   </label>"
	+"<input type=\"button\"  onclick=\"uploadImage(this)\" style=\"margin-left:10px\"	 value=\"浏览图片\" class=\"btn btn-warning\"/>"
	+"<input type=\"text\" class=\"span7\" name=\"picupload\" ccc=\"imagesInput\"  style=\"width: 207px;margin-left:7px\" value=\"\" >"
	+"<input type='button' class='baisc_button absolute cancel-btn' value='取消' onclick=\"uploadImageCancel(this)\"/>"
	+"<img id=\"picurl\" ccc=\"picurl\"   style=\"max-width: 50px;max-height: 50px;\"  val=\"\">"	
	+"<\div><\div>";
	$(event).parent().children("span.flag").prepend(main);
}
function uploadImageCancel(obj){
	$(obj).parent().parent().remove();
}


/**
 * 上传多文件
 * 把name="fileupload"的值存入数据库
 */
function uploadFile(obj){
		var flieInputObj = $(obj).parent().children("input[ccc=flieInput]");
		var fileurlObj = $(obj).parent().children("a[ccc=fileurl]");
		editor.loadPlugin('insertfile', function() {
			editor.plugin.fileDialog({
				clickFn : function(url, title) {
					flieInputObj.val(url);
					clearRootImagePath(flieInputObj);//截断url的头部
					editor.hideDialog();
					flieInputObj.attr("value",flieInputObj.val());
					fileurlObj.attr("href",url);
					fileurlObj.html(title);
				}
			});
		});
}
/**
 * 上传多文件
 */
function uploadMoreFile(){
	var main=$(
	"<div class=\"control-group\" ><div class=\"controls\"><label class=\"control-label\"></label>"
	+"<input type=\"button\" onclick=\"uploadFile(this)\"  style=\"margin-left:-10px\" class=\"btn btn-warning\" value=\"选择文件\" /> "
	+"<input type=\"text\"   name=\"fileupload\" value=\"\" ccc=\"flieInput\"   />"
	+"<input type='button' class='baisc_button absolute cancel-btn' value='取消' onclick=\"uploadFileCancel(this)\"/>"
	+"<a  ccc=\"fileurl\" style=\"color:red;text-decoration:underline;margin-left:10px\" href=\"\"> </a>"	
	+"<\div><\div>");
	$("#uploadMoreFi").prepend(main);
}
function uploadFileCancel(obj){
	$(obj).parent().parent().remove();
}

/**
 * 批量上传
 */
/*
 * <input type="button" id="batch_Images" value="批量上传" /> <!--按钮 -->
 * <div id="batch_imageView"></div>   <!--预览显示图片-->
 *  在searchbean添加  protected List<String> batch_imageView ;//批量上传图片
 *  batch_imageView :$('#batch_imageView').val(), //获得值
 */
KindEditor.ready(function(K) {
    K('#batch_Images').click(function() {
        editor.loadPlugin('multiimage', function() {
            editor.plugin.multiImageDialog({
                clickFn : function(urlList) {
                    var div = K('#batch_imageView');
                    var batch_imageUrl='';
                    K.each(urlList, function(i, data) {
                    	var _imgVal = data.url;
                         div.append('<img style="height:50px;" src="' + data.url + '">');  //data.url是图片上传之后返回的url
                         if(i==0){
                        	 batch_imageUrl=_imgVal.substring(_imgVal.indexOf("attached/"));
                         }else{
                        	 batch_imageUrl=batch_imageUrl+","+_imgVal.substring(_imgVal.indexOf("attached/"));
                         }
                    });
                    $('#batch_imageView').val(batch_imageUrl);
                    editor.hideDialog();
                }
            });
        });
    });
});

/**
 * 本地上传
 * file为新建的文件夹
 * 为“”或者null时候，不创建
 */
function uploadImage_local(obj,file){
	KindEditor.ready(function(K) {
		editor = K.editor({
			cssPath : '../js/editor/plugins/code/prettify.css',
			uploadJson : 'upload_json.htm?dir=image&kindeditor_filepath='+file,
			fileManagerJson : 'file_manager_json.htm',
			allowFileManager : true
		});
	});
	var imagesInputObj = $(obj).parent().children("input[ccc=imagesInput]");
	var picurlObj = $(obj).parent().children("img[ccc=picurl]");
	editor.loadPlugin('image', function() {
		editor.plugin.imageDialog({
			showRemote : false,//关闭图片空间
			clickFn : function(url, title, width, height, border, align) {
				imagesInputObj.val(url);
				editor.hideDialog();
				clearRootImagePath(imagesInputObj);//截断url的头部
				imagesInputObj.attr("value",imagesInputObj.val());
				picurlObj.attr("src",url);
			}
		});
	});
}
