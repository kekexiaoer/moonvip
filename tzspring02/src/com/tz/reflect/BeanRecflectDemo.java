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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import com.tz.ioc.GeLi;
import com.tz.ioc.MoGongMovie;


/**
 * 
 * ReflectDemo
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午10:13:27 
 * @version 1.0.0
 * 
 */
public class BeanRecflectDemo {
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
	
	public static void main(String[] args) {
		
		try {
			//创建刘德华对象
			ClassLoader liudehuaClassLoader = Thread.currentThread().getContextClassLoader();
			Class clz  = liudehuaClassLoader.loadClass("com.tz.ioc.LiuDeHua");
			
			//解析构造函数
			Constructor[] constructors = clz.getDeclaredConstructors();
			com.tz.ioc.LiuDeHua liudehua = (com.tz.ioc.LiuDeHua) constructors[1].newInstance("刘德华");
			
			//创建keke对象
			ClassLoader kekeClassLoader = Thread.currentThread().getContextClassLoader();
			Class kclz  = liudehuaClassLoader.loadClass("com.tz.ioc.KeKe");
			//解析构造函数
			Constructor[] kconstructors = kclz.getDeclaredConstructors();
			com.tz.ioc.KeKe keke = (com.tz.ioc.KeKe) kconstructors[1].newInstance("keke");
			
			
			//剧本
			ClassLoader jubenClassLoader = Thread.currentThread().getContextClassLoader();
			Class jclz  = jubenClassLoader.loadClass("com.tz.ioc.MoGongMovie");
			MoGongMovie moGongMovie = (MoGongMovie) jclz.newInstance();
			
			
			Method method = jclz.getDeclaredMethod("setGeLi",GeLi.class);
			method.invoke(moGongMovie, keke);
			
			
			moGongMovie.startMovie();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
