//得到焦点 
function editorFocus(id) {
	CKEDITOR.instances[id].focus();
}

//获取带有格式的内容--html
function getEditorHtml(editorId) {
	var editor = CKEDITOR.instances[editorId];
    return editor.getData();
};

//获取编辑器内容,纯文本 text
function getEditorText(editor) {
	var stemTxt = CKEDITOR.instances[editor].document.getBody().getText();
	return stemTxt;
};

//插入编辑器内容--覆盖
function setEditorContent(editorId,message) {
	var editor = CKEDITOR.instances[editorId];
	editor.setData(message);
};

//追加编辑器内容--追加
function  appendEditorContent(editorId,message){
	var editor = CKEDITOR.instances[editorId];
	editor.insertHtml(message);
};

function getUser(){
	var username = $("#user_headerpic").attr("title");
	var img = $("#user_headerpic").attr("src");
	return {
		username:username,
		img:img
	};
}


function initUserDialog(opts){
	$("#userDialogbox").css(opts).show().html("<div class='cnt'>"+
			"			<ul>"+
			"				<li><a href='#'><img src='http://imglf1.nosdn.127.net/img/K2Qvd1hzT2VLOHA4d01OcVdnbi82ZE1OWGZmcEc0M1c.jpg' width='126' height='98'/></a>"+
			"				<a href='#'><img src='http://imglf1.nosdn.127.net/img/K2Qvd1hzT2VLOHA4d01OcVdnbi82UjN0V1pJRk1LaWc.jpg' width='126' height='98'/></a>"+
			"				<a href='#'><img src='http://imglf1.nosdn.127.net/img/K2Qvd1hzT2VLOHA4d01OcVdnbi82VWh4d2hXS0UxdGg.jpg' width='126' height='98'/></a></li>"+
			"			</ul>"+
			"		</div>	"+
			"		<div class='info'>"+
			"			<div class='w-img2'>"+
			"				<a onclick='#' target='_blank' href='#'>"+
			"					<img src='http://img.ph.126.net/S4PokzYebA6xzCNf125_JA==/2633479882121850220.jpg' width='30' height='30'>"+
			"				</a>"+
			"			</div>"+
			"			<div class='bloginfo'> "+
			"				<a onclick='#'  target='_blank' href='#' class='name'>LOFTER官方博客</a>                                "+
			"				<div class='subinfo'>"+
			"					<a  href='http://i.lofter.com/' target='_blank'>文章 524</a>"+
			"					<a  style='display:none' href='#' target='_blank'>喜欢 </a>"+
			"					<a  style='display:none' href='#' target='_blank'>关注 </a>"+
			"				</div>                            "+
			"			</div>"+
			"			<div>"+
			"			<div class='followbtn'>                                    "+
			"				<a class='u-btn2 ftag' href='#'><span>+已关注</span></a>"+
			"				<a class='u-btn u-btn-1 ftag' style='display:none;' href='#'><b>+</b><span>关注</span></a>                               "+
			"				 "+
			"			</div>"+
			"		</div>"+
	"		<span class='icon'></span>");
	
	$("#userDialogbox").addClass(tz_animateIn(13)).mouseleave(function(){
		$(this).addClass(tz_animateOut(16)).hide(function(){
			$(this).removeClass("animated fadeInDownBig rotateOut");
		});
	});
};


$(function(){
	$(".tzui-tips,.tmui-tips").tmTip();
	var utimer = null;
	var outimer = null;
	$("body").on("mouseover",".tz_userinfo_xxx",function(e){
		$("#userDialogbox").removeClass("animated fadeInDownBig rotateOut").mouseover(function(){
			clearTimeout(outimer);
			clearTimeout(utimer);
		});
		clearTimeout(outimer);
		clearTimeout(utimer);
		var $this = $(this);
		utimer = setTimeout(function(){
			var left = $this.offset().left-80;
			var top = $this.offset().top-220;
			var opts = {left:left,top:top};
			initUserDialog(opts);
		},200);
		stopBubble(e);
	}).on("mouseleave",".tz_userinfo",function(){
		clearTimeout(outimer);
		outimer = setTimeout(function(){
			$("#userDialogbox").addClass(tz_animateOut(16)).hide(function(){
				$(this).removeClass("animated fadeInDownBig rotateOut");
			});
		},200);
	});
	
	
	if(window.localStorage){
		var lcolor = localStorage.getItem("color");
 		if(lcolor){
			var color = lcolor.split(",");
			if(color && color.length==2){
				if(document.getElementById("cssRule"))document.getElementById("cssRule").innerHTML +="#songlist li.selected{background:"+color[1]+";color:#999;transition:all 0.3s ease-in-out;}#songlist li:hover{background:"+color[1]+";color:#999;transition:all 0.3s ease-in-out;}#percent{height:2px;width:0%;background:linear-gradient(270deg,"+color[1]+",#999);position:relative;z-index:1}#percentBar{height:2px;position:relative;top:2px;margin:0 auto;background:"+color[1]+"} .tzui-dialog,.tzui-dialog .tzdialog_title,.tzui-dialog .tzdialog_panel{background:"+color[0]+"} .underline,.tzui-loading,.dataTable tr:hover{background:"+color[1]+"}";
			}
 		}
	}
});

