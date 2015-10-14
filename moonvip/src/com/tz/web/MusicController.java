/**
 * Tm系统平台
 * moonvip
 * com.tz.web
 * MusicController.java
 * 创建人:xuchengfei 
 * 时间：2015年6月23日-下午6:51:37 
 * 2015Tm公司-版权所有
 */
package com.tz.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.music.IMusicDao;
import com.tz.dao.music.IMusicDetailDao;
import com.tz.model.Music;
import com.tz.model.MusicDetail;
import com.tz.model.TmParams;

/**
 * 
 * MusicController
 * 创建人:xuchengfei 
 * 时间：2015年6月23日-下午6:51:37 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/music")
public class MusicController extends BaseController{

	@Autowired
	private IMusicDao musicDao;
	
	@Autowired
	private IMusicDetailDao musicDetailDao;
	
	/**
	 * 保存音乐
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月23日-下午6:58:59 
	 * @param music
	 * @return Music
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Music save(Music music) {
		music.setUserId(getUserId());
		music.setIsDelete(0);
		music.setStatus(1);
		music.setHits(0);
		musicDao.saveMusic(music);
		return music;
	}
	
	
	/**
	 * 更新音乐
	 * com.tz.web 
	 * 方法名：update
	 * 创建人：xuchengfei 
	 * 时间：2015年6月23日-下午6:59:09 
	 * @param music
	 * @return Music
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Music update(Music music) {
		music.setUpdateTime(new Date());//设置更新时间
		musicDao.updateMusic(music);
		return music;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateHits",method=RequestMethod.POST)
	public Music updateHits(Music music) {
		music.setUpdateTime(new Date());//设置更新时间
		musicDao.updateHits(music);
		return music;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateLoves",method=RequestMethod.POST)
	public String updateLoves(Music music) {
		TmParams params = new TmParams();
		params.setMusicId(music.getId());
		params.setUserId(getUserId());
		params.setType(1);	
		int count = musicDetailDao.countDetail(params);
		if(count==0){
			music.setUpdateTime(new Date());//设置更新时间
			musicDao.updateLoves(music);
			MusicDetail musicDetail = new MusicDetail();
			musicDetail.setMusicId(music.getId());
			musicDetail.setUserId(getUserId());
			musicDetail.setType(1);
			musicDetail.setIsDelete(0);
			musicDetailDao.saveDetail(musicDetail);
			return "success";
		}else{
			return "fail";
		}
	}
}
