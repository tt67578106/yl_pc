package com.ylw.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ylw.util.Constants;

@RestController
@RequestMapping("/api/v1/template")
public class TemplateRestController {
	/**
	 * 读取模板内容
	 * @param templateName
	 * @param request
	 * @return
	 */
	@RequestMapping("readTemplate")
	public String readDemo(@RequestParam("templateName")String templateName, HttpServletRequest request){
		String webappPath = request.getSession().getServletContext().getRealPath("/");
		String fileName = webappPath + "WEB-INF" + File.separator + "template" + File.separator + templateName;
		StringBuffer sb = new StringBuffer();
		File file = new File(fileName);
		if(file.exists()){
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));  
				String data = br.readLine();//一次读入一行，直到读入null为文件结束  
				while( data!=null){  
				      sb.append(data);  
				      data = br.readLine(); //接着读下一行  
				}  
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}
		return Constants.RETURN_STATUS_ERROR;
	}
	/**
	 * 修改模板内容
	 * @param templateName
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("modifyTemplate")
	public String modifyDemo(@RequestParam("templateName")String templateName, @RequestParam("content")String content, HttpServletRequest request){
		String webappPath = request.getSession().getServletContext().getRealPath("/");
		String fileName = webappPath + "WEB-INF" + File.separator + "template" + File.separator + templateName;
		File file = new File(fileName);
		if(file.exists()){
			try {
				BufferedWriter writer  = new BufferedWriter(new FileWriter(fileName));
				writer.write(content);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				return Constants.RETURN_STATUS_ERROR;
			}
			return Constants.RETURN_STATUS_SUCCESS;
		}
		return Constants.RETURN_STATUS_ERROR;
	}
}
