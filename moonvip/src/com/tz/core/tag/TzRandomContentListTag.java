package com.tz.core.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("contentRandomList")
public class TzRandomContentListTag implements TemplateDirectiveModel ,ServletContextAware {
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IContentDao contentDao = (IContentDao) applicationContext.getBean("IContentDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
		if(TmStringUtils.isEmpty(var))var="rcontent";
		TmParams tmParams = new TmParams();
		tmParams.setPageSize(pageSize==null?10:pageSize);//总条数
		List<Content> list = contentDao.findRandContents(tmParams);
		if (body != null) {
			int i = 0;
			env.setVariable(var+"_size", BeansWrapper.BEANS_WRAPPER.wrap(list.size()));
			for (Content content : list) {
				env.setVariable(var, BeansWrapper.BEANS_WRAPPER.wrap(content));
				env.setVariable(var+"_index", BeansWrapper.BEANS_WRAPPER.wrap(i));
				body.render(env.getOut());
				i++;
			}
		} else {
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}