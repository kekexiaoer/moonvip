/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.reflect
 * ReflectDemo.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����10:13:27 
 * 2015̶�ݽ�����˾-��Ȩ����
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
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����10:13:27 
 * @version 1.0.0
 * 
 */
public class BeanRecflectDemo {
	/*
	 * <!-- ѡ��Ա -->
	 	<bean id="liudehua" class="com.tz.ioc.LiuDeHua">
	 		<constructor-arg value="���»�"></constructor-arg>
	 	</bean> <!-- GeLi geLi = new KeKe("keke"); -->
	 	
	 	<bean id="keke" class="com.tz.ioc.KeKe">
	 		<constructor-arg value="keke"></constructor-arg>
	 	</bean>
	 	<!-- ���þ籾  MoGongMovie moGongMovie = new MoGongMovie(geLi);-->
	 	<bean id="moGongMovie" class="com.tz.ioc.MoGongMovie">
	 		<!-- ��������set���� ioc setע�� -->
	 		<property name="geLi" ref="liudehua"></property>
	 	</bean>
	 * 
	 * */
	
	public static void main(String[] args) {
		
		try {
			//�������»�����
			ClassLoader liudehuaClassLoader = Thread.currentThread().getContextClassLoader();
			Class clz  = liudehuaClassLoader.loadClass("com.tz.ioc.LiuDeHua");
			
			//�������캯��
			Constructor[] constructors = clz.getDeclaredConstructors();
			com.tz.ioc.LiuDeHua liudehua = (com.tz.ioc.LiuDeHua) constructors[1].newInstance("���»�");
			
			//����keke����
			ClassLoader kekeClassLoader = Thread.currentThread().getContextClassLoader();
			Class kclz  = liudehuaClassLoader.loadClass("com.tz.ioc.KeKe");
			//�������캯��
			Constructor[] kconstructors = kclz.getDeclaredConstructors();
			com.tz.ioc.KeKe keke = (com.tz.ioc.KeKe) kconstructors[1].newInstance("keke");
			
			
			//�籾
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
