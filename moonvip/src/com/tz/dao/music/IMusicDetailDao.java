/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.music
 * IMusicDao.java
 * 创建人:xuchengfei 
 * 时间：2015年6月23日-下午2:23:20 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.music;

import com.tz.model.MusicDetail;
import com.tz.model.TmParams;

/**
 * 
 * IMusicDao
 * 创建人:xuchengfei 
 * 时间：2015年6月23日-下午2:23:20 
 * @version 1.0.0
 * 
 */
public interface IMusicDetailDao {

	
	
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
	public void saveDetail(MusicDetail musicDetail);
	
	/**
	 * 统计是否已经保存过了
	 * com.tz.dao.music 
	 * 方法名：countDetail
	 * 创建人：xuchengfei 
	 * 时间：2015年8月11日-下午8:15:20 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countDetail(TmParams params);
	
}
