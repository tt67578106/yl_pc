package com.ylw.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Template(FTL)模板帮助类
 * @author Tolk
 * @version 1.0, 2015年4月17日
 */
public class TemplateUtil {
	/**
	 * 根据Map参数集合以及模板路径获取内容
	 * @param map 参数集合
	 * @param templatePath 模板路径
	 * @param templateName 模板名称
	 * @return 模板处理后的内容
	 */
	@SuppressWarnings("deprecation")
	public static String getStrByTemplate(Map<String,Object> map, String templatePath, String templateName ){
		String result = null;
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		try {
			File file = new File(templatePath);
			cfg.setDirectoryForTemplateLoading(file);
			Template t = cfg.getTemplate(templateName);
			
			StringWriter stringWriter = new StringWriter();
			try {
				t.process(map, stringWriter);
				result = stringWriter.toString();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
