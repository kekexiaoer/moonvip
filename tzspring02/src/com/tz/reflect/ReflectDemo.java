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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * ReflectDemo
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����10:13:27 
 * @version 1.0.0
 * 
 */
public class ReflectDemo {
	
	
	/*
	 *1:����ΪʲôҪѧϰ����,���ģʽ
	 *
	 * 
	 * */
	
	
	public static void main(String[] args) {
		//�����������ʵ���Ǵ������������һ�ַ�ʽ����
		//��������ʽ�м���
		/*
		 * 1:new
		 * 2:����
		 * 3:clone
		 * 4:��(webservice,webscoket������󴫵�,java.io.Serializable)
		 * */
		
//		User user = new User("keke",30);
//		user.say();
		
		
		//���䴴������
		//������������Ժͷ�����ֵ�Ļ�����������
		try {
			//Class clz = Class.forName("com.tz.reflect.User");
			//������� User.java----User.class--ClassLoader
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Class clz = loader.loadClass("com.tz.reflect.User");
			//��������
			//User userc = (User) clz.newInstance();
			
			//����:���캯�������ú�Ŀ�ģ���Ҫ��һ����������  ��˽�����Ը�ֵ
			Constructor[] constructor =  clz.getDeclaredConstructors();
			User userc = (User) constructor[1].newInstance("keke",30);
			
			//��������
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
