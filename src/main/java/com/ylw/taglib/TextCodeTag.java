package com.ylw.taglib;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;

import com.ylw.util.DataAreaUtil;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;

public class TextCodeTag {

	public static String convertCity(Long cityid){
		return DataAreaUtil.getCityNameById(cityid);
	}
	/**
	 * 查看是否含有改标签
	 * @param lable allLable
	 * @return
	 */
	public static boolean containsLables(String lable,String allLable){
		if(allLable!=null){
			return allLable.contains(lable);
		}else{
			return false;
		}
	}
	
	/**
	 * 得到热词的文字
	 * @param lable allLable
	 * @return
	 */
	public static String getHotWordValue(Element element,String attname){
		return element.getAttributeValue(attname);
	}
	
	/**
	 * 薪资描述模板
	 * @return
	 */
	public static String getSalarydescModel(){
		return "<p>【综合工资】</p><p>...</p><p>【基本工资】</p><p>...</p><p>【加班费】</p><p>...</p><p>【发放时间】</p>...<p>【其他奖金】</p><p>...</p>";
	}
	/**
	 * 职位描述模板
	 * @return
	 */
	public static String getWorkdescModel(){
		return "<p>【工作内容】</p><p>...</p><p>【工作时间】</p><p>...</p><p>【工作环境】</p><p>...</p><p>【晋升空间】</p><p>...</p><p>【如何转正】</p><p>...</p><p>【其他】</p><p>...</p>";
	}
	/**
	 * 职位要求模板
	 * @return
	 */
	public static String getDemanddescModel(){
		return "<p>【年龄范围】</p><p>...</p><p>【性别要求】</p><p>...</p><p>【学历要求】</p><p>...</p><p>【身高要求】</p><p>...</p><p>【视力情况】</p><p>...</p><p>【入职须知】</p><p>...</p><p>【其他】</p><p>...</p>";
	}
	/**
	 * 吃住情况模板
	 * @return
	 */
	public static String getMealsdescModel(){
		return "<p>【伙食情况】</p><p>...</p><p>【宿舍情况】</p><p>...</p><p>【其他】</p><p>...</p>";
	}
	/**
	 * 福利情况模板
	 * @return
	 */
	public static String getWelfaredescModel(){
		return "<p>【缴险标准】</p><p>...</p><p>【有薪假期】</p><p>...</p><p>【年终奖金】</p><p>...</p><p>【薪资自助查询机】</p><p>...</p><p>【节日礼品】</p><p>...</p><p>【每月津贴】</p><p>...</p><p>【职务加给】</p><p>...</p><p>【绩效奖金】</p><p>...</p><p>【旅游福利】</p><p>...</p><p>【娱乐设施】</p><p>...</p><p>【温馨提示】</p><p>...</p><p>【其他】</p><p>...</p>";
	}
	/**
	 * 将性别限制转换成文字
	 * @param gender
	 * @return
	 */
	public static String convertGenderLimit(Integer gender){
		if(gender == null || gender == 0){
			return "不限性别";
		}else if(gender == 2){
			return "女";
		}else{//1
			return "男";
		}
	}
	/**
	 * 高亮显示文本
	 * @param text
	 * @return
	 */
	public static String highlightText(String text, String wd){
		wd = HTMLInputFilter.filterSimple(wd);
		if(StringUtils.isNotBlank(text)&&StringUtils.isNotBlank(wd)){
			return text.replaceAll(wd, "<span class='red'>" + wd + "</span>");
		}
		return text;
	}
	/**
	 * 剩余时间
	 * @param date
	 * @return
	 */
	public static Integer dayLeft(java.util.Date date){
		if(date == null){
			return -1;
		}
		return DateConvertUtils.daysBetween(DateConvertUtils.getNow(), date);
	}
	/**
	 * 计算多少天之前
	 * @param date
	 * @return
	 */
	public static String dayAfter(java.util.Date date){
		if(date == null){
			return "7天前";
		}
		int day = (-DateConvertUtils.daysBetween(date, DateConvertUtils.getNow()));
		if(day!=0){
			if(day<7){
				return day+"天前";
			}else{
				return "7天前";
			}
		}else{
			int house = (-DateConvertUtils.hoursBetween(date, DateConvertUtils.getNow()));
			if(house!=0){
				return house+"小时前";
			}else{
				return (-DateConvertUtils.minBetween(date, DateConvertUtils.getNow()))+"分钟前";
			}
		}
	}
	
	/**
	 * 截取
	 * @param text
	 * @param length
	 * @return
	 */
	public static String subString(String text, Integer length){
		if(StringUtils.isNotBlank(text) && text.length() >= length){
			return text.substring(0, length).concat("...");
		}
		return text;
	}
	/**
	 * 截取 html转纯text
	 * @param text
	 * @param length
	 * @return
	 */
	public static String subText(String text, Integer length){
		if(StringUtils.isNotBlank(text) && text.length() >= length){
			return HTMLInputFilter.Html2Text(text.substring(0, length).concat("...")).replaceAll("\"", "");
		}
		return text;
	}

}
