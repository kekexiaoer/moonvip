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
public class User {

	public String username;
	private int age;

	public User() {

	}

	public User(String username, int age) {
		this.username = username;
		this.age = age;
	}

	public void say() {
		System.out.println(this.username + "今年" + this.age + "岁...");
	}

}
