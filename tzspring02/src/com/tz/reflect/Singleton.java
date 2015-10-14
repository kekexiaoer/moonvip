/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.reflect
 * User.java
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午10:17:26 
 * 2015潭州教育公司-版权所有
 */
package com.tz.reflect;

/**
 * 
 * User 创建人:xuchengfei 时间：2015年10月10日-下午10:17:26
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
