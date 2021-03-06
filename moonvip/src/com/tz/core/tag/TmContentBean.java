package com.tz.core.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.model.TmParams;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("contentBean")
public class TmContentBean implements TemplateDirectiveModel,ServletContextAware {
	
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IContentDao contentDao = (IContentDao) applicationContext.getBean("IContentDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		Integer id = TmTemplateDirectiveModelUtil.getInteger("id",params);
		if (var == null) {
			var = "content";
		}
		TmParams tmParams = new TmParams();
		tmParams.setId(id);
		Content content = contentDao.getContent(tmParams);
		if (content != null) {
			env.setVariable(var, BeansWrapper.BEANS_WRAPPER.wrap(content));
		}

		if (body != null) {
			if (content != null) {
				body.render(env.getOut());
			}
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
