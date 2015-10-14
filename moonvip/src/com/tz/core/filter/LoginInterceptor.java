package com.tz.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tz.core.TmConstant;
import com.tz.model.User;

/**
 * 登陆拦截器
 * LoginInterceptor<BR>
 * 创建人:潭州学院-keke <BR>
 * 时间：2015年2月7日-下午11:28:46 <BR>
 * 
 * @version 1.0.0
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User moonUser = (User) request.getSession().getAttribute(TmConstant.SESSION_USER_KEY);
		String requestType = request.getHeader("X-Requested-With");
		if(moonUser==null){
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				response.getWriter().print("logout");
			}else{
				response.sendRedirect(request.getContextPath()+"/"+"login");//首页居多
			}
			return false;//终止后面的拦截器的执行
		}else{
			//request.getSession().setAttribute("session_cid", moonUser.getCluster().getId());
			//request.getSession().setAttribute("session_did", moonUser.getDepartment().getId());
			return true;//让下一个拦截器去处理
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
