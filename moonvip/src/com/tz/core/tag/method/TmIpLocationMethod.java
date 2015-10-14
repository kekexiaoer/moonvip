//package com.tz.core.tag.method;
//
//import java.util.List;
//
//import org.apache.struts2.ServletActionContext;
//import org.springframework.stereotype.Component;
//
//import com.tm.util.TmStringUtils;
//import com.tm.util.ip.TmIpUtil;
//
//import freemarker.template.TemplateMethodModel;
//import freemarker.template.TemplateModelException;
//@Component("ipLocation")
//public class TmIpLocationMethod implements TemplateMethodModel {
//	// 这里的长度计算，以汉字为标准，两个字母作为一个字符
//	@SuppressWarnings("rawtypes")
//	public Object exec(List args) throws TemplateModelException {
//		String filePath = getRoot() + "\\WEB-INF\\plugin\\ip";
//		if (args.size() > 1)
//			throw new TemplateModelException("Wrong arguments!");
//
//		String ip = String.valueOf(args.get(0));
//		String ipLocation = "";
//		if (TmStringUtils.isEmpty(ip)) {
//			return ipLocation;
//		}
//		ipLocation = TmIpUtil.ipLocation(ip,filePath);
//		return ipLocation;
//	}
//
//	
//	/**
//	 * 获得服务器端操作的项目路径:X:/tomcat6/webapps/ExmayCMS/
//	 * 注意结尾带有/
//	 * @return
//	 */
//	public static String getRoot() {
//		String applicationRoot = ServletActionContext.getServletContext().getRealPath("/");
//		applicationRoot = applicationRoot.replaceAll("//", "/");
//		return applicationRoot;
//	}
//}
