$(function(){
	$(window).scroll(function(){
		if($(this).scrollTop() > 100){
			$("#button_offset").removeClass("none");
	}else{
		$("#button_offset").addClass("none");
	}
});

if(window.localStorage){
	var html = localStorage.getItem("content_data");
	var title = localStorage.getItem("title_data");
	if(html)setEditorContent("p_desc",html);
	if(title)$("#title").val(title);
}

try{
	CKEDITOR.on('instanceReady', function (e) { 
		if(e.editor.document.$.addEventListener) 
			e.editor.document.$.addEventListener('keydown',ckeydown,false);
		else if(e.editor.document.$.attachEvent)
			e.editor.document.$.attachEvent('onkeyup',function(e){ckeydown(e);});  
	});
	function ckeydown(){
		if(window.localStorage){
			localStorage.setItem("content_data", editor.getData());
			localStorage.setItem("title_data", $("#title").val());
			}
		}
	}catch(e){}
});

var editor = CKEDITOR.replace("p_desc",{height:760});	
function tz_saveContent() {
	var html = editor.getData();
	var params = $("#editor_warp").serializeArray();
	params.push({name:"img",value:$("#img").data("img")});
	params.push({name:"width",value:$("#img").data("iw")});
	params.push({name:"height",value:$("#img").data("ih")});
	params.push({name:"thumnail",value:$("#img").data("thumnail")});
	params.push({name:"content",value:html});
	params.push({name:"type",value:$("#channelIdsec").find("option:selected").data("type")});
	$.tzAjax.request({model:"content",method:"save",callback:function(data){
		loading("操作成功!",4);
		tz_staticHtml(data.id);//静态化一次
		localStorage.removeItem("content_data");
		localStorage.removeItem("title_data");
		window.location.href = basePath+"/content/edit/"+data.id;
	}},params);
};

function clearEditorBox(){
	$(parent.document).find("#editbox").empty().hide();
}

$(function(){
	$.tzUpload({
		url:basePath+"/upload/file",
		targetId:"uploadBtn",
		size:"10 MB",
		data:{"oldName":$("#img").data("img")||""},
		type:"*.jpg;*png",
		single:true,
		callback:function(data){
		var jdata = eval("("+data+")");
		$("#imgbox").html("<img id='img' data-thumnail='"+jdata.thumnail+"' data-iw='"+jdata.width+"' data-ih='"+jdata.height+"' class='tmui-tips' tip='请上传封面图片' data-img='"+jdata.url+"' src='"+rootPath+"/"+jdata.url+"?d="+new Date+"' width='320' height='180'/>");
			tz_saveContent();
		}
	});
});