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
					if(isEmpty(data)){
						$container.find("h1.t").html("Sorry...   数据好像没有找到!!!");
						return;
					}
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
						},1000).html("<div class='box'>"+
								"<div class='itemsbox wave front'>"+
								"<a href='"+rootPath+"/"+json.staticLink+"' title='#'>"+
								"	<img src='"+json.img+"' width='"+(width)+"' height='"+bitheight+"' style='transform: rotateY(0deg);'>"+
								"</a>"+
								"<span class='mtip'>"+json.channelName+"</span>"+
								"<div class='info'>"+
								"	<h1><a href='"+rootPath+"/"+json.staticLink+"' class='tmui-ellipsis w220' title='"+json.title+"'>"+json.title+"</a></h1>"+
								"	<p>"+
								"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0052;</i>&nbsp;"+json.loves+"</a>"+
								"		<a href='javascript:void(0);'><i class='iconfont'>&#xf01aa;</i>&nbsp;"+json.hits+"</a>"+
								"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0142;</i>&nbsp;"+(json.comments||0)+"</a>"+
								"	</p>"+
								"</div>"+
							"</div>"+
							"<div class='back'>"+
							"<a href='"+rootPath+"/"+json.staticLink+"' title='#'>"+
							"	<img src='"+json.img+"' width='"+(width)+"' height='"+bitheight+"' style='transform: rotateY(0deg);'>"+
							"</a>"+
							"<span class='mtip'>"+json.channelName+"</span>"+
							"<div class='info'>"+
							"	<h1><a href='"+rootPath+"/"+json.staticLink+"' class='tmui-ellipsis w240' title='"+json.title+"'>"+json.title+"</a></h1>"+
							"	<p>"+
							"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0052;</i>&nbsp;"+json.loves+"</a>"+
							"		<a href='javascript:void(0);'><i class='iconfont'>&#xf01aa;</i>&nbsp;"+json.hits+"</a>"+
							"		<a href='javascript:void(0);'><i class='iconfont'>&#xf0142;</i>&nbsp;"+(json.comments||0)+"</a>"+
							"	</p>"+
							"</div></div>"+
						"</div>");
						$container.find("h1.t").remove();
						$container.append($div);
						//确定下一次元素的top位置，然后在这里面找一个最短位置
						arrTop[minIndex] +=(bitheight+space+78);
						$(".mtip").each(function(){
							$(this).css("background",tzUtil.getRandomColor());
						});
						mark = true;
						$load.hide();
					});
				}
				
			},{channelId:$("body").data("opid"),pageNo:pageNo * pageSize});
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