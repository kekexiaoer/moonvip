package com.tz.core.tag.method;

import java.util.List;

import org.springframework.stereotype.Component;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@Component("flex")
public class TmSubstringMethod implements TemplateMethodModel {
	// 这里的长度计算，以汉字为标准，两个字母作为一个字符
	@SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		if (args.size() > 2)
			throw new TemplateModelException("Wrong arguments!");
		String content = String.valueOf(args.get(0));
		Integer length = Integer.valueOf(String.valueOf(args.get(1)));
		String result = "";
		String other = "";
		String message = "";
		if(content.length() > length){
			result = content.substring(0, length);
			other = "<span class='tm-description-hidden' style='display:none;color:#333'>"+content.substring(length, content.length())+"</span>&nbsp;&nbsp;<a href='javascript:void(0);' title='展开/折叠' style='font-family: cursive;color:green;font-size:14px;font-weight:bold;' onclick='tm_note_show(this)'>︾</a>";
			message = result+other;
		}else{
			message = content;
		}
		return message;
	}
}
