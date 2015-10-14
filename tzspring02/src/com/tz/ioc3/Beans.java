/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc3
 * Beans.java
 * 创建人:xuchengfei 
 * 时间：2015年10月14日-上午12:07:57 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * Beans
 * 创建人:xuchengfei 
 * 时间：2015年10月14日-上午12:07:57 
 * @version 1.0.0
 * 
 */
@Configuration
public class Beans {
	
//	基于xml文件配置
//	ApplicationContext=== classPathXmlApplicationContext
//			FileSystemApplicationContext
//			WebApplicationContext --都是 去中配置文件.xml
		
	
//	基于类配置		
			
	
	@Bean(name="student")
	public Student getStudent(){
		Student student = new Student();
		student.setAddress("湖南长沙");
		student.setUsername("kek111111111111111111111e");
		student.setAge(30);
		return student;
	}
}
