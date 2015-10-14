CKEDITOR.dialog.add("UploadPic",
	function(editor) {
		return {
			title : '上传图片',
			minWidth : 390,
			minHeight : 150,
			contents : [ {
				id : "tab1",
				label : "",
				title : "",
				expand : !0,
				padding : 0,
				elements : [ {
					type : "html",
					html : "<span id='tmupload'></span>"
				} ]
			} ],
			onShow:function(){
			},
			onLoad:function(){
				$.tzUpload({
					url:basePath+"/upload/file",
					targetId:"tmupload",
					size:"10 MB",
					type:"*.jpg;*png",
//					single:true,
					callback:function(data){
						var jdata = eval("("+data+")");
						editor.insertHtml("<div class='tzui-img'><img style='max-width:100%;' src='"+rootPath+"/"+jdata.url+"'/></div>");
						tz_editContent();
					}
				});
			}
		}
});