CKEDITOR.dialog.add("UploadMovie",function(editor) {
	return {
		title : '插入上传视频',
		minWidth : 460,
		minHeight : 80,
		contents : [ {
			id : "tab1",
			label : "",
			title : "",
			expand : !0,
			padding : 0,
			elements : [ {
				type : "html",
				html : "<div style='margin-top:10px;'><p><input type='text' id='tzmovieurl' style='border:1px solid #ccc;height:32px;width:99%;' placeholder='请插入优酷视频地址(670 * 360)'></p></div>"
			} ]
		} ],
		onLoad:function(){
			
		},
		onOk:function(e){
			var videoUrl = $("#tzmovieurl").val();
			if(isNotEmpty(videoUrl) && videoUrl.indexOf("youku")!=-1){
				var json = tm_getYoukuId(videoUrl);
				if(json){
					editor.insertHtml(json.content);
					$("#tzmovieurl").val("");
				}else{
					tmLoading("您输入的YouKu播放地址有误!",2);
					$("#tzmovieurl").select();
				}
			}else {
				$("#tzmovieurl").focus();
				tmLoading("请输入播放地址!",2);
			}
		}
	}
});

function tm_getYoukuId(url){
	var json = {};
	if(url.indexOf("<iframe")!=-1){
		var $iframe = $(url);
		json.mark = 1;
		json.content = "<iframe height='420' width='670' src='"+$iframe.attr("src")+"' frameborder=0 allowfullscreen></iframe>";
	}else{
		var youkuId =  url.substring(url.indexOf("id_")+3,url.indexOf(".html"));
		json.mark = 2;
		json.content = "<div class='youkuplayer' id='"+youkuId+"' data-pid='"+youkuId+"' style='width:670px;height:400px;background:#000;line-height:400px;text-align:center;color:#fff;font-size: 24px;font-weight: bold;'><span>潭州学院视频-精品课程-谦虚戒骄</span></div>";
	}
	return json;
}

//onOk: /*function*/ ,
//onLoad: /*function*/,
//onShow: /*function*/,
//onHide: /*function*/,
//onCancel: /*function*/,
//resizable: /* none,width,height or both  */,
//contents: /*content definition, basically the UI of the dialog*/
