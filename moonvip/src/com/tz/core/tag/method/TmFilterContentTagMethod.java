//package com.tz.core.tag.method;
//
//import java.util.List;
//
//import org.jsoup.Jsoup;
//import org.springframework.stereotype.Component;
//
//import com.tm.util.TmStringUtils;
//
//import freemarker.template.TemplateMethodModel;
//import freemarker.template.TemplateModelException;
//
//@Component("filterEmpty")
//public class TmFilterContentTagMethod implements TemplateMethodModel{
//	// 这里的长度计算，以汉字为标准，两个字母作为一个字符
//	@SuppressWarnings("rawtypes")
//	public Object exec(List args) throws TemplateModelException {
//		if (args.size() > 1)throw new TemplateModelException("Wrong arguments!");
//		String content = String.valueOf(args.get(0));
//		String result  = Jsoup.parse(content).text(); 
//		if(TmStringUtils.isNotEmpty(result) && result.length()>200){
//			return result.substring(0, 200)+"...";
//		}else {
//			return result;
//		}
//	}
//
//}
