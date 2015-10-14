/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc2
 * Test.java
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:48:59 
 * 2015潭州教育公司-版权所有
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
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:48:59 
 * @version 1.0.0
 * 
 */
public class Test {

	public static void main(String[] args) {
		//加载到了资源文件
		Resource resource = new ClassPathResource("bean.xml");
		//Resource resource = new FileSystemResource("D:/tzprojects/tzspring02/src/bean.xml");
		//初始化工程
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		//获取
		MoGongMovie moGongMovie =  (MoGongMovie) beanFactory.getBean("moGongMovie");
		MoGongMovie moGongMovie2 =  (MoGongMovie) beanFactory.getBean("moGongMovie");
		moGongMovie.startMovie();
		
		System.out.println(moGongMovie2+"==="+moGongMovie);
	}
}
