/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc
 * Director.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:19:40 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc;

/**
 * 
 * Director
 * ������:xuchengfei 
 * ʱ�䣺2015��10��10��-����9:19:40 
 * @version 1.0.0
 * 
 */
public class Director {

	
	
	public void director(){
		//ѡ��ɫ
		GeLi geLi = new KeKe("keke");
		//���籾�����ɫ
		MoGongMovie moGongMovie = new MoGongMovie(geLi);
//		moGongMovie.setGeLi(geLi);
		//��ʼ��̨��
		moGongMovie.startMovie();
	}
}
