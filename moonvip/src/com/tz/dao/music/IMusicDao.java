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

import java.util.List;

import com.tz.model.Music;
import com.tz.model.TmParams;

/**
 * 
 * IMusicDao
 * 创建人:xuchengfei 
 * 时间：2015年6月23日-下午2:23:20 
 * @version 1.0.0
 * 
 */
public interface IMusicDao {

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
	public List<Music> findMusics(TmParams params);
	public List<Music> findMusicsPage(TmParams params);
	
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
	public Music getMusic(TmParams params);
	public Music loadMusics(TmParams params);
	
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
	public void updateMusic(Music music);
	public void updateHits(Music music);
	public void updateLoves(Music music);
	
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
	public void saveMusic(Music music);
	
}
