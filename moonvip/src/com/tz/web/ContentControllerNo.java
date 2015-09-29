package com.tz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.content.IContentDao;
import com.tz.dao.music.IMusicDao;
import com.tz.model.Content;
import com.tz.model.Music;
import com.tz.model.TmParams;

/**
 * 
 * IndexController 
 * 创建人:xuchengfei 
 * 时间：2015年6月3日-下午7:47:24
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/loadcontent")
public class ContentControllerNo extends BaseController{

	@Autowired
	private IContentDao contentDao;
	
	@Autowired
	private IMusicDao musicDao;
	
	/**
	 * 
	 * 更新页面
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:58 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/data",method=RequestMethod.POST)
	public List<Content>  listMap(TmParams params) {
		params.setPageSize(30);
		params.setIsDelete(0);//未删除
		params.setStatus(1);//已发布
		params.setOrderSql("c.create_time desc ,c.hits desc ");
		List<Content> contents = contentDao.loadContents(params);
		return contents;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/countData",method=RequestMethod.POST)
	public int countContent(TmParams params){
		params.setIsDelete(0);//未删除
		params.setStatus(1);//已发布
		int itemcount = contentDao.countContents(params);
		return itemcount;
	}
	
	@ResponseBody
	@RequestMapping(value="/music",method=RequestMethod.POST)
	public Music loadMusic(TmParams params){
		Music music = musicDao.loadMusics(params);
		return music;
	}
	
}