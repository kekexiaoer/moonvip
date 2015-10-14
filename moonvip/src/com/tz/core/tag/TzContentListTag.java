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

@Component("contentList")
public class TzContentListTag implements TemplateDirectiveModel ,ServletContextAware {
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IContentDao contentDao = (IContentDao) applicationContext.getBean("IContentDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		String week = TmTemplateDirectiveModelUtil.getString("week", params);
		Integer id = TmTemplateDirectiveModelUtil.getInteger("id", params);
		Integer isDelete = TmTemplateDirectiveModelUtil.getInteger("isDelete", params);
		Integer channelId = TmTemplateDirectiveModelUtil.getInteger("channelId", params);
		String filterChannelId = TmTemplateDirectiveModelUtil.getString("filterChannelId", params);
		Integer categoryId = TmTemplateDirectiveModelUtil.getInteger("categoryId", params);
		Integer status = TmTemplateDirectiveModelUtil.getInteger("status", params);
		Integer push = TmTemplateDirectiveModelUtil.getInteger("push", params);
		Integer type = TmTemplateDirectiveModelUtil.getInteger("type", params);
		Integer isTop = TmTemplateDirectiveModelUtil.getInteger("isTop", params);
		Integer pageNo = TmTemplateDirectiveModelUtil.getInteger("pageNo", params);
		Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
		Integer filterId = TmTemplateDirectiveModelUtil.getInteger("filterId", params);
		String orderSql = TmTemplateDirectiveModelUtil.getString("orderSql", params);
		if(TmStringUtils.isEmpty(var))var="content";
		TmParams tmParams = new TmParams();
		tmParams.setId(id);//根据某个ID查询
		tmParams.setIsTop(isTop);//是否置顶
		tmParams.setFilterId(filterId);//过滤某个ID
		tmParams.setChannelId(channelId);//查询那个栏目下的数据
		tmParams.setCategoryId(categoryId);
		tmParams.setFilterChannelId(filterChannelId);
		tmParams.setPush(push);//是否推荐0否1是
		tmParams.setWeek(week);//根据本周 week 本月month
		tmParams.setType(type);//代表1是文章2图片
		tmParams.setIsDelete(isDelete==null?0:isDelete);//未删除0 1删除
		tmParams.setStatus(status==null?1:status);//默认发布 0未发布1发布
		tmParams.setPageNo(pageNo==null?0:pageNo);//分页
		tmParams.setPageSize(pageSize==null?10:pageSize);//总条数
		tmParams.setOrderSql(orderSql==null?" c.create_time desc ":orderSql);//排序时间 默认根据创建时间
		List<Content> list = contentDao.findContents(tmParams);
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