//四级地址插件
$(function() {
	var _url = "getProvince.html";
	$.ajax({
		type : 'POST' ,
		url : _url ,
		data : {},
		dataType : "json",
		success: function(data){
		},
		error:function(er){
			console.log("selectProvince error!er = "+er);
		}
	});
});

function getArea(obj){
	var aere4level = obj.value;
// 	alert("aere4level   "+aere4level);
	if(aere4level==null ||aere4level=='')return;
/*	if(aere4level==null ||aere4level==''){alert(aere4level)
		if(level==1){
//			$("#city").empty();
//			$("#area").empty();
			$("#city").find("option").remove(); 
			$("#area").find("option").remove(); 
			$("#street").find("option").remove(); 
//			$("#street").empty();
		}else if(level==2){
//			$("#area").empty();
//			$("#street").empty();
			$("#area").find("option").remove(); 
			$("#street").find("option").remove(); 
		}else if(level==3){
//			$("#street").empty();
			$("#street").find("option").remove(); 
		}else if(level==4){
			
		}
		return;
	}*/
	aere4level = aere4level.split(',');
	var code =aere4level[0];
	var parentId = aere4level[1];
	var level = aere4level[2];
	parentId=code;
	if(level==1){
		$("#city").empty().append("<option  value=\""+code+","+parentId+","+level+"\">--选择城市--</option>");
		$("#area").empty().append("<option  value=\""+code+","+parentId+","+level+"\">--选择区/县--</option>");
		$("#street").empty().append("<option value=\""+code+","+parentId+","+level+"\">--选择街道--</option>");
		$("#provinceCode").val(code);
	}else if(level==2){
		$("#area").empty().append("<option  value=\""+code+","+parentId+","+level+"\">--选择区/县--</option>");
		$("#street").empty().append("<option value=\""+code+","+parentId+","+level+"\">--选择街道--</option>");
		$("#cityCode").val(code);
	}else if(level==3){
		$("#street").empty().append("<option value=\""+code+","+parentId+","+level+"\">--选择街道--</option>");
		$("#areaCode").val(code);
	}else if(level==4){
		$("#streetCode").val(code);
		return;
	}
	var _url = "getArea.html?parentId="+parentId;
	$.ajax({
	  type: 'POST',
	  url: _url,
	  data: {},
	  dataType: "json",
	  success: function(data){
		  $.each(data,function(index,value){
// 			  console.log("index="+index+",value="+value.code+","+value.name+","+value.parentId+","+value.level);
			  if(level==1){
				  $("#city").append("<option value='"   +value.code  +","+  value.parentId    +","+   value.level +    "'>"+value.name+"</option>");
			  }else if(level==2){
				  $("#area").append("<option value='"   +value.code  +","+  value.parentId    +","+   value.level +    "'>"+value.name+"</option>");
			  }else if(level==3){
				  $("#street").append("<option value='"   +value.code  +","+  value.parentId    +","+   value.level +    "'>"+value.name+"</option>");
			  }
		  });
	  },
	  error:function(er){
		  console.log("changeProvince error!er = "+er);
	  }
	});
}