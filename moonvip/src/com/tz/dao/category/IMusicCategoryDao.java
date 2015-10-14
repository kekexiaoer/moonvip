/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.user
 * IUserDao.java
 * 创建人:xuchengfei 
 * 时间：2015年6月28日-下午10:51:29 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.category;

import java.util.List;

import com.tz.model.MusicCategory;
import com.tz.model.TmParams;


/**
 * 
 * IUserDao
 * 创建人:xuchengfei 
 * 时间：2015年6月28日-下午10:51:29 
 * @version 1.0.0
 * 
 */
public interface IMusicCategoryDao {

	/**
	 * 查询所有的音乐分类
	 * com.tz.dao.category 
	 * 方法名：findCategories
	 * 创建人：xuchengfei 
	 * 时间：2015年7月28日-下午3:01:08 
	 * @param params
	 * @return List<MusicCategory>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<MusicCategory> findCategories(TmParams params);
	
}
