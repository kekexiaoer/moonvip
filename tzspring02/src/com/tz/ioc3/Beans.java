/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc3
 * Beans.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��14��-����12:07:57 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc3;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * Beans
 * ������:xuchengfei 
 * ʱ�䣺2015��10��14��-����12:07:57 
 * @version 1.0.0
 * 
 */
@Configuration
public class Beans {
	
//	����xml�ļ�����
//	ApplicationContext=== classPathXmlApplicationContext
//			FileSystemApplicationContext
//			WebApplicationContext --���� ȥ�������ļ�.xml
		
	
//	����������		
			
	
	@Bean(name="student")
	public Student getStudent(){
		Student student = new Student();
		student.setAddress("���ϳ�ɳ");
		student.setUsername("kek111111111111111111111e");
		student.setAge(30);
		return student;
		
		
	}
}
