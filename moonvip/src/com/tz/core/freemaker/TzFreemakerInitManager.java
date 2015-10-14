/**
 * Tm系统平台
 * moonvip
 * com.tz.core.freemaker
 * TzFreemakerInitManager.java
 * 创建人:xuchengfei 
 * 时间：2015年5月14日-下午2:10:25 
 * 2015Tm公司-版权所有
 */
package com.tz.core.freemaker;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;



/**
 * 
 * TzFreemakerInitManager
 * 创建人:xuchengfei 
 * 时间：2015年5月14日-下午2:10:25 
 * @version 1.0.0
 * 
 */
public class TzFreemakerInitManager extends FreeMarkerConfigurer implements ApplicationContextAware{

	private ApplicationContext context;
	public void init() throws IOException, TemplateException{
		Configuration config = createConfiguration();
		Map<String, TemplateModel> beans = (Map<String, TemplateModel>) context
				.getBeansOfType(TemplateModel.class);
		for (Entry<String, TemplateModel> entry: beans.entrySet()) {
			config.setSharedVariable(entry.getKey(), entry.getValue());
		}
		super.setConfiguration(config);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
}
