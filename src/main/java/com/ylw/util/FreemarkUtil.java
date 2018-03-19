package com.ylw.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkUtil {

	/**
	 *
	 * @param templateName 模板文件名
	 * @param data 数据map
	 * @param targetPath 目标文件路径
	 * @param targetHtmlName 目标文件名
	 * @param context 上下文对象
	 * @param ctx 网址base地址 
	 * @return
	 */
	public boolean genFileByTemplate(String templateName,
			Map<String, Object> data, String targetPath, String targetHtmlName,
			ServletContext context, String ctx) {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("ctx", ctx);
		root.put("data", data);
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(context, "/WEB-INF/ftl");
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		Writer out = null;
		try {
			Template template = cfg.getTemplate(templateName, "UTF-8");
			String filePath = targetPath + File.separatorChar + targetHtmlName + ".html";
			File htmlFile = new File(filePath);
			htmlFile.deleteOnExit();
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "utf-8"));
			template.process(root, out);
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 根据Map参数集合以及模板路径获取内容
	 * @param map 参数集合
	 * @param templatePath 模板路径
	 * @param templateName 模板名称
	 * @return 模板处理后的内容
	 * @author Jack
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
