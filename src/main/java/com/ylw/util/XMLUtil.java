package com.ylw.util;

import java.io.IOException;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * xml读写工具
 * @author Nicolas
 *
 */
public class XMLUtil {

	/**
	 * 读取所有节点
	 * @param filePath
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static List<Content> readXML(String filePath) throws JDOMException, IOException{
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(filePath);
		return doc.getContent();
	}
	/**
	 * 读取所有节点，返回对应对象节点集合
	 * @param filePath
	 * @param elementName
	 * @return 
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static List<Element> readXMLByElementName(String filePath, String elementName) throws JDOMException, IOException{
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(filePath);
		return doc.getRootElement().getChildren(elementName);
	}
	
}
