/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc3
 * UserDao.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����11:44:34 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc3;

import org.springframework.stereotype.Component;

/**
 * 
 * UserDao
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����11:44:34 
 * @version 1.0.0
 * 
 * @Component �����κ�ע�⣬����spring�������й���(applicationContext)���й���
 * 
 * 
 */
@Component
public class UserDao {
	
	
	public void saveUser(){
		System.out.println("�ҵ����������𣬰�����Ҫ������Ŷ");
	}

}
