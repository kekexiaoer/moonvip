$(function(){
	var cid = $("#contentId").val();
	/*上传音乐*/
	$.tzUpload({
		url:basePath+"/upload/file",
		targetId:"uploadBtn2",
		size:"100 MB",
		data:{"selfDir":"music/"+cid},
		type:"*.mp3",
		single:true,
		callback:function(data){
			var jdata = eval("("+data+")");
			var cid = $("#contentId").val();
			var len = $("#musicbox").find("li").length * 1 + 1;
			var params = {
				"name":jdata.name,
				"sizeString":jdata.sizeString,
				"timer":jdata.timer,
				"sort":jdata.sort,
				"author":jdata.author,
				"specialName":jdata.title,
				"linksrc":jdata.url,
				"contentId":cid,
				"sort":len
			};
			
			$.tzAjax.request({model:"music",method:"save",limit:false,callback:function(data){
				$("#tzmusicbox").prepend("<div class='mitems music_items clearfix' id='tz_music_items_"+data.id+"'  data-url='"+jdata.url+"' data-opid='"+data.id+"'>"+
				"						<a href='javascript:void(0);' class='fl del' data-opid='"+data.id+"' onclick='tz_delete_music(this)'><i class='iconfont'>&#xf00b3;</i></a>"+
				"						<div class='imgup fl'>"+
				"							 <span class='up'><a href='javascript:void(0);' onclick='openBrowse(this,0)' class='upbtns'><i class='iconfont'>&#xf0113;</i></a></span>"+
				"							 <p><img src='"+rootPath+"/images/logo.png'   class='img' height='150' width='150'/> </p>"+
				"							 <p>"+
				"							 	是否发布：<label><input type='radio' name='status"+data.id+"' checked='checked' value='1'>是</label>"+
				"								<label><input type='radio' name='status"+data.id+"' value='0'>否</label>"+
				"							</p>"+
				"						</div>"+
				"						<div class='info fl '>"+
				"							<ul>"+
				"								<li class='mt5'>"+
				"									歌名：<input class='inp tmui-tips' style='width:176px;' type='text' name='name'  value='"+jdata.name+"' placeholder='歌名'/>"+
				"									歌手：<input class='inp tmui-tips' style='width:192px;' type='text' name='author' value='"+jdata.author+"' placeholder='演唱者'/>"+
				"									排序：<input class='inp tmui-tips' style='width:176px;' type='text' name='sort' value='1' placeholder='排序号'/>"+
				"								</li>"+
				"								<li class='mt5'>"+
				"									专辑：<input class='inp tmui-tips' style='width:176px;' type='text' name='atitle' value='"+jdata.title+"' placeholder='专辑'/>"+
				"									时长：<input class='inp tmui-tips' style='width:192px;' type='text' name='timer'  value='"+jdata.timer+"' placeholder='时长'/>"+
				"									点击：<input class='inp tmui-tips' style='width:176px;' type='text' name='hits' value='0' placeholder='点击数'/>"+
				"								</li>"+
				"								<li class='mt5'><span style='vertical-align: top;'>描述：</span><textarea class='tarea tzui-tips'  name='cdescription' placeholder='描述'></textarea></li>"+
				"								<li class='mt5'><span style='vertical-align: top;'>歌词：</span><textarea class='tarea tzui-tips'  name='lrc' placeholder='歌词'></textarea></li>"+
				"							</ul>"+
				"							<p class='btns'>"+
				"								<a href='javascript:void(0);' class='edit fr' data-opid='"+data.id+"'  data-url='"+jdata.url+"' onclick='tz_edit_music(this)'><i class='iconfont'>&#xf014f;</i></a>"+
				"								<a href='javascript:void(0);' onclick='openBrowse(this,1)' data-url='"+jdata.url+"' class='upbtns fr'><i class='iconfont'>&#xf0113;</i></a>"+
				"							</p>"+
				"						</div>"+
				"					</div>");
				
				
			}},params);
		}
	});
})

