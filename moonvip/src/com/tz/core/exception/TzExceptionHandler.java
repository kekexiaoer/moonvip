package com.tz.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class TzExceptionHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		// 根据不同错误转向不同页面
		if(ex instanceof TzBusinessException) {
			return new ModelAndView("redirect:error.html");
		}else if(ex instanceof TzParameterException) {
			return new ModelAndView("redirect:error.html");
		} else {
			return new ModelAndView("redirect:error.html");
		}
		
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("ex", ex);
//		// 根据不同错误转向不同页面
//		if(ex instanceof TzBusinessException) {
//			return new ModelAndView("error", model);
//		}else if(ex instanceof TzParameterException) {
//			return new ModelAndView("error-parameter", model);
//		} else {
//			return new ModelAndView("error", model);
//		}
	}
}
