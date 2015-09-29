CKEDITOR.dialog.add("UpLink",function(editor) {
	return {
		title : '插入链接',
		minWidth : 960,
		minHeight :460,
		contents : [ {
			id : "tab1",
			label : "",
			title : "",
			expand : !0,
			padding : 0,
			elements : [ {
				type : "html",
				html : "<div style='margin-top:10px;'><p><input type='text' id='tzlink' style='border:1px solid #ccc;height:32px;width:99%;' placeholder='请插入链接地址'></p></div>"
			} ]
		} ],
		onLoad:function(){
			$("#tzlink").focus();
		},
		onOk:function(e){
			var videoUrl = $("#tzlink").val();
			if(isEmpty(videoUrl) || !/^http:\/\//.test(videoUrl)){
				tmLoading("您输入的链接地址有误!",1);
				$("#tzlink").select();
				return false;
			}
			editor.insertHtml("<a href='"+videoUrl+"' class='tmui_link' target='_blank'>"+videoUrl+"</a>");
		}
	}
});

//onOk: /*function*/ ,
//onLoad: /*function*/,
//onShow: /*function*/,
//onHide: /*function*/,
//onCancel: /*function*/,
//resizable: /* none,width,height or both  */,
//contents: /*content definition, basically the UI of the dialog*/
