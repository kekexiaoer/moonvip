package com.tz.core;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 
 * 获取服务器的根目录
 * TmBasePathExposer<BR>
 * 创建人:潭州学院-keke <BR>
 * 时间：2015年1月18日-上午12:06:48 <BR>
 * @version 1.0.0
 *
 */
public class TmBasePathExposer implements ServletContextAware {

	private ServletContext servletContext;
	private String basePath;

	public void init() {
		servletContext.setAttribute("basePath",basePath==null?servletContext.getContextPath():basePath);
	}


	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
