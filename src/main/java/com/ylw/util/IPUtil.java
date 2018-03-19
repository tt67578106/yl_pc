package com.ylw.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * IP地址相关
 * @author Nicolas
 *
 */
public class IPUtil {
	private static Logger logger = LoggerFactory.getLogger(IPUtil.class);
	
	private static RestTemplate restTemplate = new RestTemplate();
	/**
	 * 通过baidu接口获取地址返回json
	 * @param ip
	 * @return
	 */
	public static String getByBaiduIpAddr(String ip){
		String url = Constants.BAIDU_MAP_API_IP + "&ip="+ip;
		String result = restTemplate.getForObject(url, String.class);
		if(StringUtils.isEmpty(result)){
			return null;
		}
		Pattern p = Pattern.compile("failed");
		if(p.matcher(result).find()){
			logger.info("定位失败：\t" + result);
		}else if(result.indexOf("city_code")!=-1){
			return result;
		}else{
			logger.info("定位失败：\t" + result);
		}
		return null;
	}
	/**
	 * 通过taobao接口获取地址返回json
	 * @param ip
	 * @return
	 */
	public static String getByTaobaoIpAddr(String ip){
		String url = Constants.TAOBAO_MAP_API_IP + "?ip="+ip;
		String result = restTemplate.getForObject(url, String.class);
		if(StringUtils.isEmpty(result)){
			return null;
		}
		int start = result.indexOf("\"city_id\":\"");
		if(start != -1){
			return result;
		}else{
			logger.info("定位失败：\t" + result);
		}
		return null;
	}
    /**
     * 获得真实ip
     * @param request
     * @return
     */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip != null&&ip.contains(",")){
			ip=ip.split(",")[0].toString();
		}
		return ip;
	}
	
}