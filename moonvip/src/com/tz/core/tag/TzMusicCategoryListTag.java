package com.tz.core.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.dao.category.IMusicCategoryDao;
import com.tz.model.MusicCategory;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("musicCategoryList")
public class TzMusicCategoryListTag implements TemplateDirectiveModel ,ServletContextAware {
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IMusicCategoryDao categoryDao =  (IMusicCategoryDao) applicationContext.getBean("IMusicCategoryDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		Integer isDelete = TmTemplateDirectiveModelUtil.getInteger("isDelete", params);
		Integer pageNo = TmTemplateDirectiveModelUtil.getInteger("pageNo", params);
		Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
		String orderSql = TmTemplateDirectiveModelUtil.getString("order", params);
		if(TmStringUtils.isEmpty(var))var="category";
		TmParams tmParams = new TmParams();
		tmParams.setIsDelete(isDelete==null?0:isDelete);
		tmParams.setPageNo(pageNo==null?0:pageNo);
		tmParams.setPageSize(pageSize==null?30:pageSize);
		tmParams.setOrderSql(orderSql==null?" sort asc ":orderSql);
		List<MusicCategory> categories = categoryDao.findCategories(tmParams);
		if (body != null) {
			int i = 0;
			env.setVariable(var+"_size", BeansWrapper.BEANS_WRAPPER.wrap(categories.size()));
			for (MusicCategory category : categories) {
				env.setVariable(var, BeansWrapper.BEANS_WRAPPER.wrap(category));
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