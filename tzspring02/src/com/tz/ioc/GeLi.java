/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc
 * GeLi.java
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:14:06 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc;

/**
 * 
 * GeLi
 * 创建人:xuchengfei 
 * 时间：2015年10月10日-下午9:14:06 
 * @version 1.0.0
 * 
 */
public class GeLi {
	
	
	private String name;
	
	public GeLi(){
		
	}
	
	public GeLi(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * com.tz.ioc 
	 * 方法名：responseAsk
	 * 创建人：xuchengfei 
	 * 手机号码:15074816437
	 * 时间：2015年10月10日-下午9:15:09 
	 * @param message 
	 * 返回类型：void
	 * @exception 
	 * @since  1.0.0
	 */
	public void responseAsk(String message){
		System.out.println(this.name+"===="+message);
	}
}
