package com.tz.core.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.dao.channel.IChannelDao;
import com.tz.model.Channel;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("channelList")
public class TzChannelListTag implements TemplateDirectiveModel ,ServletContextAware {
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IChannelDao channelDao = (IChannelDao) applicationContext.getBean("IChannelDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		Integer isDelete = TmTemplateDirectiveModelUtil.getInteger("isDelete", params);
		Integer status = TmTemplateDirectiveModelUtil.getInteger("status", params);
		String filterId = TmTemplateDirectiveModelUtil.getString("filterId", params);
		Integer pageNo = TmTemplateDirectiveModelUtil.getInteger("pageNo", params);
		Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
		String orderSql = TmTemplateDirectiveModelUtil.getString("orderSql", params);
		
		if(TmStringUtils.isEmpty(var))var="channel";
		TmParams tmParams = new TmParams();
		tmParams.setIsDelete(isDelete==null?0:isDelete);
		tmParams.setPageNo(pageNo==null?0:pageNo);
		tmParams.setFids(filterId);
		tmParams.setStatus(status==null?1:status);
		tmParams.setPageSize(pageSize==null?10:pageSize);
		tmParams.setOrderSql(orderSql==null?" sort asc":orderSql);
		List<Channel> list = channelDao.findChannels(tmParams);
		if (body != null) {
			int i = 0;
			env.setVariable(var+"_size", BeansWrapper.BEANS_WRAPPER.wrap(list.size()));
			for (Channel channel : list) {
				env.setVariable(var, BeansWrapper.BEANS_WRAPPER.wrap(channel));
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