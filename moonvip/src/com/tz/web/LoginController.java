package com.tz.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.core.TmConstant;
import com.tz.dao.user.IUserDao;
import com.tz.model.TmParams;
import com.tz.model.User;
import com.tz.util.TmStringUtils;
import com.tz.util.email.TmEmailTemplate;

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
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Autowired
	private IUserDao userDao;
	
	@ResponseBody
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String ajaxLogin(HttpServletRequest request){
		contextProvider.logout();
		return "success";
	}
	
	@RequestMapping(value="/loginSuccess")
	public String loginSuccess(HttpServletRequest request){
		String mark = request.getParameter("mark");
		request.setAttribute("mark", mark);
		return "loginSuccess";
	}
	
	/**
	 * 登陆方法
	 * com.tz.web 
	 * 方法名：login
	 * 创建人：xuchengfei 
	 * 时间：2015年6月28日-下午11:09:54 
	 * @param params
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/logined",method=RequestMethod.POST)
	public String login(TmParams params) {
		if(params!=null){
			if(TmStringUtils.isEmpty(params.getAccount()))return "account_null";
			if(TmStringUtils.isEmpty(params.getPassword()))return "pwd_null";
			params.setIsDelete(0);
			String saltPwd = TmStringUtils.md5Base64("keke_"+params.getPassword());
			params.setPassword(saltPwd);
			User user = userDao.getUser(params);
			if(user!=null){
				if(user.getActive()==0){
					return "active";
				}
				contextProvider.setSessionAttribute(TmConstant.SESSION_USER_KEY, user);
				contextProvider.setSessionAttribute(TmConstant.SESSION_USER_KEY_ID, user.getId());
				contextProvider.setSessionAttribute(TmConstant.SESSION_USER_KEY_USERNAME, user.getUsername());
				return user.getUsername()+"#"+user.getHeaderPic();
			}else{
				return "fail";
			}
		}else{
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	public String reg(User user) {
		if(user!=null){
			try {
				if(TmStringUtils.isEmpty(user.getAccount()))return "account_null";
				if(TmStringUtils.isEmpty(user.getPassword()))return "pwd_null";
				if(TmStringUtils.isEmpty(user.getUsername()))return "username_null";
				if(TmStringUtils.isEmpty(user.getCode()))return "code_null";
				String code = (String)contextProvider.getSessionAttribute(user.getAccount());
				if(TmStringUtils.isNotEmpty(code) && !code.equalsIgnoreCase(user.getCode()))return "error_code";
				TmParams params = new TmParams();
				params.setAccount(user.getAccount());
				User user2 = userDao.getUser(params);
				if(user2==null){	
					user.setIsDelete(0);
					String saltPwd = TmStringUtils.md5Base64("keke_"+user.getPassword());
					user.setPassword(saltPwd);
					user.setRole(0);
					user.setHeaderPic("images/header.png");
					user.setActive(1);
					userDao.saveUser(user);
					contextProvider.removeAttribute(user.getAccount());//删除邮箱注册码
					contextProvider.setSessionAttribute(TmConstant.SESSION_USER_KEY, user);
					contextProvider.setSessionAttribute(TmConstant.SESSION_USER_KEY_ID, user.getId());
					contextProvider.setSessionAttribute(TmConstant.SESSION_USER_KEY_USERNAME, user.getUsername());
					return "success";
				}else{
					return "exist";
				}
			} catch (Exception e) {
				return "fail";
			}
		}else{
			return "fail";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/code",method=RequestMethod.POST)
	public String code(User user) {
		if(user!=null){
			try {
				if(TmStringUtils.isEmpty(user.getAccount()))return "account_null";
				TmParams params = new TmParams();
				params.setAccount(user.getAccount());
				User user2 = userDao.getUser(params);
				if(user2==null){
					String random = TmStringUtils.getRandomString(6);
					TmEmailTemplate.sendEmailForEmailCode(user.getAccount(), random);
					contextProvider.setSessionAttribute(user.getAccount(), random);
					return "success";
				}else{
					return "exist";	
				}
			} catch (Exception e) {
				return "fail";
			}
		}else{
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/backcode",method=RequestMethod.POST)
	public String backcode(User user) {
		if(user!=null){
			try {
				if(TmStringUtils.isEmpty(user.getAccount()))return "account_null";
				TmParams params = new TmParams();
				params.setAccount(user.getAccount());
				User user2 = userDao.getUser(params);
				if(user2!=null){
					String random = TmStringUtils.getRandomString(6);
					TmEmailTemplate.sendEmailForBackPassword(user2.getUsername(),user2.getAccount(), random);
					contextProvider.setSessionAttribute(user2.getAccount()+"_back", random);
					return "success";
				}else{
					return "noexist";	
				}
			} catch (Exception e) {
				return "fail";
			}
		}else{
			return "fail";
		}
	}

	@ResponseBody
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(User user) {
		if(user!=null){
			try {
				if(TmStringUtils.isEmpty(user.getAccount()))return "account_null";
				if(TmStringUtils.isEmpty(user.getPassword()))return "pwd_null";
				if(TmStringUtils.isEmpty(user.getCode()))return "code_null";
				String bcode = (String)contextProvider.getSessionAttribute(user.getAccount()+"_back");
				if(TmStringUtils.isNotEmpty(bcode) && !bcode.equalsIgnoreCase(user.getCode()))return "error_code";
				TmParams params = new TmParams();
				params.setAccount(user.getAccount());
				User user2 = userDao.getUser(params);
				if(user2!=null){//如果根据邮箱查询存在，那边就更新密码
					user2.setPassword(TmStringUtils.md5Base64("keke_"+user.getPassword()));
					user2.setUpdateTime(new Date());
					userDao.updateUser(user2);
					contextProvider.logout();//登出
					return "success";
				}else{
					//否则不存，前往提示注册
					return "noexist";	
				}
			} catch (Exception e) {
				return "fail";
			}
		}else{
			return "fail";
		}
	}
}