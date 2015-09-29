package com.tz.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tz.dao.comment.ICommentDao;
import com.tz.model.TmParams;

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
@RequestMapping("/commentno")
public class CommentControllerNo {

	@Autowired
	private ICommentDao commentDao;
	
	
	
	/**
	 * 列表页面
	 * com.tz.web 
	 * 方法名：list
	 * 创建人：xuchengfei 
	 * 时间：2015年6月12日-下午2:02:50 
	 * @param params
	 * @return
	 * @throws JSONException String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public String list(TmParams params) throws JSONException {
		params.setIsDelete(0);
		params.setStatus(1);
		params.setOrderSql("c.status,c.create_time desc");
		List<Map<String, Object>> comments = commentDao.findComments(params);
		int count = commentDao.countComments(params);
		return JSONUtil.serialize(comments)+"#"+count;
	}
	
	
	
	/**
	 * 更换模板
	 * com.tz.web 
	 * 方法名：template
	 * 创建人：xuchengfei 
	 * 时间：2015年6月12日-下午2:02:58 
	 * @param contentId
	 * @return ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/template/{id}")
	public ModelAndView template(@PathVariable("id")Integer contentId,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", contentId);
		modelAndView.addObject("params", request.getParameter("params"));
		modelAndView.setViewName("commentTemplate");
		return modelAndView;
	}
	
}