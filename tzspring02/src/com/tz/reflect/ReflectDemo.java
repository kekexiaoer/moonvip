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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * ReflectDemo
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午10:13:27 
 * @version 1.0.0
 * 
 */
public class ReflectDemo {
	
	
	/*
	 *1:我们为什么要学习反射,设计模式
	 *
	 * 
	 * */
	
	
	public static void main(String[] args) {
		//反射的作用其实就是创建对象的另外一种方式而已
		//创建对象方式有几种
		/*
		 * 1:new
		 * 2:反射
		 * 3:clone
		 * 4:流(webservice,webscoket对象对象传递,java.io.Serializable)
		 * */
		
//		User user = new User("keke",30);
//		user.say();
		
		
		//反射创建对象
		//如果给类中属性和方法赋值的话，创建对象
		try {
			//Class clz = Class.forName("com.tz.reflect.User");
			//类加载器 User.java----User.class--ClassLoader
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Class clz = loader.loadClass("com.tz.reflect.User");
			//创建对象
			//User userc = (User) clz.newInstance();
			
			//解析:构造函数的作用和目的：主要第一：创建对象  给私有属性赋值
			Constructor[] constructor =  clz.getDeclaredConstructors();
			User userc = (User) constructor[1].newInstance("keke",30);
			
			//解析方法
			Method method = clz.getDeclaredMethod("say");
			method.invoke(userc);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		
	}
}
