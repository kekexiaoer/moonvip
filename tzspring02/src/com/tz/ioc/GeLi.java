/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc
 * GeLi.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:14:06 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc;

/**
 * 
 * GeLi
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:14:06 
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
	 * ��������responseAsk
	 * �����ˣ�xuchengfei 
	 * �ֻ�����:15074816437
	 * ʱ�䣺2015��10��10��-����9:15:09 
	 * @param message 
	 * �������ͣ�void
	 * @exception 
	 * @since  1.0.0
	 */
	public void responseAsk(String message){
		System.out.println(this.name+"===="+message);
	}
}
