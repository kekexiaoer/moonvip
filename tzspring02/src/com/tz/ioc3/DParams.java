/**
 * tzdeskϵͳƽ̨
 * tzspring02
 * com.tz.ioc3
 * DParams.java
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����11:13:21 
 * 2015̶�ݽ�����˾-��Ȩ����
 */
package com.tz.ioc3;

/**
 * 
 * DParams
 * ������:xuchengfei 
 * ʱ�䣺2015��10��13��-����11:13:21 
 * @version 1.0.0
 * 
 */
public class DParams {

	
//	public void sum(int a,int b){
//		System.out.println(a+b);
//	}
//
//	
//	public void sum(int a,int b,int c){
//		System.out.println(a+b+c);
//	}
	
	public static void sum(int ...params){
		int sum = 0;
		for (int i : params) {
			sum+=i;
		}
		System.out.println(sum);
	}

	
//	public static void sum2(String p,String c,int ...params){
//		int sum = 0;
//		for (int i : params) {
//			sum+=i;
//		}
//		System.out.println(sum);
//	}
	
	
	public static void main(String[] args) {
//		sum();
//		sum(1,1,5,5,8,9,9,7,8,9,47);
		
	}
	
	
}
