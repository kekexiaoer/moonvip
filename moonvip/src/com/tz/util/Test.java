package com.tz.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.core.freemaker.TzFreemakerInitManager;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class Test {
	public static void main(String[] args) throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		TzFreemakerInitManager freemakerInitManager = (TzFreemakerInitManager) context.getBean("freemarkerConfig");
//		TzFreemakerInitManager freemakerInitManager = new TzFreemakerInitManager();
//		freemakerInitManager.getConfiguration();
		/* 在整个应用的生命周期中，这个工作你应该只做一次。 */
		/* 创建和调整配置。 */
		Configuration cfg = freemakerInitManager.getConfiguration();
		File path = new File("D:/tzprojects/moonvip/WebRoot/WEB-INF/pages");
		cfg.setDirectoryForTemplateLoading(path);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
//		/* 在整个应用的生命周期中，这个工作你可以执行多次 */
//		/* 获取或创建模板 */
		Template temp = cfg.getTemplate("index.html");
//		System.out.println(temp.toString());
//		/* 创建数据模型 */
//		Map root = new HashMap();
//		root.put("user", "Big Joe");
//		Map latest = new HashMap();
//		latest.put("url", "products/greenmouse.html");
//		latest.put("name", "green mouse");
//		root.put("latestProduct", latest);
//		/* 将模板和数据模型合并 */
		FileOutputStream outputStream = new FileOutputStream(new File(path,"index2.html"));
		Writer out = new OutputStreamWriter(outputStream);
		temp.process(null, out);
		out.flush();
	}
}