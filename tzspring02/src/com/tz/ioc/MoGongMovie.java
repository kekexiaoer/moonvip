/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc
 * LiuDeHua.java
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:09:38 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc;

/**
 * 
 * LiuDeHua
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:09:38 
 * @version 1.0.0
 * 
 */
public class MoGongMovie {

	private GeLi geLi;
	
	public MoGongMovie(){
		
	}
	
	//构造函数注入
	public MoGongMovie(GeLi geLi){
		this.geLi = geLi;
	}
	
	
	
	/**
	 * 叩城门
	 * com.tz.ioc 
	 * 方法名：responseAsk
	 * 创建人：xuchengfei 
	 * 手机号码:15074816437
	 * 时间：2015年10月10日-下午9:09:59 
	 * @param message 
	 * 返回类型：void
	 * @exception 
	 * @since  1.0.0
	 */
	public void startMovie(){
		//选择一个演员
		geLi.responseAsk("墨者隔离");
	}

	
	//setter注入
	public void setGeLi(GeLi geLi) {
		this.geLi = geLi;
	}
	
}
