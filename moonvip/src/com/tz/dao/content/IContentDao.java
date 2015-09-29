/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.content
 * ContentDaoImpl.java
 * 创建人:xuchengfei 
 * 时间：2015年6月8日-下午1:12:30 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tz.model.Content;
import com.tz.model.TmParams;

/**
 * 
 * ContentDaoImpl
 * 创建人:xuchengfei 
 * 时间：2015年6月8日-下午1:12:30 
 * @version 1.0.0
 * 
 */
public interface IContentDao {

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
	public List<Content> findContents(TmParams params);
	public List<Content> loadContents(TmParams params);
	public int countContents(TmParams params);
	
	/**
	 * 
	 * 随机文章
	 * com.tz.dao.content 
	 * 方法名：findRandContents
	 * 创建人：xuchengfei 
	 * 时间：2015年6月13日-下午4:59:09 
	 * @param params
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Content> findRandContents(TmParams params);
	
	/**
	 * 查询单条数据
	 * com.tz.dao.content 
	 * 方法名：getContent
	 * 创建人：xuchengfei 
	 * 时间：2015年6月8日-下午9:32:46 
	 * @param params
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	public Content getContent(TmParams params);
	
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
	public void updateContent(Content content);
	
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
	public void saveContent(Content content);
	
	/**
	 * 找到一篇文章的上一篇和下一篇
	 * com.tz.dao.content 
	 * 方法名：getNextPrev
	 * 创建人：xuchengfei 
	 * 时间：2015年6月13日-下午12:02:37 
	 * @param params
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	public Content getNextPrev(TmParams params);
	
	/**
	 * 点击数和更新数量和收藏数量
	 * com.tz.dao.content 
	 * 方法名：updateHLC
	 * 创建人：xuchengfei 
	 * 时间：2015年7月22日-下午11:22:58 
	 * @param map
	 * @return Integer
	 * @exception 
	 * @since  1.0.0
	 */
	public Integer updateHLC(Map<String, Object> map);
	
	
	/**
	 * 查询用户是否已经点击过收藏，喜欢和阅读数
	 * com.tz.dao.content 
	 * 方法名：getHLC
	 * 创建人：xuchengfei 
	 * 时间：2015年7月23日-上午12:55:25 
	 * @param map
	 * @return Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> getHLC(Map<String, Object> map);
	
	/**
	 * 查询内容的点击数
	 * com.tz.dao.content 
	 * 方法名：getHLCMessage
	 * 创建人：xuchengfei 
	 * 时间：2015年7月31日-下午8:51:00 
	 * @param cid
	 * @return HashMap<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public HashMap<String, Object> getHLCMessage(TmParams params);
	
}
