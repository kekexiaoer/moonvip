package com.tz.core.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.dao.music.IMusicDao;
import com.tz.model.Music;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("musicList")
public class TzMusicListTag implements TemplateDirectiveModel ,ServletContextAware {
	private ServletContext servletContext;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IMusicDao musicDao = (IMusicDao) applicationContext.getBean("IMusicDao");
		String var = TmTemplateDirectiveModelUtil.getString("var", params);
		Integer isDelete = TmTemplateDirectiveModelUtil.getInteger("isDelete", params);
		Integer contentId = TmTemplateDirectiveModelUtil.getInteger("cid", params);
		Integer status = TmTemplateDirectiveModelUtil.getInteger("status", params);
		String orderSql = TmTemplateDirectiveModelUtil.getString("orderSql", params);
		if(TmStringUtils.isEmpty(var))var="music";
		TmParams tmParams = new TmParams();
		tmParams.setContentId(contentId);
		tmParams.setIsDelete(isDelete==null?0:isDelete);//未删除0 1删除
		tmParams.setStatus(status);//默认发布 0未发布1发布
		tmParams.setOrderSql(orderSql==null?" m.sort asc ":orderSql);//排序时间 默认根据创建时间
		List<Music> list = musicDao.findMusics(tmParams);
		if (body != null) {
			int i = 0;
			env.setVariable(var+"_size", BeansWrapper.BEANS_WRAPPER.wrap(list.size()));
			for (Music music : list) {
				env.setVariable(var, BeansWrapper.BEANS_WRAPPER.wrap(music));
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