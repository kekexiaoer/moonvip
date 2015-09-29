var htmlCodeMirror,cssCodeMirror,jsCodeMirror = null;
window.onload = function(){
	jsCodeMirror = CodeMirror.fromTextArea(document.getElementById("jseditor"),{
		modes: "javascript",
		tabSize:2,/*按下tab键盘的空格数*/
		indentWithTabs: true,
		smartIndent: true,
		lineNumbers: true,/*是否显示行号*/
		matchBrackets : false,
		theme:"ambiance",/*设置皮肤*/
		firstLineNumber:1,/*行号从什么位置开始*/
		autofocus: true,/*自动获取焦点*/
		showCursorWhenSelecting:true,//是否会全选的时候绘制光标
		readOnly:false/*是否禁止输入*/
	});
	cssCodeMirror = CodeMirror.fromTextArea(document.getElementById("csseditor"),{mode: "css",lineNumbers:true,theme:"ambiance"/*设置皮肤*/});
	htmlCodeMirror = CodeMirror.fromTextArea(document.getElementById("htmleditor"),{mode: "htmlmixed",lineNumbers:true,theme:"ambiance"/*设置皮肤*/});
	var wh = this.innerHeight;
	$("#descbox,.CodeMirror,.CodeMirror-gutters").height(wh);
	setEditor();
	this.addEventListener("resize",function(){
		var wh = this.innerHeight-36;
		$("#descbox,.CodeMirror,.CodeMirror-gutters").height(wh);
		var cleftDom = document.getElementById("cleft");
		var crightDom = document.getElementById("cright");
		var tempboxDom = document.getElementById("tempbox");
		var sdom = document.getElementById("c_sliderbar");
		crightDom.style.width = "36%";
		sdom.style.left = "36%";
		tempboxDom.style.marginLeft = "36%";
		tempboxDom.style.width = "100%";
		cleftDom.style.width = "64%";
		setEditor();
	});

	//绑定事件 change改变的时候 
	htmlCodeMirror.on("change",function(){
		setEditor();
		setEditorData();
	});
	cssCodeMirror.on("change",function(){
		setEditor();
		setEditorData();
	});
	
	jsCodeMirror.on("change",function(){
		setEditor();
		setEditorData();
	});
	
	/*if(window.localStorage){
		var htmlData = localStorage.getItem("html_editor_data");
		var jsData = localStorage.getItem("js_editor_data");
		var cssData = localStorage.getItem("css_editor_data");
		htmlCodeMirror.setValue(htmlData);
		jsCodeMirror.setValue(jsData);
		cssCodeMirror.setValue(cssData);
	};*/
	(function(){
	var cleftDom = document.getElementById("cleft");
	var crightDom = document.getElementById("cright");
	var tempboxDom = document.getElementById("tempbox");
	var sdom = document.getElementById("c_sliderbar");
	var sm = false;
	sdom.addEventListener("mousedown",function(e){
		tm_forbiddenSelect();
		sm = true;
		var ww = window.innerWidth;
		document.addEventListener("mousemove",function(e){
			if(sm){
				var nx = e.clientX;
				if(nx <=380)nx = 380;
				if(nx >= ww-100)nx = ww-100;
				sdom.style.left = nx+"px";
				var p = nx / ww;
				var np = 1-p;
				crightDom.style.width = (p * 100) +"%";
				cleftDom.style.width = (np * 100) +"%";
				tempboxDom.style.width = (np * 100) +"%";
				tempboxDom.style.marginLeft = (p * 100) +"%";
			}
		},false);
		sdom.addEventListener("mouseup",function(e){
			sm = false;
		},false);
		document.addEventListener("mouseup",function(e){
				sm = false;
			},false);
		},false);
	})();
	
	
	var opid = $("#editbox").data("opid");
	if(isNotEmpty(opid)){
		document.onkeydown = function(e){  
			e = window.event || e;  
			var keycode = e.keyCode || e.which;  
			if(e.ctrlKey &&  keycode ==83 ){  
				setEditor();
				$("#editbox").trigger("click");
				e.preventDefault();  
			}  
		};
	}
	
	if(window.localStorage){
 		var lcolor = localStorage.getItem("color");
 		if(lcolor){
			var color =lcolor.split(",");
			if(color){
				$(".changebg").css("background",color[0]);
				$(".cbg").css("background",color[1]);
			}
 		}
	}
};

function setEditor(){
	var previewDom = window.frames['preview'];
	previewDom.document.open();
	previewDom.document.write(htmlCodeMirror.getValue());
	previewDom.document.write("<style>"+cssCodeMirror.getValue()+"<\/style>");
	previewDom.document.write("<script>"+jsCodeMirror.getValue()+"<\/script>");
	previewDom.document.close();
};


function tzclickme(id,callback){
	document.getElementById(id+"click").addEventListener("click",callback,false);
};

function siblings(dom){
	var parents = dom.parentElement;
	var childs = parents.children;
	var adoms = Array.prototype.slice.call(childs);
	var arr = [];
	adoms.forEach(function(edom){
		if(dom !== edom){
			arr.push(edom);
		}
	});
	return arr;
};

tzclickme("html",function(){
	this.className = "active";
	siblings(this).forEach(function(dom){
		dom.className = "";
	});
	var hdom = document.getElementById("htmlbox");
	$(hdom).stop(true,true).slideDown("slow").siblings().slideUp("slow");
});

tzclickme("desc",function(){
	this.className = "active";
	siblings(this).forEach(function(dom){
		dom.className = "";
	});
	var hdom = document.getElementById("descbox");
	$(hdom).stop(true,true).slideDown("slow").siblings().slideUp("slow");
});

tzclickme("css",function(){
	this.className = "active";
	siblings(this).forEach(function(dom){
		dom.className = "";
	});
	var hdom = document.getElementById("cssbox");
	$(hdom).stop(true,true).slideDown("slow").siblings().slideUp("slow");
});

tzclickme("js",function(){
	this.className = "active";
	siblings(this).forEach(function(dom){
		dom.className = "";
	});
	var hdom = document.getElementById("jsbox");
	$(hdom).stop(true,true).slideDown("slow").siblings().slideUp("slow");
});

$("#editbox").click(function(){
	var htmlCode = htmlCodeMirror.getValue();
	var jsCode = jsCodeMirror.getValue();
	var cssCode = cssCodeMirror.getValue();
	var id = $(this).data("opid");
	var params = {"htmlCode":htmlCode,"cssCode":cssCode,"jsCode":jsCode};
	$.tzAjax.request({url:basePath+"/content/codeUpdate/"+id,callback:function(data){
		if(data){
			loading("编辑成功...",4);
		}
	}},params);
});

function setEditorData(){
	var htmlSource = htmlCodeMirror.getValue();
	var jsSource = jsCodeMirror.getValue();
	var cssSource = cssCodeMirror.getValue();
	if(window.localStorage){
		localStorage.setItem("html_editor_data",htmlSource);
		localStorage.setItem("js_editor_data",jsSource);
		localStorage.setItem("css_editor_data",cssSource);
	}
};

function noRight(doc){  
//frames的集合  
    var fs = doc.frames;  
    for(var i=0;i<fs.length;i++){  
        var f = fs[i];  
        var d = f.document;  
        var b = d.body;  
        b.oncontextmenu=bodyNoRight;  
        noRight(d);  
    }  
} ;