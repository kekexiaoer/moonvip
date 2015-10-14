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
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����10:13:27 
 * @version 1.0.0
 * 
 */
public class BeanRecflectDemo2 {
	
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
	
	/**
	 * ���캯����������
	 * com.tz.reflect 
	 * ��������createObject
	 * �����ˣ�xuchengfei 
	 * �ֻ�����:15074816437
	 * ʱ�䣺2015��10��10��-����11:09:16 
	 * @param packageName
	 * @param index
	 * @param args
	 * @return 
	 * �������ͣ�T
	 * @exception 
	 * @since  1.0.0
	 */
	public static <T> T createObject(String packageName,int index,Object...args){
		try {
			ClassLoader liudehuaClassLoader = Thread.currentThread().getContextClassLoader();
			Class clz = liudehuaClassLoader.loadClass(packageName);
			//�������캯��
			Constructor[] constructors = clz.getDeclaredConstructors();
			T liudehua = (T) constructors[index].newInstance(args);
			return liudehua;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ͨ��class��������
	 * com.tz.reflect 
	 * ��������objectNewInstance
	 * �����ˣ�xuchengfei 
	 * �ֻ�����:15074816437
	 * ʱ�䣺2015��10��10��-����11:09:24 
	 * @param packageName
	 * @return 
	 * �������ͣ�T
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
//			//�������»�����
//			LiuDeHua liuDeHua = createObject("com.tz.ioc.LiuDeHua", 1, "���»�");
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
		 * �ṹ���£�
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
		 * ��ӡ������£�
		 *  D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\bean.xml===file [D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\bean.xml]==bean.xml
			D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc\bean.xml===file [D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc\bean.xml]==bean.xml
			D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc2\bean.xml===file [D:\tzprojects\tzspring02\WebRoot\WEB-INF\classes\com\tz\ioc2\bean.xml]==bean.xml
		 * 
		 * */
	}
}