package com.tz.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.comment.ICommentDao;
import com.tz.model.Comment;
import com.tz.util.ip.TmIpUtil;

/**
 * 
 * IndexController 
 * 创建人:xuchengfei 
 * 时间：2015年6月3日-下午7:47:24
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentController extends BaseController{

	@Autowired
	private ICommentDao commentDao;
	
	/**
	 * 保存评论
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月12日-下午2:02:43 
	 * @param comment
	 * @return Comment
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Comment save(Comment comment,HttpServletRequest request) {
		comment.setIsDelete(0);
		comment.setIsTop(0);
		comment.setStatus(1);
		comment.setUserId(getUserId());
		comment.setIp(TmIpUtil.getIpAddr(request));
		commentDao.saveComment(comment);
		return comment;
	}
	
	/**
	 * 更新评论
	 * com.tz.web 
	 * 方法名：update
	 * 创建人：xuchengfei 
	 * 时间：2015年6月12日-下午2:02:37 
	 * @param comment
	 * @return Comment
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Comment update(Comment comment) {
		comment.setUpdateTime(new Date());
		commentDao.updateComment(comment);
		return comment;
	}
}