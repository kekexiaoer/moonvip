/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc
 * LiuDeHua.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:09:38 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc;

/**
 * 
 * LiuDeHua
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:09:38 
 * @version 1.0.0
 * 
 */
public class MoGongMovie {

	private GeLi geLi;
	
	public MoGongMovie(){
		
	}
	
	//���캯��ע��
	public MoGongMovie(GeLi geLi){
		this.geLi = geLi;
	}
	
	
	
	/**
	 * ߵ����
	 * com.tz.ioc 
	 * ��������responseAsk
	 * �����ˣ�xuchengfei 
	 * �ֻ�����:15074816437
	 * ʱ�䣺2015��10��10��-����9:09:59 
	 * @param message 
	 * �������ͣ�void
	 * @exception 
	 * @since  1.0.0
	 */
	public void startMovie(){
		//ѡ��һ����Ա
		geLi.responseAsk("ī�߸���");
	}

	
	//setterע��
	public void setGeLi(GeLi geLi) {
		this.geLi = geLi;
	}
	
}
