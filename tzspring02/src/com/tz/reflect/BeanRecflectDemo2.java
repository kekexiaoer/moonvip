/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.reflect
 * ReflectDemo.java
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午10:13:27 
 * 2015潭州教育公司-版权所有
 */
package com.tz.reflect;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.tz.ioc.GeLi;
import com.tz.ioc.KeKe;
import com.tz.ioc.LiuDeHua;
import com.tz.ioc.MoGongMovie;


/**
 * 
 * ReflectDemo
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午10:13:27 
 * @version 1.0.0
 * 
 */
public class BeanRecflectDemo2 {
	
	/*
	 * <!-- 选演员 -->
	 	<bean id="liudehua" class="com.tz.ioc.LiuDeHua">
	 		<constructor-arg value="刘德华"></constructor-arg>
	 	</bean> <!-- GeLi geLi = new KeKe("keke"); -->
	 	
	 	<bean id="keke" class="com.tz.ioc.KeKe">
	 		<constructor-arg value="keke"></constructor-arg>
	 	</bean>
	 	<!-- 配置剧本  MoGongMovie moGongMovie = new MoGongMovie(geLi);-->
	 	<bean id="moGongMovie" class="com.tz.ioc.MoGongMovie">
	 		<!-- 必须生成set方法 ioc set注入 -->
	 		<property name="geLi" ref="liudehua"></property>
	 	</bean>
	 * 
	 * */
	
	/**
	 * 构造函数创建独享
	 * com.tz.reflect 
	 * 方法名：createObject
	 * 创建人：xuchengfei 
	 * 手机号码:15074816437
	 * 时间：2015年10月10日-下午11:09:16 
	 * @param packageName
	 * @param index
	 * @param args
	 * @return 
	 * 返回类型：T
	 * @exception 
	 * @since  1.0.0
	 */
	public static <T> T createObject(String packageName,int index,Object...args){
		try {
			ClassLoader liudehuaClassLoader = Thread.currentThread().getContextClassLoader();
			Class clz = liudehuaClassLoader.loadClass(packageName);
			//解析构造函数
			Constructor[] constructors = clz.getDeclaredConstructors();
			T liudehua = (T) constructors[index].newInstance(args);
			return liudehua;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过class创建对象
	 * com.tz.reflect 
	 * 方法名：objectNewInstance
	 * 创建人：xuchengfei 
	 * 手机号码:15074816437
	 * 时间：2015年10月10日-下午11:09:24 
	 * @param packageName
	 * @return 
	 * 返回类型：T
	 * @exception 
	 * @since  1.0.0
	 */
	public static <T> T objectNewInstance(String packageName){
		try {
			ClassLoader liudehuaClassLoader = Thread.currentThread().getContextClassLoader();
			Class clz = liudehuaClassLoader.loadClass(packageName);
			T liudehua = (T) clz.newInstance();
			return liudehua;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
//		try {
//			//创建刘德华对象
//			LiuDeHua liuDeHua = createObject("com.tz.ioc.LiuDeHua", 1, "刘德华");
//			KeKe keke = createObject("com.tz.ioc.KeKe", 1, "keke");
//			MoGongMovie gongMovie = objectNewInstance("com.tz.ioc.MoGongMovie");
//			gongMovie.setGeLi(keke);
//			
//			
//			gongMovie.startMovie();	
//			
//			
//			
//			LiuDeHua liuDeHua2 = new LiuDeHua();
//			KeKe ke = new KeKe();
//			MoGongMovie gongMovie2 = new MoGongMovie();
//			gongMovie2.setGeLi(ke);
//			gongMovie2.startMovie();
//			
//			
//					
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	
		
//		ListableBeanFactory
//		HierarchicalBeanFactory
//		ConfigurableBeanFactory
//		AutowireCapableBeanFactory
//		SingletonBeanRegistry
//		BeanDefinitionRegistry
		
		
//		DefaultSingletonBeanRegistry
//		AbstractBeanFactory
//		AbstractAutowireCapableBeanFactory
//		DefaultListableBeanFactory
//		XmlBeanFactory
//		ConfigurableListableBeanFactory
		
		/*
		 * 结构如下：
		 * /tzspring02/src
			com.tz
				com.tz.ioc
					/tzspring02/src/com/tz/ioc/Director.java
					/tzspring02/src/com/tz/ioc/GeLi.java
					/tzspring02/src/com/tz/ioc/KeKe.java
					/tzspring02/src/com/tz/ioc/LiuDeHua.java
					/tzspring02/src/com/tz/ioc/MoGongMovie.java
					/tzspring02/src/com/tz/ioc/Test.java
					/tzspring02/src/com/tz/ioc/bean.xml
			com.tz.ioc2
				/tzspring02/src/com/tz/ioc2/Test.java
				/tzspring02/src/com/tz/ioc2/bean.xml
			com.tz.reflect
				/tzspring02/src/com/tz/bean.xml
		 * 
		 * */
		
		try {
			ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = patternResolver.getResources("classpath:com/tz/**/*.xml");
			for (Resource resource : resources) {
				System.out.println(resource.getFile().getAbsolutePath()+"==="+resource.getDescription()+"=="+resource.getFilename());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 打印结果如下：
		 *  D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\bean.xml===file [D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\bean.xml]==bean.xml
			D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc\bean.xml===file [D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc\bean.xml]==bean.xml
			D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc2\bean.xml===file [D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc2\bean.xml]==bean.xml
		 * 
		 * */
	}
}