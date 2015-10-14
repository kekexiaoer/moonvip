/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc3
 * UserDao.java
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午11:44:34 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc3;

import org.springframework.stereotype.Component;

/**
 * 
 * UserDao
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午11:44:34 
 * @version 1.0.0
 * 
 * @Component 加入任何注解，代表被spring容器进行管理(applicationContext)进行管理
 * 
 * 
 */
@Component
public class UserDao {
	
	
	public void saveUser(){
		System.out.println("找到你了你了吗，啊。我要保存了哦");
	}

}
