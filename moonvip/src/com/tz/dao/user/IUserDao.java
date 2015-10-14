/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.user
 * IUserDao.java
 * 创建人:xuchengfei 
 * 时间：2015年6月28日-下午10:51:29 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.user;

import java.util.List;

import com.tz.model.TmParams;
import com.tz.model.User;

/**
 * 
 * IUserDao
 * 创建人:xuchengfei 
 * 时间：2015年6月28日-下午10:51:29 
 * @version 1.0.0
 * 
 */
public interface IUserDao {
	
	/**
	 * 查询所有的用户信息
	 * com.tz.dao.user 
	 * 方法名：findUsers
	 * 创建人：xuchengfei 
	 * 时间：2015年8月1日-上午11:19:47 
	 * @param params
	 * @return List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> findUsers(TmParams params);
	public int countUsers(TmParams params);

	/**
	 * 	查询用户信息
	 * com.tz.dao.user 
	 * 方法名：getUser
	 * 创建人：xuchengfei 
	 * 时间：2015年6月28日-下午10:53:01 
	 * @param params
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	public User getUser(TmParams params);
	
	/**
	 * 更新内容
	 * com.tz.dao.content 
	 * 方法名：updateContent
	 * 创建人：xuchengfei 
	 * 时间：2015年6月9日-下午7:58:21 
	 * @param content void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateUser(User user);
	
	/**
	 * 保存内容
	 * com.tz.dao.content 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月11日-下午1:38:51 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	public void saveUser(User user);
}
