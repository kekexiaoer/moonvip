var musicDudioDom = document.getElementById("audiobox");
function playMusic(){
	//播放器对象
	var $container =  $("#container");
	$container.find(".play").off("click").on("click",function(e){
		var $this = $(this);
		//flyMusic($this);
		$container.find(".play").show();
		$container.find(".paused").hide();
		$container.find(".stage").removeClass("wave2 magictime swashIn");
		$this.parents(".stage").addClass("wave2 magictime swashIn").find(".play").hide();
		$this.parents(".stage").find(".paused").show();
		var opid = $this.data("opid");
	
		$.tzAjax.request({url:rootPath+"/loadcontent/music",callback:function(data){
			musicDudioDom.src = rootPath+"/"+data.linksrc;
			musicDudioDom.play();
			musicDudioDom.volume= 5/10;
			musicDudioDom.loop="loop";
			musicDudioDom.oncanplaythrough = function(){
				var $ttime = $this.closest(".box").find(".ttimer");
				$ttime.html(getFormatTimer(this.duration));
			};
			
			musicDudioDom.addEventListener("timeupdate",function(){
				$container.find(".stage").find(".percent").width("0%");
				$container.find(".stage").find(".timer").text("00:00");
				var $time = $this.closest(".box").find(".timer");
				var $percent = $this.closest(".box").find(".percent");
				$time.html(getFormatTimer(this.currentTime));
				var percent = (this.currentTime / this.duration)*100;
				$percent.width(percent+"%");
			},false);
			
			//播放点击数加1
			tzhlc(opid,1);
		}},{"contentId":opid});
		e.stopPropagation();
	});
	
	$container.find(".paused").on("click",function(){
		$(this).parents(".stage").find(".paused").hide();
		$(this).parents(".stage").find(".play").show();
		musicDudioDom.pause();
	});
};

function flyMusic($obj){
	var src = $obj.parents(".box").find("img").eq(0).attr("src");
	var offset = $('#cartbox').offset(), flyer = $("<img src='"+src+"' style='border-radius:50%;z-index:6' width='50' height='50'>");
	flyer.fly({
		start: {
			left: event.pageX,
			top: event.pageY
		},
		end: {
			left: offset.left+50,
			top: offset.top+50,
			width: 40,
			height: 40
		},
		onEnd:function(){
			$("#cartbox").find(".user_img img").attr("src",src);
			flyer.fadeOut("slow",function(){
				$(this).remove();
			});
		}
	});
}


function getFormatTimer(timer){
	if(!timer)return "00:00";
	var m = parseInt(timer / 60,10);
	var s = parseInt(timer % 60,10);
	return (m<10?("0"+m):m)+":"+(s<10?("0"+s):s);
};

