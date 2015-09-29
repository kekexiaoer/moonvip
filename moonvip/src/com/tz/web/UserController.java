package com.tz.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.user.IUserDao;
import com.tz.model.TmParams;
import com.tz.model.User;
import com.tz.util.TmStringUtils;

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
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private IUserDao userDao;
	
	@RequestMapping("/{id}")
	public String index(@PathVariable("id")Integer id){
		return "/user/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String index(User user){
		user.setId(getUserId());
		userDao.updateUser(user);
		if(TmStringUtils.isNotEmpty(user.getUsername()))getUser().setUsername(user.getUsername());
		if(TmStringUtils.isNotEmpty(user.getHeaderPic()))getUser().setHeaderPic(user.getHeaderPic());
		if(TmStringUtils.isNotEmpty(user.getSign()))getUser().setSign(user.getSign());
		if(user.getJob()!=null)getUser().setJob(user.getJob());
		if(TmStringUtils.isNotEmpty(user.getCompany()))getUser().setCompany(user.getCompany());
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validate(User user){
		TmParams params = new TmParams();
		params.setId(getUserId());
		params.setPassword(TmStringUtils.md5Base64("keke_"+user.getPassword()));
		User user2 = userDao.getUser(params);
		return user2==null?"fail":"success";
	}
	
	@ResponseBody
	@RequestMapping(value="/updatepwd",method=RequestMethod.POST)
	public String updatepwd(User user){
		user.setId(getUserId());
		user.setUpdateTime(new Date());
		user.setPassword(TmStringUtils.md5Base64("keke_"+user.getPassword()));
		userDao.updateUser(user);
		return "success";
	}
}