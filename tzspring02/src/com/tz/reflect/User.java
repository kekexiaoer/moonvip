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
		System.out.println(this.username + "����" + this.age + "��...");
	}

}