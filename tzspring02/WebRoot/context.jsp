<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tz.ioc.KeKe"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	
	//1：web.xml进行注册
 	//WebApplicationContext context = (WebApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
	KeKe ke = (KeKe)context.getBean("keke");
	
	
	ke.responseAsk("讲课");


%>
