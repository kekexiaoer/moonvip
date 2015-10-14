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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tz.ioc.KeKe;

/**
 * 
 * ResourceDemo
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����8:56:44 
 * @version 1.0.0
 * 
 */
public class ApplicationContextDemo {

	
	public static void main(String[] args) throws IOException {
		//��һ��:ͨ����·��װ�������ļ�
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/bean.xml");
//		new ClassPathXmlApplicationContext(new String[]{"classpath:bean.xml","classpath:bean2.xml","d://bean.xml"});
//		new ClassPathXmlApplicationContext("bean.xml");
		//�ڶ��֣�ͨ������·��װ�������ļ�
		ApplicationContext context = new FileSystemXmlApplicationContext("file:/D:/tzprojects/tzspring02/src/bean.xml");
//		KeKe ke = (KeKe)context.getBean("keke");
//		ke.responseAsk("����");
		KeKe ke2 = context.getBean(KeKe.class);
		ke2.responseAsk("����");
		//������WebApplicationContext
		//������ AnnotationConfigApplicationContext
		ApplicationContext context2 = new AnnotationConfigApplicationContext("com.tz.ioc3");
		UserDao userDao = (UserDao) context2.getBean("userDao");
		userDao.saveUser();
		//�����ַ�ʽ	
		//ApplicationContext context3 = new AnnotationConfigApplicationContext(Beans.class);
		//Student student = context3.getBean(Student.class);
		//System.out.println(student.getUsername());
	}
}
