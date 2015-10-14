/**
 * tzdesk系统平台
 * tzspring02
 * com.tz.ioc3
 * DParams.java
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午11:13:21 
 * 2015潭州教育公司-版权所有
 */
package com.tz.ioc3;

/**
 * 
 * DParams
 * 创建人:xuchengfei 
 * 时间：2015年10月13日-下午11:13:21 
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
