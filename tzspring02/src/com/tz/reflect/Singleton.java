/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.reflect
 * User.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����10:17:26 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.reflect;

/**
 * 
 * User ������:xuchengfei ʱ�䣺2015��10��10��-����10:17:26
 * 
 * @version 1.0.0
 * 
 */
public class Singleton {

	private Singleton(){
		
	}
	
	public int num;
	
	static Singleton singleton = null;
	public static Singleton getInstance(){
		if(singleton==null){
			singleton = new Singleton();
		}
		return singleton;
	}
	

}
