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
import java.util.Map;

import com.tz.ioc.GeLi;
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
public class SingletonDemo {
	
	public static void main(String[] args) {
		//设计解决什么样子的问题：---对象
		//应用的场景--单列模式
		Singleton singleton = Singleton.getInstance();
		singleton.num++;
		System.out.println(singleton.num);
		Singleton singleton2 = Singleton.getInstance();
		singleton2.num++;
		System.out.println(singleton2.num);
		Singleton singleton3 = Singleton.getInstance();
		singleton3.num++;
		System.out.println(singleton3.num);
		
		System.out.println("===================================");
		
		NoSingleton noSingleton = new NoSingleton();
		noSingleton.num ++;
		System.out.println(noSingleton.num);
		
		NoSingleton noSingleton2 = new NoSingleton();
		noSingleton2.num ++;
		System.out.println(noSingleton2.num);
		
		
		NoSingleton noSingleton3 = new NoSingleton();
		noSingleton3.num ++;
		System.out.println(noSingleton3.num);
		
		
		
		
		
		
		
	}
}
