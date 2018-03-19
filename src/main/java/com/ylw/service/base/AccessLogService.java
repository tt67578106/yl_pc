package com.ylw.service.base;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 记录用户访问路
 *  @author jack
 */
@Component
@Transactional
public class AccessLogService {
//	private static Logger logger = LoggerFactory.getLogger(AccessLogService.class);

	/**
	 * 添加用户访问日志
	 * @param request
	 * @param userId
	 * @param dataId
	 */
//	public void addAccessLog(HttpServletRequest request,Integer source, Integer dataId) {
//		String session_id=request.getSession().getId();
//		String access_url= request.getRequestURL().toString();
//		if(StringUtils.isNotEmpty(request.getQueryString())){
//			access_url = access_url+"?"+request.getQueryString();
//		}
//		String referer_url = request.getHeader("Referer");
//		String access_ip = IPUtil.getIpAddr(request);
//		String _json = null;
//		_json = "{\"sessionId\":\"" + session_id + "\",\"source\":\""+source+"\",\"types\":\"1\","
//				+ "\"accessUrl\":\""+access_url+"\",\"refererUrl\":\"" + referer_url
//				+ "\",\"userId\":\"" + getCurrentUserId(request) + "\",\"dataId\":\"" + dataId + "\",\"accessIp\":\"" + access_ip + "\"}";
//		String status = "";
//		try {
//			RestTemplate restTemplate = new RestTemplate();
//			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();  
//	        map.add("log",DESUtil.encrypt(_json, Constants.REST_DES_KEY));  
//			status = restTemplate.postForObject(Constants.ACCESS_LOG_ROOT,map,String.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("err:记录用户访问路径异常"+e.getMessage());
//		}
//		if(status.equals("2")){
//			logger.info("记录用户访问路径失败");
//		}
//		
//	}
	
	/**
	 * 取出Cookie中的当前用户Id.
	 *//*
	private Integer getCurrentUserId(HttpServletRequest request) {
		try {
			String cookieValue = Constants.COOKIE_KEY_USER_VERIFY;
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			if (user != null && user.getId() != null) {
				return user.getId();
			} else if (user == null && StringUtils.isNotBlank(cookieValue)) {
				user = memcachedClient.get(cookieValue);
				if (user != null && user.getId() != null) {
					request.getSession().setAttribute(Constants.SESSION_KEY_USER, user);
					logger.info("使用Cookie强制登录 ");
					return user.getId();
				}
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}*/

}
