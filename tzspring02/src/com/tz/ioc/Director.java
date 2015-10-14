/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc
 * Director.java
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:19:40 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc;

/**
 * 
 * Director
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:19:40 
 * @version 1.0.0
 * 
 */
public class Director {

	
	
	public void director(){
		//选角色
		GeLi geLi = new KeKe("keke");
		//给剧本分配角色
		MoGongMovie moGongMovie = new MoGongMovie(geLi);
//		moGongMovie.setGeLi(geLi);
		//开始对台本
		moGongMovie.startMovie();
	}
}
