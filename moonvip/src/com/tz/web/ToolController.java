package com.tz.web;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/admin/tool")
public class ToolController {
	
	@RequestMapping("/insertcode")
	public ModelAndView insertcode() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("insertCode");
		return modelAndView;
	}
	
	
}