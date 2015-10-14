$(function(){
	//下一页 append是往元素后面追加
	$(".next").click(function(){
		var $silderbox= $("#silderbox");
		var width = $silderbox.find("li").eq(0).width();
		$silderbox.stop(true,true).animate({left:width*-1},function(){
			var $first = $(this).children().first();
			var index = $first.next().attr("index");	
			$(this).css("left",0).append($first);
			$(".b-yd").find("li").eq(index).addClass("selected").siblings().removeClass("selected");
		});
	}).mouseover(function(){
		clearInterval(timer);
	}).mouseout(function(){
		autoPlay();
	});

	//上一页 prepend在往元素的前面追加
	$(".prev").click(function(){
		//拿到容器的对象
		var $silderbox= $("#silderbox");
		//这个获取元素的宽度
		var width = $silderbox.find("li").eq(0).width();
		//获取最后一个元素
		var $last = $silderbox.find("li").last();
		var index = $last.attr("index");	
		/*给元素加上样式-640 ,当调用prepend方法的时候，最后一个元素就是第一个元素了*/
		$silderbox.css("left",width*-1).prepend($last);
		/*把元素又回归到0状态*/
		$silderbox.stop(true,true).animate({left:0});
		$(".b-yd").find("li").eq(index).addClass("selected").siblings().removeClass("selected");
	}).mouseover(function(){
		clearInterval(timer);
	}).mouseout(function(){
		autoPlay();
	});
	
	
	
	$(".b-yd").find("li").click(function(){
		var $silderbox= $("#silderbox");
		var index = $(this).index();
		var $last = $silderbox.find("li[index='"+index+"']");
		var width = $last.width();
		$silderbox.css("left",width*-1).prepend($last.nextAll()).prepend($last);
		$silderbox.stop(true,true).animate({left:0});
		$(this).addClass("selected").siblings().removeClass("selected");
	}).mouseover(function(){
		clearInterval(timer);
	}).mouseout(function(){
		autoPlay();
	});
	
	function index(){
		$("#silderbox").find("li").each(function(i){
			$(this).attr("index",i);
		})
	};
	
	var timer = null;
	//调用
	autoPlay();
	//自动播放
	function autoPlay(){
		timer = setInterval(function(){$(".next").trigger("click");},2000);
	};
});	