/*上传音乐和封面图片*/
var $fileObj = null;
var mark = 0;
function openBrowse(obj,m){ 
	$fileObj = $(obj);
	mark = m;
	var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
	if(ie){ 
		document.getElementById("file").click(); 
		document.getElementById("filename").value=document.getElementById("file").value;
	}else{
		var a=document.createEvent("MouseEvents");//FF的处理 
		a.initEvent("click", true, true);  
		document.getElementById("file").dispatchEvent(a); 
	} 
} 


// 	Attributes	设置或返回文件或文件夹的属性
// 	DateCreated	返回指定文件或文件夹的创建时间
// 	DateLastAccessed	返回最近访问文件或文件夹的创建时间
// 	DateLastModified	返回最后修改指定文件和文件夹的日期和日期
// 	Drive	返回指定文件或文件夹所在的驱动器的驱动器号
// 	Name	设置或返回文件或文件夹的名称
// 	ParentFolder	返回指定文件或文件夹的父文件夹对象
// 	Path	返回指定文件或文件夹或驱动器的路径
// 	ShortName	返回短名称
// 	ShortPath	返回短路径
// 	Size	对于文件,以字节为单位返回指定文件的大小.
// 	对于文件夹,以字节为单位返回文件夹中包含的所有子文件夹中的所有文件和子文件夹的大小
// 	Type	返回文件或文件夹的信息.
function uploadFile() {
    var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
// 	    var type = fileObj.type;
// 	    if(type!="image/jpeg"){
// 	    	loading("请选择图片文件!",2);
// 	    	return;
// 	    }
    var cid = $("#contentId").val();
    var $img = $fileObj.parents(".music_items").find(".img");
    // FormData 对象
    var form = new FormData();
    form.append("doc", fileObj);                           // 文件对象
    if(mark==0){
	    form.append("selfDir","music/"+cid);
	    form.append("oldName", $img.data("src")||"");
    }else{
	    form.append("oldName",$fileObj.data("url")||"");
    }
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", basePath+"/upload/file", true);
    xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){//状态4和200代表和服务器端交互成功
			var data = xhr.responseText.trim();
			var jdata = eval("("+data+")");
			if(mark==0){
				$img.data("src",jdata.url).attr("src",rootPath+"/"+jdata.url+"?d="+new Date().getTime());
				$fileObj.parents(".music_items").find(".edit").trigger("click");
			}
			loading("修改成功!!",4);
		}
	};
    xhr.upload.addEventListener("progress", progressFunction, false);
    xhr.send(form);
};


 function progressFunction(evt) {
// 	     var progressBar = document.getElementById("progressBar");
// 	     var percentageDiv = document.getElementById("percentage");
// 	     if (evt.lengthComputable) {
// 	         progressBar.max = evt.total;
// 	         progressBar.value = evt.loaded;
// 	         percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
// 	     }
 } ; 
 
 
 function tz_edit_music(obj,isDelete){
		var opid = $(obj).data("opid");
		var $op = $("#tz_music_items_"+opid);
		var cid = $("#contentId").val();
		var name = $op.find("input[name='name']").val();
		var title = $op.find("input[name='atitle']").val();
		var author = $op.find("input[name='author']").val();
		var timer = $op.find("input[name='timer']").val();
		var img = $op.find(".img").data("src");
		var description = $op.find("textarea[name='cdescription']").val();
		var lrc = $op.find("textarea[name='lrc']").val();
		var sort = $op.find("input[name='sort']").val();
		var hits = $op.find("input[name='hits']").val();
		var status = $op.find("input[name='status"+opid+"']:checked").val();
		var params = {
			"id":opid,	
			"name":name,
			"timer":timer,
			"sort":sort,
			"hits":hits,
			"author":author,
			"specialName":title,
			"contentId":cid,
			"img":img,
			"isDelete":isDelete,
			"status":status,
			"description":description,
			"lrc":lrc,
			"sort":sort
		};
		$.tzAjax.request({model:"music",method:"update",callback:function(data){
			loading("修改成功!!",4);
		}},params);
	}
	
	function tz_delete_music(obj){
		$.tzConfirm({content:"您确定删除吗?",callback:function(ok){
			if(ok){
				var opid = $(obj).data("opid");
				$("#tz_music_items_"+opid).fadeOut("slow",function(){
					$(this).remove();
				});
				tz_edit_music($(obj).prev(),1);
			}
		}});
	}