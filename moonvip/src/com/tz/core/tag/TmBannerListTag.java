package com.tz.core.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.dao.banner.IBannerDao;
import com.tz.model.Banner;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("bannerList")
public class TmBannerListTag implements TemplateDirectiveModel ,ServletContextAware {
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IBannerDao bannerDao = (IBannerDao) applicationContext.getBean("IBannerDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		Integer id = TmTemplateDirectiveModelUtil.getInteger("id", params);
		Integer status = TmTemplateDirectiveModelUtil.getInteger("status", params);
		Integer type = TmTemplateDirectiveModelUtil.getInteger("type", params);
		Integer pageNo = TmTemplateDirectiveModelUtil.getInteger("pageNo", params);
		Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
		String orderSql = TmTemplateDirectiveModelUtil.getString("orderSql", params);
		if(TmStringUtils.isEmpty(var))var="banner";
		TmParams tmParams = new TmParams();
		tmParams.setId(id);//根据某个ID查询
		tmParams.setType(type==null?1:type);//代表1是文章2图片
		tmParams.setStatus(status==null?1:status);//默认发布 0未发布1发布
		tmParams.setPageNo(pageNo==null?0:pageNo);//分页
		tmParams.setPageSize(pageSize==null?6:pageSize);//总条数
		tmParams.setOrderSql(orderSql==null?" sort asc ":orderSql);//排序时间 默认根据创建时间
		List<Banner> list = bannerDao.findBanners(tmParams);
		if (body != null) {
			int i = 0;
			env.setVariable(var+"_size", BeansWrapper.BEANS_WRAPPER.wrap(list.size()));
			for (Banner banner : list) {
				env.setVariable(var, BeansWrapper.BEANS_WRAPPER.wrap(banner));
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