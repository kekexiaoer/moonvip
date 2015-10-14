/**
 * Tm系统平台
 * moonvip
 * com.tz.core.dao
 * BaseDaoImpl.java
 * 创建人:xuchengfei 
 * 时间：2015年5月13日-上午10:37:11 
 * 2015Tm公司-版权所有
 */
package com.tz.core.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * BaseDaoImpl
 * 创建人:xuchengfei 
 * 时间：2015年5月13日-上午10:37:11 
 * @version 1.0.0
 * 
 */
public class BaseDaoImpl {
 
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
}