/*更新点击数*/
var tzhlc = function(cid,type){
	$.tzAjax.request({url:basePath+"/content/hlc/"+cid+"/"+type,callback:function(data){
		switch(data){
			case 1: 
				if(type==1){
					$("#l"+type+"_"+cid).find(".iconfont").addClass("iconon");
				}else if(type==2){
					loading("推荐-1",3);
					tzhlcount(cid,type,1);
					$("#l"+type+"_"+cid).find(".iconfont").removeClass("iconon");
				}else if(type==3){
					loading("喜欢-1",3);
					tzhlcount(cid,type,1);
					$("#l"+type+"_"+cid).find(".iconfont").removeClass("iconon");
				}
				break;
			case 0: 
				if(type==1){
					tzhlcount(cid,type,0);
				}else if(type==2){
					loading("推荐+1",3);
					tzhlcount(cid,type,0);
				}else if(type==3){
					loading("喜欢+1",3);
					tzhlcount(cid,type,0);
				}
				$("#l"+type+"_"+cid).find(".iconfont").addClass("iconon");
				break;
		};
		
		getHcl(cid);
	}});
	
	getHcl(cid);
};

var getHcl = function(cid){
	$.tzAjax.request({limit:false,url:basePath+"/content/gethlc/"+cid,callback:function(data){
		if(data){
			var i=0,len = data.length;
			for(;i<len;i+=1){
				if(data[i].isDelete==0){
					$("#l"+data[i].type+"_"+cid).find(".iconfont").addClass("iconon");
				}else{
					$("#l"+data[i].type+"_"+cid).find(".iconfont").removeClass("iconon");
				}
			}
			getContent(cid);
		}
	}});
	getContent(cid)
};

var getContent = function(cid){
	$.tzAjax.request({limit:false,url:basePath+"/content/get/"+cid,callback:function(data){
		if(data){
			$("#tz_love_"+cid).text(data.loves);
			$("#tz_coll_"+cid).text(data.collections);
			$("#tz_comm_"+cid).text(data.comments);
			$("#tz_hits_"+cid).text(data.hits);
		}
	}});
};


function tzhlcount(cid,type,mark){
	if(type==1){
		var $hits = $("#tz_hits_"+cid);
		var text = $hits.text()*1;
		mark==0?$hits.text(text+1):$hits.text(text-1);
	}else if(type==3){
		var $love = $("#tz_love_"+cid);
		var text = $love.text()*1;
		mark==0?$love.text(text+1):$love.text(text-1);
	}else if(type==2){
		var $coll = $("#tz_coll_"+cid);
		var text = $coll.text()*1;
		mark==0?$coll.text(text+1):$coll.text(text-1);
	}
	
}

var tzContent = {
 	edit : function(){
 		var id = $("body").data("opid");
		window.location.href = basePath+"/content/edit/"+id;
	}	
};

var tzLogin = {
	init:function(){
		$.tzAjax.request({url:rootPath+"/login/ajaxLogin",callback:function(data){
			if(data){
				$('#headerbox').html("<div>"+
				"	<a href='"+rootPath+"/user' class='color-login fl'>"+
				"		<img src='"+rootPath+"/"+data.headerPic+"' id='user_headerpic' title='"+data.username+"' width='24' height='24' class='fl'/>"+
				"		<span class='fl tmui-ellipsis w70'>"+data.username+"</span>"+
				"	</a>"+
				"	<span class='color-login span fl'>/</span>	"+
				"	<a href='"+rootPath+"/loginout' class='color-login reg fl'>退出</a>"+
				"</div>");
			}else{
				$('#headerbox').html("<div>"+
				"	<a href='"+rootPath+"/login' class='color-login fl'>登陆</a>"+
				"	<span class='color-login span fl'>/</span>	"+
				"	<a href='"+rootPath+"/reg' class='color-login reg fl'>注册</a>"+
				"</div>");
			}
		}})
	},
	logout:function(){
		$.tzAjax.request({url:rootPath+"/login/logout",callback:function(data){
			if(data=="success"){
				top.window.location.href = rootPath+"/login";
			}
		}});
	}
};

