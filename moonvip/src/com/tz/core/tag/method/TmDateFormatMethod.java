package com.tz.core.tag.method;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tz.util.TmStringUtils;
import com.tz.util.date.TmDateUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@Component("timeFormat")
public class TmDateFormatMethod implements TemplateMethodModel {
	// 这里的长度计算，以汉字为标准，两个字母作为一个字符
	@SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		if (args.size() > 2)
			throw new TemplateModelException("Wrong arguments!");

		String dateTime = String.valueOf(args.get(0));
		String format = String.valueOf(args.get(1));
		if (TmStringUtils.isEmpty(dateTime)) {
			return "";
		}
		Date date = TmDateUtil.parseDateTime(dateTime);
		String times = TmDateUtil.getCurrentDate(date, format);
		return times;
	}
}
