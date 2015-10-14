/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc3
 * ContextScannerDemo.java
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午11:48:46 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc3;

import java.io.File;
import java.lang.annotation.Annotation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * ContextScannerDemo
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午11:48:46 
 * @version 1.0.0
 * 
 */
public class ContextScannerDemo {

	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		
		File[] files = new File("D:/tzprojects/tzspring02/src/com/tz/ioc3").listFiles();
		for (File file : files) {
			String filename = StringUtils.cleanPath(file.getAbsolutePath());
			String name = filename.replaceAll("D:/tzprojects/tzspring02/src/", "").replaceAll("/", ".").replace(".java", "");
			System.out.println(name);
			//反射
			Class clz = Class.forName(name);
			Object obj = clz.newInstance();
			Annotation component =clz.getAnnotation(Component.class);
			if(component!=null){
				//注册到applicationContext
			}
		}
	}
}
