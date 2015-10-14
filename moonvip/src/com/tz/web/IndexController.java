package com.tz.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class IndexController extends BaseController{

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(getUser()==null){
			String window = request.getParameter("window");
			modelAndView.addObject("window", window);
			modelAndView.setViewName("login");
		}else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	@RequestMapping("/loginDialog")
	public ModelAndView loginDialog(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(getUser()==null){
			modelAndView.setViewName("loginDialog");
		}else{
			modelAndView.addObject("mark", "1");
			modelAndView.setViewName("loginDialog");
		}
		return modelAndView;
	}
	
	@RequestMapping("/forget")
	public ModelAndView forget() {
		ModelAndView modelAndView = new ModelAndView();
		if(getUser()==null){
			modelAndView.setViewName("forget");
		}else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	@RequestMapping("/reg")
	public ModelAndView reg() {
		ModelAndView modelAndView = new ModelAndView();
		if(getUser()==null){
			modelAndView.setViewName("reg");
		}else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	/**
	 * 登出
	 * com.tz.web 
	 * 方法名：loginout
	 * 创建人：xuchengfei 
	 * 时间：2015年6月30日-下午3:12:07 
	 * @return ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginout() {
		ModelAndView modelAndView = new ModelAndView();
		contextProvider.logout();
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	
	
	
	
	
	@RequestMapping("/music")
	public ModelAndView musicindex() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("channel/music");
		return modelAndView;
	}
	

	@RequestMapping("/java")
	public ModelAndView java() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("channel/java");
		return modelAndView;
	}
	
	@RequestMapping("/js")
	public ModelAndView js() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("channel/js");
		return modelAndView;
	}
	
	@RequestMapping("/db")
	public ModelAndView db() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("channel/db");
		return modelAndView;
	}
	
	@RequestMapping("/zixun")
	public ModelAndView zixun() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("channel/zixun");
		return modelAndView;
	}
	
	@RequestMapping("/tool")
	public ModelAndView tool() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("channel/tool");
		return modelAndView;
	}
	

	@RequestMapping("/music/{id}")
	public ModelAndView music(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", id);
		modelAndView.setViewName("content/music");
		return modelAndView;
	}
	

	@RequestMapping("/life")
	public ModelAndView life() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("life");
		return modelAndView;
	}

	@RequestMapping("/page/{id}")
	public ModelAndView content(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", id);
		modelAndView.setViewName("content/content");
		return modelAndView;
	}

	
	@RequestMapping("/code/{id}")
	public ModelAndView code(HttpServletRequest request,@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("code");
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	
}