var s_step =0;
function tzsliderbar(){
	
	$(".bar").off().mousedown(function(e){
		var $this = $(this);
		var y = e.clientY ;
		var t = $this.position().top ;
		var mark = $this.data("mark")||"true";
		var h =$this.parents(".sliderbox").height() - 20;
		var k =$this.parents(".sliderbox").find(".cnt").height();
		var c = k - h;
		$this.parents(".omark").show();
		if(c<0){
			$this.parents(".omark").hide();
			return;
		}
		tm_forbiddenSelect();
		$(document).mousemove(function(e){
			if(mark=="true"){
				var ny = e.clientY;
				var nt = ny -y+t ;
				if(nt<=0)nt = 0;
				if(nt>=h)nt = h;
				var p = nt / h;
				$this.css({top:nt});
				s_step = nt;
				$this.parents(".sliderbox").find(".cnt").css("top",c * p *-1);
			}
		}).mouseup(function(){
			mark = "false";
			$this.data("mark","true");
			tm_autoSelect();
		});
	}).trigger("mousedown");

	$(".cbar").off().mousedown(function(e){
		var $this = $(this);
		var y = e.clientY - $this.parents(".sliderbox").offset().top + $(window).scrollTop() - 20;
		var h =$this.parents(".sliderbox").height();
		var c =$this.parents(".sliderbox").find(".cnt").height()-h; 
		var p = y / h;
		$this.next().css({top:y});
		$this.parents(".sliderbox").find(".cnt").css("top",c * p *-1);
		s_step = y;
		tm_autoSelect();
	});
	
	
	/*
		在非firefox浏览器中，滚轮向上滚动返回的数值是120，向下滚动返回-120
		而在firefox浏览器中，滚轮向上滚动返回的数值是-3，向下滚动返回3
		代码部分如下，e.wheelDelta是判断是否为非firefox浏览器，e.detail为firefox浏览器
	*/
	var scrollFunc=function(e){ 
		e=e || window.event; 
		var value ;
		var h =$(".sliderbox").height();
		var c =$(".sliderbox").find(".cnt").height()-h; 
		if(e.wheelDelta){//IE/Opera/Chrome 
			value=e.wheelDelta; 
		}else if(e.detail){//Firefox 
			value=e.detail; 
		} 
		
		if(value==120 || value==-3){//向上
			s_step -=2;
		}

		if(value==-120 || value==3){//向下
			s_step +=2;
		}

		if(s_step<=0)s_step=0;
		if(s_step>=h)s_step=h0;
		$(".sliderbox").find(".bar").css("top",s_step);
		var p = s_step / h;
		$(".sliderbox").find(".cnt").css("top",c * p *-1);
	} 
	/*注册事件*/ 
	if(document.addEventListener){ 
		document.addEventListener('DOMMouseScroll',scrollFunc,false); 
	}//W3C 
	window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome 
};


function changeBar($this,p){
	var h =$this.parents(".sliderbox").height();
	var c =$this.parents(".sliderbox").find(".cnt").height();
	$this.next().css({top:c * p});
	$this.parents(".sliderbox").find(".cnt").css("top",c * p *-1);
	tm_autoSelect();
};


function tz_staticHtml(id){
	$.tzAjax.request({limit:false,url:rootPath+"/static/html",callback:function(data){
		loading((data!="error"?"静态化成功...":"静态化失败"),3);
		if(data!="error"){
			$("#tz_items_"+id).find(".link").attr("href",rootPath+"/"+data);
		}
	}},{"id":id});
};

function tz_staticindex(){
	$.tzAjax.request({limit:false,url:rootPath+"/static/index",callback:function(data){
		loading((data=="success"?"静态化成功...":"静态化失败"),3);
	}});
};

var tzBanner = {
	edit : function(cid){
		$.tzIframe({content:basePath+"/banner/list?cid="+cid,width:720,height:400,callback:function(iframe){
			if(iframe){
				iframe.tsaveEdit();
			}
		}});
	}	
};

function hoverLi(){

	$(".itembox").find("li").mousemove(function(e){
		var ev = e || window.event;
		var width = $(this).width();
		var juli = ev.clientX - $(this).offset().left;
		if( juli <= width /2){
			$(this).find("img").get(0).style.transform = "rotateY(-30deg)";
		}else{
			$(this).find("img").get(0).style.transform = "rotateY(30deg)";
		}
	}).mouseleave(function(){
		$(this).find("img").get(0).style.transform = "rotateY(0deg)";	
	});
};

$(function(){
	hoverLi();
});


