/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc3
 * ResourceDemo.java
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午8:56:44 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc3;

import java.io.IOException;

import org.jboss.weld.context.ApplicationContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.WebApplicationContext;

import com.tz.ioc.LiuDeHua;

/**
 * 
 * ResourceDemo
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午8:56:44 
 * @version 1.0.0
 * 
 */
public class ResourceDemo {

	
	public static void main(String[] args) throws IOException {
//		Resource resource = new ClassPathResource("/bean.xml");
//		resource = new FileSystemResource("d:/tzspring02/src/bean.xml");
//		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
		
		/*spring容器加载资源文件的核心类 PathMatchingResourcePatternResolver*/
//		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
//		Resource resource = resourceLoader.getResource("classpath:bean.xml");//ClassPathResource
////		System.out.println(resource.getDescription()+"===="+resource.getFile().getAbsolutePath());
//		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
//		LiuDeHua object =  (LiuDeHua)xmlBeanFactory.getBean("liudehua");
//		LiuDeHua object2 =  (LiuDeHua)xmlBeanFactory.getBean("liudehua");
//		object.responseAsk("讲课");
//		
//		System.out.println(object);
//		System.out.println(object2);
//		
//		String[] names = xmlBeanFactory.getBeanDefinitionNames();
//		for (String string : names) {
//			System.out.println("==========="+string);
//			System.out.println(xmlBeanFactory.getBean(string));
//		}
		
		
		
		
		//BeanFactory:并不是立马初始化对象，而是在调用getBean的时候初始化对象
		//ApplicationContext就立马初始化对象，注册Map中，但是它比我们BeanFacotory国际化，资源文件的加载，bean生命周期
		
		
//		Resource resource = resourceLoader.getResource("classpath:com/tz/**/bean*.xml");//ClassPathResource
//		for (Resource resource : resources) {
//			System.out.println(resource.getDescription()+"===="+resource.getFile().getAbsolutePath());
//		}
		
//		org.springframework.context.ApplicationContext
		
//		ClassPathXmlApplicationContext
//		WebApplicationContext
//		FileSystemXmlApplicationContext
		
		
		
	}
}
