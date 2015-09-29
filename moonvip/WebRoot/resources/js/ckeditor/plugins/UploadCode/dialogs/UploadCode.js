CKEDITOR.dialog.add("UploadCode",function(editor) {
	var $ck = this;
	$.tzIframe({content:basePath+"/tool/insertcode",width:920,height:460,callback:function(iframe,$dialog,opts){
		if(iframe){
			iframe.tz_click(function(code){
				appendEditorContent("p_desc",code);
				$dialog.next().remove();
				tzUtil.animates($dialog,opts.animate);
			});	
		}
	}});
	$(".cke_dialog_background_cover").remove();
});

