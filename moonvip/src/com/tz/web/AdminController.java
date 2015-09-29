package com.tz.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tz.dao.channel.IChannelDao;
import com.tz.dao.content.IContentDao;
import com.tz.dao.user.IUserDao;
import com.tz.model.Channel;
import com.tz.model.Content;
import com.tz.model.TmParams;
import com.tz.model.User;

/**
 * 
 * IndexController 创建人:xuchengfei 时间：2015年6月3日-下午7:47:24
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private IContentDao contentDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IChannelDao channelDao;

	@RequestMapping("/list")
	public ModelAndView index(TmParams params) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/list");
		return view;
	}
	

	@RequestMapping(value = "/listTemplate", method = RequestMethod.POST)
	public String index(TmParams params, HttpServletRequest request) {
		//if(getUser()!=null && getUser().getRole()==0)params.setUserId(getUserId());
		params.setOrderSql("c.is_delete asc,c.status asc,c.create_time desc");
		int count = contentDao.countContents(params);
		List<Content> contents = contentDao.findContents(params);
		request.setAttribute("contents", contents);
		request.setAttribute("totalCount", count);
		return "admin/list";
	}

	@RequestMapping("/userlist")
	public ModelAndView userlist(TmParams params) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/userlist");
		return view;
	}

	@RequestMapping(value = "/user/listTemplate", method = RequestMethod.POST)
	public String usertemplate(TmParams params, HttpServletRequest request) {
		params.setOrderSql("create_time desc");
		int count = userDao.countUsers(params);
		List<User> users = userDao.findUsers(params);
		request.setAttribute("users", users);
		request.setAttribute("totalCount", count);
		return "admin/userlist";
	}

	@ResponseBody
	@RequestMapping(value = "/user/updateInfo", method = RequestMethod.POST)
	public String updatepwd(User user) {
		// user.setId(getUserId());
		user.setUpdateTime(new Date());
		userDao.updateUser(user);
		return "success";
	}

	@RequestMapping("/channellist")
	public ModelAndView channellist(TmParams params) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/channellist");
		return view;
	}

	@RequestMapping(value = "/channel/listTemplate", method = RequestMethod.POST)
	public String channeltemplate(TmParams params, HttpServletRequest request) {
		params.setOrderSql("sort asc");
		int count = channelDao.countChannels(params);
		List<Channel> channels = channelDao.findChannels(params);
		request.setAttribute("channels", channels);
		request.setAttribute("totalCount", count);
		return "admin/channellist";
	}

	@ResponseBody
	@RequestMapping(value = "/channel/updateInfo", method = RequestMethod.POST)
	public String updatechannel(Channel channel) {
		channel.setId(getUserId());
		channel.setUpdateTime(new Date());
		channelDao.updateChannel(channel);
		return "success";
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("show");
		return modelAndView;
	}

	@RequestMapping("/add/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		TmParams params = new TmParams();
		params.setId(id);
		Content content = contentDao.getContent(params);
		if(content.getUserId()==getUserId() || getUser().getRole()==1){
			modelAndView.addObject("content", content);
			modelAndView.setViewName("show");
		}else{
			modelAndView.setViewName("redirect:/300.html");
		}
		return modelAndView;
	}
}