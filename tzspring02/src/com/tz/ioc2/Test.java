/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc2
 * Test.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:48:59 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.tz.ioc.MoGongMovie;

/**
 * 
 * Test
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:48:59 
 * @version 1.0.0
 * 
 */
public class Test {

	public static void main(String[] args) {
		//���ص�����Դ�ļ�
		Resource resource = new ClassPathResource("bean.xml");
		//Resource resource = new FileSystemResource("D:/tzprojects/tzspring02/src/bean.xml");
		//��ʼ������
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		//��ȡ
		MoGongMovie moGongMovie =  (MoGongMovie) beanFactory.getBean("moGongMovie");
		MoGongMovie moGongMovie2 =  (MoGongMovie) beanFactory.getBean("moGongMovie");
		moGongMovie.startMovie();
		
		System.out.println(moGongMovie2+"==="+moGongMovie);
	}
}
