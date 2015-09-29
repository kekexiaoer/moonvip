/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.content
 * ContentDaoImpl.java
 * 创建人:xuchengfei 
 * 时间：2015年6月8日-下午1:12:30 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.comment;

import java.util.List;
import java.util.Map;

import com.tz.model.Comment;
import com.tz.model.TmParams;

/**
 * 
 * ContentDaoImpl
 * 创建人:xuchengfei 
 * 时间：2015年6月8日-下午1:12:30 
 * @version 1.0.0
 * 
 */
public interface ICommentDao {

	/**
	 * 查询所有的内容评论
	 * com.tz.dao.content 
	 * 方法名：findContents
	 * 创建人：xuchengfei 
	 * 时间：2015年6月8日-下午2:09:36 
	 * @param params
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> findComments(TmParams params);
	
	/**
	 * 求总数 
	 * com.tz.dao.comment 
	 * 方法名：countComments
	 * 创建人：xuchengfei 
	 * 时间：2015年6月12日-上午10:50:00 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countComments(TmParams params); 
	
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
	public void saveComment(Comment comment);
	
	/**
	 * 更新评论
	 * com.tz.dao.comment 
	 * 方法名：updateComment
	 * 创建人：xuchengfei 
	 * 时间：2015年6月12日-上午10:55:42 
	 * @param params void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateComment(Comment comment);
}
