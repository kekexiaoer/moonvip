package com.tz.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tz.dao.banner.IBannerDao;
import com.tz.dao.content.IContentDao;
import com.tz.model.Banner;
import com.tz.model.Content;
import com.tz.model.TmParams;
import com.tz.util.TmStringUtils;

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
@RequestMapping("/admin/banner")
public class BannerController extends BaseController{

	@Autowired
	private IBannerDao bannerDao;
	@Autowired
	private IContentDao contentDao;
	
	@RequestMapping("/list")
	public ModelAndView edit(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		TmParams params = new TmParams();
		params.setOrderSql(" sort asc");
		params.setStatus(1);
		params.setPageNo(0);
		params.setPageSize(100);
		String cid = request.getParameter("cid");
		if(TmStringUtils.isNotEmpty(cid) && !cid.equals("0")){
			TmParams params2 = new TmParams();
			params2.setId(Integer.parseInt(cid));
			Content content = contentDao.getContent(params2);
			modelAndView.addObject("content", content);
		}
		List<Banner> banners = bannerDao.findBanners(params);
		modelAndView.addObject("banners", banners);
		modelAndView.setViewName("banner/list");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 更新页面
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:58 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Banner save(Banner banner) {
		banner.setUserId(getUserId());
		banner.setUsername(getUser().getUsername());
		banner.setHeaderPic(getUser().getHeaderPic());
		bannerDao.saveBanner(banner);
		return banner;
	}
	
	@ResponseBody
	@RequestMapping(value="/get/{id}",method=RequestMethod.POST)
	public Banner get(@PathVariable("id")Integer id){
		TmParams params = new TmParams();
		params.setId(id);
		Banner banner = bannerDao.getBanner(params);
		return banner;
	}
	
	
	/**
	 * 
	 * 更新页面
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:58 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Banner update(Banner banner) {
		banner.setUpdateTime(new Date());
		bannerDao.updateBanner(banner);
		return banner;
	}
}