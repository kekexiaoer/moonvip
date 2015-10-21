package com.tz.core.tag.method;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tz.util.TmStringUtils;
import com.tz.util.date.TmDateTimeUtil;
import com.tz.util.date.TmDateUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@Component("dataMinus")
public class TmDateMinusMethod implements TemplateMethodModel {
	// 这里的长度计算，以汉字为标准，两个字母作为一个字符
	@SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		if (args.size() > 2)
			throw new TemplateModelException("Wrong arguments!");

		String startTime = String.valueOf(args.get(0));
		String endTime = String.valueOf(args.get(1));
		if (TmStringUtils.isEmpty(endTime)) {
			endTime = TmDateUtil.getCurrentDatetime(new Date());
		}
		if(TmStringUtils.isNotEmpty(startTime)){
			int number = TmDateTimeUtil.numYear(endTime, startTime);
			return number;
		}else{
			return 0;
		}
	}
}
