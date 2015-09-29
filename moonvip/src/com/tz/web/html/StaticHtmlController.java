/**
 * Tm系统平台
 * moonvip
 * com.tz.web.html
 * StaticHtmlController.java
 * 创建人:xuchengfei 
 * 时间：2015年5月14日-下午6:05:21 
 * 2015Tm公司-版权所有
 */
package com.tz.web.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.core.freemaker.TzFreemakerInitManager;
import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;
import com.tz.web.BaseController;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * StaticHtmlController
 * 创建人:xuchengfei 
 * 时间：2015年5月14日-下午6:05:21 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/static")
public class StaticHtmlController extends BaseController{
	
	@Autowired
	private ApplicationContext context;
	@Autowired
	private IContentDao contentDao;
	
	@RequestMapping("/index")
	@ResponseBody
	public String index()  {
		FileOutputStream outputStream = null;
		try {
			TzFreemakerInitManager freemakerInitManager = (TzFreemakerInitManager) context.getBean("freemarkerConfig");
			/* 在整个应用的生命周期中，这个工作你应该只做一次。 */
			/* 创建和调整配置。 */
			String templatePath = contextProvider.getApplicationRealPath("WEB-INF/pages");
			String targetPath = contextProvider.getApplicationRealPath("/");
			Configuration cfg = freemakerInitManager.getConfiguration();
			File path = new File(templatePath);
			cfg.setDirectoryForTemplateLoading(path);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			//	/* 在整个应用的生命周期中，这个工作你可以执行多次 */
			//	/* 获取或创建模板 */
			Template temp = cfg.getTemplate("index.html");
			System.out.println(temp.toString());
			/* 创建数据模型 */
			Map<String,Object> root = new HashMap<String,Object>();
			root.put("basePath", contextProvider.getBasePath());
			root.put("request", contextProvider.getRequest());
			/* 将模板和数据模型合并 */
			outputStream = new FileOutputStream(new File(targetPath,"index.html"));
			Writer out = new OutputStreamWriter(outputStream,"UTF-8");
			temp.process(root, out);
			out.flush();
			return "success";
		} catch (IOException e) {
			return "error";
		} catch (TemplateException e) {
			return "error";
		} finally{
			try {
				if(outputStream!=null)outputStream.close();
			} catch (Exception e2) {
			}
		}
	}
	
	
	
	
	/**
	 * 
	 * 更新页面
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:58 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/html")
	public String update(HttpServletRequest request) {
		TzFreemakerInitManager freemakerInitManager = (TzFreemakerInitManager) context.getBean("freemarkerConfig");
		Configuration config = freemakerInitManager.getConfiguration();
		try {
			String realPath = request.getRealPath("/");
			String id = request.getParameter("id");
			if(TmStringUtils.isEmpty(id))return "error";
			TmParams params = new TmParams();
			params.setId(Integer.parseInt(id));
			Content content = contentDao.getContent(params);
			if(content==null)return "error";
			config.setDirectoryForTemplateLoading(new File(realPath+"/WEB-INF/pages/"));
			config.setObjectWrapper(new DefaultObjectWrapper());
			Template temp = config.getTemplate("template/"+content.getType()+".html");
			Map<String, Object> data = new HashMap<String,Object>();
			data.put("id", id);
			data.put("basePath", contextProvider.getBasePath());
			data.put("request", contextProvider.getRequest());
			
			String staticUrl = content.getStaticLink();
			File targetFile = null;
			if(TmStringUtils.isNotEmpty(staticUrl)){
				File rootPath = new File(realPath);
				targetFile = new File(rootPath,staticUrl);
				File pFile = new File(targetFile.getParent());
				if(!pFile.exists())pFile.mkdirs();
			}else{
				String ymd = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
				String rpath = "pages/"+getType(content.getType())+"/"+ymd;
				String path = request.getRealPath(rpath);
				File crootPath = new File(path);
				if(!crootPath.exists())crootPath.mkdirs();
				String cname = id+TmStringUtils.getRandomString(8)+".html"; 
				targetFile = new File(crootPath,cname);
				staticUrl = rpath+"/"+cname;
			}
			
			Writer out = new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8");
			temp.process(data, out);
			out.flush();
			content.setStaticLink(staticUrl);
			contentDao.updateContent(content);
			return staticUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} catch (TemplateException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	private String getType(Integer type){
		String result = "";
		switch (type) {
			case 1:result = "c";break;
			case 2:result = "m";break;
			case 3:result = "js";break;
			case 4:result = "db";break;
			case 5:result = "zx";break;
			case 6:result = "tl";break;
		}
		return result;
	}

}
