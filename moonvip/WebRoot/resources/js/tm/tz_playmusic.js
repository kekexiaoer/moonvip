var audio = tzAudio(function(timer,ftimer){
	document.getElementById("timer").innerHTML = ftimer;
	$(".period").addClass("r");
	$(".tz_cssloading").addClass("rp");
},function(mtimer,mmtimer,currentIndex){
	document.getElementById("stimer").innerHTML = mmtimer;
	var totalTime = this.duration;
	var percent = (mtimer / totalTime)*100;
	setProgress((percent.toFixed(0)));
	tm_changeLrc(mtimer);
	$("#tz_timer_"+currentIndex).text(mmtimer);
	changeBar($(".cbar"),(mtimer / totalTime));
},function(song){
	var lrctext = $("#tz_musiclrc_"+song.opid).text();
	$("#tz_loadimgbox,#tz_tupian").attr("src",song.img+"?d="+new Date().getTime());
	if(isEmpty(lrctext)){
		$(".gc-box").slideUp("slow",function(){
			tzsliderbar();
		});
	}else{
		setMusicLrc(lrctext,function(){
			$(".gc-box").slideDown("slow",function(){
				$(".cnt,.bar").css("top",0);
				tzsliderbar();
			});
		});
	}
},function(index){//播放完毕一首后
	$(".pause").hide();
	$(".play").show().eq(index).hide().next().show().parents("li").addClass("selected").siblings().removeClass("selected");
	tz_musichits($(".play").show().eq(index).data("opid"));
},"loop",3);

function loadMusicList(){
	$("#songlist").find("li").each(function(){
		var hrf = $(this).data("url");
		if(hrf){
			var opid = $(this).data("opid");
			var img = $(this).find(".img").attr("src");
			var name = $(this).find(".name").text();
			var url = dencryption(hrf,9);
			audio.add(name,rootPath+"/"+url,img,opid);
		}
	});
	bindProgress(audio);
	$("#songlist").find("li").eq(0).find(".play").trigger("click");
};

function tz_play(index,obj){
	var opid = $(obj).data("opid");
	$(".play").show();
	$(".pause").hide();
	$(obj).hide().next().show().parents("li").addClass("selected").siblings().removeClass("selected");
	audio.play(index);
	$(".period").addClass("r");
	tz_musichits(opid);
};

function tz_pause(obj){
	$(obj).hide().prev().show().parents("li").removeClass("selected").siblings().removeClass("selected");
	audio.stop();
	$(".period").removeClass("r");
	//$(".tz_cssloading").removeClass("rp");
};
loadMusicList();

function changeLrc(){
	$(".gc-box").stop(true,true).slideToggle("slow");
};

function tz_musichits(opid){
	$.tzAjax.request({url:basePath+"/music/updateHits",callback:function(data){
	}},{"id":opid});
}

function tz_loves(obj){
	var opid = $(obj).data("opid");
	$.tzAjax.request({url:basePath+"/music/updateLoves",callback:function(data){
		if(data=="success"){
			loading("收藏成功",4);
		}else{
			loading("已收藏",4);
		}
	}},{"id":opid});
}

