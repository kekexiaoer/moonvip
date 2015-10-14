/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc3
 * ContextScannerDemo.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����11:48:46 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc3;

import java.io.File;
import java.lang.annotation.Annotation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * ContextScannerDemo
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����11:48:46 
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
			//����
			Class clz = Class.forName(name);
			Object obj = clz.newInstance();
			Annotation component =clz.getAnnotation(Component.class);
			if(component!=null){
				//ע�ᵽapplicationContext
			}
		}
	}
}
