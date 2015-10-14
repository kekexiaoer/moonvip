/**
 * Tm系统平台
 * moonvip
 * com.tz.dao.banner
 * IBannerDao.java
 * 创建人:xuchengfei 
 * 时间：2015年7月16日-下午2:55:02 
 * 2015Tm公司-版权所有
 */
package com.tz.dao.banner;

import java.util.List;

import com.tz.model.Banner;
import com.tz.model.TmParams;

/**
 * 
 * IBannerDao
 * 创建人:xuchengfei 
 * 时间：2015年7月16日-下午2:55:02 
 * @version 1.0.0
 * 
 */
public interface IBannerDao {
	/**
	 * 查询所有的Banner
	 * com.tz.dao.banner 
	 * 方法名：findBanners
	 * 创建人：xuchengfei 
	 * 时间：2015年6月8日-下午2:09:36 
	 * @param params
	 * @return List<Banner>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Banner> findBanners(TmParams params);
	
	/**
	 * 查询单条数据
	 * com.tz.dao.banner 
	 * 方法名：getBanner
	 * 创建人：xuchengfei 
	 * 时间：2015年6月8日-下午9:32:46 
	 * @param params
	 * @return Banner
	 * @exception 
	 * @since  1.0.0
	 */
	public Banner getBanner(TmParams params);
	
	/**
	 * 更新Banner
	 * com.tz.dao.banner 
	 * 方法名：updateBanner
	 * 创建人：xuchengfei 
	 * 时间：2015年6月9日-下午7:58:21 
	 * @param banner void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateBanner(Banner banner);
	
	/**
	 * 保存Banner
	 * com.tz.dao.banner 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月11日-下午1:38:51 
	 * @param banner
	 * @return Banner
	 * @exception 
	 * @since  1.0.0
	 */
	public void saveBanner(Banner banner);
}