$(function(){
	//定义列的宽度
	var width = 240;
	//间距
	var space = 20;
	//列实际宽度
	var owdith = width + space;
	//获取容器对象
	//js写法var containerDom = document.getElementById("container");
	var $container = $("#container");
	//loader对象
	//js写法var loaderDom = document.getElementById("loader");
	var $load = $("#loader");
	//总列数
	var cells = 0;
	//分页
	var pageNo = 0;
	var pageSize = 30;
	var itemcount = 0;
	//防止重复提交
	var mark = true;
	

	function getCells(){
		cells = Math.floor($(window).innerWidth() / owdith);
		if(cells < 3){
			cells = 3;
		}else if(cells >5){
			cells = 5;
		}
		$container.width(cells * owdith - space);
	};
	
	//初始化列数
	getCells();
	//记录坐标位置
	var arrLeft = [];
	var arrTop = [];
	for(var i=0;i<cells;i++){
		arrTop.push(0);
		arrLeft.push(i * owdith);
	};
	
	function loadCount(){
		$.ajax({
			type:"post",
			url:rootPath+"/loadcontent/countData",
			data:{"channelId":3},
			success:function(data){
				itemcount = data;
				loadData();
			}
		});
	};
	
	
	var ic = 0; 
	function loadData(){
		if(mark){
			mark = false;
			$load.show();
			$.tzAjax.request({
				url:rootPath+"/loadcontent/data",
				callback:function(data){
					if(!data)return;
					data.forEach(function(json,index){ 
						ic++;
						var $div =$("<div></div>");
						var minIndex = getMin();
						var bitheight = (width / json.width) * json.height;
						
						$div.addClass("stage magictime swashIn").stop(true,true).animate({
							width:width,
							height:bitheight+78,
							left:arrLeft[minIndex],
							top:arrTop[minIndex]
						},1000).html("<div class='box' style='height:"+(bitheight+78)+"px'>"+
								"<div class='itemsbox wave front'>"+
								"<a href='"+rootPath+"/"+json.staticLink+"' title='#'>"+
								"	<img src='"+json.img+"' width='"+(width)+"' height='"+bitheight+"' style='transform: rotateY(0deg);'>"+
								"</a>"+
								"<span class='mtip'>"+json.categoryName+"</span>"+
								"<div class='info'>"+
								"	<h1><a href='"+rootPath+"/"+json.staticLink+"' title='"+json.title+"'>"+json.title+"</a></h1>"+
								"	<p>"+
								"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0052;</i>&nbsp;"+json.loves+"</a>"+
								"		<a href='javascript:void(0);'><i class='iconfont'>&#xf01aa;</i>&nbsp;"+json.hits+"</a>"+
								"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0142;</i>&nbsp;"+(json.comments||0)+"</a>"+
								"		<a href='javascript:void(0);' class='play' data-opid='"+json.id+"'><i class='iconfont'>&#xe607;</i></a>"+
								"		<a href='javascript:void(0);' class='none paused'><i class='iconfont'>&#xe632;</i></a>"+
								"		<a href='javascript:void(0);'><span class='timer'>00:00</span>/<span class='ttimer'>00:00</span></a>"+
								"	</p><div class='percent'></div>"+
								"</div>"+
							"</div>"+
							"<div class='back' style='height:"+(bitheight+78)+"px'>"+
							"<a href='"+rootPath+"/"+json.staticLink+"' title='#'>"+
							"	<img src='"+json.img+"' width='"+(width)+"' height='"+bitheight+"' style='transform: rotateY(0deg);'>"+
							"</a>"+
							"<span class='mtip'>"+json.categoryName+"</span>"+
							"<div class='info'>"+
							"	<h1><a href='"+rootPath+"/"+json.staticLink+"' title='"+json.title+"'>"+json.title+"</a></h1>"+
							"	<p>"+
							"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0052;</i>&nbsp;"+json.loves+"</a>"+
							"		<a href='javascript:void(0);'><i class='iconfont'>&#xf01aa;</i>&nbsp;"+json.hits+"</a>"+
							"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0142;</i>&nbsp;"+(json.comments||0)+"</a>"+
							"		<a href='javascript:void(0);' class='play' data-opid='"+json.id+"'><i class='iconfont'>&#xe607;</i></a>"+
							"		<a href='javascript:void(0);' class='none paused'><i class='iconfont'>&#xe632;</i></a>"+
							"		<a href='javascript:void(0);'><span class='timer'>00:00</span>/<span class='ttimer'>00:00</a>"+
							"	</p><div class='percent'></div>"+
							"</div></div>"+
						"</div>");
						$container.find("h1.t").remove();
						$container.append($div);
						//确定下一次元素的top位置，然后在这里面找一个最短位置
						arrTop[minIndex] +=(bitheight+space+80);
						$(".mtip").each(function(){
							$(this).css("background",tzUtil.getRandomColor());
						});
						mark = true;
						$load.hide();
					});
					hoverImg();
					playMusic();
				}
				
			},{channelId:3,pageNo:pageNo * pageSize});
		}
	};

	loadCount();  
	//绑定窗口的滚动事件
	$(window).on("scroll",function(){
		var len = $container.find(".stage").length;
		if(itemcount <= len)return;
		var ih = $(window).scrollTop() + $(window).innerHeight();
		var minIndex = getMin();
		var top = arrTop[minIndex] + 50;
		if(ih > top && mark){
			pageNo++;
			loadData();
		}
	});
	
	//窗口改变事件
	$(window).on("resize",function(){
		var len = cells;//改变之前的列6
		getCells();
		if(len == cells){
			return;	
		}
		arrLeft = [];
		arrTop = [];
		for(var i=0;i<cells;i++){
			arrTop.push(0);
			arrLeft.push(i * owdith);
		};

		$container.find(".stage").each(function(){
			var min = getMin();
			$(this).stop(true,true).animate({
				left:arrLeft[min],
				top:arrTop[min]
			});
			arrTop[min] +=$(this).height() + space +100 ; 
		});
	});

	
	/*找到当前这个元素最短的那个top元素的坐标*/
	function getMin(){
		var val = arrTop[0];
		var index = 0;
		for(var i=1;i<arrTop.length;i++){
			if(arrTop[i] < val){
				val = arrTop[i];
				index = i;
			}
		}
		return index;
	};
	
	function hoverImg(){
		var $container = $("#container");
		$container.find(".stage").mousemove(function(e){
			var width = $(this).width()/2;
			var x =e.clientX - $(this).offset().left;
			if(x <=150){
				$(this).find(".box").get(0).style.transform = "rotateY(-180deg)";
			}else{
				$(this).find(".box").get(0).style.transform = "rotateY(180deg)";
			}
		}).mouseleave(function(){
			$(this).find(".box").get(0).style.transform = "rotateY(0deg)";
		});
	};
});


