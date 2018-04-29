var display_id;
var canvas_id;
function render(src){ 
	var canvas = document.getElementById(canvas_id); 
		var MAX_WIDTH = 320; 
		var image = new Image(); 
		image.onload = function(){
			//if(image.width > MAX_WIDTH) { 
			//	image.height *=MAX_WIDTH/image.width ; 
			//	image.width = MAX_WIDTH; 
			//} 
			var ctx = canvas.getContext("2d"); 
			ctx.clearRect(0, 0, canvas.width, canvas.height); 
			canvas.width = image.width; 
			canvas.height = image.height; 
			ctx.drawImage(image, 0, 0, image.width, image.height); 
		}; 
		image.src = src; 
	}; 
function uploadImg(displayid,canvasid){
	display_id=displayid;
	canvas_id=canvasid;
	var fileid="file_"+Date.parse(new Date())+Math.round(Math.random()*1000);
	imgfile="<input type=\"file\" style=\"position:fixed;top:-10000px;\" accept=\"image/*\" id=\""+fileid+"\"/>";
	$("body").prepend(imgfile);
	document.getElementById(fileid).addEventListener("change",readFile,false);
	$("#"+fileid).click();
}
function readFile(){ 
	if ( typeof(FileReader) === 'undefined' ){
	    prommpt("抱歉，你的浏览器版本不支持上传图片");
		input.setAttribute( 'disabled','disabled' );
	}	
	var file = this.files[0];
	if(!/image\/\w+/.test(file.type)){
		$.Prompt("请确保文件为图像类型",1000);
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e){
		$("#"+display_id).html("<canvas id=\""+canvas_id+"\" class=\"imgs\"></canvas> " );
		render(e.target.result); 
	}; 
}
function getUploadImgValue(canvasid){
	if(document.getElementById(canvasid)==null){
		return null;
	}
	return encodeURIComponent(document.getElementById(canvasid).toDataURL("image/jpg").substring(22));
}