CKEDITOR.dialog.add("UploadFile",
	function(editor) {
		var topicId = "";
		return {
			title : '上传文件',
			minWidth : 640,
			minHeight : 420,
			contents : [ {
				id : "tab1",
				label : "",
				title : "",
				expand : !0,
				padding : 0,
				elements : [ {
					type : "html",
					html : "<div id='attchements'></div>"
				} ]
			} ],
			onShow:function(){
				var html = editor.getData();
				var $imgs = $(html).find("img");
				var ih = "";
				for(var i=0;i<$imgs.length;i++){
					var src = $imgs.eq(i).attr("src");
					if(src && src.indexOf(rootPath)==-1){
						ih+="<div style='float:left;position:relative;margin-left:10px;'><img height='140' width='200' src='"+src+"'><span style='position:absolute;left:84px;top:66px;color:#fff;font-size:16px'>0%</span><a href='javascript:void(0);' style='position:absolute;left:84px;top:86px;color:#fff;font-size:12px' onclick='tz_download(this,editor)'>下载</a></div>";
					}else{
						if(src){
							var isrc = src.replace(rootPath+"/","");
							ih+="<div style='float:left;position:relative;margin-left:10px;'><img height='140' width='200' data-usrc='"+isrc+"' src='"+src+"'><span style='position:absolute;left:84px;top:66px;color:#fff;font-size:16px'>100%</span><a href='javascript:void(0);' style='position:absolute;left:84px;top:86px;color:#fff;font-size:12px' onclick='setting_img(this)'>设为封面</a></div>";
						}
					}
				}
				$("#attchements").html(ih);
			},
			onLoad:function(){
				
			}
		}
});

function setting_img(obj){
	var src = $(obj).prev().prev().data("usrc");
	var opid = $("#contentId").val(); 
	$("#img").val(src);
	$("#imgbox").html("<img id='img' class='tmui-tips' tip='请上传封面图片' data-img='"+src+"' src='"+rootPath+"/"+src+"?d="+new Date+"' width='320' height='180'/>");
	$("#c_imgbox").html("<img src='"+rootPath+"/"+src+"?d="+new Date+"' data-img='"+src+"' x id='c_img' height='140' width='100%'>");
	if(isNotEmpty(opid))tz_editContent();
}

var cobj = {};
function tz_download(obj,editor){
	var src = $(obj).prev().prev().attr("src");
	cobj.src = src;
	$.ajax({
		type:"post",
		url:basePath+"/content/download",
		data:{"img":src},
		success:function(data){
			$("#attchements").find("img[src='"+src+"']").data("usrc",data).next().next().attr("onclick","setting_img(this)").text("设为封面");
			var html = editor.getData();
			html = html.replace(src,rootPath+"/"+data);
			editor.setData(html);
		}
	});
	cobj[src] = setInterval(function(){
		imgpercent(src);
	}, 300);
}

function imgpercent(src){
	$.ajax({
		type:"post",
		url:basePath+"/content/percent",
		data:{"img":src},
		success:function(data){
			var p = parseInt(data,10);
			if((p+1)>=100){
				clearInterval(cobj[src]);
				$("#attchements").find("img[src='"+src+"']").next().html(100+"%");
			}else{
				$("#attchements").find("img[src='"+src+"']").next().html(data);
			}
		}
	});
}