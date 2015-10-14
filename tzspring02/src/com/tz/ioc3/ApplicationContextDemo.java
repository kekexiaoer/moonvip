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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tz.ioc.KeKe;

/**
 * 
 * ResourceDemo
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午8:56:44 
 * @version 1.0.0
 * 
 */
public class ApplicationContextDemo {

	
	public static void main(String[] args) throws IOException {
		//第一种:通过类路径装载配置文件
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/bean.xml");
//		new ClassPathXmlApplicationContext(new String[]{"classpath:bean.xml","classpath:bean2.xml","d://bean.xml"});
//		new ClassPathXmlApplicationContext("bean.xml");
		//第二种：通过磁盘路径装载配置文件
		ApplicationContext context = new FileSystemXmlApplicationContext("file:/D:/tzprojects/tzspring02/src/bean.xml");
//		KeKe ke = (KeKe)context.getBean("keke");
//		ke.responseAsk("讲课");
		KeKe ke2 = context.getBean(KeKe.class);
		ke2.responseAsk("讲课");
		//第三种WebApplicationContext
		//第四种 AnnotationConfigApplicationContext
		ApplicationContext context2 = new AnnotationConfigApplicationContext("com.tz.ioc3");
		UserDao userDao = (UserDao) context2.getBean("userDao");
		userDao.saveUser();
		//第五种方式	
		//ApplicationContext context3 = new AnnotationConfigApplicationContext(Beans.class);
		//Student student = context3.getBean(Student.class);
		//System.out.println(student.getUsername());
	}
}