/*
document.getElementById("exlist").onclick = function(){
	$(".play_list").stop(true,true).slideToggle();
};

//展开
document.getElementById("expyes").onclick= function(){
	//获取音乐播放器的盒子
	var musicDoms = document.getElementsByClassName("music_box")[0];
	//音乐列表的盒子
	var listDom = document.getElementsByClassName("play_list")[0];
	//动画收起来
	move(musicDoms,{left:-541})
	move(listDom,{left:-541})
	//点击元素隐藏
	this.style.display = "none";
	//把对立面显示出来
	document.getElementById("expno").style.display = "block";
};
	
//收起
document.getElementById("expno").onclick= function(){
	//获取音乐播放器的盒子
	var musicDoms = document.getElementsByClassName("music_box")[0];
	//音乐列表的盒子
	var listDom = document.getElementsByClassName("play_list")[0];
	//动画收起来
	move(musicDoms,{left:0})
	move(listDom,{left:0})
	//点击元素隐藏
	this.style.display = "none";
	//把对立面显示出来
	document.getElementById("expyes").style.display = "block";
}





function move(obj,json,callback){ 
	clearInterval(obj.timer);//防止用户重复执行动画
	obj.timer =  setInterval(function(){
		//标识判断是否已经循环到最后一个了
		var mark = true;
		for(var attr in json){
			attr = attr.toLowerCase();
			var val = "";
			if(attr=="opacity"){
				val = Math.round(css( obj, 'opacity' ) * 100);
			}else{
				val = parseInt(css(obj,attr));//val实时在变化
			}

			//计算速度 (json[k]-val) 距离 除以8计算，这段距离平均要走多少步才走完
			var speed = (json[attr]-val) / 8;//没30毫秒所走的距离.
			//取最大值，小于0去最小值 .val可能会出现小数
			speed = speed >0 ? Math.ceil(speed) : Math.floor(speed);
			if(val != json[attr]){
				mark = false;
				if(attr=="opacity"){
					obj.style.opacity = (val + speed) / 100;
					obj.style.filter = 'alpha(opacity='+ (val + speed) +')';
				}else{
					obj.style[attr] = val+speed+"px";
				}
			}
		}

		
		//回调函数的处理
		if(mark){
			clearInterval(obj.timer);
			if(callback)callback.call(obj);
		}
	},30);
};

function css(obj,attr){
	var v = obj.currentStyle ?obj.currentStyle[attr]:
	window.getComputedStyle(obj,null)[attr];
	if(v=="auto")v = 0;
	return v;
};*/