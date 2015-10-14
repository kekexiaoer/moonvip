/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.category
 * ICategoryDao.java
 * 创建人:xuchengfei 
 * 时间：2015年6月9日-下午6:32:24 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.channel;

import java.util.List;

import com.tz.model.Channel;
import com.tz.model.TmParams;

/**
 * 
 * ICategoryDao
 * 创建人:xuchengfei 
 * 时间：2015年6月9日-下午6:32:24 
 * @version 1.0.0
 * 
 */
public interface IChannelDao {
	/**
	 * 查询所有的内容
	 * com.tz.dao.content 
	 * 方法名：findContents
	 * 创建人：xuchengfei 
	 * 时间：2015年6月8日-下午2:09:36 
	 * @param params
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Channel> findChannels(TmParams params);
	public int countChannels(TmParams params);
	
	
	/**
	 * 更新栏目
	 * com.tz.dao.channel 
	 * 方法名：updateChannel
	 * 创建人：xuchengfei 
	 * 时间：2015年8月1日-下午3:24:46 
	 * @param channel void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateChannel(Channel channel);
}
