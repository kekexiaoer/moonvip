/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc3
 * ResourceDemo.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����8:56:44 
 * 2015̶�ݽ�����˾-��Ȩ����
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
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����8:56:44 
 * @version 1.0.0
 * 
 */
public class ResourceDemo {

	
	public static void main(String[] args) throws IOException {
//		Resource resource = new ClassPathResource("/bean.xml");
//		resource = new FileSystemResource("d:/tzspring02/src/bean.xml");
//		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
		
		/*spring����������Դ�ļ��ĺ����� PathMatchingResourcePatternResolver*/
//		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
//		Resource resource = resourceLoader.getResource("classpath:bean.xml");//ClassPathResource
////		System.out.println(resource.getDescription()+"===="+resource.getFile().getAbsolutePath());
//		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
//		LiuDeHua object =  (LiuDeHua)xmlBeanFactory.getBean("liudehua");
//		LiuDeHua object2 =  (LiuDeHua)xmlBeanFactory.getBean("liudehua");
//		object.responseAsk("����");
//		
//		System.out.println(object);
//		System.out.println(object2);
//		
//		String[] names = xmlBeanFactory.getBeanDefinitionNames();
//		for (String string : names) {
//			System.out.println("==========="+string);
//			System.out.println(xmlBeanFactory.getBean(string));
//		}
		
		
		
		
		//BeanFactory:�����������ʼ�����󣬶����ڵ���getBean��ʱ���ʼ������
		//ApplicationContext�������ʼ������ע��Map�У�������������BeanFacotory���ʻ�����Դ�ļ��ļ��أ�bean��������
		
		
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
